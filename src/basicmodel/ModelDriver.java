/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import ProtoTyp.Zahlformat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author EbmGastroService
 */
public class ModelDriver extends KeyModel {

    LinkedHashMap<String, Number> optiTemp = new LinkedHashMap<>();
    Object[] Zeile;
    LinkedHashMap<String, Object[]> orderList;
    private double[] PTR;//PallTemperatur Rechts
    private double[] PTL;//PallTemperatur Links
    private String[] PNR;
    private String[] PNL;
    private String PallLastName;
    private double OPT;
    private double Diff;
    double Tolaranz;
    private double T_lastone;
    private double P_lastone;
    private double BetriebZeit;
    private double[] OfenScaler;
    TempSkaler tempskaler;
    PName names;
    On o;
    private int Kammer;
    private int Reihen;
    Zahlformat zf = new Zahlformat();
    private String inhalt;
    tabelView tv = new tabelView(new String[]{"ModelDriver", "Test"});
    private String myName;

    public ModelDriver(double diff) {
        orderList = new LinkedHashMap<>();
        Collections.synchronizedMap(orderList);
        Collections.synchronizedMap(optiTemp);

        Kammer = 5;
        Reihen = 5;

        this.Diff = diff;
        Tolaranz = 0.25;//25% der Diff        
        tempskaler = new TempSkaler();
    }

    public ModelDriver() {
        orderList = new LinkedHashMap<>();
        Collections.synchronizedMap(orderList);
        Collections.synchronizedMap(optiTemp);

        Kammer = 5;
        Reihen = 5;

        this.Diff = 120.0;
        Tolaranz = 0.25;//25% der Diff        
        tempskaler = new TempSkaler();
        inhalt = "Maschiene Nr 26514X";
    }

    final void creatListen() {
        Listen(getPauseZeit(), getDiff());
    }

    public void creatListen(double zeit, double diff) {
        Listen(zeit, diff);
    }

    public void setDiff(double diff) {
        Diff = diff;
    }

    void setMe(int kammer, int reihen) {
        this.Kammer = kammer;
        this.setReihen(reihen);
    }

    public double getDiff() {
        return Diff;
    }

    /**
     *
     * @return PallTemeratur
     */
    public final double[] getPallList_on() {
        setOfenScaler(tempskaler.getMyScaler());
        for (int i = 0; i < getPTR().length; i++) {
            getPTR()[i] = getPallTemp(0.0, 0.0, 0.0, getDiff());//(double) vonListen(OfenScaler[i])[8];//getPallTemp(0.0, 0.0, 0.0, getDiff());
            getPTL()[i] = getPallTemp(0.0, 0.0, 0.0, getDiff());
        }
        setPallLastName("l/r");
        setOPT(0.0);
        setP_lastone((getPTR()[getPTR().length-1]+getPTL()[getPTL().length-1])/2);
        return getPTL();
    }

    /**
     *
     * @param kammer Sorteneinheiten
     * @param reihen AnzahlPallinoos
     */
    public final void setAutomat(int kammer, int reihen) {
        setKammer(kammer);
        setReihen(reihen);
        tempskaler.setAnzahlPallinoosProSeite(reihen);
        tempskaler.setAnzahlKammer(kammer);
        startZero();
        showList(300.0, "N");
    }

    final void startZero() {
        TempScaler_On();        
        setPTR(new double[getOfenScaler().length]);
        setPTL(new double[getOfenScaler().length]);
        names = new PName(getReihen() * 2);//Pallinoos Namenvergabe = P+Zahl P01,P02,..        
        setPNL(names.getLN());//P Name Links
        setPNR(names.getRN());//P Name Rechts				
        getPallList_on();
    }

    public final void TempScaler_On() {
        tempskaler.creatScaler();
        setOfenScaler(tempskaler.getMyScaler());
    }

    Object[] AbbauZeile(int z) {
        double T = getOfenScaler()[z];
        Zeile = new Object[9];
        Zeile[0] = T;
        Zeile[1] = getKey(T);
        Zeile[2] = getZeitBedarf(T, getDiff());
        Zeile[3] = getZeitProGrad(T, getDiff());
        Zeile[4] = getAbbauWert(getPauseZeit(), T, getDiff());

        Zeile[5] = getPNL()[z];
        Zeile[6] = getPTL()[z];

        Zeile[7] = getPNR()[z];
        Zeile[8] = getPTR()[z];
        return Zeile;
    }

    public void Order(int mal) {
        if (mal > 0) {
            setT_lastone(getOfenScaler()[getOfenScaler().length - 1]);
            o = new On(getPTR(), getPTL(), getPNR(), getPNL(), getT_lastone());
            setPallLastName(o.getBpn());
            TempScaler_On();
            for (int i = 1; i <= mal; i++) {
                getBackSet();
            }
        }
    }

    void getBackSet() {
        //System.out.println("Pall Numer ( " + (i + 1) + " )");
        o.Start();
        setPTR(o.getPtr());
        setPTL(o.getPtl());
        setPNR(o.getPnr());
        setPNL(o.getPnl());
        setP_lastone(o.getBpt());
        setT_lastone(o.getT_lastone());
        setPallLastName(o.getBpn());
        StartDrehen();
    }
    void StartDrehen() {
        orderList.clear();
        optiTemp.clear();
        //setPauseZeit(30.0d);//es wird im Groupenbinder angefordert
        setBetriebZeit(getBetriebZeit() + 30.0);
        TemperaturScalerAbbauen();
        for (int i = 0; i < getOfenScaler().length; i++) {
            AbbauZeile(i);
            orderList.putIfAbsent("" + (int) getOfenScaler()[i], Zeile);
        }
        optiTemp.put("+1 - " + getMyName() + ", Ausbacken nach Sekunden:", 30.0);        
        System.out.println(" ** Bestellung ** "+getMyName());
        showHead(3);
        showAbbau();
        AusbackTemp();
    }


    void listentabelle() {
        System.out.println();
        showHead(1);
        showHead(2);
        for (double i = 0d; i < 325d; i += 10d) {
            readlisten(i);
        }
        System.out.println();

    }

    void TemperaturScalerAbbauen() {
        for (int i = 0; i < getOfenScaler().length; i++) {
            getOfenScaler()[i] = getTempReihenAbbau(getPauseZeit(), getOfenScaler()[i], getDiff());
            getPTR()[i] = getPallTempAufbau(getPauseZeit(), getOfenScaler()[i], getPTR()[i], getDiff());//+50;
            getPTL()[i] = getPallTempAufbau(getPauseZeit(), getOfenScaler()[i], getPTL()[i], getDiff());//+50;
        }
        setP_lastone((getPTR()[getPTR().length-1]+getPTL()[getPTL().length-1])/2);       
       // setT_lastone(getOfenScaler()[getOfenScaler().length - 1]);         
    }

    final String ReihenScaler() {
        int leng = getOfenScaler().length - 1;
        double T_firstone = getOfenScaler()[0];
        double Plastone = (getPTL()[leng] + getPTR()[leng]) / 2.0;
        double Tlastone = getOfenScaler()[leng];
        if (Tlastone > Plastone && Tlastone > leng && T_firstone > 1.0) {
            double myTemp = Tlastone / getOfenScaler().length;
            for (int i = 0; i < getOfenScaler().length; i++) {
                double T = myTemp * (i + 1);
                getOfenScaler()[i] = T;
            }
            setInhalt("Ofen ist noch Heiss bei" );
            return "Ofen ist noch Heiss bei: " + zf.Zahl(Tlastone) + "°C letzen Pallinos sind bei: " + zf.Zahl(Plastone) + "°C";
        } else if (Tlastone <= Plastone || Tlastone <= leng || T_firstone < 1.0) {
            setInhalt("Resthietze ist voll ausgebaut");
            return "Resthietze ist voll ausgebaut: " + zf.Zahl(Tlastone) + "°C letzen Pallinos sind bei: " + zf.Zahl(Plastone) + "°C";
        } else {
            return "Ofen einschalten! ";
        }
    }

    
    public void showList(double pausezeit, String showme) {
        Collections.synchronizedMap(orderList);
        optiTemp.clear();        
        setBetriebZeit(getBetriebZeit() + pausezeit);
        double teiler = 1800.0;
        int mal;
        if (pausezeit < 1800.0) {
            teiler = pausezeit;
        }
        mal = (int) (pausezeit / teiler);

        for (int x = 1; x <= mal; x++) {
            orderList.clear();
            String ab = ReihenScaler();

            if (ab.contains("Heiss")) {
                for (int i = 0; i < getOfenScaler().length; i++) {
                    AbbauZeile(i);
                    orderList.putIfAbsent("" + (int) getOfenScaler()[i], Zeile);
                }
                setPauseZeit(teiler);
                if (showme.equals("J")) {
                    showHead(3);
                    showAbbau();
                }
                //AbtauKammer();                
                TemperaturScalerAbbauen();
                System.out.printf("" + x + " " + ab + " Pause seit: %5.1f Minuten oder %5.1f Std.%n", (x * teiler) / 60.0, (x * teiler) / 3600.0);
                optiTemp.put(ab, x * teiler);
                setT_lastone(getOfenScaler()[getOfenScaler().length - 1]);
            } else {
                System.out.println(x + " " + ab + " Pause Ende: " + (x * teiler) / 60.0 + " Minuten");
                optiTemp.put(ab, x * teiler);
            }
        }
    }

    void showAbbau() {
        orderList.entrySet().stream().forEach((Map.Entry<String, Object[]> m) -> {
            Object[] value = m.getValue();
            for (Object o1 : value) {
                if (o1.toString().contains("L") || o1.toString().contains("R") || o1.toString().contains("*")) {
                    System.out.printf("%16s", o1.toString());
                } else {
                    System.out.printf("%16.3f", (double) o1);
                }
            }
            System.out.println();
        });
    }

    String ergenzZeile(String key) {
        String str = "-----------------------------------------------";
        int l = key.length();
        int a = str.length() - l;//55 Schätzlänge für die unbekannte String von m.getKey()
        if (a > 0) {
            str = str.substring(0, a);//Achtung wenn a -x ausweist, dann entsteht fehler
        }
        if (a < 0) {
            str = "";
        }
        return str;
    }

    public void showMap() {  
        setInhalt("");
        optiTemp.entrySet().stream().forEach((Map.Entry<String, Number> m) -> {
            System.out.printf("%n          %s %s %8.2f", m.getKey(), ergenzZeile(m.getKey()), m.getValue());
            String str = zf.Zahl((double) m.getValue(), 2);
            getTv().addDate(m.getKey(), str);            
        });        
        System.out.println();        
    }
    

    public LinkedHashMap<String, Number> getOptiTemp() {
        return optiTemp;
    }

    String getRichtung() {
        String r = getPallLastName().substring(0, 1);
        if (r.contains("L")) {
            return "Links";
        } else {
            return "Rechts";
        }
    }

    double getNumber() {
        setPallLastName(o.Bopname(getPallLastName()));
        //System.out.println("P Last " + PallLastName);
        return Double.parseDouble(getPallLastName().substring(1, getPallLastName().length()));
    }

    void AusbackTemp() {
        System.out.println("Demonstration für Temperaturveränderung im Ofen");
        for (double temp = 300.0; temp < 325.0; temp += 0.5) {
            double OfenPallTemp = (30d / getZeitProGrad(temp, getDiff())) + getP_lastone();//ZeitProGrad          
            OptimaleTemperatur(temp, OfenPallTemp, getT_lastone(), getP_lastone(), 30d);//1,5Min           
        }
    }

    double OptimaleTemperatur(double OfenHeitzTemp, double OfenPallTemp, double Tlastone, double Plastone, double onZeit) {

        if (OfenPallTemp > getDiff() - getTolaranz() && OfenPallTemp < getDiff() + getTolaranz()) {
            optiTemp.put(" 1 - Tolaranz " + Tolaranz * 100 + "% beim T (" + getDiff() + "+/-) ", getTolaranz());
            System.out.printf("%nOptimale Ofentemperatur: %5.0f°C, nach:%4.0f Sec. Abbauen P-letzte Temp.:%6.2f°C, Zeit pro 1Grad: %6.2f  Ausgebacken bei:%6.2f°C%n",
                    (float) OfenHeitzTemp, onZeit, Plastone, (double) vonListen((double) OfenHeitzTemp)[3], OfenPallTemp);
            double bz = getBetriebZeit() + 30d;
            optiTemp.put(" 2 - Betriebzeit " + new Zahlformat().Zahl((bz / 60d), 1) + " Minuten oder Std. ", bz / 3600d);
            optiTemp.put(" 3 - Pallinoos ist von " + getRichtung() + " Numer: ", getNumber());
            optiTemp.put(" 4 - Pallinoos Temperatur vordem Ausbacken:", Plastone);
            optiTemp.put(" 5 - Pallinoos Temperatur nachdem Backen  :", OfenPallTemp);
            setOPT(OfenPallTemp);
            tempskaler.getEinschaltZeit((double) OfenHeitzTemp, Tlastone).entrySet().stream().forEach((m) -> {
                optiTemp.put(m.getKey(), m.getValue());
            });
            showMap();
        } else {
            System.out.print("..");
        }
        return getOPT();
    }

    @Override
    public void showMe() {
        System.out.println("Demonstration für Temperaturveränderung");
        System.out.println("Zeitbedarf bei Zielerhitzung bis:" + (int) getDiff() + "°C, die gefrorene Pall sind "
                + "\nim Kammer oben bei -° und unten im Ofen bei 180°C, wäre den Letzen nach 5 Minuten oder nach 15 Minuten Hitze.");
        System.out.println("Temp.    Key      Zeitbdarf   ZeitProGrad   Hitze Abbauen  Pall -18°C     nach 5Min    nach 15 Min    nach 1Std.");

    }

    HashMap<String, Object[]> getOrderList() {
        return orderList;
    }

    /**
     * @return the Tolaranz
     */
    public double getTolaranz() {
        return Tolaranz * getDiff();
    }

    /**
     * @return the OfenScaler
     */
    public double[] getOfenScaler() {
        return OfenScaler;
    }

    /**
     * @param Tolaranz the Tolaranz to set
     */
    public void setTolaranz(double Tolaranz) {
        this.Tolaranz = Tolaranz;
    }

    /**
     * @return the Kammer
     */
    public int getKammer() {
        return Kammer;
    }

    /**
     * @param Kammer the Kammer to set
     */
    public void setKammer(int Kammer) {
        this.Kammer = Kammer;
    }

    /**
     * @return the Reihen
     */
    public int getReihen() {
        return Reihen;
    }

    /**
     * @param Reihen the Reihen to set
     */
    public void setReihen(int Reihen) {
        this.Reihen = Reihen;
    }

    /**
     * @return the PTR
     */
    public double[] getPTR() {
        return PTR;
    }

    /**
     * @param PTR the PTR to set
     */
    public void setPTR(double[] PTR) {
        this.PTR = PTR;
    }

    /**
     * @return the PTL
     */
    public double[] getPTL() {
        return PTL;
    }

    /**
     * @param PTL the PTL to set
     */
    public void setPTL(double[] PTL) {
        this.PTL = PTL;
    }

    /**
     * @return the PNR
     */
    public String[] getPNR() {
        return PNR;
    }

    /**
     * @param PNR the PNR to set
     */
    public void setPNR(String[] PNR) {
        this.PNR = PNR;
    }

    /**
     * @return the PNL
     */
    public String[] getPNL() {
        return PNL;
    }

    /**
     * @param PNL the PNL to set
     */
    public void setPNL(String[] PNL) {
        this.PNL = PNL;
    }

    /**
     * @return the PallLastName
     */
    public String getPallLastName() {
        return PallLastName;
    }

    /**
     * @param PallLastName the PallLastName to set
     */
    public void setPallLastName(String PallLastName) {
        this.PallLastName = PallLastName;
    }

    /**
     * @return the OPT
     */
    public double getOPT() {
        return OPT;
    }

    /**
     * @param OPT the OPT to set
     */
    public void setOPT(double OPT) {
        this.OPT = OPT;
    }

    /**
     * @return the T_lastone
     */
    public double getT_lastone() {
        return T_lastone;
    }

    /**
     * @param T_lastone the T_lastone to set
     */
    public void setT_lastone(double T_lastone) {
        this.T_lastone = T_lastone;
    }

    /**
     * @return the P_lastone
     */
    public double getP_lastone() {
        return P_lastone;
    }

    /**
     * @return the BetriebZeit
     */
    public double getBetriebZeit() {
        return BetriebZeit;
    }

    /**
     * @param OfenScaler the OfenScaler to set
     */
    public void setOfenScaler(double[] OfenScaler) {
        this.OfenScaler = OfenScaler;
    }

    /**
     * @return the tv
     */
    public tabelView getTv() {
        return tv;
    }

    /**
     * @param P_lastone the P_lastone to set
     */
    public void setP_lastone(double P_lastone) {
        this.P_lastone = P_lastone;
    }

    /**
     * @param BetriebZeit the BetriebZeit to set
     */
    public void setBetriebZeit(double BetriebZeit) {
        this.BetriebZeit = BetriebZeit;
    }

    /**
     * @return the myName
     */
    public String getMyName() {
        return myName;
    }

    /**
     * @param myName the myName to set
     */
    public void setMyName(String myName) {
        this.myName = myName;
    }

    /**
     * @return the inhalt
     */
    public String getInhalt() {
        return inhalt;
    }

    /**
     * @param inhalt the inhalt to set
     */
    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

}

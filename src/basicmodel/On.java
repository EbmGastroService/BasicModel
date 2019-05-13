// created on 20.11.2014 at 17:51
package basicmodel;

/**
 * Hier werden im Auftauenkammer Rechte und Linke Radius mit Temp-Abbau, Resthitze, Bewegung und
 * Ausbacken an Direction übergeben.
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
class On{
    int x, i;
    private String[] Pnl;//Linke und Rechte Pallinoos Namen L10,L08,L06,.. und R09,R07,R05,..
    private String[] Pnr;//Linke und Rechte Pallinoos Namen L10,L08,L06,.. und R09,R07,R05,..
    private double[] Ptl;//Temp Links
    private double[] Ptr;//Temp Rechts   
    private double Bpt;//Pallinos Temp zum Backofen! P_lastone 
    String Bpn;//Name für Pallinooos im Backofen PallLastName
    double Pt;//PalTemp von Links oder Rechts
    PName P;    
    private final double T_lastone;
    private double LastReiheTemp;
    
    public On(double[]PTR,double[]PTL,String[]PNR,String[]PNL,double T_lastone){   
       
        this.Ptl = PTL;
        this.Ptr = PTR;
        this.Pnl = PNL;
        this.Pnr = PNR;
        this.x = PTR.length;   // Number of series        
        /** creat names of the Balls and start with the value of the top.*/
        this.P = new PName(x * 2, PNL[0], PNR[0]); 
        this.T_lastone = T_lastone;
        Bpn="L01";
        Pt = 0;         //one ball temperatur to swap it               
        Bpt = 0;
        i = 0;                
        
    }


    
    final void Start() {
        String Aus = LetOut();// Serviere 1 Pall.
        Display(Aus, i++);
        //Zeige();//*******Zeigt Daten an        
    }


    String getBpn() {
        return Bpn;
    }

    //Durchschnittsteperatur den letzten Pallinoos
    double getPt() {
        return Pt;
    }
    /**
     * 
     * @return Einlassen von L od. R.
     */

    String LetIn() {
        P.ON();//Namen neue vergeben bei PName(ln.length*2,ln[0],lr[0])	
        Pnl = P.getLN();//Linke Namen
        Pnr = P.getRN();//Rechte Namen
        String ein = P.getEin();//Einlasser war vom Rechts oder Links?
        return ein;
    }

    void Display(String Aus, int i) {        
        System.out.printf("%nAusgabe: ("+(i + 1)+")Stk. %s %5.2f ", Aus,getBpt());
        
    }
    /**
     * Pall -18°C kommt Rein, und eines >100°C kommt Raus, Next P Richtung? (L zu R).
     * Achtung die "*" nicht wegnehmen!
     * @return 
     */
    
    String LetOut() {
        double d1, d2;
        Pt = 0;
        String AusR = "von R " + "*(" + Pnr[Ptr.length - 1] + ")* " + "P.Temp.";
        String AusL = "von L " + "*(" + Pnl[Ptl.length - 1] + ")* " + "P.Temp.";

        d1 = Ptr[Ptr.length - 1];
        d2 = Ptl[Ptl.length - 1];
        LastReiheTemp = d1 + d2;
        //System.out.printf("%nLetzte Reihen Temperatur: %8.2f%n" , getLastReiheTemp());

        String ein = LetIn();//letzte Einlasser Richtung war von L od R.			

        //Annahme bei ON dauert die Hitze 30 Sekunden, 
        //dann sollte bei je ON Aufheitzung die Reihen neue berechnet werden.			
        //Drehen();	//für 30 Sekunden im Backofen aufheitzen

        String Aus = PTBewegen(ein, AusR, AusL, d1, d2);		//Pall. Temp. anpassen.
        Backing(Aus);        
        return Aus;
    }
    
    /**
     * 
     * Wenn es von R dann nächst von L
     * übergebe lastone an den Ofen
     * Berechne der Temp neue an die bewegte Seite
     * 
     * @param ein Einlasser Richtung
     * @param AusR war von Rechts?
     * @param AusL war von Links?
     * @param d1 lastone Temperatur Rechts
     * @param d2 lastone Temperatur Links
     * @return wenn es von ausR war, dann nächste ist ausL
     */

    String PTBewegen(String ein, String AusR, String AusL, double d1, double d2) {
        String Aus = "";
        if ("L".equals(ein)) {
            Aus = AusR;
            Pt = d1;
            Bpt = d1;
            Ptr = swap(Ptr);
        }
        if ("R".equals(ein)) {
            Aus = AusL;
            Pt = d2;
            Bpt = d2;
            Ptl = swap(Ptl);
        }        
        return Aus;
    }

    //Bt Balltemp aus eine Richtung 
    //zum Backofen soll um Backofenhitze ausgebacken von 25° auf 170°c 
    void Backing(String Aus) {
        Bpn = Bopname(Aus);              
    }

    String Bopname(String str) {
        if (str.contains("*(")) {
            str = str.substring(str.indexOf("*(")+2, str.indexOf(")*"));
        }
        return str;
    }

    //Pxx Oben geht zum Reihe 02, Auf Reihe 01 ist P+1 vom Lage Pt=-18°C
    double[] swap(double[] p) {
        double[] n = new double[x];
        int j = 0;			// Erste Position
        n[0] = -18.0;//neue Daten zum Ersten Position, Temeratur vom Lage
        for (int k = 1; k < n.length; k++) { // Zweite Pos anstelle vom 1 Position
            double w = p[j]; //Daten vom Alten
            n[k] = w;//neue Daten vom Alten übernehmen
            j++;
        }
        return n;
    }

    void Zeige() {
        String str;
        int z = x / 2;
        System.out.printf("%n PLN     Lt            PRN     Rt              %n");
        for (int k = 0; k < x; k++) {
            if (k < z + 1) {
                str = " O" + k;
            } else {
                str = " U" + k;
            }
            if (k == (x - 1)) {
                str = "BO";
            }
            System.out.printf("%5s ",str);
            System.out.printf("%7s", getPnl()[k]);
            System.out.printf("%15.2f", getPtl()[k]);
            System.out.printf("%7s", getPnr()[k]);
            System.out.printf("%15.2f%n", getPtr()[k]);
            
        }
    }

    /**
     * @return the Pnl
     */
    public String[] getPnl() {
        return Pnl;
    }

    /**
     * @return the Pnr
     */
    public String[] getPnr() {
        return Pnr;
    }
   
    /**
     * @return the Ptl
     */
    public double[] getPtl() {
        return Ptl;
    }

    /**
     * @return the Ptr
     */
    public double[] getPtr() {
        return Ptr;
    }

    /**
     * @return the LastReiheTemp
     */
    public double getLastReiheTemp() {
        return LastReiheTemp;
    }

    /**
     * @return the Bpt
     */
    public double getBpt() {
        return Bpt;
    }

    /**
     * @return the T_lastone
     */
    public double getT_lastone() {
        return T_lastone;
    }

}

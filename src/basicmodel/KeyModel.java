/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author EbmGastroService
 */
abstract class KeyModel implements Modellinteface {
    LinkedHashMap<String, List> myListen = new LinkedHashMap<>();
    private double PauseZeit;
    

    /**
     *
     * @param T
     * @return
     */
    @Override
    public final double getKey(double Temp) {
        return (36.0 / (3900.0 - (Temp * 12.0)));
    }

    /**
     *
     * @param Temp
     * @param Diff
     * @return
     */
    @Override
    public final double getZeitBedarf(double Temp, double Diff) {
        return (Diff / getKey(Temp));
    }

    /**
     *
     * @param Temp
     * @param Diff
     * @return
     */
    @Override
    public final double getZeitProGrad(double Temp, double Diff) {
        return (getZeitBedarf(Temp, Diff) / Temp);
    }
  
    /**
     *
     * @param offZeit
     * @param Temp
     * @param Diff
     * @return
     */
    @Override
    public final double getAbbauWert(double offZeit, double Temp, double Diff) {
        return offZeit/getZeitProGrad(Temp, Diff);
    }    

    /**
     *
     * @param offZeit
     * @param ReiheTemp
     * @param Diff
     * @return
     */
    @Override
    public final double getTempReihenAbbau(double offZeit, double ReiheTemp, double Diff) {
        return ReiheTemp - getAbbauWert(offZeit, ReiheTemp, Diff);
    }
    /**
     *
     * @param offZeit
     * @param ReiheTemp
     * @param Diff
     * @return
     */
    @Override
    public final double getTempReihenAufbau(double offZeit, double ReiheTemp, double Diff) {
        return ReiheTemp - getAbbauWert(offZeit, ReiheTemp, Diff);
    }

    /**
     *
     * @param offZeit
     * @param ReihenTemp
     * @param PallTemp
     * @param Diff
     * @return
     */
    @Override
    public final double getPallTempAufbau(double offZeit, double ReihenTemp, double PallTemp, double Diff) {
        return PallTemp + getAbbauWert(offZeit, ReihenTemp, Diff);
    }

    /**
     *
     * @param onZeit
     * @param ReihenTemp
     * @param PallTemp
     * @param Diff
     * @return
     */
    @Override
    public final double getPallTemp(double onZeit, double ReihenTemp, double PallTemp, double Diff) {
        if (onZeit == 0.0) {
            return -18.0;
        } else {
            return getPallTempAufbau(onZeit, ReihenTemp, PallTemp, Diff);
        }
    }
    Object[] getHead(int i) {
        if (i == 1) {
            return new Object[]{" ", "3900-(12*(1))", "Diff/(2)", "(3)/T", "Zeit/(3)","(1)-(5)","-18°C+(5)"};
        }
        if (i == 2) {
            return new Object[]{"(1) Temp", "(2) ModKey", "(3) Zeitbedarf", "(4) ZBproGrad", "(5) AbbauWert", "(6) T Abbau","(7) PallTemp"};
        } else {
            return new Object[]{"(1) Temp", "(2) ModKey", "(3) Zeitbedarf", "(4) ZBproGrad", "(5) ZeitFaktor", "(6) Lnks", "(7) PallTemp", "(8) Rechts", "(9) PallTemp"};
        }
    }

    public List line( double i, double Diff) {        
        ArrayList<Double> l = new ArrayList<>();
        /**
         * Fixenteil
         */
        l.add(i);//(1)
        l.add(getKey(i));//(2)

        /**
         * Variablenteil
         */
        l.add(getZeitBedarf(i, Diff));//(3)
        l.add(getZeitProGrad(i, Diff));//(4)        
        l.add(getAbbauWert(getPauseZeit(), i, Diff));//(5)        
        l.add(getTempReihenAufbau(getPauseZeit(), i, Diff));//(06)        
        l.add(getPallTemp(getPauseZeit(), i, -18.0, Diff));//(07)
        return l;
    }

    public LinkedHashMap<String, List> Listen(double pausezeit, double Diff) {
        setPauseZeit(pausezeit);
        myListen = new LinkedHashMap<>();
        Collections.synchronizedMap(myListen);
        double interval = 0.10;
        for (double i = 0.0; i < 325d; i += interval) {
            myListen.put(genKey(i), line(i ,Diff));
        }
        return myListen;
    }

    

    void showHead(int i) {
        for (Object H_date : getHead(i)) {
            System.out.printf("%16s", H_date);
        }
        System.out.println();
    }
    public Object[] vonListen(double Temp) {        
        if(myListen.containsKey(genKey(Temp))){        
            return myListen.get(genKey(Temp)).toArray();                
        }else return new Object[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    }
    public void subllist(double von,double bis,double loop) {                        
        System.out.println("Zeit:"+getPauseZeit());
        showHead(1);
        showHead(2);
        for(double i=von;i<bis;i+=loop){
            readlisten(i);
        }
    }

    public void readlisten(double Temp) {
       if(myListen.containsKey(genKey(Temp))){        
            Object[] keyzeile = myListen.get(genKey(Temp)).toArray();                
            for (Object keyzeile1 : keyzeile) {
                System.out.printf("%16.3f", (double)keyzeile1);
            }
            System.out.println();        
        }else System.out.println("dieser Temp ist nicht vorhanden");        
    }


    public void showMe(LinkedHashMap<String, List> MapFix) {
        System.out.println("Zeit:"+getPauseZeit());
        showHead(1);
        showHead(2);
        MapFix.entrySet().stream().forEach((Map.Entry<String, List> m) -> {
            // System.out.print(m.getKey()+ " " +m.getValue());
            Object[] o = m.getValue().toArray();
            for (Object o1 : o) {
                System.out.printf("%16.2f", (double) o1);
            }
            System.out.println();
        });
    }

    String genKey(double Temp) {
        Temp = Math.round(Temp * 100);
        double t = Math.round(Temp / 10);
        String val = "" + (int) t;
        return val;
    }

    /**
     * @return the PauseZeit
     */
    @Override
    public double getPauseZeit() {
        return PauseZeit;
    }

    /**
     * @param PauseZeit the PauseZeit to set
     */
    @Override
    public void setPauseZeit(double PauseZeit) {
        this.PauseZeit = PauseZeit;
    }

}

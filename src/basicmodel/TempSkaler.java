/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import java.util.Collections;
import java.util.LinkedHashMap;

/**
 *
 * @author EbmGastroService
 */
public class TempSkaler {

    private double AufHeitzenMax;
    private double HeitzZeit;
    private double[] MyScaler;
    private int AnzahlKammer;
    private int AnzahlPallinoosProSeite;
    private double LeitFactor;

    public TempSkaler(int AnzahlKammer) {
        this.AnzahlKammer = AnzahlKammer;
        AufHeitzenMax = 324.0d;
        HeitzZeit = 30.0d;
        AnzahlPallinoosProSeite = 10;
        LeitFactor = 0.80d;//80% die Hietze wird übertragen als RestHietze
    }
    public TempSkaler(int AnzahlKammer,int AnzPallproSeite) {
        this.AnzahlKammer = AnzahlKammer;
        this.AnzahlPallinoosProSeite = AnzPallproSeite;
        AufHeitzenMax = 324.0d;
        HeitzZeit = 30.0d;        
        LeitFactor = 0.80d;//80% die Hietze wird übertragen als RestHietze
    }


    TempSkaler() {
        AnzahlKammer = 5;
        AufHeitzenMax = 324.0d;
        HeitzZeit = 30.0d;
        AnzahlPallinoosProSeite = 5;
        LeitFactor = 0.80d;//80% die Hietze wird übertragen als RestHietze
    }

    double getHeitzLeistungProSekunde() {
        return AufHeitzenMax / HeitzZeit;
    }

    double getRestHietze() {
        return AufHeitzenMax * getLeitFactor();
    }

    final double[] creatScaler() {
        double ReihenHitze = getRestHietze() / getAnzahlPallinoosProSeite();
        double ReihenKammer = (double) (ReihenHitze / getAnzahlKammer());
        MyScaler = new double[getAnzahlPallinoosProSeite()];
        double x = 0.0d;
        for (int i = 0; i < MyScaler.length; i++) {
            x++;
            MyScaler[i] = x * ReihenKammer;
        }
        return MyScaler;
    }

    double getLastTemperatur() {
        return MyScaler[MyScaler.length - 1];
    }

    double getIst2SollHietze(double IstWert) {
        return (IstWert / getLeitFactor());
    }

    double getAllKammerTemp(double oneKammerTemp) {
        return (oneKammerTemp * getAnzahlKammer());
    }

    LinkedHashMap<String, Number> getEinschaltZeit(double OfenHeitzTemp, double KammerTemp) {
        LinkedHashMap<String, Number> einDaten = new LinkedHashMap<String, Number>();
        Collections.synchronizedMap(einDaten);
        //System.out.println(KammerTemp);

        double OfenHeitzBedarf = OfenHeitzTemp / getHeitzLeistungProSekunde();
        double Ist_KammerHietze = getAllKammerTemp(KammerTemp);
        double KammerHietze_Need = getRestHietze() - Ist_KammerHietze;
        double OfenRestHietze = getIst2SollHietze(Ist_KammerHietze);
        double OfenHietze_Need = getIst2SollHietze(KammerHietze_Need);
        double AufheizZeit = OfenHietze_Need / getHeitzLeistungProSekunde();
        double Heisluft = OfenHeitzBedarf - AufheizZeit;
                     //" 5 - Pallinoos Temperatur nachdem Backen  :"
        einDaten.put(" 6 - Optimale Ofentemperatur:..............", OfenHeitzTemp);
        einDaten.put(" 7 - Gesamte Aufheitzzeit:.................", OfenHeitzBedarf);
        einDaten.put(" 8 - Ofen Resthietze vordem Backen:........", OfenRestHietze);
        einDaten.put(" 9 - Aufheitzen Temperaturbedarf:..........", OfenHietze_Need);
        einDaten.put("10 - Aufheitzen Zeitbedarf in Sekunden:....", AufheizZeit);
        einDaten.put("11 - Heissluft Zeitbedarf in Sekunden:.....", Heisluft);
        return einDaten;
    }

    /**
     * @return the AufHeitzenMax
     */
    public double getAufHeitzenMax() {
        return AufHeitzenMax;
    }

    /**
     * @param AufHeitzenMax the AufHeitzenMax to set
     */
    public void setAufHeitzenMax(double AufHeitzenMax) {
        this.AufHeitzenMax = AufHeitzenMax;
    }

    /**
     * @param HeitzZeit the HeitzZeit to set
     */
    public void setHeitzZeit(double HeitzZeit) {
        this.HeitzZeit = HeitzZeit;
    }

    /**
     * @return the HeitzZeit
     */
    public double getHeitzZeit() {
        return HeitzZeit;
    }

    /**
     * @return the AnzahlPallinoosProSeite
     */
    public int getAnzahlPallinoosProSeite() {
        return AnzahlPallinoosProSeite;
    }

    /**
     * @param AnzahlPallinoosProSeite the AnzahlPallinoosProSeite to set
     */
    public void setAnzahlPallinoosProSeite(int AnzahlPallinoosProSeite) {
        this.AnzahlPallinoosProSeite = AnzahlPallinoosProSeite;
    }

    /**
     * @return the myScaler
     */
    public double[] getMyScaler() {
        return MyScaler;
    }

    /**
     * @param myScaler the myScaler to set
     */
    public void setMyScaler(double[] myScaler) {
        this.MyScaler = myScaler;
    }

    /**
     * @return the AnzahlKammer
     */
    public int getAnzahlKammer() {
        return AnzahlKammer;
    }

    /**
     * @param AnzahlKammer the AnzahlKammer to set
     */
    public void setAnzahlKammer(int AnzahlKammer) {
        this.AnzahlKammer = AnzahlKammer;
    }

    /**
     * @return the LeitFactor
     */
    public double getLeitFactor() {
        return LeitFactor;
    }

    /**
     * @param LeitFactor the LeitFactor to set
     */
    public void setLeitFactor(double LeitFactor) {
        this.LeitFactor = LeitFactor;
    }

}

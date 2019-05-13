package ProtoTyp;
// created on 05.09.2014 at 14:04

/**
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
public class Trommel extends Pall implements Bauelemente {

    double Rad_Out;
    double Rad_In;

    public Trommel() {
    }

    public void setTrommel(double Ro, double Ri) {
        Rad_Out = Ro;
        Rad_In = Ri;
    }

    public double getRad_Out() {
        return Rad_Out;
    }

    public double getRad_In() {
        return Rad_In;
    }

    public double getVolume_Out() {
        return (Rad_Out / 2) * (2 * Math.PI);
    }

    public double getVolume_In() {
        return (Rad_In / 2) * (2 * Math.PI);
    }

    //2*Offnung mitdem Umfang ein Pall_Radius 1 mal Oben und 1 mal Unten als Einlasser.
    @Override
    public double getStock() {
        return Math.rint((getVolume_Out() / Radius)) - 2f;
    }

    @Override
    public double getArea() {
        double ro = getRad_Out() / 2;
        double ri = getRad_In() / 2;
        double fo = Math.PI * (ro * ro);
        double fi = Math.PI * (ri * ri);
        double fa = fo - (2 * (Radius * Radius));
        double mf = fa + 3 * (fo - fi);
        return mf / 10000;
    }

    @Override
    public double getVolume() {
        return getVolume_Out() - getVolume_In();
    }

    @Override
    public double getGewicht() {
        return (getStock() * Gewicht) / 1000;
    }

    @Override
    public int getAnzahlBauelemente() {
        return 5;
    }

    @Override
    public double getMaterialGewicht() {
        //1 m Blech = 1.5 Kg
        return (getArea() * 1.5);
    }

    @Override
    public String getEigenschaft() {
        Zahlformat mz = new Zahlformat();
        return "\nAuftaukammer:\nKammerreifen, Aussen: " + mz.Zahl(Rad_Out) + " cm, Innen: " + mz.Zahl(Rad_In) + " cm"
                + "\nBauelemente: " + getAnzahlBauelemente() + " aus folierten Blech"
                + "\n2 Ausen, 1 Innen und und 2 Deckelseiten."
                + "\nMaterialfläche: " + mz.Zahl(getArea(), 2) + " m., Netto Gewicht: " + mz.Zahl(getMaterialGewicht(), 2) + " Kg."
                + "\nKammer Inhalt: " + mz.Zahl(getStock()) + " Stk., Gewicht: " + mz.Zahl(getGewicht(), 2) + " Kg."
                + "\nBrutto Gewicht, Kammern inkl. Inhalt: " + mz.Zahl((getGewicht() + getMaterialGewicht()), 2) + " Kg.";
    }
}

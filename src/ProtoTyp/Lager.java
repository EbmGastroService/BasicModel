package ProtoTyp;

// created on 05.09.2014 at 14:14
/**
 * @author Mourad El Bakry
 * @author EbmGastroService
 */
public class Lager extends Pall implements Bauelemente {

    double Leng;
    double Bereit;

    public void setLage(double L, double B) {
        Leng = L;
        Bereit = B;
    }

    public double getHeight() {
        return Leng;
    }

    public double getWidth() {
        return Bereit;
    }

    @Override
    public double getArea() {
        double Kantenseite = ((Leng + Radius + 0.2) * (Bereit + Radius + 0.2));
        double Deckseite = ((Leng - 0.2) * (Bereit - 0.2));
        return (Kantenseite + Deckseite) / 10000;
    }

    @Override
    public double getVolume() {
        return 2 * (Leng + Bereit);
    }

    @Override
    public double getStock() {
        return (Leng / Radius) * (Bereit / Radius);
    }

    @Override
    public double getGewicht() {
        return (getStock() * Gewicht) / 1000;
    }

    @Override
    public int getAnzahlBauelemente() {
        return 2;
    }

    @Override
    public double getMaterialGewicht() {
        //100 Cm=1100 Gram
        //1m = 1.1 kg
        return getArea() * 1.1;
    }

    @Override
    public String getEigenschaft() {
        Zahlformat mz = new Zahlformat();
        return "Lager:\nMasse (LxBxT): " + mz.Zahl(Leng) + " x " + mz.Zahl(Bereit) + " x " + mz.Zahl(Radius) + " cm."
                + "\nBauelemente: " + getAnzahlBauelemente() + " aus Kunststoff, Kantenseite und Deckelseite."
                + "\nMaterialfläche: " + mz.Zahl(getArea(), 2) + " m., Netto Gewicht: " + mz.Zahl(getMaterialGewicht(), 2) + " kg. "
                + "Inhalt: " + mz.Zahl(getStock()) + " Stk., Gewicht: " + mz.Zahl(getGewicht(), 2) + " Kg."
                + "\nBrutto Gewicht, Lager inkl. Inhalt: " + mz.Zahl((getGewicht() + getMaterialGewicht()), 2) + " Kg.";
    }
}

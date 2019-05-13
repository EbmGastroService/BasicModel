package ProtoTyp;

// created on 05.09.2014 at 14:04
/**
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
//Kugelmasse: Radius, Gewicht
public abstract class Pall {

    double Radius;
    double Gewicht;
    double pi = Math.PI;

    public Pall() {
        Radius = 5f;
        Gewicht = 40f;
    }

    public void setPall(double R, double G) {
        Radius = R;
        Gewicht = G;
    }

    public double getRadius() {
        return Radius;
    }

    public double getWeight() {
        return Gewicht;
    }

    public double getArea() {
        double r = getRadius() / 2;
        return (pi * (r * r));
    }

    public double getVolume() {
        double r = getRadius() / 2;
        return (2 * pi * r);
    }

    double getGewicht() {
        return Gewicht;
    }

    double getStock(double BallRadius) {
        return 1f;
    }

    int getAnzahlBauelemente() {
        return 1;
    }

    double getMaterialGewicht() {
        return 0f;
    }

    public String getEigenschaft() {
        Zahlformat mz = new Zahlformat();
        return "\nPallinoos:\nPallinoos sind Teiggefülte Kugeln, vorgebacken und gefrören"
                + "\nEtwa " + mz.Zahl(Radius) + " cm Rund und " + mz.Zahl(getWeight()) + " gram schwer.";
              //+"\nUmfang: "+mz.Zahl(getVolume())+" cm, Fläche: "+mz.Zahl(getArea())+" cm."
    }

}

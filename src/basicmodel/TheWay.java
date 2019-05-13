package basicmodel;

/**
 *
 * @author EbmGastroService
 */
public class TheWay {

    int Spalte;
    String PallName;
    int mySpalte;

    public TheWay(String PallName) {
        Spalte = 30;
        this.PallName = PallName;
    }

    int getSpalte() {
        if (PallName == null || PallName.length() == 0) {
            PallName = "L/R";
        }
        if (PallName.contains("L") || PallName.contains("L/R")) {
            mySpalte = Spalte / 2;
        } else if (PallName.contains("R")) {
            mySpalte = (Spalte * 2) - 5; //55
        }
        System.out.println("PallName: " + PallName + "," + mySpalte);
        return mySpalte;
    }
}

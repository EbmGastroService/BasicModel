package ProtoTyp;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *Model <code>präserntiert bestimmte Daten über jegliche Automatentwurf</code>
 * die Standardautomat ist für die Öfentlichkeitseinrichtungen wie Flughafen, Spital usw.
 * die Officeautomat ist für Büro Werkstätte und kleine Organisationen.
 * Homeautomat ist für Haushaltanwendung 
 * Variabelmodel ist für selbst Zusammengestaltung.
 * jeder hat eine Lager, Auftaukammer(Trommel) und Ofen.
 * 
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */

public class Model extends Pall {

    double[] ModDate;
    float LagerL;
    String ModName;
    Lager L;
    Trommel T;

    public Model() {
        ModName = "";
        LagerL = 20f;
    }

    public double[] getModel(int ID) {
        switch (ID) {
            case 1:
                Standard();
                break;
            case 2:
                Office();
                break;
            case 3:
                Haushalt();
                break;
            case 4:
                Home();
                break;
            case 5:
                Variabel();
                break;
            default:
                break;
        }
        return ModDate;
    }

    public double getLagerBestand() {
        return L.getStock();
    }

    public double getTrommelBestand() {
        return T.getStock();
    }

    public String getModelName() {
        return ModName;
    }

    public void setLagerL(float d) {
        LagerL = d;
    }

    void Variabel() {
        float LagerBereite = (float) (LagerL * 1.5);
        float TrommelRadiusA = (LagerL);
        float TrommelRadiusI = TrommelRadiusA - (2 * 5);
        String str;
        if (LagerBereite < 12f) {
            LagerBereite = 18f;
        }
        if (TrommelRadiusA < 12f) {
            TrommelRadiusA = 12f;
            LagerL += TrommelRadiusI;
            TrommelRadiusI = TrommelRadiusA - (2 * 5);
            str = "Kurregiert! LagerL: " + (int) LagerL + ", Lager Bereite: " + (int) LagerBereite + ", TRA: " + (int) TrommelRadiusA + ", TRI: " + (int) TrommelRadiusI;
        } else {
            str = "Ok! LagerL: " + (int) LagerL + ", Lager Bereite: " + (int) LagerBereite + ", TRA: " + (int) TrommelRadiusA + ", TRI: " + (int) TrommelRadiusI;
        }
        System.out.println(str);
        ModDate = new double[]{5f, 40f, LagerL, LagerBereite, TrommelRadiusA, TrommelRadiusI};
        ModName = "Variabel";
        Global();
    }

    void Global() {
        L = new Lager();
        T = new Trommel();
        super.setPall(ModDate[0], ModDate[1]);
        L.setLage(ModDate[2], ModDate[3]);
        T.setTrommel(ModDate[4], ModDate[5]);
    }

    void Standard() {
        ModDate = new double[]{5d, 40d, 40d, 60d, 35d, 25d};
        ModName = "Standard";
        Global();
    }

    void Office() {
        ModDate = new double[]{5d, 40d, 30d, 40d, 22d, 12d};
        ModName = "Office";
        Global();
    }

    void Haushalt() {
        ModDate = new double[]{5d, 40d, 15d, 20d, 16d, 6d};
        ModName = "Home";
        Global();
    }

    void Home() {
        ModDate = new double[]{5d, 40d, 15d, 20d, 12d, 2d};
        ModName = "Easyone";
        Global();
    }

    public String ModelDatenStr(int AnzahlSorten, int modnr) {
        getModel(modnr);
        Zahlformat mz = new Zahlformat();
        double backof = 15.0;
        double salatTeil = 80.0;
        int l_kapazi = (int) L.getStock() * AnzahlSorten;
        int a_kapazi = 2 * AnzahlSorten;
        String lager_bedarf = "\nLageraufrüstung über Funksteurung";
        if (modnr == 2) {
            lager_bedarf = "\nLageraufrüstung über Betreuer/In";
        }
        if (modnr == 3 || modnr == 4 || LagerL < 20f) {
            lager_bedarf = "\nLageraufrüstung vom Supermarkt";
            salatTeil = 0;
            backof = 10;
        }
        double AmassH = (int) L.getHeight() + 5 + T.getRad_Out() + 10;
        if (ModName.equals("Home") || ModName.equals("Easyone")) {
            AmassH += backof;
        } else {
            AmassH += backof + salatTeil;
        }
        String str = ModName + ",  "
                + "Anzahl Sortenkammer: " + AnzahlSorten
                + "\nAussen Maße B,H,T: " + (AnzahlSorten * 6) + ", " + mz.Zahl(AmassH) + ", " + mz.Zahl(L.getWidth() + 3.0) + " cm"
                //  +"\nPallinoos: " +mz.Zahl(super.getRadius()) + " cm rund, "+ mz.Zahl(super.getWeight())+ " Gram"                
                + "\nLagerregal L,B: " + mz.Zahl(L.getHeight()) + ", " + mz.Zahl(L.getWidth()) + " cm"
                + "\nAuftauKammer Radius\n*Aussen: " + mz.Zahl(T.getRad_Out()) + "," + " Innen: " + mz.Zahl(T.getRad_In()) + " cm"
                + "\nBackofen: " + backof + " cm hoch."
                + "\nZubehör Kammer: " + mz.Zahl(salatTeil) + " cm hoch."
                + "\nInhalt je Lagerregal : " + mz.Zahl(L.getStock()) + " Stk."
                + "\nInhalt je Kammer: " + mz.Zahl(T.getStock()) + " Stk."
                + "\nGewicht je Regalinhalt: " + mz.Zahl((L.getGewicht())) + " Kg"
                + "\nGewicht je Kammerinhalt: " + mz.Zahl(T.getGewicht()) + " Kg"
                + "\nGewicht die Kammerninhalt: " + mz.Zahl((T.getGewicht() * AnzahlSorten)) + " Kg"
                + "\nGewicht die Regaleninhalt: " + mz.Zahl((L.getGewicht() * AnzahlSorten)) + " Kg"
                + "\nGesamte Inhaltsgewicht: " + (Math.rint(T.getGewicht() * AnzahlSorten) + Math.rint(L.getGewicht() * AnzahlSorten)) + " Kg"
                + "\nService Kapazität: " + a_kapazi + " Stk/Minute"
                + "\nService Kapazität: " + a_kapazi * 60d + " Stk/Stunde"
                + "\nLager Kapazität: " + l_kapazi + " Stk"
                + lager_bedarf;
        //System.out.println(L.getEigenschaft() + super.getEigenschaft() + T.getEigenschaft());
        return str;
    }

    public TextFlow MDaten(int AnzahlSorten, int modnr) {
        getModel(modnr);
        Zahlformat mz = new Zahlformat();
        double backof = 15.0;
        double salatTeil = 80.0;
        int l_kapazi = (int) L.getStock() * AnzahlSorten;
        int a_kapazi = 2 * AnzahlSorten;
        String lager_bedarf = "\nLageraufrüstung über Funksteurung";
        if (modnr == 2) {
            lager_bedarf = "\nLageraufrüstung über Betreuer/In";
        }
        if (modnr == 3 || modnr == 4 || LagerL < 20f) {
            lager_bedarf = "\nLageraufrüstung vom Supermarkt";
            salatTeil = 1f;
            backof = 10f;
        }
        double AmassH = (float) L.getHeight() + 5f + T.getRad_Out() + 10f;
        if (ModName.equals("Home") || ModName.equals("Easyone")) {
            salatTeil = 1f;
            AmassH += backof;
        } else {
            AmassH += backof + salatTeil;
        }
        Text Tbold01 = new Text(ModName + " Automat mit " + AnzahlSorten + " Sortenkamme"
                + "\nAussen Maße BxHxT: " + (AnzahlSorten * 6) + ", " + mz.Zahl(AmassH) + ", " + mz.Zahl(L.getWidth() + 3.0) + " cm.");
        Tbold01.setStyle("-fx-font-weight:bold;");
        Text Tbold02 = new Text("\nBackofen: " + backof + " cm hoch. Zubehör Kammer: " + mz.Zahl(salatTeil) + " cm hoch."
                + "\nService Kapazität: " + a_kapazi + " Stk/Min., " + a_kapazi * 60 + " Stk/Std., Lager Kapazität: " + l_kapazi + " Stk." + lager_bedarf);

        double Baumit = ((L.getMaterialGewicht() + T.getMaterialGewicht()) * AnzahlSorten);
        double LagG = (L.getGewicht() * AnzahlSorten);
        double TromG = (T.getGewicht() * AnzahlSorten);
        Text Tnorm02 = new Text("\n" + AnzahlSorten + " Sortenfächer"
                + "\nLager Inhaltsgewicht: " + mz.Zahl(LagG, 2) + " Kg."
                + "\nKammern Inhaltsgewicht: " + mz.Zahl(TromG, 2) + " Kg."
                + "\nBauelemente  Lager &  Kammer: " + mz.Zahl(Baumit, 2) + " kg."
                + "\nGesamt für Lager und Kammern: " + mz.Zahl(TromG + LagG + Baumit, 2) + " Kg.");
        Tnorm02.setStyle("-fx-font-weight:bold;");
        Group tf = new Group();
        Text ll = new Text(L.getEigenschaft());
        Text tt = new Text(T.getEigenschaft());
        Text pp = new Text(getEigenschaft());
        Text sk = new Text("Seitlich Skize");
        sk.setStyle("-fx-font-size:11;-fx-fill:blue;-fx-align:center;");
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        Skize ski = new Skize();
        vb.getChildren().addAll(sk, ski.creatBox(L.getWidth(), L.getHeight(), 1), ski.creatKammer(T.getRad_Out(), T.getRad_In()));
        if (modnr > 2) {
            vb.getChildren().addAll(ski.creatOfen(T.getRad_Out(), 0), ski.creatBox((AnzahlSorten * 5), salatTeil, 0));
        } else {
            vb.getChildren().addAll(ski.creatOfen(T.getRad_In(), 1), ski.creatBox((AnzahlSorten * 5), salatTeil, 0));
        }

        VBox vb1 = new VBox();
        vb1.getChildren().addAll(Tbold01, ll, tt, Tnorm02, pp, Tbold02);

        HBox hb = new HBox();
        hb.getChildren().addAll(vb1, vb);
        tf.getChildren().addAll(hb);

        TextFlow tff = new TextFlow();
        tff.getChildren().addAll(tf);
        return tff;
    }
}

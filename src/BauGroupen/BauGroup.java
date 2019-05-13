package BauGroupen;

import ProtoTyp.Zahlformat;
import basicmodel.ModelDriver;
import basicmodel.tabelView;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
public class BauGroup extends ModelDriver implements ArtikelBaugroppe {

    int Reihen;
    Einlasser LagerF, OfenF;//für Animation Oben und unten abhängig von ang
    int Menge;
    int Lagerstand;
    public Label mengenLabel, LagerLabel;
    private Label TempLabel, GroupLabel;

    private String sorte;
    String Richtung;
    private String PallName;
    Zahlformat zf;
    private double Tlastone, Plastone;//letzte Pallinoos Temp und letzte TempScaler
    int ang;//angel -1 ist Links, 1 ist Rechts
    tabelView viewMap;//übernehmt daten von ModelDriver Map
    /**
     * 
     * @param R 
     */

    public BauGroup(int R) {
        Reihen = R;
        Richtung = "L";
        ang = 0;
    }
    /**
     * 
     * @param kammer //Sorten
     * @param reihen //Wie viel Pallinoos im Auftaukammer Links und Rechts
     */

    public BauGroup(int kammer, int reihen) {
        zf = new Zahlformat();
        super.setAutomat(kammer, reihen);
        this.PallName = super.getPallLastName();
        this.Reihen = super.getReihen();
        this.Lagerstand = 100;
        this.Menge = 0;
        this.Tlastone = super.getT_lastone();
        this.Plastone = super.getP_lastone();
        this.TempLabel = new Label("Resthitze: " + zf.Zahl((super.getT_lastone() * super.getKammer()), 2) + BZ());
        this.GroupLabel = Tlabel();
        TempLabel.setPrefWidth(200d);
        TempLabel.setAlignment(Pos.CENTER);
        TempLabel.setId("Freilauf_green");
        this.viewMap = super.getTv();
        getRichtung();
    }

    public void UpdatBalls() {
        setPallName(super.getPallLastName());
        setTlastone(super.getT_lastone());
        setPlastone(super.getP_lastone());
        setGroupLabel(Tlabel());
        setTempLabel("Resthitze : " + zf.Zahl((getTlastone() * super.getKammer()), 2) +""+ BZ());
        this.viewMap = super.getTv();
        getRichtung();
    }

    public void OrderMe(int i) {
        if (i > 0) {
            super.Order(i);
        }
    }

    final String BZ() {
        double bz = getBetriebZeit();
        String str = "°C Betriebzeit: " + zf.Zahl(bz, 2) + " Min.";
        if (getBetriebZeit() > 60.0 && getBetriebZeit()< 3600.0) {
            bz = getBetriebZeit() / 60.0;            
            str = "°C\nBetriebzeit: " + zf.Zahl(bz, 2) + " Min.";
        }
        if (getBetriebZeit() > 3600.0) {
            bz = getBetriebZeit() / 3600.0;           
            String mzeit = zf.Zahl(bz, 2);
            String mStd = mzeit.substring(0 , mzeit.length()-3);
            double mMin = Double.parseDouble(mzeit.substring(mzeit.length()-2,mzeit.length()))*0.6;
            str = "°C\nBetriebzeit: " + mStd + " Std: "+zf.f(mMin, 0)+" Min.";
        }
        return str;
    }

    String TestMap() {
        if (getInhalt().contains("voll")) {
            TempLabel.setId("Freilauf_red");
            return "Freilauf_red";
        } else {
            TempLabel.setId("Freilauf_green");
            return "Freilauf_green";
        }
    }

    public String getTlastoneStyle() {
        String st = "lightblue";
        if (getTlastone() > 1.0 && getTlastone() < 10.0) {
            st = "white";
        }
        if (getTlastone() > 10.0 && getTlastone() < 20.0) {
            st = "orange";
        }
        if (getTlastone() > 20.0 && getTlastone() < 30.0) {
            st = "pink";
        }
        if (getTlastone() > 30.0) {
            st = "red";
        }
        return "-fx-background-color:linear-gradient(to top, black 5%," + st + 
                ", white,blue);-fx-effect:innershadow(gaussian,black,10,0.0,0,0.5);";
    }

    final String getRichtung() {
        String rtg;
        if (getPallLastName().contains("L") || getPallLastName().contains("l/r")) {
            rtg = "L";
            ang = 1;
        } else {
            rtg = "R";
            ang = -1;
        }
        setPallName(rtg);
        this.Richtung = rtg;
        return rtg;
    }

    public Group creatAuftaukammerGroup() {
        Group AGroup = new Group();
        AGroup.getChildren().addAll(Auftaukammer(),
                new BallGroup(Reihen).creatBalls("L"),
                new BallGroup(Reihen).creatBalls("R"));
        return AGroup;
    }

    public Group creatAuftaukammerGroup(int i) {
        Group AGroup = new Group();
        AGroup.getChildren().addAll(Auftaukammer(),
                new BallGroup(Reihen).creatBalls("L", getPNL(), getPTL()),
                new BallGroup(Reihen).creatBalls("R", getPNR(), getPTR()));
        return AGroup;
    }

    public void setSorte(String sn) {
        super.setMyName(sn);
        sorte = sn;
    }

    @Override
    public Rectangle Gefrierfach() {
        return creatBauElement(width, 1 * heightEinheit);
    }

    @Override
    public Rectangle Auftaukammer() {
        return creatBauElement(width, Reihen * heightEinheit);
    }

    @Override
    public Rectangle Ofen() {
        return creatBauElement(width, 2 * heightEinheit);
    }

    @Override
    public Group GefrierEinlasser() {
        return creatLagerEinlasser(creatBauElement(heightEinheit + 4, heightEinheit + 4));
    }

    @Override
    public Group OfenEinlasser() {
        return creatOfenEinlasser(creatBauElement(heightEinheit + 4, heightEinheit + 4));
    }

    @Override
    public Rectangle creatBauElement(double E_width, double E_height) {
        LinearGradient lg = LinearGradient.valueOf("linear-gradient(to right, black,white,grey,lightgrey,black)");
        return new Rectangle(E_width, E_height, lg);
    }

    @Override
    public VBox cereatArtikelBauGroupe() {
        VBox Abg = new VBox();
        Abg.setId("vbox");
        Abg.setAlignment(Pos.CENTER);
        Abg.getChildren().addAll(getSortenLagerLabel(), GefrierEinlasser(),
                creatAuftaukammerGroup(1), OfenEinlasser(),
                getMengenLabel(), getSortenNamesLabel(), getGroupLabel(), ActionButton());
        return Abg;
    }

    Group creatTeilkammer() {
        Rectangle Topkammer = creatBauElement(width, heightEinheit);
        Group Tg = new Group();
        Tg.getChildren().addAll(Topkammer, new BallGroup(1).creatBalls("L"), new BallGroup(1).creatBalls("R"));
        return Tg;
    }

    @Override
    public Group creatLagerEinlasser(Rectangle r) {
        Group EinlasserGroup = new Group();
        LagerF = new Einlasser(ang);
        //LagerF.start();
        Rectangle gef = Gefrierfach();
        r.setY(gef.getHeight());
        r.setX(gef.getWidth() / 2 - r.getWidth() / 2);
        Group eif = LagerF.einGroup;
        eif.setLayoutY(gef.getHeight() / 2 + r.getHeight() / 2);
        eif.setLayoutX(gef.getWidth() / 2 - r.getWidth() / 2);

        EinlasserGroup.getChildren().addAll(gef, r, eif);
        return EinlasserGroup;
    }

    @Override
    public Group creatOfenEinlasser(Rectangle r) {
        Group EinlasserGroup = new Group();
        OfenF = new Einlasser(ang);
        //OfenF.start();
        Rectangle ofen = Ofen();
        ofen.setFill(LinearGradient.valueOf("linear-gradient(to top, black,white,red,lightgrey,black)"));
        ofen.setY(r.getHeight());
        r.setX((ofen.getWidth() - r.getWidth()) / 2);
        Group eif = OfenF.einGroup;
        eif.setLayoutY(r.getHeight() / 4);
        eif.setLayoutX((ofen.getWidth() - r.getWidth()) / 2);
        EinlasserGroup.getChildren().addAll(r, ofen, eif);
        return EinlasserGroup;
    }

    @Override
    public Label getMengenLabel() {
        mengenLabel = initMe(mengenLabel);
        mengenLabel.setText("" + getMenge());
        return mengenLabel;
    }

    @Override
    public Label getSortenNamesLabel() {
        Label l = new Label("Pallinoos");
        l.setId("hbox1");
        l.setStyle(getTlastoneStyle());//"-fx-background-color:linear-gradient(to bottom, dimgrey,black,lightgrey,black)");
        l.setPrefSize(width, 10.0);
        return l;
    }

    final Label Tlabel() {
        Label l = new Label(zf.Zahl(getTlastone(), 1) + "°: " + zf.Zahl(getPlastone(), 1) + "°");
        l.setStyle("-fx-font-size:9px;");
        l.setId(TestMap());
        l.setPrefSize(width, 40);
        l.setAlignment(Pos.TOP_CENTER);
        return l;
    }

    public Label getSortenLagerLabel() {
        LagerLabel = initMe(LagerLabel);
        if (Lagerstand >= 0 && Lagerstand < 5) {
            LagerLabel.setStyle("-fx-text-fill:green;-fx-background-color:linear-gradient(to left, black,white,orange,lightgrey,black);");
            LagerLabel.setText("Funk " + Lagerstand);
        }else if (Lagerstand < 0) {
            LagerLabel.setStyle("-fx-background-color:linear-gradient(to left, black,white,red,lightgrey,black);");
            LagerLabel.setText("Funk " + Lagerstand);
        } else {
            LagerLabel.setText("" + Lagerstand);
        }
        return LagerLabel;
    }

    Label initMe(Label l) {
        l = new Label();
        l.setStyle("-fx-background-color:linear-gradient(to bottom, black,white,black,lightgrey,black);");
        l.setPrefSize(width, 10.0);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    @Override
    public Button ActionButton() {
        Button sorteAction = new Button(getSorte());
        sorteAction.setPrefSize(width, 25.0);
        sorteAction.setAlignment(Pos.CENTER);
        sorteAction.setOnAction((ActionEvent event) -> {
            Menge++;
            Lagerstand--;
            mengenLabel.setText("" + getMenge());
            LagerLabel.setText("" + Lagerstand);
            LagerF.play();
            OfenF.play();
        });
        return sorteAction;
    }

    /**
     * @param PallName the PallName to set
     */
    public void setPallName(String PallName) {
        this.PallName = PallName;
    }

    /**
     * @return the Menge
     */
    public int getMenge() {
        return Menge;
    }

    /**
     * @param Menge the Menge to set
     */
    public void setMenge(int Menge) {
        this.Menge = Menge;
    }

    /**
     *
     * @param n
     */
    public void setLagerstand(int n) {
        Lagerstand = n;
    }

    public int getLagerstand() {
        return Lagerstand;// - Menge;
    }

    public String getPallName() {
        return PallName;
    }

    /**
     * @return the sorte
     */
    public String getSorte() {
        return sorte;
    }

    /**
     * @return the Tlastone
     */
    public double getTlastone() {
        return Tlastone;
    }

    /**
     * @param Tlastone the Tlastone to set
     */
    public void setTlastone(double Tlastone) {
        this.Tlastone = Tlastone;
    }

    /**
     * @return the Tlast
     */
    public Label getTempLabel() {
        return TempLabel;
    }

    /**
     * @param str
     */
    public void setTempLabel(String str) {
        this.TempLabel.setText(str);
    }

    /**
     * @return the viewMap
     */
    public tabelView getViewMap() {
        return viewMap;
    }

    /**
     * @param viewMap the viewMap to set
     */
    public void setViewMap(tabelView viewMap) {
        this.viewMap = viewMap;
    }

    /**
     * @return the Plastone
     */
    public double getPlastone() {
        return Plastone;
    }

    /**
     * @param Plastone the Plastone to set
     */
    public void setPlastone(double Plastone) {
        this.Plastone = Plastone;
    }

    /**
     * @return the GroupLabel
     */
    public Label getGroupLabel() {
        return GroupLabel;
    }

    /**
     * @param GroupLabel the GroupLabel to set
     */
    public void setGroupLabel(Label GroupLabel) {
        this.GroupLabel = GroupLabel;
    }
}

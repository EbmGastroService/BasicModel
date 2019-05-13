package InfoSeiten;


import static java.lang.Math.random;
import java.text.DateFormat;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import static javafx.util.Duration.seconds;

/**
 * @author Mourad El Bakry
 * @author EbmGastroService
 */
public class Panimation {

    /* extends Application {    
    public static void main(String[] args) {
        launch(args);
    }*/
    private PathTransition LagerpathTransition, AkpathTransition, BkpathTransition, BackofenpathTransition;
    private ParallelTransition parallelTransition;
    int Freilauf_Sec = 0;
    Label ontext;
    DateFormat df;
    GridPane grid;
    Pane root;
    int rep = 5;

    public void setRep(int r) {
        rep = r;
    }

    public Pane getPane() {
        initPane();
        return grid;
    }

    Pane initPane() {
        grid = new GridPane();
        root = new Pane();
        root.setCache(true);
        root.setMinSize(290f, 720f);

        RadialGradient g = creatColor(40, 70, 45);
        RadialGradient g1 = creatColor(50, 70, 25);
        RadialGradient g2 = creatColor(60, 90, 35);
        RadialGradient g3 = creatColor(75, 60, 45);
        df = DateFormat.getTimeInstance(DateFormat.SHORT);
        ontext = new Label();
        ontext.setAlignment(Pos.CENTER);
        ontext.setStyle("-fx-font-family: Arial Black;-fx-fill: white;"
                + "-fx-font-size: 14px;-fx-font-weight: bold;"
                + "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,2);"
        );
        EinAusAni();

        //Die Kugeln im Lagerregal (12x8) od. LxB: 60x40cm
        Group layer = creatLayer(13, 9, g);

        //Kugelreihe center in der Lagerregal cyan color.
        Group layer1 = creatLayer(9, 1, g3);
        Rectangle layerHg = new Rectangle(120.0, 40.0, 40.0, 140.0);
        layerHg.setFill(Color.web("cyan"));
        layer1.getChildren().add(layerHg);

        //layer1.setBlendMode(BlendMode.OVERLAY);        
        layer1.setEffect(new BoxBlur(40, 40, 5));
        //G-Kammerkugel für Lager Animation
        Circle LagerAnimation = new Circle(140.0, 100.0, 10, g);
        //A-Kammerkugel für Kammer Animation.******************
        Circle KammerAnimation = new Circle(116.0, 210.0, 10, g1);
        Circle BkAnimation = new Circle(116.0, 342.0, 10, g1);
        //B-Kammerkugel für Backofen Animation       
        Circle BackofenAnimation = new Circle(140.0, 445.0, 10, g2);
        //A-Kammer Aussenseite
        Circle A_Aussen = new Circle(140.0, 275.0, 110, Color.web("#ef508c"));
        Circle A_Innen = new Circle(140.0, 275.0, 82);
        //A-Kammer Path ************* ********* ******
        //Circle A_KammerPath = new Circle(140.0, 275.0, 70);
        //A-Kammer Innenseite
        Circle KammerCenter = new Circle(140.0, 275.0, 60);
        Circle KammerCenter1 = new Circle(140.0, 275.0, 56, Color.web("#ef518c"));

        A_Innen.setStroke(Color.web("white", 0.5f));
        A_Innen.setStrokeWidth(5f);
        KammerCenter1.setStroke(Color.web("white", 0.5f));
        KammerCenter1.setStrokeWidth(5f);
        A_Aussen.setEffect(new BoxBlur(100, 100, 2));
        KammerCenter.setEffect(new BoxBlur(30, 30, 3));
        // B-Kammer
        Circle BackofenCenter = new Circle(140.0, 425.0, 40, Color.RED);
        Circle BackofenInnen = new Circle(140.0, 425.0, 50);
        Circle BackofenAussen = new Circle(140.0, 425.0, 65, Color.web("#ef518c"));

        BackofenCenter.setEffect(new BoxBlur(30, 30, 3));
        BackofenAussen.setEffect(new BoxBlur(30, 30, 3));
        BackofenInnen.setStroke(Color.web("white", 0.5f));
        BackofenInnen.setStrokeWidth(15f);
        //Lagerregal Rahmen
        Rectangle Lagerregal = new Rectangle(30.0, 10.0, 240, 160);
        Lagerregal.setStrokeType(StrokeType.OUTSIDE);
        Lagerregal.setStroke(Color.web("white", 0.9f));
        Lagerregal.setStrokeWidth(6f);
        Lagerregal.setArcHeight(10);
        Lagerregal.setArcWidth(10);        //Lagerregal.setFill(Color.AQUA);
        //Hintergrund Top
        Rectangle HgTop = new Rectangle(0.0, 0.0, 290, 50);
        HgTop.setFill(Color.web("#ffffb3"));
        //HgTop.setEffect(new BoxBlur(30, 50, 8));

        //Hintergrund oben
        Rectangle HgOben = new Rectangle(10.0, 40.0, 270, 250);
        HgOben.setFill(Color.DODGERBLUE);
        HgOben.setEffect(new BoxBlur(30, 50, 8));
        //Hintergrund unten
        Rectangle HgCenter = new Rectangle(10, 290, 270, 200);
        HgCenter.setFill(Color.web("red"));//rose
        HgCenter.setEffect(new BoxBlur(30, 50, 8));
        //Hintergrund zum Boden
        Rectangle HgUnten = new Rectangle(10, 470, 270, 100);
        HgUnten.setFill(Color.web("orange"));
        HgUnten.setEffect(new BoxBlur(30, 50, 8));

        Path BackofenPath = new Path(new MoveTo(140, 345), new CubicCurveTo(140, 345, 140, 520, 140, 550));
        Path LagerPath = new Path(new MoveTo(140, 120), new CubicCurveTo(140, 120, 140, 180, 140, 210));
        Path AKpath = new Path(new MoveTo(116, 210), new CubicCurveTo(116, 210, 140, 210, 160, 210));
        Path BKpath = new Path(new MoveTo(116, 342), new CubicCurveTo(116, 342, 140, 342, 160, 342));

        root.getChildren().add(HgTop);//G-Path    
        root.getChildren().add(HgOben);//G-Lade
        root.getChildren().add(HgCenter);//B-Lade        
        root.getChildren().add(HgUnten);//G-Lade
        root.getChildren().add(Lagerregal);//G-Lade
        root.getChildren().add(layer);//G-Kugeln        

        root.getChildren().add(A_Aussen);//A-Außen
        root.getChildren().add(A_Innen);//A-Außen
        //Gefrierlager Einlaser
        Rectangle Einlaser = creatEinlaser(130, 174, 22, 16);
        root.getChildren().add(Einlaser);//Gefrierlager-Einlaser
        root.getChildren().add(layer1);//G-Kugeln        
        //**
        //LagerAnimation.setBlendMode(BlendMode.SRC_ATOP);
        root.getChildren().add(LagerAnimation);//G-Kugel   

        root.getChildren().add(KammerAnimation);//A-Kugel_O
        root.getChildren().add(BkAnimation);//A-Kugel_U

        root.getChildren().add(KammerCenter1);//A-Innen
        root.getChildren().add(KammerCenter);//A-Innen

        root.getChildren().add(BackofenAussen);//Backofen-Außen
        root.getChildren().add(BackofenInnen);//Backofen-Innen
        root.getChildren().add(BackofenCenter);//Backofen-Kern

        //Backofen Einlaser
        Rectangle B_Einlaser = creatEinlaser(130, 360, 22, 12);
        root.getChildren().add(B_Einlaser);//Backofen-Einlaser
        //Service Einlaser
        Rectangle S_Einlaser = creatEinlaser(130, 480, 22, 16);
        root.getChildren().add(S_Einlaser);//Backofen-Einlaser        

        root.getChildren().add(BackofenAnimation);//B-Kugel          

        root.getChildren().add(creatLayer());//Auftaukammer-Kugeln
        root.getChildren().add(addText("Lager\n-18°C", 120, 120));
        root.getChildren().add(addText("Kammer Oben\n-17°C", 15, 200));
        root.getChildren().add(addText("-10°C", 15, 290));
        root.getChildren().add(addText("Kammer Unten\n+20°C", 15, 360));
        root.getChildren().add(addText("Backofen\n+300°C", 15, 450));
        ontext.setLayoutX(225);
        ontext.setLayoutY(440);
        root.getChildren().add(ontext);
        root.getChildren().add(addText("Servierfertig\n+120°C", 15, 520));
        root.setCache(true);
        root.setCacheHint(CacheHint.ROTATE);
        //grid.setValignment(pplogo(), VPos.TOP);
        grid.setStyle("-fx-background-color:linear-gradient(to right, black 10%,orange,lightgray,black 10%);");//#ffffb3;");
        grid.add(pplogo(), 0, 0);
        grid.add(root, 0, 1);

        //**Backofen Animation
        FillTransition ft = new FillTransition(seconds(2), BackofenCenter, Color.BURLYWOOD, Color.RED);
        ft.setCycleCount(rep);//Animation.INDEFINITE);
        ft.setAutoReverse(true);
        //**Hintergrund Animation
        FillTransition ft1 = new FillTransition(seconds(2), HgCenter, Color.RED, Color.BURLYWOOD);
        ft1.setCycleCount(rep);//Animation.INDEFINITE);
        ft1.setAutoReverse(true);

        // Auftau-Kammer Oben        
        AkpathTransition = new PathTransition(seconds(3), AKpath, KammerAnimation);
        AkpathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        AkpathTransition.setCycleCount(rep);//Timeline.INDEFINITE);
        AkpathTransition.setAutoReverse(true);

        // Lager Animation;
        LagerpathTransition = new PathTransition(seconds(3), LagerPath, LagerAnimation);
        LagerpathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        LagerpathTransition.setCycleCount(rep);//Timeline.INDEFINITE);
        LagerpathTransition.setAutoReverse(false);

        // Auftau-Kammer Unten
        BkpathTransition = new PathTransition(seconds(3), BKpath, BkAnimation);
        BkpathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        BkpathTransition.setCycleCount(rep);//Timeline.INDEFINITE);
        BkpathTransition.setAutoReverse(true);

        // Backofen Animation
        BackofenpathTransition = new PathTransition(seconds(3), BackofenPath, BackofenAnimation);
        BackofenpathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        BackofenpathTransition.setCycleCount(rep);//Timeline.INDEFINITE);
        BackofenpathTransition.setAutoReverse(false);

        //** Animation gemeinsam starten.
        parallelTransition = new ParallelTransition(LagerpathTransition, Ani(layer),
                AkpathTransition, BkpathTransition, BackofenpathTransition, ft, ft1);
        parallelTransition.setCycleCount(rep);//Timeline.INDEFINITE);
        parallelTransition.setAutoReverse(false);//true);
        //play();

        return grid;
    }

    ImageView pplogo() {
        ImageView img = new ImageView(new Image(Panimation.class.getResourceAsStream("pict/pplogo.png")));
        img.setFitHeight(100f);
        img.setFitWidth(290f);
        //img.setX(15f);        img.setY(550f);
        return img;
    }

    Text addText(String str, double x, double y) {
        Text t = new Text();
        t.setText(str);
        t.setCache(true);
        t.setStyle("-fx-font-family: Arial Black;-fx-fill: FIREBRICK;"
                + "-fx-font-size: 17px;-fx-font-weight: bold;"
                + "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,2);"
        );
        t.setX(x);
        t.setY(y);
        return t;
    }

    Timeline Ani(Group layer) {
        Timeline timeline = new Timeline();
        layer.getChildren().stream().forEach((circle) -> {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(seconds(0),
                            new KeyValue(circle.translateXProperty(), random() * -10),
                            new KeyValue(circle.translateYProperty(), random() * -6),
                            new KeyValue(circle.rotateProperty(), random() * -270),
                            new KeyValue(circle.cacheHintProperty(), CacheHint.QUALITY)
                    ),
                    new KeyFrame(seconds(2),
                            new KeyValue(circle.translateXProperty(), random() * 10),
                            new KeyValue(circle.translateYProperty(), random() * 6),
                            new KeyValue(circle.rotateProperty(), random() * 180),
                            new KeyValue(circle.cacheHintProperty(), CacheHint.QUALITY)
                    )
            );
        });
        timeline.setAutoReverse(true);
        timeline.setCycleCount(rep);//Animation.INDEFINITE);        
        //EinAusAni();
        return timeline;
    }

    void EinAusAni() {                            
        Timeline digitalTime = new Timeline(
                new KeyFrame(seconds(0), (ActionEvent actionEvent) -> {                    
                        ontext.setText("OFF");                        
                    }),
                new KeyFrame(seconds(1), (ActionEvent actionEvent) -> {
                        ontext.setText("ON\n" +10+"\nSek");
                    }),
                new KeyFrame(seconds(2), (ActionEvent actionEvent) -> {
                        ontext.setText("ON\n" +20+"\nSek");
                    }),
                new KeyFrame(seconds(3), (ActionEvent actionEvent) -> {
                        ontext.setText("ON\n" +30+"\nSek");
                    }),                
                new KeyFrame(seconds(4), (ActionEvent actionEvent) -> {                    
                        ontext.setText("OFF");                        
                    })                
        );
        digitalTime.setCycleCount(rep);
        digitalTime.play();
    }

    RadialGradient creatColor(int p, int p1, int p2) {
        return RadialGradient.valueOf("radial-gradient(center " + p + "% " + (100 - p) + "%, radius " + p1 + "%,  cyan, orange " + p2 + "%, magenta)");
    }

    Rectangle creatEinlaser(int x, int y, int w, int h) {
        Rectangle Einlaser = new Rectangle(w, h, Color.GRAY);
        Einlaser.setStroke(Color.web("white", 0.5f));
        Einlaser.setStrokeWidth(6f);
        Einlaser.setX(x);
        Einlaser.setY(y);

        return Einlaser;
    }

    Group creatLayer(int w, int h, RadialGradient g) {
        Group layer = new Group();
        for (int x = 1; x < h; x++) {
            for (int i = 1; i < w; i++) {
                Circle circle = new Circle(20 + (i * 20), (x * 20), 10, g);
                layer.getChildren().add(circle);
            }
        }
        return layer;
    }

    Group creatLayer() {
        RadialGradient g2 = creatColor(60, 90, 35);
        Group layer = new Group();
        Circle L1 = new Circle(73, 257, 10, g2);
        Circle L2 = new Circle(86, 235, 10, g2);
        Circle L3 = new Circle(100, 220, 10, g2);
        Circle L4 = new Circle(116, 210, 10, g2);
        Circle L5 = new Circle(70, 275, 10, g2);
        Circle L6 = new Circle(73, 295, 10, g2);
        Circle L7 = new Circle(81, 315, 10, g2);
        Circle L8 = new Circle(97, 332, 10, g2);
        Circle L9 = new Circle(122, 342, 10, g2);

        Circle R1 = new Circle(205, 257, 10, g2);
        Circle R2 = new Circle(197, 239, 10, g2);
        Circle R3 = new Circle(179, 220, 10, g2);
        Circle R4 = new Circle(160, 210, 10, g2);
        Circle R5 = new Circle(210, 275, 10, g2);
        Circle R6 = new Circle(205, 295, 10, g2);
        Circle R7 = new Circle(198, 315, 10, g2);
        Circle R8 = new Circle(177, 332, 10, g2);
        Circle R9 = new Circle(160, 342, 10, g2);
        //Im Backofen
        Circle Bl = new Circle(125, 465, 10, g2);
        Circle Br = new Circle(150, 465, 10, g2);

        //Servier 3 Kugeln
        Circle Sr = new Circle(150, 530, 10, g2);
        Circle Sl = new Circle(120, 540, 10, g2);
        Circle Sc = new Circle(135, 520, 10, g2);

        layer.getChildren().addAll(L1, L2, L3, L4, L5, L6, L7, L8, L9, R1, R2, R3, R4, R5, R6, R7, R8, R9, Bl, Br, Sr, Sl, Sc);

        return layer;
    }

    public void play() {
        parallelTransition.play();
    }

    public void stop() {
        parallelTransition.stop();
    }

    /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pallinoos Automat seitliche Ansicht || copyright Mourad El Bakry 2015");
        primaryStage.setScene(new Scene(initPane()));//,290,600));
        primaryStage.show();
    }
     */
}

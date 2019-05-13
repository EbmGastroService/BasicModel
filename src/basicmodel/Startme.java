package basicmodel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import BauGroupen.GroupenBinder;
import InfoSeiten.Prototype;
import InfoSeiten.myStage;
import ProtoTyp.Model;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author EbmGastroService
 */
public class Startme extends Application {
    
    Scene scene;
    myStage infostage, menustage, modstage, tabstage;
    GroupenBinder groupenBinder;
    Model mod;
    int Lager, Trommel, ModNumer;
    String ModName, ModDate;
    String[] SortenNames, mySorten;
    StackPane stp;
    double PosX, PosY;
    
    public Startme() {
        PosX = 0d;
        PosY = 250d;
        mySorten = new String[]{
            "Cardinale", "Salami", "Funghi", "Mozzera", "Formagio" //5 Sorten
            , "Spinatci", "Polo", "Hawaii" //8 Sorten
            , "Tonno", "Fruti", "Carciofi", "Pavaroti"//12 Sorten
        };
    }
    
    public void testModel(int model) {
        ModNumer = model;
        mod = new Model();
        int length = mySorten.length;
        switch (model) {
            case 1:
                length = mySorten.length;
                break;
            case 2:
                length = 8;
                break;
            case 3:
                length = 5;
                break;
            case 4:
                length = 2;
                break;
            default:
                break;
        }
        if (model == 5 || model == 7) {
            
        }
        SortenNames = new String[length];
        System.arraycopy(mySorten, 0, SortenNames, 0, length);
        setMe(ModNumer, SortenNames, 0);
    }
    
    public void setMe(int modNr, String[] newSorten, float VModelL) {
        mod = new Model();
        ModNumer = modNr;
        if (VModelL > 0f) {
            mod.setLagerL(VModelL);
        }
        mod.getModel(ModNumer);//1 bis 3
        Lager = (int) mod.getLagerBestand();
        Trommel = (int) mod.getTrommelBestand();
        ModName = mod.getModelName();
        SortenNames = newSorten;
        ModDate = mod.ModelDatenStr(SortenNames.length, ModNumer);
        PosX += 10;
        PosY += 20;
        showInfo();
    }
    
    void showInfo() {
        VBox infoBox = new VBox(5);
        Label modInfo = new Label("I n f o b l a t t\n****\n" + ModDate + "\n");        
        modInfo.setPrefWidth(220f);
        modInfo.setAlignment(Pos.TOP_CENTER);
        modInfo.setTextAlignment(TextAlignment.CENTER);
        modInfo.setId("display1");
        infoBox.setAlignment(Pos.TOP_CENTER);
        infoBox.setId("Freilauf_red");
        Button showIt = myButton(ModName + " Automat testen!");        
        showIt.setOnAction((ActionEvent event) -> {
            if (modInfo.getText().contains("Home")) {
                testModel(3);
                showIt.setDisable(true);
            }
            if (modInfo.getText().contains("Office")) {
                testModel(2);
                showIt.setDisable(true);
            }
            if (modInfo.getText().contains("Standard")) {
                testModel(1);
                showIt.setDisable(true);
            }
            if (infostage.isShowing()) {
                infostage.closeAll();
                los();
            }
            
        });
        infoBox.getChildren().addAll(modInfo, showIt);
        scene = new Scene(infoBox);
        //scene.getStylesheets().add(Startme.class.getResource("AutomatStyleSheet.css").toExternalForm());        
        infostage = new myStage(scene);
        infostage.setTitle(ModName + " Infoblat");
        infostage.setX(PosX);
        infostage.setY(PosY);
    }
    
    Tooltip Tip(String str) {
        Tooltip t = new Tooltip(str + "\nEchtgetreute Animation\nÜberzeugende Actionen\nT-Schwankung während die Pausezeit");
        //t.setId("Tooltip");
        return t;
    }
    
    Tooltip Tip(int i) {
        String[] str = new String[5];
        str[0] = "Standard Automat mit 12 Sorten und 20 Pallinoos im Kammer";
        str[1] = "Office Automat mit 8 Sorten und 12 Pallinoos im Kammer";
        str[2] = "Home Automat mit 5 Sorten und 8 Pallinoos im Kammer";
        str[3] = "Bitte Vorwahl Automat klicken! dann Hier Drucken\nsonst sehen Sie Office Automat!";
        str[4] = "Hier haben Sie Infoblätte in Tabs\nBauelementen, Skizen, und nochmehr.";
        return Tip(str[i]);
    }
    
    void menuActions() {
        stp = new StackPane();
        stp.setId("display");
        
        VBox buttonBox = new VBox(10);
        // buttonBox.setId("display");
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPrefWidth(250);
        Button mod1 = myButton("Standard Automat");
        mod1.setTooltip(Tip(0));
        mod1.setOnAction((ActionEvent event) -> {
            testModel(1);
            mod1.setDisable(true);
        });
        Button mod2 = myButton("Office Automat");
        mod2.setTooltip(Tip(1));
        mod2.setOnAction((ActionEvent event) -> {
            testModel(2);
            mod2.setDisable(true);
        });
        Button mod3 = myButton("Home Automat");
        mod3.setTooltip(Tip(2));
        mod3.setOnAction((ActionEvent event) -> {
            testModel(3);
            mod3.setDisable(true);
        });
        Button TabsPan = myButton("Show in Infoblatt");
        TabsPan.setTooltip(Tip(4));
        TabsPan.setOnAction((ActionEvent event) -> {
            creatTabs();
            TabsPan.setDisable(true);
        });
        Button ende = myButton("Beenden");
        ende.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });
        buttonBox.getChildren().addAll(mod1, mod2, mod3, TabsPan, ende);
        stp.getChildren().add(buttonBox);
        
        scene = new Scene(stp,220,220);                
        //scene.getStylesheets().add(Startme.class.getResource("AutomatStyleSheet.css").toExternalForm());
        menustage = new myStage(scene);
        menustage.setTitle("MENU-Automaten Auswahl");
    }
    
    Button myButton(String t) {
        Button B = new Button(t);
        B.setPrefSize(180, 30);        
        return B;
    }
    
    void creatTabs() {
        scene = new Scene(new Prototype().creatTabPane(), 1200, 700);
        //scene.getStylesheets().add(Startme.class.getResource("AutomatStyleSheet.css").toExternalForm());        
        tabstage = new myStage(scene);
        if (ModName == null) {
            ModName = "";
        }
        String tit = ModName + " " + tabstage.getTitle();
        tabstage.setTitle(tit);
        tabstage.setAlwaysOnTop(true);
        
    }
    
    void los() {        
        
        Group gb = new GroupenBinder(SortenNames.length, Trommel / 2, SortenNames, Lager).MainBoard();//Kammer, Reihen                           
        System.out.println("X:"+gb.computeAreaInScreen());
        if (ModName.contains("Standard")) {                    
            gb.setManaged(true);
            gb.setScaleX(gb.getScaleX()*0.85);
            gb.setScaleY(gb.getScaleY()*0.85);                        
        }
        stp = new StackPane();
        stp.getChildren().add(gb);
        scene = new Scene(stp,stp.getWidth()-20d,stp.getHeight()-20d);      
        
        modstage = new myStage(scene);
        String tit = "Demo-" + ModName + " " + modstage.getTitle();
        modstage.setTitle(tit);
        modstage.centerOnScreen();
        
    }
    
    @Override
    public void start(Stage Pstage) {
        // BaiscModel_Preloader baiscModel_Preloader = new BaiscModel_Preloader();
        menuActions();
        Pstage = menustage;
        Pstage.setX(0);
        Pstage.setY(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BauGroupen;

import ProtoTyp.Zahlformat;
import basicmodel.TempChart;
import basicmodel.tabelView;
import basicmodel.treeTableView;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author EbmGastroService
 */
public class GroupenBinder {

    private final BauGroup[] bauGroup;
    private String[] PallName;
    private final String[] SortenNames;

    private int Kammer;
    private int Reihen;
    private int Lager;
    int Verpakt, Ofen;
    double Heitzen;
    
    Label TempLabel, InfoLabel;
    HBox SorteHbox, TempStyle;
    VBox SortenVBox, test;
    BorderPane SortenMain;    
    
    tabelView GroupenDaten;
    treeTableView Tree;
    TempChart tChart;

    Double[] X, Y;

    public GroupenBinder(int Kammer, int Reihen, String[] SortenNames, int Lager) {
        this.Kammer = Kammer;
        this.Reihen = Reihen;
        this.Lager = Lager;
        Verpakt = 0;
        Heitzen = 30d;
        Ofen = 1;
        this.SortenNames = SortenNames;
        X = new Double[Kammer];
        Y = new Double[Kammer];

        bauGroup = new BauGroup[Kammer];

        SorteHbox = new HBox();
        SortenVBox = new VBox();
        SortenMain = new BorderPane();
        TempLabel = new Label();
        InfoLabel = new Label("Verpackt: 0, Backofen: 1 On, 30.0 Sek\nAufheizen: 30.0 Sek, Umluftzeit: 0.0 Sek");
        TempStyle = new HBox();
        test = new VBox();        
        MDSet();
    }

    final void MDSet() {
        for (int i = 0; i < getKammer(); i++) {
            bauGroup[i] = new BauGroup(getKammer(), getReihen());
            bauGroup[i].setDiff(120.0); //ZielTemperatur
            bauGroup[i].setTolaranz(0.15);
            bauGroup[i].setPauseZeit(3600.0d);
            bauGroup[i].Listen(100.0, 100.0);

            bauGroup[i].setSorte(SortenNames[i]);
            bauGroup[i].setLagerstand(getLager());
            
            SorteHbox.getChildren().add(bauGroup[i].cereatArtikelBauGroupe());
            TempStyle.setStyle(bauGroup[i].getTlastoneStyle());
            
            GroupenDaten = bauGroup[i].getViewMap();
            TempLabel = bauGroup[i].getTempLabel();
            X[i] = bauGroup[i].getTlastone();
            Y[i] = bauGroup[i].getPlastone();
        }
        Tree = new treeTableView(SortenNames, GroupenDaten);
        tChart = new TempChart(X, Y);
        Aufheitzen();
    }

    final void initGroup(int i) {
        bauGroup[i].UpdatBalls();
        bauGroup[i].mengenLabel.setText("0");
        bauGroup[i].setMenge(0);
        TempStyle.setStyle(bauGroup[i].getTlastoneStyle());
        GroupenDaten = bauGroup[i].getViewMap();
        TempLabel = bauGroup[i].getTempLabel();
        X[i] = bauGroup[i].getTlastone();
        Y[i] = bauGroup[i].getPlastone();
        SorteHbox.getChildren().set(i, bauGroup[i].cereatArtikelBauGroupe());
        tChart.setXY(X, Y);
        Tree.setTv(i, GroupenDaten);

    }

    void Aufheitzen() {
        double x = 0d;
        double y = 0d;
        for (int i = 0; i < bauGroup.length; i++) {
            x += X[i];
            y += Y[i];
        }
        Heitzen += (324d - x) / 10.8;
        //System.out.println("AUFHEITZEN: x:" + x + ", y: " + y + " Heitzen: " + Heitzen + " Sec.");
    }

    Button initButton(String name) {
        Button b = new Button(name);
        //b.setPrefSize(120, 30);
        b.setAlignment(Pos.CENTER);
        return b;
    }

    void showGroupPause(double pause) {
        for (int i = 0; i < bauGroup.length; i++) {
            bauGroup[i].showList(pause, "N");
            initGroup(i);
        }
    }

    int[] bestellen() {
        int[] M = new int[bauGroup.length];
        for (int i = 0; i < bauGroup.length; i++) {
            int m = Integer.parseInt(bauGroup[i].mengenLabel.getText());
            if (m > 0) {
                M[i] = m;
            } else {
                M[i] = 0;
            }
        }
        return M;
    }

    int TestMengen() {
        int[] M = bestellen();
        int mengen = 0;
        for (int i = 0; i < bauGroup.length; i++) {
            if (M[i] > 0) {
                mengen++;
            }
        }
        return mengen;
    }

    public Group MainBoard() {
        Zahlformat zf = new Zahlformat();                
        InfoLabel.setPrefWidth(280d);
        InfoLabel.setAlignment(Pos.CENTER);
        //InfoLabel.setTextAlignment(TextAlignment.CENTER);
        Button ok = initButton("Bestellen");
        ok.setOnAction((ActionEvent event) -> {
            int[] M = bestellen();
            int t = 0;
            for (int i = 0; i < bauGroup.length; i++) {
                bauGroup[i].OrderMe(M[i]);
                setVerpakt(M[i]);
            }
            if (TestMengen() > 0) {
                showGroupPause(30.0);
            }
            Ofen++;
            Aufheitzen();            
            String zeittext = "\nAufheizen: " + zf.Zahl(Heitzen, 1) + " Sek" + ", Umluftzeit: " + zf.Zahl((Ofen * 30) - Heitzen, 1) + " Sek";
            if (Heitzen > 60d) {
                zeittext = "\nAufheizen: " + zf.Zahl(Heitzen / 60d, 1) + " Min" + ", Umluftzeit: " + zf.Zahl(((Ofen * 30) - Heitzen) / 60d, 1) + " Min";
            }
            if (Heitzen > 3600d) {
                zeittext = "\nAufheizen: " + zf.Zahl(Heitzen / 3600d, 1) + " Std" + ", Umluftzeit: " + zf.Zahl(((Ofen * 30) - Heitzen) / 3600d, 1) + " Std";
            }
            InfoLabel.setText("Verpackt: " + getVerpakt() + ", Backofen: " + Ofen + " On, "+zf.Zahl((Ofen*0.5),1)+" Min" + zeittext);
        });

        Button pause30 = initButton("30 Min");
        pause30.setOnAction((ActionEvent event) -> {
            showGroupPause(1800.0);
        });
        Button pause05 = new Button("5 Min");
        pause05.setOnAction((ActionEvent event) -> {
            showGroupPause(300.0);
        });
        Button pause10 = new Button("10 Min");
        pause10.setOnAction((ActionEvent event) -> {
            showGroupPause(600.0);
        });
        Button pause15 = new Button("15 Min");
        pause15.setOnAction((ActionEvent event) -> {
            showGroupPause(900.0);
        });

        Button auto = initButton("Auto-Menge");
        auto.setOnAction((ActionEvent event) -> {
            //Bestellmenge sollte für die Verpackung in etwa 3 bis 5 Stück sein.
            Random rwahl = new Random();
            for (int i = 0; i < 5; i++) {
                int randomInt = rwahl.nextInt(bauGroup.length);
                bauGroup[randomInt].mengenLabel.setStyle(" -fx-text-fill:green;-fx-font-size:13px;-fx-font-weight:bold;"
                        + "-fx-background-color:linear-gradient(red,yellow,green);");
                bauGroup[randomInt].ActionButton().fire();
            }
        });
        Button autoPause = initButton("Auto-Pause");
        autoPause.setOnAction((ActionEvent event) -> {
            if (TestMengen() == 0) {
                Random rwahl = new Random();
                int randomInt = rwahl.nextInt(4);
                if (randomInt == 0) {
                    pause05.fire();
                }
                if (randomInt == 1) {
                    pause10.fire();
                }
                if (randomInt == 2) {
                    pause15.fire();
                }
                if (randomInt == 3) {
                    pause30.fire();
                }
                System.out.println("Random:" + randomInt);
            } else {
                InfoLabel.setText("Bitte auf Bestellen drucken!!");
            }
        });

        TempStyle.setPrefWidth(15);
        TempStyle.setId("hbox");

        test.setPrefWidth(470);        
       // test.setId("display");        
        test.getChildren().addAll(tChart.getRoot(), Tree.getRoot());        

        HBox btns = new HBox(10);
        btns.setAlignment(Pos.CENTER);
        HBox PauseBtns = new HBox(5);
        PauseBtns.getChildren().addAll(autoPause, pause30, pause10);
        PauseBtns.setAlignment(Pos.CENTER);

        btns.getChildren().addAll(getTlast(), ok, auto, PauseBtns, InfoLabel);

        BorderPane bp = new BorderPane();
        bp.setId("auswertung");
        bp.setPrefHeight(60);
        bp.setCenter(btns);        

        SortenMain.setId("vbox");
        SortenMain.setLeft(TempStyle);
        SortenMain.setRight(test);
        SortenMain.setCenter(SorteHbox);        

        SortenMain.setBottom(bp);
        SortenMain.setMaxHeight(SorteHbox.getMaxHeight() + 65);
        SortenMain.setMaxWidth(SorteHbox.getMaxWidth() + 470);
        Group g = new Group(SortenMain);
        return g;//SortenMain;
    }

    /**
     * @return the PallName
     */
    public String[] getPallName() {
        return PallName;
    }

    /**
     * @param PallName the PallName to set
     */
    public void setPallName(String[] PallName) {
        this.PallName = PallName;
    }

    /**
     * @return the Kammer
     */
    public int getKammer() {
        return Kammer;
    }

    /**
     * @param Kammer the Kammer to set
     */
    public void setKammer(int Kammer) {
        this.Kammer = Kammer;
    }

    /**
     * @return the Reihen
     */
    public int getReihen() {
        return Reihen;
    }

    /**
     * @param Reihen the Reihen to set
     */
    public void setReihen(int Reihen) {
        this.Reihen = Reihen;
    }

    private Label getTlast() {
        return TempLabel;
    }

    /**
     * @return the Lager
     */
    public int getLager() {
        return Lager;
    }

    /**
     * @param Lager the Lager to set
     */
    public void setLager(int Lager) {
        this.Lager = Lager;
    }

    private String[] getSortenNames() {
        return SortenNames;
    }

    /**
     * @return the Verpakt
     */
    public int getVerpakt() {
        return Verpakt;
    }

    /**
     * @param Verpakt the Verpakt to set
     */
    public void setVerpakt(int Verpakt) {
        this.Verpakt += Verpakt;
    }

}

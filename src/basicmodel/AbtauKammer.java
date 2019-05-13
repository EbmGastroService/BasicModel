/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import static java.lang.Math.random;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author EbmGastroService
 */
public class AbtauKammer {
    int Spalte;
    int Zeile;    
    int X;
    int boball;
    creatRect cr; 
    creatBalls cb;
    Animation ani;
    
    
    Color[] TcL, TcR;
    double[]PtempL,PtempR;
    double BpTemp;
    String PallName;
    Label slabel, mlabel;
    Group Boballs, eBallsO, eBallsU,akBalls,kammer;
    BorderPane root;
    int menge,mlager,Alager;
    String SorteName;
    
    public AbtauKammer(int Spalte,double[]PTL,double[]PTR,double OPT,String PallName){
        this.Spalte=Spalte;
        this.Zeile=PTL.length;        
        this.PtempL=PTL;
        this.PtempR=PTR;
        this.BpTemp=OPT;
        this.PallName = PallName;
        TcL = new TColor().ColorSkaler(PtempL);
        TcR = new TColor().ColorSkaler(PtempR);
        
        Boballs = new Group();
        eBallsO = new Group();
        eBallsU = new Group();
        akBalls = new Group();
        kammer = new Group();
        boball = 0;
        menge = 0;
        Alager=100;
        mlager=0;
        SorteName="Salami";
        cr = new creatRect();
        cb =new creatBalls(Spalte,PtempL,PtempR,OPT);
        ani = new Animation(Spalte,Zeile,PallName);
        root = new BorderPane();
        mlabel = new Label(" " + mlager + "P. " + (100 * mlager / Alager) + "%");
        slabel = new Label("Pallinoos\n" + menge);
        

    }
    void startPane() {        
        ani.theway();
        creatKammer();//        
        ani.getPathOben(eBallsO);
        ani.getPathUnten(eBallsU);
    }
    
    void creatKammer() {
        Group lagerBalls = new Group();
        kammer = new Group();

        Rectangle lager, E1, E2, Bo;
        lager = cr.creatRect((2 * Spalte) + (Spalte/2)-1, Spalte + 4, Color.BURLYWOOD);
        lager.setX(0);
        lager.setY(0);

        lagerBalls.getChildren().addAll(lager, cb.creatBalls(18, 15, 3, "H"));

        E1 = cr.creatRect(Spalte + 4, Spalte, Color.BLUEVIOLET);
        E1.setX(18);
        E1.setY(Spalte + 4);

        E2 = cr.creatRect(Spalte + 4, Spalte, Color.BROWN);
        E2.setX(18);
        E2.setY(((Zeile + 2) * Spalte) + 8);

        eBallsO.getChildren().add(cb.creatBalls(Spalte + 4, Spalte + 15, 1, "H"));
        eBallsU.getChildren().add(cb.creatBalls(Spalte + 4, (((Zeile + 2) * Spalte) + 18), 1, "H"));
        if (boball > 0) {
            double posYball = random() * (2 * Spalte);
            double posXball = (X + (boball * 1)) + random() * (1 * Spalte);
            if (posYball < 23) {
                posYball = 23;
            }
            if (posYball > 50) {
                posYball = 50;
            }
            if (posXball > 50) {
                posXball = 50;
            }
            addBalls(cb.creatBalls((int) posXball, (((Zeile + 3) * Spalte) + (int) posYball), 1, "H"));//
        }

        Bo = cr.creatRect((2 * Spalte) + 14, Spalte * 2, Color.DARKRED);
        Bo.setX(0);
        Bo.setY(((Zeile + 3) * Spalte) + 8);
        //Boballs.setVisible(false);
        //                                                1    ,     2        ,3 ,  4 , 5  ,6 ,           7             8 ,     9            ,10,11,12,           13         ,14,         15
        //kammer.getChildren().addAll(lager,lagerText,Ak, E1,B1,d1,Einlasser1Text,zR,KammerText,E2,B2, d2, Einlasser2Text,Bo,BackofenText);
        //                                                  1           2       3                             4     5       6           7           8  
        //kammer.getChildren().clear();
        kammer.getChildren().addAll(lagerBalls, E1, creatAufTaukammer(), E2, Bo, eBallsO, eBallsU, Boballs);

    }
    void addBalls(Group kugel) {
        Boballs.getChildren().add(kugel);

    }

    public void clear() {
        Boballs.getChildren().clear();
        //menge = 0;
        boball = 0;
    }
    Group creatAufTaukammer() {
        Group akBallsL, akBallsR;
        //akBalls.getChildren().clear();        
        

        Rectangle Ak, zR;

        Ak = cr.creatRect((Spalte * 2) + 14, (Zeile * Spalte) + 4, Color.GRAY);
        Ak.setX(0);
        Ak.setY(4 + (2 * Spalte));

        akBallsL = cb.creatBalls(17, 6 + (2 * Spalte) - 15, Zeile, "V");
        akBallsR = cb.creatBalls(55, 6 + (2 * Spalte) - 15, Zeile, "V");

        zR = cr.creatRect(8, ((Zeile - 2) * Spalte), Color.BLACK);
        zR.setX(Spalte + 2);
        zR.setY(7 + (3 * Spalte));

        ani.AniBalls(akBallsL, akBallsR);
        akBalls.getChildren().addAll(Ak, akBallsL, zR, akBallsR);
        return akBalls;
    }    
    public BorderPane action() {
        root.setStyle("-fx-background-color:linear-gradient(black,lightgray,black);");
        VBox vb = new VBox(5);
        slabel.setId("Sortenmengeleer");
        slabel.setAlignment(Pos.CENTER);
        slabel.setTextAlignment(TextAlignment.CENTER);
        mlabel.setAlignment(Pos.CENTER);
        root.setTop(mlabel);
        Button btn = new Button(SorteName);
        btn.setOnAction((ActionEvent event) -> {
            if (mlager > 0) {
                clear();
                boball = 0;
                mlager--;
                if (mlager > -1) {
                    menge++;
                    slabel.setId("Sortenmengevoll");
                    slabel.setText("Pallinoos\n " + menge);
                } else {
                    menge = 0;
                    RefrishMenge();
                }
            }

        });
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color:linear-gradient(gold,black,gold,black);");
        vb.getChildren().addAll(slabel, btn);
        //kammer=new emptyKammer(30,PtempL,PtempR,BpTemp).leereKammer();
        creatKammer();
        root.setCenter(kammer);
        //root.setCache(true);  
        //root.setCacheHint(CacheHint.SPEED);

        root.setBottom(vb);
        return root;
    }
    public void RefrishMenge() {
        slabel.setText("Pallinoos\n0");
        mlabel.setText(" " + mlager + "P. " + (100 * mlager / Alager) + "%");
    }



    
}

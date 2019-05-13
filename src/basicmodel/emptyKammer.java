/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import ProtoTyp.Zahlformat;
import static java.lang.Math.random;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author EbmGastroService
 */
public class emptyKammer {

    int Spalte;
    int Zeile;
    Color[] TcL, TcR;
    double[] PtempL, PtempR;
    double BpTemp;
    TColor Tc;
    creatRect cr;
    creatEinlasserForm einf;
    myT mt;
    creatColor cc;

    public emptyKammer() {
        this.Spalte = 40;
        this.Zeile = 12;
        this.PtempL = new double[]{-18d, -18d, -18d, -18d, -18d, -18d, -18d, -18d, -18d, -18d};
        this.PtempR = new double[]{-18d, -18d, -18d, -18d, -18d, -18d, -18d, -18d, -18d, -18d};
        this.BpTemp = -17d;
        Tc = new TColor();
        TcL = Tc.ColorSkaler(PtempL);
        TcR = Tc.ColorSkaler(PtempR);
        mt = new myT();
        cr = new creatRect();
        einf = new creatEinlasserForm();
        cc = new creatColor();
    }

    public emptyKammer(int Spalte, double[] PTL, double[] PTR, double P_lastone) {
        this.Spalte = Spalte;
        this.Zeile = PTL.length;
        this.PtempL = PTL;
        this.PtempR = PTR;
        this.BpTemp = P_lastone;
        Tc = new TColor();
        TcL = Tc.ColorSkaler(PtempL);
        TcR = Tc.ColorSkaler(PtempR);
        mt = new myT();
        cr = new creatRect();
        einf = new creatEinlasserForm();
        cc = new creatColor();
    }
    public HBox getMe(){
        HBox hb=new HBox();        
        hb.setStyle("-fx-border-color:black;"
                + "-fx-background-color:linear-gradient(to right,black,white, darkgrey,white);");
        //hb.setPrefWidth(2*Spalte);
        hb.getChildren().add(leereKammer());
        return hb;
    }

    public Group leereKammer() {
        Group kammer = new Group();

        Rectangle lager, E1, E2, Bo;
        Ellipse Ak, zR;

        int BallRadius = Spalte / 2;
        int FreiRaum = 4;
        int KammerWidth = (2 * Spalte) + 15;
        int KammerHeight = (Zeile * Spalte) + FreiRaum;
        int LagerHeight = Spalte + FreiRaum;
        int BackofenHeight = 2 * Spalte;
        int Einlasser = Spalte + FreiRaum;
        int EinlasserHeight = Spalte;
        int BallCenterX = BallRadius + (FreiRaum / 2);
        int LagerEinlasserCenterY = Einlasser + BallRadius;
        int BackofenEinlasserY = ((Zeile + 2) * Spalte) + (2 * FreiRaum);
        int BackofenEinlasserCenterY = BackofenEinlasserY + BallRadius;

        lager = cr.creatRect(KammerWidth, LagerHeight, Color.BURLYWOOD);
        lager.setX(0);
        lager.setY(0);
        Text lagerText = mt.myT("Gefrier\nLager");
        lagerText.setX(10f);
        lagerText.setY(BallRadius);
        //Einlasserrahmen vom Lager zum Auftaukammer
        E1 = cr.creatRect(Einlasser, EinlasserHeight, Color.BLUEVIOLET);
        E1.setX(3 + Spalte / 2);
        E1.setY(Einlasser);
        //EinlasserForm vom Lager zum Kammer
        Arc d1 = einf.creatEinlasserForm(LagerEinlasserCenterY);//new Arc((18 + 17), (Spalte + 4 + 15), 15, 15, 145, 225);

        Group B1 = creatBalls(20 + BallCenterX, Spalte + 15, 1, "H");
        //Auftaukammer mit 2Spaltenbereite und zwischenRaum von 14
        Ak = einf.creatEllipse(KammerWidth / 2, (KammerHeight + BallRadius) / 2, 1);
        //Ak.setLayoutX(0);        Ak.setLayoutY(4 + (2 * Spalte)/2);
        Ak.setCenterX(KammerWidth / 2);
        Ak.setCenterY((KammerHeight + LagerHeight + Einlasser + EinlasserHeight + Spalte) / 2);//4 + (2 * Spalte)));        
        //Zwischenraum
        zR = einf.creatEllipse(8, ((Zeile - 3) * Spalte) / 2, 0);
        //zR.setLayoutX(Spalte + 2);        zR.setLayoutY(7 + (3 * Spalte));
        zR.setCenterX(8 + KammerWidth / 2);
        zR.setCenterY((KammerHeight + LagerHeight + Einlasser + EinlasserHeight + Spalte) / 2);
        Text KammerText = mt.myT("Auftau\nKammer");
        KammerText.setX(10f);
        KammerText.setY((Math.random() * Zeile) + zR.getCenterY());
        KammerText.setRotate(random() * 45f);
        //Einlasserrahmen vom Kammer zum Backofen
        E2 = cr.creatRect(Einlasser, Spalte, Color.BROWN);
        E2.setX(3 + Spalte / 2);
        E2.setY(BackofenEinlasserY);

        Text Einlasser1Text = mt.myT("Kammer\nEinlasser");
        Einlasser1Text.setX(10d);
        Einlasser1Text.setY(LagerHeight + Einlasser);

        Text Einlasser2Text = mt.myT("Ofen\nEinlasser");
        Einlasser2Text.setX(10d);
        Einlasser2Text.setY(((Zeile + 2) * Spalte));

        //EinlasserForm vom Kammer zum Backofen centerY=Mite der EinlasserRahmen E2
        Arc d2 = einf.creatEinlasserForm(BackofenEinlasserCenterY);//new Arc((18 + 17), ((Zeile + 2) * Spalte) + 8 + 15, 15, 15, 145, 225);

        Group B2 = creatBalls(22 + BallCenterX, BackofenEinlasserCenterY-1, 1, "H");

        Bo = cr.creatRect(KammerWidth, BackofenHeight, Color.DARKRED);
        Bo.setX(0);
        Bo.setY(((Zeile + 3) * Spalte) + 8);
        Text BackofenText = mt.myT("Backofen");
        BackofenText.setX(10f);
        BackofenText.setY(((Zeile + 4) * Spalte) + BallRadius);
        kammer.getChildren().addAll(lager, lagerText, Ak, E1, B1, d1, Einlasser1Text, zR, KammerText, E2, B2, d2, Einlasser2Text, Bo, BackofenText);
        //kammer.getChildren().addAll(Ak,zR, lager, E1, B1, d1, E2, B2, d2, Bo,lagerText,KammerText,BackofenText,Einlasser1Text,Einlasser2Text);        
        return kammer;

    }

    Group creatBalls(int x, int y, int m, String str) { //xpos,ypos,m=Anzahl Balls, Str=Horizental od Vertical
        Group Balls = new Group();
        String strn = "";
        Color co = null;
        Text tl = new Text();
        Circle c;
        int tx;
        for (int i = 1; i <= m; i++) {
            if ("H".equals(str)) {
                if (y < Spalte * 2) {
                    strn = "-18°C";
                    co = Color.BLUE;
                    tl = mt.myT(strn);
                } else {
                    strn = "" + new Zahlformat().Zahl(BpTemp);
                    co = Color.DARKRED;
                    tl = mt.myT(strn);
                }
                if (strn.length() < 3) {
                    tx = 4;
                } else if ("-18°C".equals(strn)) {
                    tx = 16;
                } else {
                    tx = 10;
                }
                Group Ball = new Group();
                tl.setX((i * x) - tx);
                tl.setY(y + 4);
                c = new Circle((i * x), y, (Spalte / 2), cc.creatColor(45, 50, 55, co, Color.web("whitesmoke")));
                Ball.getChildren().addAll(c, tl);
                Balls.getChildren().add(Ball);
            }
            if ("V".equals(str)) {
                if (x == 17) {
                    strn = "" + new Zahlformat().Zahl(PtempL[i - 1]);
                    co = TcL[i - 1];
                    tl = mt.myT(strn);
                } else if (x == 55) {
                    strn = "" + new Zahlformat().Zahl(PtempR[i - 1]);
                    co = TcR[i - 1];
                    tl = mt.myT(strn);
                }
                Group Ball = new Group();
                if (strn.length() < 3) {
                    tx = strn.length() / 2;//4;
                } else {
                    tx = 13;
                }
                tl.setX(x - tx);
                tl.setY((y + 2) + (i * Spalte));
                c = new Circle(x, y + (i * Spalte), (Spalte / 2), cc.creatColor(55, 50, 35, co, Color.web("whitesmoke")));
                Ball.getChildren().addAll(c, tl);
                Balls.getChildren().add(Ball);
                //            Balls.setCacheHint(CacheHint.SCALE);
            }
        }
        return Balls;
    }

}

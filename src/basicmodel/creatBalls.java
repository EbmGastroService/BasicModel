/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import ProtoTyp.Zahlformat;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author EbmGastroService
 */
public class creatBalls {
    myT mt;
    int Spalte;
    int Zeile;    
    Color[] TcL, TcR;
    double[]PtempL,PtempR;
    double BpTemp;
    TColor Tc;
    public creatBalls(int Spalte,double[]PtempL,double[]PtempR,double P_Tlastone){
        this.PtempL = PtempL;
        this.PtempR = PtempR;
        this.BpTemp = P_Tlastone;
        this.Spalte = Spalte;
        this.Zeile = PtempL.length;
        Tc = new TColor();
        TcL = Tc.ColorSkaler(PtempL);
        TcR = Tc.ColorSkaler(PtempR);
        
        mt=new myT();
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
                c = new Circle((i * x), y, (Spalte / 2), new creatColor().creatColor(45, 50, 55, co, Color.web("whitesmoke")));
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
                c = new Circle(x, y + (i * Spalte), (Spalte / 2), new creatColor().creatColor(55, 50, 35, co, Color.web("whitesmoke")));
                Ball.getChildren().addAll(c, tl);
                Balls.getChildren().add(Ball);
    //            Balls.setCacheHint(CacheHint.SCALE);
            }
        }
        return Balls;
    }
    
}

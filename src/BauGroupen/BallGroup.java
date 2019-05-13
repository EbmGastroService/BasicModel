package BauGroupen;

import ProtoTyp.Zahlformat;

import static BauGroupen.ArtikelBaugroppe.heightEinheit;
import static BauGroupen.ArtikelBaugroppe.width;
import basicmodel.TColor;
import basicmodel.creatColor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
public class BallGroup {

    int BallReihen;

    BallGroup(int reihen) {
        BallReihen = reihen;
    }

    public Group creatBalls(String Richtung) {
        double einheit = heightEinheit / 2;
        double X = einheit;
        if (Richtung.equals("R")) {
            X = width - einheit;
        }
        Group BallGroup = new Group();
        for (int i = 0; i < BallReihen; i++) {
            String str = "" + (i * 10 + 50 + "°");
            Circle c = new Circle(X, einheit + (i * heightEinheit), einheit);
            c.setFill(RadialGradient.valueOf("radial-gradient(radius 100%, blue, darkred ,orange)"));
            BallGroup.getChildren().addAll(creatText(str, "", c, 0.0));
        }
        return BallGroup;
    }

    public Group creatBalls(String Richtung, String[] PN, double[] PT) {
        Zahlformat zf = new Zahlformat();
        double einheit = heightEinheit / 2;
        double X = einheit;
        if (Richtung.equals("R")) {
            X = width - einheit;
        }
        Group BallGroup = new Group();
        for (int i = 0; i < BallReihen; i++) {
            String str = " " + zf.Zahl(PT[i], 2) + "°";
            String str1 = "  " + PN[i];
            Circle c = new Circle(X, einheit + (i * heightEinheit), einheit);
            c.setFill(RadialGradient.valueOf("radial-gradient(radius 100%, blue, darkred ,orange)"));
            Color co = new TColor().ColorSkaler(PT[i]);
            c.setFill(new creatColor().creatColor(35, 60, 25, co, Color.web("whitesmoke")));
            BallGroup.getChildren().addAll(creatText(str, str1, c, 5.0));            
        }
        return BallGroup;
    }

    Group creatText(String str, String str1, Circle c, double pos) {
        Group Ball = new Group();
        int leng = str.length() + 2;
        Text t = new Text(str);
        Text t1 = new Text(str1);
        t.setId("ballText");
        t1.setId("ballText");
        t.setCache(true);
        double x = c.getCenterX() - (2 * leng);
        t.setX(x);
        t1.setX(x + 2.0);
        t.setY(c.getCenterY() - pos);// 4);
        t1.setY(c.getCenterY() + (pos + 3.0));// 4);
        c.setSmooth(true);
        Ball.getChildren().addAll(c, t, t1);
        return Ball;

    }

}

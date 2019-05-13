package ProtoTyp;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
public class Skize {
    /**
     * Lagerfach mit Einlasse oder ohne
     * @param Bereit
     * @param Leng
     * @param mit
     * @return 
     */

    public Group creatBox(double Bereit, double Leng, int mit) {
        Rectangle r = new Rectangle(Bereit * 2, Leng * 2, LinearGradient.valueOf("linear-gradient(to bottom,black 5%,white 75%,black)"));

        r.setStroke(Color.web("white", 0.7f));
        r.setStrokeType(StrokeType.OUTSIDE);
        r.setStrokeWidth(0.3f);
        r.setArcWidth(20);
        r.setArcHeight(10);
        Rectangle e = new Rectangle(10, 10, LinearGradient.valueOf("linear-gradient(to left,black,white,black)"));
        e.setX(Bereit - 5);
        e.setY(Leng * 2);
        Group lager = new Group();
        if (mit > 0) {
            lager.getChildren().addAll(r, e);
        } else {
            lager.getChildren().addAll(r);
        }
        return lager;
    }
    /**
     * Demonstriert eine Reifenförmige AuftauKammer
     * @param Rad_Out Außenradius
     * @param Rad_In Innenradius des Reifens
     * @return 
     */

    public Group creatKammer(double Rad_Out, double Rad_In) {
        Circle c1 = new Circle(Rad_Out, RadialGradient.valueOf("radial-gradient(radius 100%,black,white,black)"));
        c1.setStroke(Color.ANTIQUEWHITE);
        c1.setStrokeWidth(0.5f);

        Circle c2 = new Circle(Rad_In, Color.ALICEBLUE);
        c2.setStroke(Color.ANTIQUEWHITE);
        c2.setStrokeType(StrokeType.INSIDE);
        c2.setStrokeWidth(0.5f);
        Rectangle e = new Rectangle(10, 10, LinearGradient.valueOf("linear-gradient(to left,black,white,black)"));
        e.setX(c1.getCenterX() - 5);
        e.setY(Rad_Out);

        Group Kammer = new Group();
        Kammer.getChildren().addAll(c1, c2, e);
        return Kammer;
    }
    /**
     * 
     * @param Rad_Out für Ovalförmige mit Außendrchmesser
     * @param mit mit Einlasser oder nicht
     * @return 
     */

    public Group creatOfen(double Rad_Out, int mit) {
        Ellipse c1 = new Ellipse(Rad_Out, Rad_Out * 0.6);
        c1.setFill(RadialGradient.valueOf("radial-gradient(radius 100%,black,white,black)"));
        c1.setStroke(Color.ANTIQUEWHITE);
        c1.setStrokeWidth(0.7f);
        Rectangle e = new Rectangle(10, 10, LinearGradient.valueOf("linear-gradient(to left,black,white,black)"));
        e.setX(c1.getCenterX() - 5);
        e.setY(Rad_Out * 0.6);

        Group Kammer = new Group();
        if (mit > 0) {
            Kammer.getChildren().addAll(c1, e);
        } else {
            Kammer.getChildren().addAll(c1);
        }
        return Kammer;
    }

}

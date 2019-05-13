package BauGroupen;

import static BauGroupen.ArtikelBaugroppe.heightEinheit;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import static javafx.util.Duration.seconds;


/**
 *Einlasser <code>demonistriert wie ein Pallinoos zum nächste Kammer übertragen wird.</code>
 * Es dreht sich zwischen die Kammers oben und unten sodass es von Gefrierlager zum Auftauenkammer und von Auftauenkammer zum Backofen am selben gang geschehen wird.
 * Es bedingt eine Richtungwechsel mal Linksdrehung und am nächsten Rechtsdrehung.
 * d1 ist die Einlasserform, Ball ist ein Pallinoos und ang ist die Drehrichtung
 * ang wird mit dem t mal in Minus- oder in Plusdrehung.
 * 
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
public class Einlasser {

    Arc d1;
    Group einGroup;
    Circle Ball;
    Timeline drehen;
    int ang, t;
    public Einlasser(int ang){
        this.ang = ang;
        start();
    }

    final void start() {
        //ang = 1;
        creatBall();
        EinlasserForm();
    }

    void EinlasserForm() {
        double einheit = heightEinheit / 2;
        d1 = new Arc(einheit, einheit, einheit, einheit, 145, 275);
        d1.setStroke(Color.web("red", 0.5f));
        d1.setType(ArcType.ROUND);
        d1.setFill(RadialGradient.valueOf("radial-gradient(radius 100%,black,darkgrey,white,blue)"));
        einGroup = new Group();
        einGroup.getChildren().addAll(Ball, d1);
    }

    void Ani() {
        t = ang;        
        drehen = new Timeline();
        drehen.getKeyFrames().addAll(
                new KeyFrame(seconds(0),
                        new KeyValue(d1.rotateProperty(), 15 * ang)
                ),
                new KeyFrame(seconds(1),
                        new KeyValue(d1.rotateProperty(), 135 * ang),
                        new KeyValue(Ball.fillProperty(), null)
                ),
                new KeyFrame(seconds(2),
                        new KeyValue(d1.rotateProperty(), 180 * ang)
                ),
                new KeyFrame(seconds(3),
                        new KeyValue(d1.rotateProperty(), 335 * ang),
                        new KeyValue(Ball.fillProperty(), RadialGradient.valueOf("radial-gradient(radius 100%, red,black,orange)"))
                )
        );
        //drehen.setAutoReverse(true);
        drehen.setCycleCount(1);//Animation.INDEFINITE);                                
    }

    void creatBall() {
        double einheit = heightEinheit / 2;
        Ball = new Circle(einheit, einheit, einheit);
        Ball.setFill(RadialGradient.valueOf("radial-gradient(radius 100%, red,black,orange)"));
    }

    void play() {
        Ani();
        drehen.play();        
        ang = -t;
    }

}

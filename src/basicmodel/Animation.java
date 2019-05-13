/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import static java.lang.Math.random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import static javafx.util.Duration.seconds;

/**
 *
 * @author EbmGastroService
 */
public class Animation {

    int Spalte;
    int Zeile;
    String PallName;

    public Animation(int Spalte, int Zeile, String PallName) {
        this.Spalte = Spalte;
        this.Zeile = Zeile;
        this.PallName = PallName;
        System.out.println("PallName: " + PallName);
    }

    int theway() {
        int l = new TheWay(PallName).getSpalte();
        System.out.println("PallName: " + PallName + "," + l);
        return l;
    }

    //Einlaser Bewegung
    void getPathOben(Group eBallsO) {

        double einX = Spalte + 4;//34
        double posY = 2 * Spalte;
        double posX = Spalte;
        if (theway() > (Spalte / 2)) {
            posX = Spalte + 10;//Richtung Rechts bei 40
        }
        Path pO = new Path(new MoveTo(posX, posY - 2), new CubicCurveTo(einX, posY, einX, (posY + 23), theway(), posY + 23));
        AniP(eBallsO, pO).play();
    }

    void getPathUnten(Group eBallsU) {

        double einX = Spalte + 4;//34
        double posY = ((Zeile + 1) * Spalte);
        double posX = Spalte;
        if (theway() > (Spalte / 2)) {
            posX = Spalte + 10;//Richtung Rechts bei 40
        }
        Path pU = new Path(new MoveTo(posX, posY + (Spalte * 1.5)),
                new CubicCurveTo(einX, posY + (Spalte * 1.5),
                        einX, posY + (3 * Spalte), posX, posY + (3 * Spalte)));
        AniP(eBallsU, pU).play();
    }

    PathTransition AniP(Group c, Path path) {
        //Path path = new Path(new MoveTo(x, y), new CubicCurveTo(x, y + 25, x, y + 65, x+18,y+65));        
        PathTransition pathTransition = new PathTransition(seconds(2), path, c);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(2);//Timeline.INDEFINITE);//3
        pathTransition.setAutoReverse(true);
        return pathTransition;
    }

    void AniBalls(Group layerL, Group layerR) {
        if (theway() == (Spalte / 2)) {
            Ani(layerL);
        } else {
            Ani(layerR);
        }
    }

    Timeline Ani(Group layer) {
        Timeline timeline = new Timeline();
        layer.getChildren().stream().forEach((Circle) -> {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(seconds(0),
                            new KeyValue(Circle.rotateProperty(), random() * 45)
                    ),
                    new KeyFrame(seconds(3),
                            new KeyValue(Circle.rotateProperty(), random() * 90)
                    )
            );
        });
        timeline.setAutoReverse(false);
        timeline.setCycleCount(1);//Animation.INDEFINITE);//3
        timeline.play();

        return timeline;
    }

}

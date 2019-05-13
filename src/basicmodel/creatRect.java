/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author EbmGastroService
 */
public class creatRect {
    Rectangle creatRect(int x, int y, Color c) {
        LinearGradient lg = LinearGradient.valueOf("linear-gradient(to right, black,white," + c + ",lightgrey,black)");
        LinearGradient lgbo = LinearGradient.valueOf("linear-gradient(to bottom, black,white,darkred,lightgrey,black)");
        Rectangle Ra;
        if (c == Color.DARKRED) {
            Ra = new Rectangle(x, y, lgbo);
        } else {
            Ra = new Rectangle(x, y, lg);
        }
        Ra.setStroke(Color.web("white", 0.5f));
        Ra.setStrokeType(StrokeType.OUTSIDE);
        Ra.setStrokeWidth(0.1f);
        Ra.setArcHeight(10);
        Ra.setArcWidth(10);
        return Ra;
    }
}

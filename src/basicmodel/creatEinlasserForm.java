/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author EbmGastroService
 */
public class creatEinlasserForm {
    
    Arc creatEinlasserForm(double centerY) {
        //double centerX, double centerY, double radiusX, double radiusY, double startAngle, double lengt
        //Da E1 posX=18 und der Ballradius =15, wird der BallCenterX um 2 erhöht, daher beim x +=17  =35
        Arc d1 = new Arc((20 + 22), centerY, 20, 20, 145, 225);
        d1.setType(ArcType.ROUND);
        d1.setFill(RadialGradient.valueOf("radial-gradient(radius 100%, black, yellow,white ,black)"));
        return d1;
    }
    Ellipse creatEllipse(int x, int y, int c) {
        RadialGradient rg = RadialGradient.valueOf("radial-gradient(focus-angle 45grad, focus-distance 25%, center 55% 45%,"
                + " radius 55%, reflect, black,dimgrey 55%, lightgrey,grey)");
        RadialGradient rg1 = RadialGradient.valueOf("radial-gradient(focus-angle 45grad, focus-distance 20%, center 55% 45%,"
                + " radius 55%, reflect, black 55%,dimgrey,lightgrey 20%,grey)");
        Ellipse e = new Ellipse(x, y);
        if (c > 0) {
            e.setFill(rg);
        } else {
            e.setFill(rg1);
        }

        e.setStroke(Color.web("white", 0.5f));
        e.setStrokeType(StrokeType.OUTSIDE);
        e.setStrokeWidth(0.2f);
        return e;
    }
    
}

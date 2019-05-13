/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;

/**
 *
 * @author EbmGastroService
 */
public class creatColor {
        public RadialGradient creatColor(int p, int p1, int p2, Color c, Color c1) {
        return RadialGradient.valueOf("radial-gradient(focus-angle 45grad, focus-distance 20%, center " + p
                + "% " + (100 - p) + "%, radius " + p1 + "%, reflect," + c1 + ", " + c + " " + p2 + "%, orange)");
    }

    
}

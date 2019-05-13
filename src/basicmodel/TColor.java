package basicmodel;

import javafx.scene.paint.Color;

/**
 *ColorSkaler<code> bestimmt der optische Temperaturveränderung</code>
 * Wenn es gefroren ist dann cyan bis grün, sonst immer dunkler bis es Braun wird
 * 
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
public class TColor {

    public Color ColorSkaler(double tp) {
        Color Tc = Color.ALICEBLUE;
        if (tp > -20.5 && tp < -17.5) {
            Tc = Color.rgb(0, 122, 206);            
            Tc = Tc.interpolate(Color.BLUE, 0.5);
        }
        if (tp >= -17.5 && tp < -15.5) {
            Tc = Color.WHITE;
            Tc = Tc.interpolate(Color.BLUE, 0.5);
        }
        if (tp >= -15.5 && tp < -10.5) {
            Tc = Color.YELLOW;
            Tc = Tc.interpolate(Color.CYAN, 0.7);
        }
        if (tp >= -10.5 && tp < 0) {
            Tc = Color.BISQUE;
            Tc = Tc.interpolate(Color.GREEN, 0.5);
        }
        if (tp >= 0 && tp < 20) {
            Tc = Color.CORAL;
            Tc = Tc.interpolate(Color.CHOCOLATE, 0.2);
        }
        if (tp >= 20 && tp < 50) {
            Tc = Color.RED;
            Tc = Tc.interpolate(Color.CHOCOLATE, 0.7);
        }
        if (tp >= 50 && tp < 70) {
            Tc = Color.rgb(197, 136, 0);
        }
        if (tp >= 70) {
            Tc = Color.rgb(139, 0, 0, 1);
        }
        //Tc.darker();
        
        
        return Tc;
    }

    public Color[] ColorSkaler(double[] tp) {
        Color[] TempColor = new Color[tp.length];
        for (int m = 0; m < tp.length; m++) {
            TempColor[m] = ColorSkaler(tp[m]);           
        }
        return TempColor;
    }

    public Color[] ColorWeb(Color[] c) {
        for (int i = 0; i < c.length; i++) {
            c[i] = Color.web(c[i].toString());
        }
        return c;
    }

}

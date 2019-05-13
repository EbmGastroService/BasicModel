package ProtoTyp;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
public class Zahlformat { 

    public synchronized String Zahl(Number amount) {
        DecimalFormat nf = new DecimalFormat();        
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);
        return nf.format(amount);
    }  
    public synchronized String Zahl(double amount, int fraction) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(fraction);
        df.setMinimumFractionDigits(fraction);
        return df.format(amount);
    }
    /**
     * 
     * @param amount
     * @param fraction
     * @return 
     */

    public synchronized String f(Number amount, int fraction) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(fraction);
        df.setMinimumFractionDigits(fraction);
        return df.format(amount);
    }

}

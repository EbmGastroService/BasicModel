/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import javafx.scene.text.Text;

/**
 *
 * @author EbmGastroService
 */
public class myT {
     Text myT(String strn) {
        Text tl = new Text(strn);
        tl.setId("infoText");
        //tl.setCache(true);        
        return tl;
    }
    
}

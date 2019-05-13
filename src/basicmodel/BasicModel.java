/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

/**
 *
 * @author EbmGastroService
 */
public abstract class BasicModel {

    public void showMe() {       
        
        ModelDriver ml = new ModelDriver();
        ml.setDiff(120.0); //ZielTemperatur
        ml.setTolaranz(0.15);
        ml.setAutomat( 5,5 );//kammer, Reihen      
        
        
       // ml.readlisten(80.0d);
        ml.setPauseZeit(3600d);
        ml.creatListen();
        //ml.readlisten(10.0d);
        System.out.println("Zeit:"+ml.getPauseZeit()+" Diff:"+ml.getDiff());
        ml.subllist(10d, 53d, 10d);
       //ml.subllist(25d, 325d, 25d);
        //ml.listentabelle();
        


    //  ml.showList(3600.00, "J");        
      //ml.Order(5);  
      ml.showList( 8600.0, "J");
        //ml.Order(1);  ml.Order(1);  ml.Order(2);  
      ml.Order(3);  ml.Order(1);  ml.Order(2);  
                /*
        ml.showList(3600d, "J");        
        ml.Order(1);  
        ml.showList(600d, "J");
        ml.Order(1);  
        ml.showList(7200.0, "J");        
        ml.Order(4);  
       */

        
        //System.out.println("Pause Zeit: "+ml.getPauseZeit()+" Diff:"+ml.getDiff());
       // ml.showMe(ml.Listen(600.0,ml.getDiff()));
        //Diff=120 Pause=10800 >55,00            0,01        10800,00          196,36            1,00           55,00           -0,00           90,00           37,00
       //Diff=120 Pause=3600  >235,00            0,03         3600,00           15,32            1,00          235,00            0,00           30,00          217,00
       //Diff=120 Pause=600   >310,00            0,20          600,00            1,94            1,00          310,00           -0,00            5,00          292,00
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        BasicModel bm = new BasicModel() {        };
        bm.showMe();
        
    }*/

}

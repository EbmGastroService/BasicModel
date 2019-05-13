package InfoSeiten;


import ProtoTyp.Model;
import javafx.geometry.Pos;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 *@author El Bakry Mourad
 * @author EbmGastroService
 */
public class InfoPages {
    
    TitledPane Inhalt(String T, int mod) {
        HBox hb = new HBox(80);
        hb.setAlignment(Pos.TOP_CENTER);        
        //hb.setStyle("-fx-background-color:linear-gradient(to right, black,orange,white,black);");
        VBox vb=new VBox();
        Model m = new Model();
        TextFlow Tf=new TextFlow();
        int sorte = 0;
        if (mod == 1) {
            sorte = 12;
            vb.getChildren().addAll(pplogo(14));
        }
        if (mod == 2) {
            sorte = 8;
            vb.getChildren().addAll(pplogo(11));
        }
        if (mod == 3) {
            sorte = 5;
            vb.getChildren().addAll(pplogo(13));
        }
        if (mod == 4) {
            sorte = 2;
            vb.getChildren().addAll(pplogo(13));
        }        
        String mstr="";
        if (mod < 5) {
            Tf = m.MDaten(sorte, mod);
            //mstr = m.ModelDatenStr(sorte, mod);
        }else if (mod == 6) {
            mstr = "\nInfoblatt"
                    + "\n\nZiele dieses Applications ist Erklärung Folgenden:"
                    + "\n\n1 - Resthitzer Verteilung"
                    + "\n2 - Auftaukammerfunktion"
                    + "\n3 - Einlasser Bedienung L/R"
                    + "\n4 - Temperaturschwankung"
                    + "\n\nWas geschehe während eine Pause? "
                    + "Was pasiert, wenn die Auslastung eine Sorte so hoch wird? "                    
                    + "Es ist gelungen, beim höhere Auslastung eine Sorte, Temperatur schwankung"
                    + " anschaulich zu machen. Es steht fest wie im Patent, "
                    + "im Oberenbereich des Auftaukammers bleibt der Temperatur beständig in (-°) Grad "
                    + "und im Unteren Bereich naher an der Backofen ist bereit aufgetaut!"
                    + "\n\nWas von einen Automat ist das:"
                    + "\nEin Abfertigungsautomat zum Ausbacken und Verabreichen von vorgewählten "
                    + "Mengen aus verschiedenen Nahrungsmitteln Sorten, die kugelförmig gefüllte "
                    + "vorgebackene und gefroren im Sortenfach gelagerte Gebäckspezialitäten, "
                    + "sodass die von Kern gefroren zu Backofenfrische und Kern heiß ausgebacken "
                    + "werden und fügt dazu Zutaten und Beilagen anschließend verpacken in nur 30 Sekunden. "
                    + "\nDer Automat kann in verschiedene Varianten gebaut werden. Standard für 12 Sortenfächer "
                    + "je 80 Kugeln, gesamt 960 Kugeln im Gefrierlager und Abfertigungskapazität von 1440 in die Stunde."
                    + " \nOffice-Automat für 8 Sortenfächer, oder Haushaltsautomat mit 5 Sorten.";            
            vb.getChildren().addAll(pplogo(10));
        }else if (mod == 5) {
            mstr = T +  "\n\nDer Temperaturausgleich im Rechten und "
                    + "\nLinken Radius der Auftaukammer wird durch \ndie Einlasserstuerung  \nbestimmt."
                    + "\nBeim Ausfall eine Kugel unten zum Backofen aus eine Radiusseite bewirkt "
                    + "dass oben vom Gefrierlager zum selben Kammer Radiusseite  eine Kugel befördert wird."
                    + "\nIm Nächsten Ausfall wird  bedingt gesteuert dass es von gegenüber liegende Radiusseite ausgelassen wird."
                    + "\n\nWenn es ohne Einlassersteurung getrieben wird, dann haben wir das Risiko"
                    + " dass eine Seite mehr belastet sein kann, womit es eine geringere Zeit um aufzutauen hat"
                    + " die eine Seite, und die andere Seite schnell aufgetaut wird.";            
            vb.getChildren().addAll(pplogo(12));
        }
        if(mstr.length()>0){
            Text t=new Text(mstr);            
            Tf.getChildren().add(t);
            //Tf.setPrefWidth(400f);
        }
        
        
        /*
        Label lab = new Label();
        lab.setId("labs");
        lab.setAlignment(Pos.CENTER);
        lab.setWrapText(true);
        lab.setPrefWidth(400f);
        lab.setText(mstr);        
        
        
        if(lab.getText().length()>0){
            hb.getChildren().addAll(vb,lab, pplogo(mod));                
        }else {*/
            Tf.setId("labs");
            hb.getChildren().addAll(vb,Tf, pplogo(mod));
        //}                
        TitledPane tp = new TitledPane(T,hb);        
        //tp.setStyle("-fx-font-size:12px;-fx-background-color:linear-gradient(to bottom, gray,white,orange,white,gray);");        
        tp.setAlignment(Pos.CENTER);        
        return tp;
    }
    
    ImageView pplogo(int i) {
        String fname = "pict/pplogo.gif";
        double x, y;
        x = 250f;
        y = 430f;
        switch (i) {
            case 1:
                fname = "pict/AutomatEntwurf02.gif";
                break;
            case 2:
                fname = "pict/AutomatEntwurf01.gif";
                break;
            case 3:
                fname = "pict/HaushaltEntwurf03.gif";
                break;
            case 4:
                fname = "pict/HaushaltEntwurf03.gif";
                break;
            case 5:
                fname ="pict/einlasser01.gif";
                break;
            case 6:
                fname = "pict/pplogo.png";
                break;
            case 10:
                x =90f;
                y = 210f;
                fname = "pict/trumel90g.gif";
                break;
            case 11:
                x = 280f;
                y = 319f;
                fname = "pict/p_vordere_ansicht.gif";                
                break;
            case 12:
                x = 110f;
                y = 180f;
                fname = "pict/konstruct01.gif";
                break;
            case 13:
                x = 286f;
                y = 274f;
                fname = "pict/P_auftaukammer.gif";
                break;
            case 14:
                x = 169f;
                y = 423f;
                fname = "pict/konstruct03.PNG";
                break;
            default:
                break;
        }
        ImageView img = new ImageView(new Image(InfoPages.class.getResourceAsStream(fname)));
        if (i < 4 || i==10 || i==11||i==12|| i==13) {
            img.setFitWidth(x);
            img.setFitHeight(y);
        }
        return img;
    }
}

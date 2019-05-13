package InfoSeiten;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Mourad El Bakry
 * @author EbmGastroService
 */
public class SecoundPage {

    HBox Inhalt() {
        String mstr = "Patent Nr: AT 512470 B1 2014-09-15 \n"
                + "\nIm Menu finden Sie meine Demos für Standard-, Office-, Home- und Variabel- Automaten. "
                + "Man kann die Maschine als Kunde bedienen oder die Temperaturabbau während die Pause! betrachten."
                + "\nIm Demo, befindt sich die Sortenkammers samt Lager, Lager-Einlasser, Auftaukammer, Ofen-Einlasser, "
                + "Auswahlmenge, Temp-Action (Grün oder Rot), Sortenauswahltasten, Betriebzeit- und Resthietze- anzeige und Actiontasten."
                + "\nBestellen: Erzeugt Resthietze und veranlasst das optische Temperaturabbau."                
                + "\nAlle Sorten: ist 1 Stk. pro Sorte auswählen."
                + "\nPause 30 Min. bis 5 Min: dienen optische Betrachtung über Temp-Schwankung alle Sorten, Ob es im Chart oder im Kammern."
                + " Automat kann nicht Dauerbetrieb sein, aber die Resthietze überträgt sich physikalsch weiter."
                + "\nMan hat Information über jeder Veränderung an Hand <<Chartbewegung>> PallTemp und Resthietze, sowie eine <<Datenkorb>> für alle Sorten"
                + " wie Heitzstäbe- oder Heißluft- eischaltung, Kugel ID, Kugeltemp vor- und nachdem ausbacken. "
                + "Sie sehen auch die Betriebzeitdauer und die Resthietze im Backofen und die Sortentemp im Grünen soweit es abbaubar ist, sonst Rot wenn es Voll-Ausgebaut ist."                               
                + "\n\nBetrachtet man die Unterschiede zwischen Standard- und Home- Automt, dann stellt man fest, dass im Home die Resthitze über 50° pro Sortenkammer "                                
                + "und beim Standard wo 12 Sortenkammern vorhanden sind und je Kammer 20 Kugeln trägt, da kann die Resthitze nicht viel verändern ausser "
                + "geringfügige auftauen im unteren Bereich sodas nach ablauf von etwa 1 Stunde ohne Betrieb noch immer bei -10°C vorgesehen wird,"
                + " hat man eine Sorte von denen bestelt, dann kommt die strahlende Hitze für 30 Sekunden lang und wandelt die -10° zu etwa 120°C,"
                + " ab jetzt beginnt wieder eine beschleungung des Abbaues zubetrachten."
                + "\nBeim Home Automat hat man geringere Anzahl an Sorten, daher verteilt sich die Resthitze ensprechen schnelle."                
                + "Beim Office hat man 8 Sortenkammern und 6 Pall. pro Seite, das auftauen von unten nach oben braucht längere Zeit, "
                + "in etwa 4 Stunden gleicht sich der Temperatur und die Resthietze ist vollausgebaut."
                + "\n\nIch hoffe mit diesem optischen Demonstrationen eine Erklärung für meine Vorhaben beigetragen können. "
                + "Im übrigen bedanke mich herzlichen für ihre Bemühungen, und verbleibe mit vollen  Achtung und Hoffnung auf ihren Feedback."
                + "\n\nHerzlichen Dank \nEl Bakry Mourad";

        Label lab = new Label(mstr);
        lab.setAlignment(Pos.TOP_LEFT);
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.TOP_CENTER);
        lab.setWrapText(true);
        lab.setId("labs");
        lab.setStyle("-fx-font-weight:normal;");
        lab.setPrefSize(620f, 710f);
        lab.setText(mstr);
        hb.getChildren().addAll(pplogo(1), lab, pplogo(6));
        return hb;
    }

    ImageView pplogo(int i) {
        String fname = "";
        if (i == 6) {
            fname = "pict/App_Bild03.png";
        }
        if (i == 1) {
            fname = "pict/demo_bild01.png";
        }
        ImageView img = new ImageView(new Image(SecoundPage.class.getResourceAsStream(fname)));
        img.setFitHeight(710f);
        return img;
    }

}

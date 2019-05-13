/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSeiten;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author EbmGastroService
 */
public class therdPage {

    HBox Inhalt() {
        String mstr ="Sehr geehrte Damen und Herren,\n"                
                + "\nHiermit wird versucht, die Vorstellungskraft über die Funktionalität dieses Patent zuerläuterten. "
                + "Es war mir selbst unklar, ob es möglich wäre eine konstante Temperaturerhaltung während einer Auslastung zugewähren.\n"
                + "\nÜber dieses Patent:\n"
                + "Hier handelt sich um zeitentsprechende Erfindung, damit gemeint, "
                + "der Fortschritt und gesellschaftliche Wandlung von Onlineabwicklungen und Tuchscreen Bedienungen. "
                + "Es ist Genausten dringend gebraucht und möglichst mit Erfolg zu erwarten, "
                + "solche Verfahren (Patent über Automat) ins Wirtschaftsleben zu realisieren.\n"
                + "\nPallinoos Automat:"
                + "\nIst nicht nur eine Maschine, sondern ein Konzept, aus menschlicher Sicht, fügte etliche Vorteile zur aktuellen Gesellschaft, "
                + "damit gemeint und auch wäre nachweisbar, die Menschen wollen selbst ohne irgendeine Einmischung  entscheiden können. "
                + "Wie viel soll ausgegeben? Wie groß soll meine Speise sein? Fett reich oder arm, Vegan oder Vegitaria usw."
                + " Es muss nicht sein, Häppchen Fleisch und Pommes oder ein Weckerl mit Wurst, Salatblatt und extrem teuer! Wenn der Hunger packt.\n"                                
                + "\nÜber das Konzept im wirtschaftlichen Sinn:\n"
                + "Stellen Sie sich Folgendes vor: ein Automat wie Kaffeeautomat H,B,T 165x80x60, mit Kühl-aggregat, Heizstäbe, Heißluft, Verpackung "
                + "und Zutateneinfüge, Kasse und Steuerungselemente, ob es optische oder im Hintergrund wie Funk an der Logistikabteilung!\n"
                + "1) Maschinen Bauer, Service, Logistik, Reinigung\n"
                + "2) (Produktionen) von Pallinoos und Nebenprodukten wie Saucen und Beilagen.\n"
                + "3) Die Maschinen sind variabel und können mit 12, 8 oder 4 Sorten hergestellt werden.\n"
                + "4) Ob es für Haushalt oder Büro, da muss jemand dafür sich verpflichten zu verpflegen und versorgen (Verträge und Verwaltung). \n"
                + "5) Für In- und Ausland, Franchising-, Leasing-, Versicherung- verträge  (Rechtsabteilung)\n"
                + "Man kann feststellen, welche Vorteile können hingezogen werden, im Hinblick auf Beschäftigung und Wertsteigerung in der Nahezukunft.\n"
                + "\nAlles in allem braucht man, funktionstüchtiger Prototyp und Menschen, die begeistert sind, in solche Investition einzutreten.\n"
                + "\nMit Freundlichen Grüßen <<El Bakry Mourad 2017>>";
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

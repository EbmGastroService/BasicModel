package InfoSeiten;


import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Mourad El Bakry
 * @author EbmGastroService
 */
public class FirstPage {

    Panimation p;

    HBox Inhalt() {
        Text textBold01 = new Text("Herzlich willkommen\nPatent Nr: AT512470B1, "
                + "Internet Suche: 'DE102013201209' \noder 'A 897/2012 512 470 B1' PDF von 15.09.2014 Öst.-Patentamt\n");                          
        Text mstrUnderline01 = new Text("\nDemonstration an Hand Automatenentwurf sehe dazu Standard-, Office-, Home-, oder Variabel- Automat, ");
        Text mstrNorm01 = new Text("Da sind viele Fragen beatwortet worden, wie Temperaturabbau während die Pause oder bei verbrauch eine Sorte."
                + "\n\nWie abgebildet im Animation hier links, die Maschine besteht aus 4 Kammern"
                + "\n1 - Lager mit Rotationvorechtung und ist beständig beim -18°c."
                + "\n2 - Auftaukammer, Reifenförmig mit Einlasservorichtungen oben und unten."
                + "\n3 - Backofen mit über 300°c Heißluftströmungen und Bewegungsvorichtung, "
                + "an die Kammerneinlasser verbunden und mundet zum Servicebereich unten."
                + "\n4 - Ausgabevorichtungen für zufügen von Salat, Soucen und Beilagen sowie Verpackung.");
        Text textBold02 = new Text("\n\nAuftaukammer Steuerungsvorichtungen und Bedienung:");
        Text mstrNorm02 = new Text("\nDa die Kammer Kreisförmig ist, werden die Kugeln im Radius verteilt, in Reihen von oben nach unten und in Seiten Links und Rechts. "
                + "* Im Standardautomat sind 20 Kugeln je Auftaukammer enthalten , und 12 Sorten zum anbieten, daher werden die Kugeln in 10 Reihen und 2 Radiusseiten liegen"                
                + " und genauso teilt sich die Resthitze vom Backofenwand verteilt auf 12 Sorten/Kammer."
                + " Annahme Resthitze ist 240°, diese erst auf 12 Kammern dann auf 10 Reihen ergibt 2°C im Oberen<lager>(R1) und 20°C/pro Kammer im Unteren (R10)."
                + " Jeder Reihe hat 2 Seiten, daher teilt sich die Reihentemperatur auf 2 Kugeln und ergibt im R1 1°C und im R10 10° zum übertragen."
                + "* Die Einlasser treibt sich oben und unten mal Links dann zu nächsten Bedienung Rechts, "
                + "Linksseitig geht unteren Kugel zum Backofen und vom Gefrierlager oben landet eines mit -18°C hierein, dann wird diese Seite "
                + "erst bewegt, wenn die Rechte Seite ihre Bewegung abgeschloßen hat.");
        Text textBold03 = new Text("\n\nArbeitsablauf:");
        Text mstrNorm03 = new Text("\nKunde bedient die Maschine und wählt die Sorten, dann bestätigt die Auswahl. "
                + "Backofen wird eingeschaltet und im Lagerkammern wird rotoniert, "
                + "dann landen die gewählten Sorten vom Auftaukammen zum Backofen. "
                + "Im gleichen Gang landen die selben Sorten vom Lagerkammern "
                + " zum Auftaukammern und bedingt im selber Richtung, ");
        Text mstrUnderline02 = new Text("\n\n**Physikalisch betrachtet, die Temperaturübertragung ging heiß an kalt immer weiter, bis es ausgeglichen wird ");
        Text mstrNorm04 = new Text("da im Backofen eine höhere Resthitze verbleibt, dann kann nur eine Beschleunigung stattfinden. "
                + "Die Unteren Reihen werden mit hochsten Temperatur betrieben, die Oberen haben geringere Übertragung."
                + "\n\nHerzlichen Dank\nEl Bakry Mourad");
        TextFlow textflow = new TextFlow();
        textBold01.setStyle("-fx-font-weight:bold;");
        textBold02.setStyle("-fx-font-weight:bold;");
        textBold03.setStyle("-fx-font-weight:bold;");

        mstrNorm01.setStyle("-fx-font-weight:normal;");
        mstrNorm02.setStyle("-fx-font-weight:normal;");
        mstrNorm03.setStyle("-fx-font-weight:normal;");
        mstrNorm04.setStyle("-fx-font-weight:normal;");

        mstrUnderline01.setUnderline(true);
        mstrUnderline02.setUnderline(true);
        textflow.getChildren().addAll(textBold01, mstrUnderline01, mstrNorm01, textBold02, mstrNorm02, textBold03, mstrNorm03, mstrUnderline02, mstrNorm04);
        textflow.setPrefSize(620f, 685f);
        textflow.setId("labs");

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.TOP_CENTER);
        p = new Panimation();
        hb.getChildren().addAll(p.getPane(), textflow, pplogo(6));

        return hb;
    }

    void stop() {
        p.stop();
    }

    void play() {
        p.play();
    }

    ImageView pplogo(int i) {
        String fname = "";
        if (i == 6) {
            fname = "pict/App_Bild03.png";
        }
        ImageView img = new ImageView(new Image(FirstPage.class.getResourceAsStream(fname)));
        img.setFitHeight(710f);
        return img;
    }
}

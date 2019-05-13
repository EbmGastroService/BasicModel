package InfoSeiten;

import ProtoTyp.Model;
import ProtoTyp.Zahlformat;
import basicmodel.TestModel;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * @author Mourad El Bakry
 * @author EbmGastroService
 */
public class VariableModel {

    Label F1, F2, F3, F4;
    TextField A1, A2, A3;
    float B, H;
    String AntStr;
    Model M;
    HBox Z1;
    VBox vb;
    TestModel tm;

    public VariableModel() {
        B = 0;
        H = 0;
        F1 = new Label("Automat Bereite");
        F2 = new Label("Höhe");
        F3 = new Label("Ausgabevorrichtungen Höhe");
        A1 = new TextField("30.0");
        F1.setTooltip(new Tooltip("Pallinoos Durchmesser ist 5 cm\ndaher 30 ist 6 Sorten, 20 ist 4 Sorten"));
        A1.setPrefWidth(40d);
        A2 = new TextField("55.0");
        F2.setTooltip(new Tooltip("Die gesammte Höhe von Lagerregal + Auftaukammer+Ofen + Ausgabevorrichtung\nEs kann von 48 bis 165 sein."));
        A2.setPrefWidth(50d);
        A3 = new TextField("0.0");
        F3.setTooltip(new Tooltip("Haushalts Automat benötigt keine Ausgabevorichtunge\nHier kann 0 oder 80 sein"));
        A3.setPrefWidth(40d);
        AntStr = "6 Sorten, Ausg:0, Lager+Kammer+Ofen+Zwischenräume: 55, Ges. 55 H";
        F4 = new Label(AntStr);
        Z1 = new HBox(2);
        tm = new TestModel();
    }

    public VBox inhalt() {
        vb = new VBox(1);
        Z1.setAlignment(Pos.BASELINE_LEFT);
        Z1.getChildren().addAll(F1, A1, F2, A2, F3, A3, RestartModel(), F4);
        Z1.setMaxHeight(740);

        vb.setId("hbox1");
        Label l = new Label("Jeder Änderung in der Höhe oder Bereite fügt bei betätigung des Restart Taste ein neues Model inkl. Action und Statistik"
                + "\nSie sehen am Dektop Links ein Popup Infoblatt");
        l.setTextAlignment(TextAlignment.CENTER);
        vb.getChildren().addAll(Z1, l);
        return vb;
    }

    void Konfig() {
        Zahlformat mz = new Zahlformat();
        float b = Dparse(A1.getText());
        float h = Dparse(A2.getText());// Abgrenzung&Ofen+kammeraius+Mind. 1Reihe für 6 Kugeln Lage 30+12+6=48  oder 25+12+6=43 
        float a = Dparse(A3.getText());
        if (a == 80f && h < a) {
            h += a;
            A2.setText("" + h);
        }
        if (a == 80f && h < 135f) {
            h -= a;
            a = 0f;
            A2.setText("" + h);
            A3.setText("" + a);
        }
        if (h < 48 && a != 0f) {
            a = 0f;
            A3.setText("" + a);
        }
        int AnzSorten = (int) b / 5;

        float backofandZraum = 30f;
        if (a == 0f) {
            backofandZraum = 25f;
        }
        float oben = h - (a + backofandZraum);
        float lager = oben / 2;        //Lager ist etwas höhe als die Kammerradius
        float kammer = oben - lager;
        float ges = h;
        F4.setStyle("-fx-text-fill:green;");
        AntStr = AnzSorten + " Sorten, Lager: " + mz.Zahl(lager)
                + ", Kammerradius: " + mz.Zahl(kammer)
                + ", Ofen und Abgrenzungen: " + backofandZraum
                + ", Ges.: " + mz.Zahl(ges) + " cm H.";
        if (kammer < 12f) {
            //backofandZraum = 25f;
            oben = h - backofandZraum;
            kammer = 12f;
            lager = oben - kammer;
            ges = lager + kammer + backofandZraum;
            AntStr = AnzSorten + " Sorten, Lager: " + mz.Zahl(lager)
                    + ", Kammerradius: " + mz.Zahl(kammer)
                    + ", Ofen und Abgrenzungen: " + backofandZraum
                    + ", Ges.: " + mz.Zahl(ges) + " cm H.";
        }
        if (h > 47f) {
            String[] newSorten = new String[AnzSorten];
            for (int i = 0; i < newSorten.length; i++) {
                newSorten[i] = "Sorte " + (i + 1);
            }
            vb.getChildren().remove(1);

            tm.setMe(5, newSorten, lager);
            tm.showInfo();
            vb.getChildren().add(tm.getMain());

            F4.setText(AntStr);
        } else {
            F4.setStyle("-fx-text-fill:red;");
            F4.setText("Konfigerationsfehler! Ab 48 cm Hoch, Auftaukammer muss ausgefühlt!, Lagerregal weist kleine Volum auf!");
        }
        System.out.println(AntStr);
    }

    Button RestartModel() {
        Button restart = new Button("Restart Model");
        restart.setOnAction((ActionEvent event) -> {
            Konfig();
        }
        );

        return restart;
    }

    float Dparse(String nr) {
        float f;
        try {
            f = Float.parseFloat(nr);
        } catch (Exception e) {
            f = 0f;
        }
        return f;
    }

}

package BauGroupen;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Mourad El Bakry
 * @author organisation EbmGastroService
 */
public interface ArtikelBaugroppe {

    double width = 84.0d;
    double heightEinheit = 40.0d;

    Rectangle Gefrierfach();

    Rectangle Auftaukammer();

    Rectangle Ofen();

    Group GefrierEinlasser();

    Group OfenEinlasser();

    Rectangle creatBauElement(double E_width, double E_height);

    VBox cereatArtikelBauGroupe();

    Group creatLagerEinlasser(Rectangle r);

    Group creatOfenEinlasser(Rectangle r);

    Label getMengenLabel();

    Label getSortenNamesLabel();

    Button ActionButton();
}

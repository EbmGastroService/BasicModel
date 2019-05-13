/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author EbmGastroService
 */
public class tabelView {

    private final TableView<TableDate> table = new TableView<>();
    private final ObservableList<TableDate> data
            = FXCollections.observableArrayList(new TableDate("", ""));
    TableColumn[] TC;
    String[] Headers = new String[]{"Action bezeichnung", "Wert"};
    String[] Date;

    public tabelView() {
        table.setEditable(true);
        this.TC = creatColumn(Headers);
        creatTable();
        table.setItems(data);
    }

    public tabelView(String[] date) {
        this.Date = date;
        table.setEditable(true);
        this.TC = creatColumn(Date);//Headers);        
        creatTable();
        table.setItems(data);
    }

    public void addDate(String c, String d) {
        data.add(new TableDate(c, d));
    }

    public String[] getDate(int was) {
        String[] str = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            if (was == 1) {
                str[i] = getAction(i);
            }
            if (was == 2) {
                str[i] = getWert(i);
            }
        }
        return str;
    }

    public String getWert(int i) {
        return data.get(i).getWert();
    }

    public String getAction(int i) {
        return data.get(i).getActionBez();
    }

    final void creatTable() {
        for (int i = 0; i < Headers.length; i++) {
            table.getColumns().add(TC[i]);
        }
    }

    final TableColumn[] creatColumn(String[] h) {
        TC = new TableColumn[h.length];

        TC[0] = new TableColumn(h[0]);
        TC[0].setMinWidth(320.0);
        TC[0].setCellValueFactory(new PropertyValueFactory<>("ActionBez"));

        TC[1] = new TableColumn(h[1]);
        TC[1].setMinWidth(120.0);
        TC[1].setCellValueFactory(new PropertyValueFactory<>("Wert"));
        TC[1].setCellFactory(TextFieldTableCell.forTableColumn());
        TC[1].getStyleClass().add("highlighted-cell-font");//.setId("tableNumber");       
        return TC;
    }

    public TableView getTable() {
        return table;
    }

    public static class TableDate {

        private final SimpleStringProperty ActionBez;
        private final SimpleStringProperty Wert;

        TableDate(String A, String B) {
            this.ActionBez = new SimpleStringProperty(A);
            this.Wert = new SimpleStringProperty(B);
        }

        /**
         * @return the ActionBez
         */
        public String getActionBez() {
            return ActionBez.get();
        }

        /**
         * @param actionBez
         */
        public void setActionBez(String actionBez) {
            ActionBez.set(actionBez);
        }

        /**
         * @return the Wert
         */
        public String getWert() {
            return Wert.get();
        }

        /**
         * @param wert the Wert to set
         */
        public void setWert(String wert) {
            Wert.set(wert);
        }

    }
}

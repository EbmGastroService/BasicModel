/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;

/**
 *
 * @author EbmGastroService
 */
public class treeTableView {

    StackPane rootPane;
    String[] SortenNames;
    TreeItem[] childNode;
    //ObservableList<myDate> treeData = FXCollections.observableArrayList(new myDate("Sorte", "Date", "Wert"));
    TreeItem<myDate> root;
    TreeTableColumn<myDate, String> SorteName;
    TreeTableColumn<myDate, String> Action;
    TreeTableColumn<myDate, String> Wert;
    TreeTableView<myDate> treeTableView;
    private tabelView Tv;
    
    public treeTableView(String[] SortenNames, tabelView Tv) {
        this.SortenNames = SortenNames;
        this.Tv = Tv;
        childNode = new TreeItem[SortenNames.length];
        root = new TreeItem<>(new myDate("Sorten", "", ""));                

        for (int i = 0; i < SortenNames.length; i++) {
            childNode[i] = new TreeItem<>(new myDate(SortenNames[i], "", ""));
            root.getChildren().add(childNode[i]);
        }

        root.setExpanded(true);
        rootPane = new StackPane();
        rootPane.setId("display");

        SorteName = new TreeTableColumn<>("Sorte");
        SorteName.setPrefWidth(110);
        SorteName.setCellValueFactory((
                TreeTableColumn.CellDataFeatures<myDate, String> param) -> new ReadOnlyStringWrapper(
                param.getValue().getValue().getName()));

        Action = new TreeTableColumn<>("Daten");
        Action.setPrefWidth(240);
        Action.setCellValueFactory((
                TreeTableColumn.CellDataFeatures<myDate, String> param) -> new ReadOnlyStringWrapper(
                param.getValue().getValue().getAction()));
        //Action.getStyleClass().add("highlighted-cell-font");

        Wert = new TreeTableColumn<>("wert");
        Wert.setPrefWidth(60);
        Wert.setCellValueFactory((
                TreeTableColumn.CellDataFeatures<myDate, String> param) -> new ReadOnlyStringWrapper(
                param.getValue().getValue().getWert()));       
        Wert.getStyleClass().add("highlighted-cell-font");//.setId("tableNumber");    

        treeTableView = new TreeTableView<>(root);

        treeTableView.getColumns().setAll(SorteName, Action, Wert);//.add(Sorten);

        rootPane.getChildren().add(treeTableView);
    }

    public final void addToSorte(int i) {
        childNode[i].getChildren().clear();
        String[] a = getTv().getDate(1);
        String[] w = getTv().getDate(2);                
        String str="";
        for (int x = 4; x < a.length; x += 2) { 
            if(a[x].contains("3 -"))str="PalNr "+w[x].replace(",00","");
            childNode[i].getChildren().add(new TreeItem<>(new myDate(str, a[x], w[x])));
        }
        root.getChildren().set(i, childNode[i]);
    }

    String[] getSortenNames() {
        return SortenNames;
    }

    public StackPane getRoot() {
        return rootPane;
    }

    /**
     * @return the Tv
     */
    public tabelView getTv() {       
        return Tv;
    }

    /**
     * @param i
     * @param Tv the Tv to set
     */
    public void setTv(int i, tabelView Tv) {
        this.Tv = Tv;
        addToSorte(i);
    }

    public class myDate {

        private SimpleStringProperty name;
        private SimpleStringProperty action;
        private SimpleStringProperty wert;

        public SimpleStringProperty nameProperty() {
            if (name == null) {
                name = new SimpleStringProperty(this, "name");
            }
            return name;
        }

        public SimpleStringProperty actionProperty() {
            if (action == null) {
                action = new SimpleStringProperty(this, "action");
            }
            return action;
        }

        public SimpleStringProperty wertProperty() {
            if (wert == null) {
                wert = new SimpleStringProperty(this, "wert");
            }
            return wert;
        }

        private myDate(String name, String action, String wert) {
            this.name = new SimpleStringProperty(name);
            this.action = new SimpleStringProperty(action);
            this.wert = new SimpleStringProperty(wert);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String nName) {
            name.set(nName);
        }

        public String getAction() {
            return action.get();
        }

        public void setAction(String nAction) {
            action.set(nAction);
        }

        public String getWert() {
            return wert.get();
        }

        public void setWert(String nWert) {
            wert.set(nWert);
        }

    }

}

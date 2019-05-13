package InfoSeiten;

import basicmodel.emptyKammer;
import javafx.event.Event;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author Mourad El Bakry
 * @author EbmGastroService
 */
public class Prototype {
    
    Scene scene;
    myStage pstage;
    
    Tab type(String text, int mod) {
        Tab tab = new Tab();
        tab.setText(text);
        tab.setClosable(true);
        switch (mod) {
            case 8:
                FirstPage fp = new FirstPage();
                tab.setContent(fp.Inhalt());
                tab.setOnSelectionChanged((Event event) -> {
                    if (event.isConsumed()) {
                        fp.stop();
                    }
                    fp.play();
                });
                break;
            case 9:
                BorderPane b = new BorderPane();
                b.setId("display");
                HBox hb = new HBox();                  
                emptyKammer ek = new emptyKammer();
                for (int i = 0; i < 11; i++) {
                    hb.getChildren().add(ek.getMe());
                }
                //b.setPrefWidth(1100);
                b.setCenter(hb);
                tab.setContent(b);                
                break;
            
            case 6:
                tab.setContent(new SecoundPage().Inhalt());
                break;
            case 10:
                tab.setContent(new therdPage().Inhalt());
                break;    
            case 7:
                VariableModel vm = new VariableModel();
                tab.setContent(vm.inhalt());
                break;
            default:
                break;
        }
        return tab;
    }
    
    Tab type(String text) {
        InfoPages ip = new InfoPages();
        Accordion ac = new Accordion();
        //ac.applyCss();
        ac.getPanes().addAll(ip.Inhalt("Application Infoblatt", 6), ip.Inhalt("Über Standardsautomat", 1), ip.Inhalt("Über Officeautomat", 2),
                ip.Inhalt("Über Haushaltsautomat", 3), ip.Inhalt("Über Home Easyone", 4), ip.Inhalt("Über Einlasser Funktionsentwurf", 5));
        ac.setExpandedPane(ac.getPanes().get(0));
        
        Tab tab = new Tab();
        tab.setText(text);
        tab.setClosable(false);        
        tab.setContent(ac);
        return tab;
    }
    
    public TabPane creatTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.RIGHT);
        tabPane.setId("Freilauf_yellow");
        //Prototype pt=new Prototype();
        //tabPane.getTabs().addAll(type("Willkommen",8),type("Hinweis",6),type("Standard", 1), type("Office", 2),
        //type("Home", 3),type("Easyone", 4),type("Variabel", 7),type("Info Blätter"));                       
        tabPane.getTabs().addAll(type("Willkommen", 8), type("Hinweis", 6), type("Bitte", 10), type("Info Blätter"), type("Variabel Automat", 7), type("Leere Entwurf", 9));
        return tabPane;
    }
    
}

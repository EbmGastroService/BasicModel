/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import BauGroupen.GroupenBinder;
import InfoSeiten.myStage;
import ProtoTyp.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author EbmGastroService
 */
public class TestModel {

    GroupenBinder groupenBinder;
    Model mod;
    int Lager, Trommel, ModNumer;
    String ModName,ModDate;
    String[] SortenNames, mySorten;
    BorderPane bp;
    myStage stage;
    public TestModel(){
        mySorten = new String[]{
            "Cardinale", "Salami", "Funghi", "Mozzera", "Formagio" //5 Sorten
            , "Spinatci", "Polo", "Hawaii" //8 Sorten
            , "Tonno", "Fruti", "Carciofi", "Pavaroti"//12 Sorten
        };
    }
    
    public BorderPane getMain(){     
        bp = new BorderPane();
        groupenBinder = new GroupenBinder(SortenNames.length, Trommel / 2, SortenNames,Lager);//Kammer, Reihen                   
        bp.setCenter(groupenBinder.MainBoard());        
        return bp;
    }

    public void testModel(int model) {
        ModNumer = model;
        mod = new Model();
        int length = mySorten.length;
        switch (model) {
            case 1:
                length = mySorten.length;
                break;
            case 2:
                length = 8;
                break;
            case 3:
                length = 5;
                break;
            case 4:
                length = 2;
                break;
            default:
                break;
        }
        if (model == 5 || model == 7) {

        }
        SortenNames = new String[length];
        System.arraycopy(mySorten, 0, SortenNames, 0, length);
        setMe(ModNumer, SortenNames, 0);
    }    

    public void setMe(int modNr, String[] newSorten, float VModelL) {
        mod = new Model();
        ModNumer = modNr;
        if (VModelL > 0f) {
            mod.setLagerL(VModelL);
        }
        mod.getModel(ModNumer);//1 bis 3
        Lager = (int) mod.getLagerBestand();
        Trommel = (int) mod.getTrommelBestand();
        ModName = mod.getModelName();
        SortenNames = newSorten;     
        ModDate = mod.ModelDatenStr(SortenNames.length, ModNumer);
        //showInfo();
    }
    public void showInfo(){
        Label modInfo = new Label("I N F O\n* * * * * *\n"+ModDate);        
        modInfo.setPrefWidth(225f);
        modInfo.setAlignment(Pos.CENTER);
        modInfo.setTextAlignment(TextAlignment.CENTER);
        modInfo.setId("display1");        
        
        Scene scene = new Scene(modInfo);
//        scene.getStylesheets().add(Startme.class.getResource("AutomatStyleSheet.css").toExternalForm());        
        stage = new myStage(scene);
        String tit = "Info-(V" +SortenNames.length+""+Lager+""+Trommel + ")" + stage.getTitle();
        stage.setTitle(tit);
        stage.setX(0);
        stage.setY(400);
    }

}

package InfoSeiten;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author EbmGastroService
 */
public class myStage extends Stage {

    private final String TIT = "Pallinoos Automat von El Bakry Mourad Präsentation 2012-2017";

    public myStage(Scene scene) {
        scene.getStylesheets().add(myStage.class.getResource("AutomatStyleSheet.css").toExternalForm());        
        scene.getStylesheets().add(myStage.class.getResource("Chart.css").toExternalForm());                
        super.setTitle(getTIT());
        //super.setResizable(true);
        super.setScene(scene);
        super.getIcons().add(new Image(this.getClass().getResourceAsStream("Pall.jpg")));
        
        super.setAlwaysOnTop(true);
        super.show();
    }

    /**
     * @return the TIT
     */
    public final String getTIT() {
        return TIT;
    }

    public void closeAll() {
        if(super.isShowing()){
            super.close();
        }
    }

}

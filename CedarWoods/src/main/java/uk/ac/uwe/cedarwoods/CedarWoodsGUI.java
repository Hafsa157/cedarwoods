/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
package uk.ac.uwe.cedarwoods;


/**
 *
 * @author Paula
 */



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * This class is the main entry point for the JavaFX application.
 * It loads the first screen (main menu) when the program starts.
 */
public class CedarWoodsGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        // Load the main menu FXML file
        // This is the first screen the user sees
        Parent root = FXMLLoader.load(
                getClass().getResource("/fxml/main_view.fxml")
        );

        
        // Create the scene and set window size
        Scene scene = new Scene(root, 900, 600);
        
        
        // Set window title
        stage.setTitle("Cedar Woods Accommodation System");
        
       // Attach scene to stage and display it
        stage.setScene(scene);
        stage.show();
    }

    
     /*
     * Standard JavaFX main method.
     * Launches the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

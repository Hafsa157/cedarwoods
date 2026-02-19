/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uk.ac.uwe.cedarwoods;

/**
 *
 * @author Paula
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*
 * This controller manages the main menu screen.
 * From here the user can navigate to the Accommodation Management screen
 * or exit the system.
 */
public class MenuController {

    
    /*
     * This method runs when the user clicks
     * "Accommodation Management".
     * It loads the View Accommodation screen.
     */
    @FXML
    private void openViewAccommodation(ActionEvent event) {

        try {
            // Load the accommodation screen FXML
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/view_accommodation.fxml")
            );

            Parent root = loader.load();
            // Get the current window (stage)
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();
            
            // Replace scene with accommodation screen
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            // Print any errors to console for debugging
            e.printStackTrace(); 
        }
    }

    
    /*
     * Closes the application completely.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}

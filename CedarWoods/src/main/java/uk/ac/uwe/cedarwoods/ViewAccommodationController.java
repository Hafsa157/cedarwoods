/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.uwe.cedarwoods;

/**
 *
 * @author Paula
 */



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import uk.ac.uwe.cedarwoods.model.*;
import uk.ac.uwe.cedarwoods.system.CedarWoodsSystem;


/*
 * This controller manages the Accommodation Management screen.
 *
 * It connects the GUI to the system logic and allows the user to:
 * - View all accommodation in a table
 * - Filter by area
 * - Filter by cleaning status
 * - Check guests in
 * - Check guests out
 * - Update cleaning status (Clean / Dirty / Maintenance)
 *
 * The table updates automatically whenever something changes.
 */
public class ViewAccommodationController implements Initializable {

    
    
    // TABLE COMPONENTS
    @FXML private TableView<Accommodation> table;
    @FXML private TableColumn<Accommodation, String> colNumber;
    @FXML private TableColumn<Accommodation, String> colType;
    @FXML private TableColumn<Accommodation, String> colOccupancy;
    @FXML private TableColumn<Accommodation, String> colAvailability;
    @FXML private TableColumn<Accommodation, String> colStatus;
    @FXML private TableColumn<Accommodation, Integer> colGuests;
    @FXML private TableColumn<Accommodation, String> colBreakfast;
    


     // FILTERS
    @FXML private ComboBox<String> areaBox;
    @FXML private ComboBox<String> cleaningStatusBox;
    @FXML private ComboBox<String> changeStatusBox;
    
     // INFO FIELDS
    @FXML private TextField areaDescriptionField;
    @FXML private TextField typeField;
    @FXML private TextField numberField;
    @FXML private TextField capacityField;
    @FXML private TextField priceField;
    @FXML private TextArea descriptionArea;

    // RECEPTION FIELDS
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;
    @FXML private TextField guestCountField;
    @FXML private TextField nightsField;
    @FXML private CheckBox breakfastCheck;
    
    // DASHBOARD FIELDS
    @FXML private TextField breakfastCountField;
    @FXML private TextField requireCleaningField;

    
    // SYSTEM + DATA LISTS
    private final CedarWoodsSystem system = new CedarWoodsSystem();

    private ObservableList<Accommodation> masterList;
    private ObservableList<Accommodation> filteredList;

     /*
     * This method runs automatically when the screen loads.
     * It sets up all data, filters and table columns.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
        
        // Load all accommodation from the system
        masterList = FXCollections.observableArrayList(
                system.getAllAccommodations()
        );

        filteredList = FXCollections.observableArrayList(masterList);
        table.setItems(filteredList);
        
        table.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldSel, newSel) -> updateCounts()
);


       
        // AREA FILTER SETUP
        ObservableList<String> areaOptions = FXCollections.observableArrayList();
        areaOptions.add("All");

        for (Area area : system.getAreas()) {
            areaOptions.add(area.getName());
        }

        areaBox.setItems(areaOptions);
        areaBox.getSelectionModel().selectFirst();

       areaBox.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldVal, newVal) -> {

            applyFilters();

            if (newVal.equals("All")) {
                areaDescriptionField.setText(
                        "Shows all accommodation across Cedar Woods."
                );
            } else {
                for (Area area : system.getAreas()) {
                    if (area.getName().equals(newVal)) {
                        areaDescriptionField.setText(
                                area.getDescription()
                        );
                        break;
                    }
                }
            }
        }
);


     
       

// CLEANING STATUS FILTER
cleaningStatusBox.setItems(
        FXCollections.observableArrayList(
                "ALL", "CLEAN", "DIRTY", "MAINTENANCE"
        )
);
cleaningStatusBox.getSelectionModel().selectFirst();
cleaningStatusBox.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldVal, newVal) -> applyFilters()
);


// CHANGE STATUS COMBO BOX
changeStatusBox.setItems(
        FXCollections.observableArrayList(
                "CLEAN", "DIRTY", "MAINTENANCE"
        )
);
changeStatusBox.getSelectionModel().selectFirst();

       
        // TABLE COLUMNS MAPPING
        colNumber.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getAccommodationNumber()));

        colType.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getClass().getSimpleName()));

        colOccupancy.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().isOccupied() ? "Occupied" : "Unoccupied"
                ));

        colAvailability.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().isAvailable() ? "Available" : "Unavailable"
                ));

        colStatus.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getCleaningStatus().name()
                ));

        colGuests.setCellValueFactory(c ->
                new SimpleIntegerProperty(
                        c.getValue().getNumberOfGuests()
                ).asObject()
        );

        colBreakfast.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().isBreakfastRequired() ? "Yes" : "No"
                ));

        
        // Update accommodation info when selecting a room
        table.getSelectionModel().selectedItemProperty().addListener(
        (obs, oldSel, newSel) -> {

            if (newSel != null) {

               
                areaDescriptionField.setText(
                        newSel.getDescription()
                );

                // Bottom left info panel
                typeField.setText(newSel.getClass().getSimpleName());
                numberField.setText(newSel.getAccommodationNumber());
                capacityField.setText(String.valueOf(newSel.getCapacity()));
                priceField.setText("£" + newSel.getPrice());
                descriptionArea.setText(newSel.getDescription());
            }
        }
);


        updateCounts();
    }

   
     /*
     * Filters the table based on:
     * - Selected area
     * - Selected cleaning status
     */
    private void applyFilters() {

        String selectedArea = areaBox.getValue();
        String selectedStatus = cleaningStatusBox.getValue();

        filteredList.clear();

        for (Accommodation acc : masterList) {

            boolean matchesArea = true;
            boolean matchesStatus = true;

            // AREA FILTER
            if (!selectedArea.equals("All")) {

                for (Area area : system.getAreas()) {
                    if (area.getName().equals(selectedArea)) {
                        matchesArea = area.getAccommodations().contains(acc);
                        areaDescriptionField.setText(area.getDescription());
                        break;
                    }
                }
            } else {
                areaDescriptionField.setText(
                        "Shows all accommodation across Cedar Woods."
                );
            }

            // STATUS FILTER
            if (!selectedStatus.equals("ALL")) {
                matchesStatus =
                        acc.getCleaningStatus().name()
                                .equals(selectedStatus);
            }

            if (matchesArea && matchesStatus) {
                filteredList.add(acc);
            }
        }

        updateCounts();
    }

   
    
    

 /*
     * Updates dashboard numbers:
     * - Number of breakfasts required
     * - Number of rooms that need cleaning
     */    
private void updateCounts() {

    Accommodation selected =
            table.getSelectionModel().getSelectedItem();

    if (selected != null &&
        selected.isOccupied() &&
        selected.isBreakfastRequired()) {

        breakfastCountField.setText(
                String.valueOf(selected.getNumberOfGuests())
        );

    } else {

        breakfastCountField.setText("0");
    }

    long dirty = filteredList.stream()
            .filter(a -> a.getCleaningStatus() == CleaningStatus.DIRTY)
            .count();

    requireCleaningField.setText(String.valueOf(dirty));
}


   
     /*
     * Handles guest check-in.
     */
@FXML
private void handleCheckIn() {

    Accommodation selected =
            table.getSelectionModel().getSelectedItem();

    if (selected == null) {
        showAlert("No Room Selected",
                "Please select an accommodation first.");
        return;
    }

    try {

        int guests = Integer.parseInt(guestCountField.getText());
        int nights = Integer.parseInt(nightsField.getText());

        Guest guest = new Guest(
                firstNameField.getText(),
                lastNameField.getText(),
                phoneField.getText()
        );

        selected.checkIn(
                guest,
                guests,
                nights,
                breakfastCheck.isSelected()
        );

        table.refresh();
        updateCounts();

    } catch (NumberFormatException e) {

        showAlert("Invalid Input",
                "Please enter valid numbers for guests and nights.");

    } catch (RuntimeException e) {

        showAlert("Check-In Error",
                e.getMessage());
    }
}



private void showAlert(String title, String message) {

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


    
    /*
     * Handles guest check-out.
     * This should automatically make the room DIRTY.
     */

    @FXML
    private void handleCheckOut() {

        Accommodation selected =
                table.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.checkOut();
        table.refresh();
        updateCounts();
    }

    
    /*
     * Updates cleaning status manually
     * (e.g. after cleaning or maintenance).
     */

@FXML
private void handleMaintenanceAction() {

    Accommodation selected =
            table.getSelectionModel().getSelectedItem();

    if (selected == null) {
        showAlert("No Room Selected",
                "Please select an accommodation first.");
        return;
    }

    String selectedValue = changeStatusBox.getValue();

    if (selectedValue == null) {
        showAlert("No Status Selected",
                "Please select a cleaning status.");
        return;
    }

    CleaningStatus newStatus =
            CleaningStatus.valueOf(selectedValue);

    selected.setCleaningStatus(newStatus);

    table.refresh();
    updateCounts();
}

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package uk.ac.uwe.cedarwoods.model;

/**
 *
 * @author hafsarobleh
 */


import java.util.ArrayList;


/*
 * This class represents a physical area in Cedar Woods
 * (e.g. Hilltop, Woodland, Meadow, Lakeview).
 *
 * Each Area:
 * - Has a name
 * - Has a description
 * - Contains multiple accommodation units
 *
 * I separated Area from Accommodation so that
 * accommodation can be grouped logically.
 */
public class Area {

    // ATTRIBUTES
    private String name;
    private String description;   
    
     /*
     * Each area contains a list of accommodation units.
     * For example, Hilltop contains cabins C1–C4.
     */
    private ArrayList<Accommodation> accommodations;

    
    // CONSTRUCTOR
     /*
     * When creating a new Area,
     * we set its name and description
     * and initialise an empty list of accommodation.
     */
    public Area(String name, String description) {  
        this.name = name;
        this.description = description;
        accommodations = new ArrayList<>();
    }

    
      // ADD ACCOMMODATION
     /*
     * This method allows us to add accommodation
     * into a specific area.
     *
     * It keeps the relationship between Area
     * and Accommodation organised.
     */
    
    public void addAccommodation(Accommodation accommodation) {
        accommodations.add(accommodation);
    }
    
    // GETTERS

    public ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }

    public String getName() {
        return name;
    }

    
    public String getDescription() {
        return description;
    }

    // BREAKFAST CALCULATION
        /*
     * This method calculates how many breakfasts
     * are required today in this area.
     *
     * It loops through each accommodation and:
     * - Checks if it is occupied
     * - Checks if breakfast is required
     * - Adds the number of guests
     */
    public int getBreakfastsToday() {
        int total = 0;
        for (Accommodation acc : accommodations) {
            if (acc.isOccupied() && acc.isBreakfastRequired()) {
                total += acc.getNumberOfGuests();
            }
        }
        return total;
    }
    
    // CLEANING CALCULATION
        /*
     * This method counts how many units
     * need cleaning in this area.
     *
     * According to the rules, a room becomes DIRTY
     * after check-out.
     */
    public int getUnitsToClean() {
        int count = 0;
        for (Accommodation acc : accommodations) {
            if (acc.getCleaningStatus() == CleaningStatus.DIRTY) {
                count++;
            }
        }
        return count;
    }

     
    // OBJECT REPRESENTATION
    
    /*
     * This is useful when displaying Area
     * inside combo boxes.
     *
     * It ensures only the name is shown.
     */
    @Override
    public String toString() {
        return name;
    }
}

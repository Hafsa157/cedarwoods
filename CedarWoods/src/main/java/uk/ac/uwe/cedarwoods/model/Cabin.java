/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.uwe.cedarwoods.model;

/**
 *
 * @author hafsarobleh
 */


/*
 * Cabin represents a traditional cabin unit at Cedar Woods.
 *
 * It extends the abstract Accommodation class.
 * This avoids code duplication and keeps the design clean.
 *
 * If needed, Cabin could later override methods
 * (for example different pricing rules),
 * which is why using inheritance is useful.
 */
public class Cabin extends Accommodation {

     /*
     * The constructor calls the parent constructor.
     * All shared properties are stored in Accommodation.
     */
    public Cabin(String number, int capacity, float price, String description) {
        super(number, capacity, price, description);
    }
}

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
 * GeodesicDome is another accommodation subtype.
 *
 * It extends Accommodation to reuse the shared logic
 * such as booking, cleaning status, and availability.
 *
 * This demonstrates polymorphism because
 * a GeodesicDome "is an" Accommodation.
 */
public class GeodesicDome extends Accommodation {

     /*
     * Constructor passes values up to the parent class.
     * The parent class handles all the main logic.
     */
    public GeodesicDome(String number, int capacity, float price, String description) {
        super(number, capacity, price, description);
    }
}

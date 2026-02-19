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
 * Airstream represents a premium accommodation type.
 *
 * It inherits all behaviour from Accommodation,
 * including:
 * - check-in logic
 * - check-out logic
 * - cleaning status handling
 * - availability rules
 *
 * This design keeps the system scalable,
 * as new accommodation types can easily be added.
 */
public class Airstream extends Accommodation {

     /*
     * Calls the parent constructor using super().
     */
    public Airstream(String number, int capacity, float price, String description) {
        super(number, capacity, price, description);
    }
}

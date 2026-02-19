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
 * Yurt is a specific type of Accommodation.
 *
 * It inherits all common properties and behaviour
 * from the abstract Accommodation class.
 *
 * I created this class to demonstrate inheritance.
 * Even though it currently does not add extra behaviour,
 * it allows the system to treat different accommodation
 * types separately (e.g. in reports or future features).
 */
public class Yurt extends Accommodation {

    /*
     * The constructor simply calls the parent constructor
     * using super().
     *
     * This means all shared attributes like:
     * - number
     * - capacity
     * - price
     * - description
     * are handled by the Accommodation class.
     */
    public Yurt(String number, int capacity, float price, String description) {
        super(number, capacity, price, description);
    }
}

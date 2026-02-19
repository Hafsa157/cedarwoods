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
 * This class represents a guest staying at Cedar Woods.
 *
 * It stores the guest's personal details such as:
 * - First name
 * - Last name
 * - Phone number
 *
 * Each guest is automatically assigned a unique ID.
 */
public class Guest {

   //ATTRIBUTES
    /*
     * Static counter used to automatically generate
     * unique guest IDs.
     *
     * Every time a new Guest is created,
     * this value increases.
     */
    private static int idCounter = 1;

    private final int guestId;
    private final String firstName;
    private final String lastName;
    private final String phone;

    //CONSTRUCTOR
    /*
     * When a new Guest is created,
     * they are automatically assigned:
     * - A unique ID
     * - Their personal details
     *
     * I used final fields because once a guest
     * is created, their identity should not change.
     */
    public Guest(String firstName, String lastName, String phone) {
        this.guestId = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    //GETTERS
    public int getGuestId() {
        return guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    //OBJECT REPRESENTATION
     /*
     * This method returns a readable string
     * representation of the guest.
     *
     * It is useful for debugging or printing
     * guest information in the system.
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + phone + ")";
    }
}

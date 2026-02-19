/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.uwe.cedarwoods.model;

import java.util.Date;

/**
 *
 * @author hafsarobleh
 */

/*
 * Booking represents a reservation made by a guest.
 *
 * This class connects a Guest to an Accommodation.
 * It stores important booking information such as:
 * - Check-in and check-out dates
 * - Number of guests
 * - Whether breakfast is required
 *
 * This demonstrates composition because
 * a Booking "has a" Guest.
 */
public class Booking {

    // Basic booking details
    private int bookingId;
    private Guest guest;
    private Date checkInDate;
    private Date checkOutDate;
    private int numberOfGuests;
    private boolean breakfastRequired;


    /*
     * Constructor creates a new booking
     * when a guest checks in.
     *
     * All booking information is stored here
     * instead of inside Accommodation,
     * which keeps responsibilities separated.
     */
    public Booking(int bookingId,
                   Guest guest,
                   Date checkInDate,
                   Date checkOutDate,
                   int numberOfGuests,
                   boolean breakfastRequired) {

        this.bookingId = bookingId;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.breakfastRequired = breakfastRequired;
    }

     /*
     * Returns the guest linked to this booking.
     */
    public Guest getGuest() {
        return guest;
    }

     /*
     * Returns how many guests are staying.
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

     /*
     * Returns whether breakfast was selected.
     */
    public boolean isBreakfastRequired() {
        return breakfastRequired;
    }
    
     /*
     * Calculates how many breakfasts are needed.
     *
     * If breakfast was not selected, return 0.
     * Otherwise, return number of guests.
     *
     * This method is used by the dashboard
     * to calculate breakfast totals.
     */
    public int totalBreakfasts() {
        if (!breakfastRequired) return 0;
        return numberOfGuests;
    }
}

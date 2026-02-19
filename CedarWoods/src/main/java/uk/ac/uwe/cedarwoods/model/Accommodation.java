/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uk.ac.uwe.cedarwoods.model;

/**
 *
 * @author hafsarobleh
 */


import java.util.Date;



/*
 * This is the main abstract class for all accommodation types.
 *
 * Cabin, Yurt, Airstream and GeodesicDome all extend this class.
 *
 * It contains the shared attributes and behaviour that every
 * accommodation should have.
 *
 * I made this class abstract because we never create a generic
 * "Accommodation" — we only create specific types.
 */
public abstract class Accommodation {

    // Basic information about the accommodation
    protected String accommodationNumber;
    protected int capacity;
    protected float pricePerNight;
    protected String description;
    
    // Status-related fields
    private boolean occupied;
    private CleaningStatus cleaningStatus;
    
    // Each accommodation can have one active booking at a time
    private Booking currentBooking;

  /*
     * Constructor sets up the core details when the
     * accommodation is first created.
     *
     * By default:
     * - It starts as not occupied
     * - Cleaning status is CLEAN
     */
    public Accommodation(String accommodationNumber,
                         int capacity,
                         float pricePerNight,
                         String description) {

        this.accommodationNumber = accommodationNumber;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.description = description;

        this.occupied = false;
        this.cleaningStatus = CleaningStatus.CLEAN;
    }

   /*
     * A room is available only if:
     * - It is not occupied
     * - It is CLEAN
     *
     * This ensures business rules are enforced automatically.
     */
    public boolean isAvailable() {
        // Room is only available if it's clean and not occupied
        return !occupied && cleaningStatus == CleaningStatus.CLEAN;
    }

    
    /*
     * Handles the check-in process.
     *
     * Rules enforced:
     * - Room must be available
     * - Number of guests must not exceed capacity
     *
     * A new Booking object is created and stored.
     */
    public void checkIn(Guest guest,
                        int numGuests,
                        int nights,
                        boolean breakfastRequired) {

        if (!isAvailable()) {
            throw new RuntimeException("Accommodation not available");
        }

        if (numGuests > capacity) {
            throw new RuntimeException("Capacity exceeded");
        }
        
        // Calculate check-in and check-out dates
        Date checkInDate = new Date();
        Date checkOutDate = new Date(
                checkInDate.getTime() + (long) nights * 24 * 60 * 60 * 1000
        );

        // Create new booking
        currentBooking = new Booking(
                1,
                guest,
                checkInDate,
                checkOutDate,
                numGuests,
                breakfastRequired
        );

        occupied = true;
    }

    
     /*
     * Handles the check-out process.
     *
     * After check-out:
     * - Room becomes unoccupied
     * - Cleaning status automatically becomes DIRTY
     * - Booking is cleared
     */
    public void checkOut() {
        occupied = false;
        cleaningStatus = CleaningStatus.DIRTY;
        currentBooking = null;
    }

    
    // Getters
    public String getAccommodationNumber() {
        return accommodationNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public float getPrice() {
        return pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public CleaningStatus getCleaningStatus() {
        return cleaningStatus;
    }

    public void setCleaningStatus(CleaningStatus status) {
        this.cleaningStatus = status;
    }

    public Booking getBooking() {
    return currentBooking;
}

    
    /*
     * Returns number of guests from the booking.
     * If no booking exists, returns 0.
     */
public int getNumberOfGuests() {
    if (currentBooking == null) return 0;
    return currentBooking.getNumberOfGuests();
}

    /*
     * Returns whether breakfast is required.
     * If no booking exists, returns false.
     */
public boolean isBreakfastRequired() {
    if (currentBooking == null) return false;
    return currentBooking.isBreakfastRequired();
}

}

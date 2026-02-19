/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.uwe.cedarwoods.system;

/**
 *
 * @author hafsarobleh
 */


import java.util.ArrayList;
import uk.ac.uwe.cedarwoods.model.*;



/*
 * This class represents the core system of Cedar Woods.
 *
 * It acts as the main data holder for:
 * - All areas
 * - All guests
 *
 * It is responsible for setting up the initial data
 * (areas and accommodation types).
 *
 * I separated this from the GUI so that
 * the business logic is independent from the interface.
 */
public class CedarWoodsSystem {
    
    // ATTRIBUTES
    
     /*
     * List of all guests currently created in the system.
     * This could later be extended to store booking history.
     */
    private final ArrayList<Guest> guests;
    
    /*
     * List of all areas in Cedar Woods.
     * Each area contains its own accommodation units.
     */
    private final ArrayList<Area> areas;

    
    // CONSTRUCTOR
    
     /*
     * The constructor sets up the entire campsite.
     *
     * Here I:
     * - Create the areas
     * - Create the accommodation units
     * - Assign units to their correct areas
     *
     * This simulates a real pre-configured system.
     */
    public CedarWoodsSystem() {
           

        guests = new ArrayList<>();
        areas = new ArrayList<>();


        // CREATE AREAS

        Area hilltop = new Area(
                "Hilltop",
                "Overlooks the lake below, with amazing sunrise and sunsets."
        );

        Area woodland = new Area(
                "Woodland",
                "A woodland area echoing the sounds of nature and wildlife."
        );

        Area meadow = new Area(
                "Meadow",
                "Open meadow with peaceful countryside views and fresh air."
        );

        Area lakeview = new Area(
                "Lakeview",
                "Lakeside accommodation with relaxing water views."
        );

     
        // ADD ACCOMMODATION TO AREAS
        
         /*
         * Each area contains different accommodation types.
         * Prices and capacities vary depending on type.
         */

        // Cabins (£160, accommodates 4)
        hilltop.addAccommodation(new Cabin("C1", 4, 160f,
                "A traditional cozy log cabin with small kitchen and toilet."));
        hilltop.addAccommodation(new Cabin("C2", 4, 160f,
                "A traditional cozy log cabin with small kitchen and toilet."));
        hilltop.addAccommodation(new Cabin("C3", 4, 160f,
                "A traditional cozy log cabin with small kitchen and toilet."));
        hilltop.addAccommodation(new Cabin("C4", 4, 160f,
                "A traditional cozy log cabin with small kitchen and toilet."));

        // Geodesic Domes (£120, accommodates 2)
        woodland.addAccommodation(new GeodesicDome("G1", 2, 120f,
                "A modern canvas geodesic dome with large windows for star gazing."));
        woodland.addAccommodation(new GeodesicDome("G2", 2, 120f,
                "A modern canvas geodesic dome with large windows for star gazing."));

        // Yurts (£110, accommodates 2)
        meadow.addAccommodation(new Yurt("Y1", 2, 110f,
                "A traditional yurt with cosy interior and wood burner."));
        meadow.addAccommodation(new Yurt("Y2", 2, 110f,
                "A traditional yurt with cosy interior and wood burner."));

        // Airstreams (£180, accommodates 4)
        lakeview.addAccommodation(new Airstream("A1", 4, 180f,
                "Vintage Airstream with lake views and modern interior."));
        lakeview.addAccommodation(new Airstream("A2", 4, 180f,
                "Vintage Airstream with lake views and modern interior."));
        lakeview.addAccommodation(new Airstream("A3", 4, 180f,
                "Vintage Airstream with lake views and modern interior."));
        lakeview.addAccommodation(new Airstream("A4", 4, 180f,
                "Vintage Airstream with lake views and modern interior."));

        // Add areas to system
        areas.add(hilltop);
        areas.add(woodland);
        areas.add(meadow);
        areas.add(lakeview);
    }

     // GETTERS
    
     /*
     * Returns all areas in the system.
     * Used by the GUI to populate filters.
     */
    public ArrayList<Area> getAreas() {
        return areas;
    }
    
    /*
     * Returns all accommodation across all areas.
     *
     * This method loops through every area and
     * combines their accommodation into one list.
     *
     * This is used to populate the main table.
     */
    public ArrayList<Accommodation> getAllAccommodations() {
        ArrayList<Accommodation> all = new ArrayList<>();
        for (Area area : areas) {
            all.addAll(area.getAccommodations());
        }
        return all;
    }
    
    /*
     * Adds a new guest into the system.
     * This could later be expanded for reporting.
     */
    public void addGuest(Guest guest) {
        guests.add(guest);
    }
}

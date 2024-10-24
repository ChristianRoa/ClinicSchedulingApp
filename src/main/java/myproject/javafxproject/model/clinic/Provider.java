package myproject.javafxproject.model.clinic;

/**
 * Abstract class representing a provider with a profile, location, and specialty.
 *
 * The Provider class extends the Person class and includes information about the provider's location.
 * It also defines an abstract method for calculating the provider's rate.
 *
 * @author Renil Khristi, Christian Roa
 */
public abstract class Provider extends Person {
    private Location location; // The location of the provider

    /**
     * Constructs a Provider object with the specified profile and location.
     *
     * @param profile The profile of the provider.
     * @param location The location where the provider operates.
     */
    public Provider(Profile profile, Location location) {
        super(profile);
        this.location = location;
    }

    /**
     * Gets the location of the provider.
     *
     * @return The location of the provider.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the provider to the specified location.
     *
     * @param location The new location of the provider.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Abstract method to calculate the rate charged by the provider.
     * This method must be implemented by subclasses of Provider.
     *
     * @return The rate charged by the provider.
     */
    public abstract int rate();

    /**
     * Gets the rate charged by the provider by calling the overridden rate method in the subclass.
     *
     * @return The rate charged by the provider.
     */
    public int getRate() {
        return rate();  // Calls the overridden method in the subclass
    }

    /**
     * Returns a string representation of the provider, including the profile, location,
     * county, and zip code.
     *
     * @return A string representing the provider's details.
     */
    @Override
    public String toString() {
        return super.toString() + ", " + location + ", " + location.getCounty() + " " + location.getZip();
    }
}

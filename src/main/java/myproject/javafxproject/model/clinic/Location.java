package myproject.javafxproject.model.clinic;

/**
 * Enum class that represents the location of a healthcare provider,
 * specifically including the county and ZIP code.
 *
 * @author Renil Khristi, Christian Roa
 */
public enum Location {
    BRIDGEWATER("08807", "Somerset"),
    EDISON("08817","Middlesex"),
    PISCATAWAY("08854", "Middlesex"),
    PRINCETON("08542", "Mercer"),
    MORRISTOWN("07960", "Morris"),
    CLARK("07066", "Union");

    private String zip;     // The ZIP code of the location
    private String county; // The county where the provider is located

    /**
     * Creates a Location enum constant with the specified county and ZIP code.
     *
     * @param county The county of the provider's location.
     * @param zip    The ZIP code of the provider's location.
     */
    Location(String zip, String county) {
        this.zip = zip;
        this.county = county;
    }

    /**
     * Gets the county of the provider's location.
     *
     * @return The county as a String.
     */
    public String getCounty() {
        return county;
    }

    /**
     * Gets the ZIP code of the provider's location.
     *
     * @return The ZIP code as a String.
     */
    public String getZip() {
        return zip;
    }
    /**
     * Gets the name of the city (the enum constant's name) in a more user-friendly format.
     *
     * @return The city name as a String.
     */
    public String getCity() {
        return name();
    }
}

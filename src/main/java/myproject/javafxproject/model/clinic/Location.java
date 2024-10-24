package myproject.javafxproject.model.clinic;

/**
 * Enum class that represents the location of a healthcare provider,
 * specifically including the county and ZIP code.
 *
 * @author Renil Khristi, Christian Roa
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county; // The county where the provider is located
    private final String zip;     // The ZIP code of the location

    /**
     * Creates a Location enum constant with the specified county and ZIP code.
     *
     * @param county The county of the provider's location.
     * @param zip    The ZIP code of the provider's location.
     */
    private Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
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
}

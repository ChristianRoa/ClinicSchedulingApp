package myproject.javafxproject.model.clinic;

/**
 * Represents a technician in the clinic, extending the Provider class.
 * The Technician class includes details such as the technician's rate
 * per visit and provides methods to retrieve the technician's rate
 * and to represent the technician as a string.
 *
 * @author Renil Khristi, Christian Roa
 */
public class Technician extends Provider {
    private int ratePerVisit;

    /**
     * Constructs a Technician object with the specified profile, location,
     * and rate per visit.
     *
     * @param profile the profile of the technician
     * @param location the location of the technician
     * @param ratePerVisit the rate charged by the technician per visit
     */
    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * Returns the rate charged by the technician for a visit.
     *
     * @return the technician's rate per visit
     */
    @Override
    public int rate() {
        return ratePerVisit;
    }

    /**
     * Returns a string representation of the technician, including the
     * technician's profile, location, and rate per visit.
     *
     * @return a string representation of the technician
     */
    @Override
    public String toString() {
        return "[" + super.toString() + "] [rate: $" + (double) this.ratePerVisit + "]";
    }
}

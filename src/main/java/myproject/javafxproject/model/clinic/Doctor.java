package myproject.javafxproject.model.clinic;

/**
 * Represents a doctor who provides medical services.
 * Inherits from the {@link Provider} class.
 */
public class Doctor extends Provider {
    private Specialty specialty; // The doctor's specialty
    private String npi; // National Provider Identification unique to the doctor

    /**
     * Constructs a Doctor object with the specified profile, location, specialty, and NPI.
     *
     * @param profile  The profile of the doctor, including personal information.
     * @param location The location where the doctor practices.
     * @param specialty The specialty of the doctor.
     * @param npi      The National Provider Identification number unique to the doctor.
     */
    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    /**
     * Returns the rate charged by the doctor based on their specialty.
     *
     * @return The charge rate for the doctor's specialty.
     */
    @Override
    public int rate() {
        return this.specialty.getCharge();
    }

    /**
     * Returns a string representation of the Doctor object.
     *
     * @return A string containing the doctor's profile, specialty, and NPI.
     */
    @Override
    public String toString() {
        return super.getProfile().getFname() + " " + super.getProfile().getLname() + " (" + this.npi + ")";
    }

    /**
     * Returns the specialty of the doctor.
     *
     * @return The doctor's specialty.
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Returns the National Provider Identification (NPI) number of the doctor.
     *
     * @return The NPI number of the doctor.
     */
    public String getNpi() {
        return npi;
    }
}

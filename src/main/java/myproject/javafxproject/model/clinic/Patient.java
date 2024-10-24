package myproject.javafxproject.model.clinic;

/**
 * Represents a patient with a profile and a list of completed visits.
 *
 * The Patient class is responsible for managing patient data, including
 * their profile information and associated visits (appointments).
 *
 * @author Renil Khristi, Christian Roa
 */
public class Patient extends Person {
    private Visit visits; // A linked list of visits (completed appointments)

    /**
     * Creates a Patient with the specified profile and visits.
     *
     * @param profile The profile of the patient.
     * @param visits  The linked list of completed visits for the patient.
     */
    Patient(Profile profile, Visit visits) {
        super(profile);
        this.visits = visits;
    }

    /**
     * Gets the profile of the patient.
     *
     * @return The patient's profile.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Gets the linked list of visits for the patient.
     *
     * @return The visits associated with the patient.
     */
    public Visit getVisits() {
        return visits;
    }

    /**
     * Sets the profile of the patient to the specified profile.
     *
     * @param profile The new profile to set for the patient.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Sets the visits of the patient to the specified visits.
     *
     * @param visits The new linked list of visits to set for the patient.
     */
    public void setVisits(Visit visits) {
        this.visits = visits;
    }

    /**
     * Calculates the cumulative charge for all visits of the patient.
     *
     * @return The total charge as an integer.
     */
    public int charge() {
        int totalCharge = 0;
        if (visits == null) {
            return totalCharge;
        }
        Visit currentVisit = visits;
        while (currentVisit != null) {
            Person provider = currentVisit.getAppointment().getProvider();
            if (provider instanceof Provider) {
                totalCharge += ((Provider) provider).rate();
            }
            currentVisit = currentVisit.getNext();
        }
        return totalCharge;
    }

    /**
     * Displays the information of the patient's profile and visits.
     *
     * @return A string representation of the patient's profile and visits.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Compares this patient's profile to the profile of another person.
     *
     * @param obj The person to compare to.
     * @return An integer representing the comparison result.
     */
    @Override
    public int compareTo(Person obj) {
        return this.profile.compareTo(obj.getProfile());
    }
}

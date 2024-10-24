package myproject.javafxproject.model.clinic;

import myproject.javafxproject.model.utilities.Date;

/**
 * Represents a person with a profile containing personal information.
 *
 * The Person class is responsible for managing the profile of a person
 * and allows for comparisons based on first name, last name, and date of birth.
 *
 * Implements the Comparable interface to facilitate sorting of Person objects.
 *
 * @author Renil Khristi, Christian Roa
 */
public class Person implements Comparable<Person> {
    protected Profile profile; // The profile of the person

    /**
     * Creates a Person with the specified profile.
     *
     * @param profile The profile containing personal information of the person.
     */
    public Person(Profile profile) {
        this.profile = profile;
    }

    /**
     * Gets the profile of the person.
     *
     * @return The profile of the person.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets the profile of the person to the specified profile.
     *
     * @param profile The new profile to set for the person.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Compares this person with another person for order.
     * Comparison is based on first name, then last name, and finally date of birth.
     *
     * @param person The person to compare to.
     * @return A negative integer, zero, or a positive integer as this person
     *         is less than, equal to, or greater than the specified person.
     */
    @Override
    public int compareTo(Person person) {
        int fNameCompare = this.profile.getFname().compareTo(person.getProfile().getFname());
        if (fNameCompare != 0) {
            return fNameCompare;
        }
        int lNameCompare = this.profile.getLname().compareTo(person.getProfile().getLname());
        if (lNameCompare != 0) {
            return lNameCompare;
        }
        return this.getProfile().dob().compareTo(person.getProfile().dob());
    }

    /**
     * Returns a string representation of the person's profile,
     * including their first name, last name, and date of birth.
     *
     * @return A string containing the person's full name and date of birth.
     */
    @Override
    public String toString() {
        return this.profile.getFname() + " " + this.profile.getLname() + " " + this.profile.dob();
    }
}

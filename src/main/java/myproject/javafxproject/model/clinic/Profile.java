package myproject.javafxproject.model.clinic;

import myproject.javafxproject.model.utilities.Date;

/**
 * Creates a class for profiles that are composed of the date along with the first and last name of the patient.
 *
 * The Profile class represents a patient's profile, which includes their first name, last name, and date of birth.
 * It implements the Comparable interface to allow comparison between different Profile objects based on these attributes.
 *
 * @author Renil Khristi, Christian Roa
 */
public class Profile implements Comparable<Profile> {
	private String fname; // The first name of the patient
	private String lname; // The last name of the patient
	private Date dob;     // The date of birth of the patient

	/**
	 * Constructs a Profile object with the specified first name, last name, and date of birth.
	 *
	 * @param fname The first name of the patient.
	 * @param lname The last name of the patient.
	 * @param dob   The date of birth of the patient.
	 */
	public Profile(String fname, String lname, Date dob) {
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
	}

	/**
	 * Gets the first name of the patient.
	 *
	 * @return The first name of the patient.
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Gets the last name of the patient.
	 *
	 * @return The last name of the patient.
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Gets the date of birth of the patient.
	 *
	 * @return The date of birth of the patient.
	 */
	public Date dob() {
		return dob;
	}

	/**
	 * Sets the first name of the patient to the specified name.
	 *
	 * @param fname The new first name of the patient.
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * Sets the last name of the patient to the specified name.
	 *
	 * @param lname The new last name of the patient.
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * Sets the date of birth to the specified date.
	 *
	 * @param dob The new date of birth for the patient.
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Determines if this profile is equal to another object.
	 * Two profiles are considered equal if their first names, last names,
	 * and dates of birth are the same.
	 *
	 * @param obj The object to compare to.
	 * @return True if the profiles are the same; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Profile) {
			Profile other = (Profile) obj;
			return this.fname.equals(other.fname) && this.lname.equals(other.lname) && this.dob.equals(other.dob);
		}
		return false;
	}

	/**
	 * Returns a string representation of the profile,
	 * including the first name, last name, and date of birth.
	 *
	 * @return A string containing the first name, last name, and date of birth.
	 */
	@Override
	public String toString() {
		return this.fname + " " + this.lname + " " + this.dob.toString();
	}

	/**
	 * Compares this profile with another profile for order.
	 * The comparison is based on first name, last name, and then date of birth.
	 *
	 * @param obj The profile to compare to.
	 * @return A negative integer, zero, or a positive integer as this profile
	 *         is less than, equal to, or greater than the specified profile.
	 */
	@Override
	public int compareTo(Profile obj) {
		int fnameCompare = this.fname.compareTo(obj.fname);
		if (fnameCompare > 0) {
			return 1;
		}
		else if (fnameCompare < 0) {
			return -1;
		}
		int lnameCompare = this.lname.compareTo(obj.lname);
		if (lnameCompare > 0) {
			return 1;
		}
		if (lnameCompare < 0) {
			return -1;
		}
		return this.dob.compareTo(obj.dob);
	}

	/**
	 * The main method that serves as a testbed for the compareTo method.
	 * It creates various Profile instances and compares them.
	 *
	 * @param args Command line arguments (not used).
	 */
	public static void main(String[] args) {
		Date date1 = new Date(2024, 2, 20);
		Date date2 = new Date(2023, 1, 19);
		Date date3 = new Date(2022, 3, 21);
		Profile profile1 = new Profile("John", "Smith", date1);
		Profile profile2 = new Profile("Jane", "Doe", date2);
		Profile profile3 = new Profile("John", "Smith", date1);
		Profile profile4 = new Profile("Lol", "Smith", date3);

		System.out.println(profile1.compareTo(profile2));
		System.out.println(profile1.compareTo(profile3));
		System.out.println(profile2.compareTo(profile3));
		System.out.println(profile1.compareTo(profile4));
		System.out.println(profile2.compareTo(profile4));
		System.out.println(profile4.compareTo(profile3));
		System.out.println(profile4.compareTo(profile2));
	}
}

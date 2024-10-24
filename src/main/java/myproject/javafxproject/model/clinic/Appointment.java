package myproject.javafxproject.model.clinic;

import myproject.javafxproject.model.utilities.Date;

/**
 * This class models an appointment with attributes for date, timeslot, patient, and provider.
 *
 * <p>Author: Christian Roa, Renil Khristi</p>
 */
public class Appointment implements Comparable<Appointment> {
    /**
     * The date and first entry of an Appointment
     */
    protected Date date;
    /**
     * The selected Timeslot and second entry of an Appointment
     */
    protected Timeslot timeslot;
    /**
     * The Person type patient and third entry of an Appointment
     */
    protected Person patient;
    /**
     * The Person type provider and fourth entry of an Appointment
     */
    protected Person provider;

    /**
     * Constructor to create an Appointment object with the given date, timeslot, patient, and provider.
     *
     * @param date     the date of the appointment
     * @param timeslot the timeslot of the appointment
     * @param patient  the patient associated with the appointment
     * @param provider the provider for the appointment
     */
    public Appointment(Date date, Timeslot timeslot, Person patient, Person provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Retrieves the date of the appointment.
     *
     * @return the date of the appointment
     */
    public Date getDate() {
        return date;
    }

    /**
     * Retrieves the timeslot of the appointment.
     *
     * @return the timeslot of the appointment
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Retrieves the patient profile associated with the appointment.
     *
     * @return the patient profile
     */
    public Person getPatient() {
        return patient;
    }

    /**
     * Retrieves the patient as a Patient object if the patient is indeed a Patient,
     * otherwise returns null.
     *
     * @return the patient as a Patient object or null if not applicable
     */
    public Patient getPatientAsPatient() {
        if (patient instanceof Patient) {
            return (Patient) patient;
        }
        return null; // In case the patient is not a Patient instance
    }

    /**
     * Retrieves the provider of the appointment.
     *
     * @return the provider of the appointment
     */
    public Person getProvider() {
        return provider;
    }

    /**
     * Retrieves the provider as a Provider object if the provider is indeed a Provider,
     * otherwise returns null.
     *
     * @return the provider as a Provider object or null if not applicable
     */
    public Provider getProviderAsProvider() {
        if (provider instanceof Provider) {
            return (Provider) provider;
        }
        return null; // In case the provider is not a Provider instance
    }

    /**
     * Updates the date of the appointment.
     *
     * @param date the new date of the appointment
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Updates the timeslot of the appointment.
     *
     * @param timeslot the new timeslot of the appointment
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Updates the patient profile associated with the appointment.
     *
     * @param patient the new patient profile
     */
    public void setPatient(Person patient) {
        this.patient = patient;
    }

    /**
     * Updates the provider of the appointment.
     *
     * @param provider the new provider of the appointment
     */
    public void setProvider(Person provider) {
        this.provider = provider;
    }

    /**
     * Compares this appointment to another appointment.
     * The comparison is based on date, timeslot, patient, and provider in that order.
     *
     * @param obj the appointment to be compared
     * @return a negative integer, zero, or a positive integer if this appointment is less than,
     *         equal to, or greater than the specified appointment
     */
    @Override
    public int compareTo(Appointment obj) {
        int dateCompare = this.date.compareTo(obj.date);
        if (dateCompare != 0) {
            return dateCompare;
        }

        int timeslotCompare = this.timeslot.compareTo(obj.timeslot);
        if (timeslotCompare != 0) {
            return timeslotCompare;
        }

        int patientCompare = this.patient.compareTo(obj.patient);
        if (patientCompare != 0) {
            return patientCompare;
        }
        return this.provider.compareTo(obj.provider);
    }

    /**
     * Returns a string representation of the appointment, including date, timeslot, patient, and provider.
     *
     * @return a string representation of the appointment
     */
    @Override
    public String toString() {
        return this.date.toString() + " " + this.timeslot + " " + this.patient.toString() + " " + this.provider.toString();
    }

    /**
     * Checks whether this appointment is equal to another object.
     * The appointments are considered equal if they have the same date, timeslot, and patient.
     *
     * @param obj the object to compare to
     * @return true if the appointments are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Appointment) {
            Appointment other = (Appointment) obj;
            return this.date.equals(other.date) && this.timeslot.equals(other.timeslot) && this.patient.equals(other.patient);
        }
        return false;
    }
}

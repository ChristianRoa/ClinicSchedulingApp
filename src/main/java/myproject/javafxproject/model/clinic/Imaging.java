package myproject.javafxproject.model.clinic;

import myproject.javafxproject.model.utilities.Date;

/**
 * Represents an imaging appointment in a medical clinic.
 * This class extends the {@link Appointment} class and includes details specific to imaging services.
 */
public class Imaging extends Appointment {
    private Radiology room; // The radiology room assigned for the imaging appointment

    /**
     * Constructs an Imaging object with the specified date, timeslot, patient, provider, and radiology room.
     *
     * @param date     The date of the appointment.
     * @param timeslot The timeslot of the appointment.
     * @param patient  The patient for the appointment.
     * @param provider The provider (doctor or technician) for the appointment.
     * @param room     The radiology room assigned for the imaging appointment.
     */
    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider, Radiology room) {
        super(date, timeslot, patient, provider);
        this.room = room;
    }

    /**
     * Returns the radiology room assigned for the imaging appointment.
     *
     * @return The radiology room for the appointment.
     */
    public Radiology getRoom() {
        return room;
    }

    /**
     * Sets the radiology room for the imaging appointment.
     *
     * @param room The radiology room to be assigned for the appointment.
     */
    public void setRoom(Radiology room) {
        this.room = room;
    }
}

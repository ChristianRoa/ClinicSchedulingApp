package myproject.javafxproject.model.clinic;
/**
 * This class implements the node structure and functionality for a linked list.
 * @author Christian Roa, Renil Khristi
 */
public class Visit {
    private Appointment appointment;
    private Visit next;

    /**
     * Default constructor that initializes a Visit with null appointment and next pointer.
     */
    public Visit() {
        this.appointment = null;
        this.next = null;
    }

    /**
     * Constructor that initializes a Visit with a given appointment and the reference to the next Visit.
     *
     * @param appointment The appointment for this visit.
     * @param next The next visit in the list.
     */
    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    /**
     * Sets the appointment for this visit.
     *
     * @param appointment The appointment to set.
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * Sets the reference to the next visit in the list.
     *
     * @param next The next Visit to set.
     */
    public void setNext(Visit next) {
        this.next = next;
    }

    /**
     * Retrieves the appointment associated with this visit.
     *
     * @return The appointment of this visit.
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Retrieves the reference to the next visit in the chain.
     *
     * @return The next Visit.
     */
    public Visit getNext() {
        return next;
    }

}

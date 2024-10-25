package myproject.javafxproject.model.clinic;

/**
 * Represents a timeslot for appointments, defined by an hour and minute.
 * This class provides functionality to validate timeslots, compare them,
 * and convert them to a string representation.
 *
 * @author Christian Roa, Renil Khristi
 */
public class Timeslot implements Comparable<Timeslot> {
    private final int hour;
    private final int minute;

    // Valid timeslots
    public static final int[][] validTimeslots = {
            {9, 0}, {9, 30}, {10, 0}, {10, 30}, {11, 0}, {11, 30},
            {14, 0}, {14, 30}, {15, 0}, {15, 30}, {16, 0}, {16, 30}
    };

    /**
     * Constructs a Timeslot object with the specified hour and minute.
     *
     * @param hour the hour of the timeslot (0-23)
     * @param minute the minute of the timeslot (0-59)
     */
    public Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Validates if the provided hour and minute correspond to a valid timeslot.
     *
     * @param hour the hour to validate
     * @param minute the minute to validate
     * @return true if the timeslot is valid, false otherwise
     */
    public boolean validTimeslot(int hour, int minute) {
        for (int[] timeslot : validTimeslots) {
            if (hour == timeslot[0] && minute == timeslot[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a Timeslot based on the given index string.
     * The index corresponds to the order of valid timeslots starting from 1.
     *
     * @param indexStr the string representation of the index
     * @return a Timeslot object if the index is valid, null otherwise
     */
    public static Timeslot getTimeSlot(String indexStr) {
        int index;
        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            return null;
        }
        if (index < 1 || index > validTimeslots.length) {
            return null;
        }
        int[] timeslot = validTimeslots[index - 1];
        return new Timeslot(timeslot[0], timeslot[1]);
    }

    /**
     * Compares this Timeslot to another Timeslot for ordering.
     *
     * @param other the Timeslot to compare to
     * @return a negative integer, zero, or a positive integer as this
     *         Timeslot is less than, equal to, or greater than the specified
     *         Timeslot
     */
    @Override
    public int compareTo(Timeslot other) {
        if (this.hour != other.hour) {
            return this.hour - other.hour;
        } else {
            return this.minute - other.minute;
        }
    }

    /**
     * Returns a string representation of the Timeslot in HH:MM format.
     *
     * @return the string representation of the Timeslot
     */
    @Override
    public String toString() {
        int displayHour = (hour == 0 || hour == 12) ? 12 : hour % 12;
        String period = hour < 12 ? "AM" : "PM";
        return String.format("%02d:%02d %s", displayHour, minute, period);
    }

    /**
     * Indicates whether some other object is "equal to" this Timeslot.
     *
     * @param obj the reference object with which to compare
     * @return true if this Timeslot is the same as the obj; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Timeslot other = (Timeslot) obj;
        return this.hour == other.hour && this.minute == other.minute;
    }
}

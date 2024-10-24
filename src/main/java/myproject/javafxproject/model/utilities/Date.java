package myproject.javafxproject.model.utilities;


import java.util.Calendar;

/**
 * This class implements Date functionality with year, month, and day attributes.
 * @author Christian Roa, Renil Khristi
 */
public class Date implements Comparable<Date> {
    /**
     * Integer representation of year
     */
    private int year;
    /**
     * Integer representation of month
     */
    private int month;
    /**
     * Integer representation of day
     */
    private int day;

    /**
     * Constructs a Date object with the specified year, month, and day.
     *
     * @param year  The year of the date.
     * @param month The month of the date (1-12).
     * @param day   The day of the date (1-31).
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Returns the year of the date.
     *
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the month of the date.
     *
     * @return The month (1-12).
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the day of the date.
     *
     * @return The day (1-31).
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the year of the date.
     *
     * @param year The year to set.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the month of the date.
     *
     * @param month The month to set (1-12).
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Sets the day of the date.
     *
     * @param day The day to set (1-31).
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Compares this Date object with another Date object.
     *
     * @param other The Date to compare with.
     * @return A negative integer, zero, or a positive integer if this Date is less than, equal to, or greater than the specified date.
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
    }

    /**
     * Checks if this Date is equal to another object.
     *
     * @param obj The object to compare.
     * @return true if the object is a Date with the same year, month, and day; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return date.year == year && date.month == month && date.day == day;
        }
        return false;
    }

    /**
     * Returns the date in "mm/dd/yyyy" format.
     *
     * @return The date as a string.
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Checks if the date falls on a weekday.
     *
     * @return true if the date is a weekday (Monday-Friday); false otherwise.
     */
    public boolean isWeekday() {
        Calendar cal = Calendar.getInstance();
        cal.set(this.year, this.month - 1, this.day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY;
    }

    /**
     * Returns the current date (today's date).
     *
     * @return Today's date as a Date object.
     */
    public static Date today() {
        Calendar rightNow = Calendar.getInstance();
        return new Date(rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH) + 1, rightNow.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Checks if the date is before today's date.
     *
     * @return true if the date is before today; false otherwise.
     */
    public boolean isBeforeToday() {
        Calendar rightNow = Calendar.getInstance();
        Calendar thisDate = Calendar.getInstance();
        thisDate.set(this.year, this.month - 1, this.day);
        return thisDate.before(rightNow);
    }

    /**
     * Checks if the date is within six months from today.
     *
     * @return true if the date is within six months from today; false otherwise.
     */
    public boolean isWithinSixMonths() {
        Calendar today = Calendar.getInstance();
        Calendar sixMonthsFromToday = Calendar.getInstance();
        sixMonthsFromToday.add(Calendar.MONTH, 6);

        Calendar thisDate = Calendar.getInstance();
        thisDate.set(this.year, this.month - 1, this.day);

        return thisDate.after(today) && !thisDate.after(sixMonthsFromToday);
    }


    /**
     * Validates if the date is a valid date. A valid date must be in the future, on a weekday, and within six months.
     *
     * @return true if the date is valid; false otherwise.
     */
    public boolean isValidDate() {
        Calendar dateToValidate = Calendar.getInstance();
        dateToValidate.setLenient(false);

        try {
            dateToValidate.set(this.year, this.month - 1, this.day);
            dateToValidate.getTime();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

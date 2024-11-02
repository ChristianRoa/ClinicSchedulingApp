package myproject.javafxproject.model.utilities;


import myproject.javafxproject.model.clinic.Appointment;
import myproject.javafxproject.model.clinic.Patient;
import myproject.javafxproject.model.clinic.Provider;

/**
 * A utility class for sorting lists of appointments and providers.
 * This class contains methods for sorting appointments by various keys
 * such as date, patient details, and location, as well as sorting
 * providers by their profiles.
 */
public class Sort {

    /**
     * Sorts the given list of appointments by appointment date, timeslot,
     * provider's last name, and provider's first name.
     *
     * @param list the list of appointments to be sorted
     */
    private static void sortByApp(List<Appointment> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                // Compare by date
                int dateCompare = a1.getDate().compareTo(a2.getDate());
                if (dateCompare > 0 ||
                        (dateCompare == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0) ||
                        (dateCompare == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) == 0 &&
                                a1.getProviderAsProvider().getProfile().getLname().compareTo(a2.getProviderAsProvider().getProfile().getLname()) > 0) ||
                        (dateCompare == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) == 0 &&
                                a1.getProviderAsProvider().getProfile().getLname().compareTo(a2.getProviderAsProvider().getProfile().getLname()) == 0 &&
                                a1.getProviderAsProvider().getProfile().getFname().compareTo(a2.getProviderAsProvider().getProfile().getFname()) > 0)) {
                    // Swap if any comparison indicates a1 > a2
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sorts the given list of appointments by patient details, including
     * last name, first name, date of birth, appointment date, and timeslot.
     *
     * @param list the list of appointments to be sorted
     */
    private static void sortByPatient(List<Appointment> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                // Compare patients by last name, first name, then date of birth
                int lastNameCompare = a1.getPatient().getProfile().getLname().compareTo(a2.getPatient().getProfile().getLname());
                if (lastNameCompare > 0 ||
                        (lastNameCompare == 0 && a1.getPatient().getProfile().getFname().compareTo(a2.getPatient().getProfile().getFname()) > 0) ||
                        (lastNameCompare == 0 && a1.getPatient().getProfile().getFname().compareTo(a2.getPatient().getProfile().getFname()) == 0 &&
                                a1.getPatient().getProfile().dob().compareTo(a2.getPatient().getProfile().dob()) > 0) ||
                        (lastNameCompare == 0 && a1.getPatient().getProfile().getFname().compareTo(a2.getPatient().getProfile().getFname()) == 0 &&
                                a1.getPatient().getProfile().dob().compareTo(a2.getPatient().getProfile().dob()) == 0 &&
                                a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (lastNameCompare == 0 && a1.getPatient().getProfile().getFname().compareTo(a2.getPatient().getProfile().getFname()) == 0 &&
                                a1.getPatient().getProfile().dob().compareTo(a2.getPatient().getProfile().dob()) == 0 &&
                                a1.getDate().compareTo(a2.getDate()) == 0 &&
                                a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    // Swap if any comparison indicates a1 > a2
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sorts the given list of appointments by the location of the provider.
     *
     * @param list the list of appointments to be sorted
     */
    private static void sortByLocation(List<Appointment> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                int countyCompare = a1.getProviderAsProvider().getLocation().getCounty().compareTo(a2.getProviderAsProvider().getLocation().getCounty());
                if (countyCompare > 0 ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sorts the list of appointments based on billing criteria.
     *
     * @param list the list of appointments to be sorted
     */
    private static void sortBill(List<Appointment> list) {
        // Implementation needed for sorting by bill
    }

    /**
     * Sorts the list of appointments based on the specified key.
     *
     * @param list the list of appointments to be sorted
     * @param key the key used to determine the sorting criteria
     * @throws IllegalArgumentException if the key is invalid
     */
    public static void appointment(List<Appointment> list, char key) {
        switch (key) {
            case 'A':
                sortByApp(list);
                break;
            case 'P':
                sortByPatient(list);
                break;
            case 'L':
            case 'O':
            case 'I':
                sortByLocation(list);
                break;
            case 'S':
                sortBill(list);
                break;
            default:
                throw new IllegalArgumentException("Invalid key");
        }
    }

    /**
     * Sorts the list of providers based on their profiles.
     *
     * @param list the list of providers to be sorted
     */
    private static void sortProviderProfile(List<Provider> list) {
        // Implementation needed for sorting providers by profile
    }

    /**
     * Sorts the list of providers by their last names and locations.
     *
     * @param list the list of providers to be sorted
     */
    private static void sortProviders(List<Provider> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Provider p1 = list.get(j);
                Provider p2 = list.get(j + 1);

                // Compare last names
                int lastNameComparison = p1.getProfile().getLname().compareTo(p2.getProfile().getLname());
                if (lastNameComparison > 0) {
                    // Swap if p1's last name is greater than p2's last name
                    Provider temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                } else if (lastNameComparison == 0) {
                    // If last names are equal, compare locations
                    if (p1.getLocation().compareTo(p2.getLocation()) > 0) {
                        // Swap if p1's location is greater than p2's location
                        Provider temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }
    }

    /**
     * Sorts the list of providers.
     *
     * @param list the list of providers to be sorted
     */
    public static void provider(List<Provider> list) {
        sortProviders(list);
    }
}

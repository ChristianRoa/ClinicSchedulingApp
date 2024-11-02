package myproject.javafxproject.model.utilities;

import myproject.javafxproject.model.clinic.Provider;

/**
 * A CircularLinkedList implementation that holds Provider objects.
 * This list allows for adding providers, retrieving the head,
 * printing the list of technician providers, and sorting them based on specific criteria.
 *
 * @author Christian Roa
 */
public class CircularLinkedList {
    /**
     * The first Node in the Circular linked list
     */
    private CircularNode head;
    private CircularNode tail;
    private CircularNode current; // New field for the current node
    private int size;

    // Constructor
    public CircularLinkedList() {
        head = null;
        tail = null;
        current = null; // Initialize current to null
        size = 0;
    }
    public static class CircularNode {

        Provider data;
        CircularNode next;

        /**
         * Constructs a CircularNode with the given Provider data.
         *
         * @param data the Provider data to store in the node
         */
        CircularNode(Provider data) {
            this.data = data;
        }

        /**
         * Gets the next node in the circular linked list.
         *
         * @return the next CircularNode
         */
        public CircularNode getNext() {
            return next;
        }

        /**
         * Gets the Provider data stored in this node.
         *
         * @return the Provider data
         */
        public Provider getData() {
            return data;
        }
    }


    /**
     * Adds a new Provider to the circular linked list.
     *
     * @param provider the Provider to add to the list
     */
    public void add(Provider provider) {
        CircularNode newNode = new CircularNode(provider);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head; // Circular link
            current = head; // Set current to head
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Maintain circular link
        }
        size++;
    }

    // Get the current node
    public CircularNode getCurrent() {
        return current;
    }

    // Move the current pointer to the next node
    public void moveToNext() {
        if (current != null) {
            current = current.next != null ? current.next : head;
        }
    }


    /**
     * Gets the number of nodes in the circular linked list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }


    /**
     * Gets the head node of the circular linked list.
     *
     * @return the head CircularNode
     */
    public CircularNode getHead() {
        return head;
    }

    /**
     * Prints the names and locations of all technician providers in the list.
     * The list is printed in a circular format with arrows indicating the next node.
     */

    /**
     * Sorts the providers in the circular linked list based on their location and last name.
     * The sorting is done in-place.
     */
    public void sort() {
        if (head == null || head.next == head) return; // Check if list is empty or has only one element
        CircularNode current;
        CircularNode index;
        Provider temp;

        for (current = head; current.next != head; current = current.next) {
            for (index = current.next; index != head; index = index.next) {
                // Compare by location first
                int locationComparison = current.data.getLocation().compareTo(index.data.getLocation());
                if (locationComparison > 0 ||
                        (locationComparison == 0 && current.data.getProfile().getLname().compareTo(index.data.getProfile().getLname()) < 0)) {
                    // Swap if current's location is greater than index's or if locations are equal and current's last name is greater
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
            }
        }
    }
}

package myproject.javafxproject.model.utilities;


import java.util.Iterator;

/**
 * A generic List implementation that supports dynamic array-like behavior.
 * It provides methods for adding, removing, and accessing elements,
 * as well as iterating over the list.
 *
 * @param <E> the type of elements in this list
 */
public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;
    private static final int untrue = -1;

    /**
     * Constructs an empty List with a default capacity.
     */
    @SuppressWarnings("unchecked")
    public List() {
        objects = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Finds the index of the specified element in the list.
     *
     * @param e the element to find
     * @return the index of the element, or -1 if not found
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Doubles the capacity of the list when it is full.
     */
    private void grow() {
        @SuppressWarnings("unchecked")
        E[] newObjects = (E[]) new Object[objects.length * 2];
        System.arraycopy(objects, 0, newObjects, 0, size);
        objects = newObjects;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param e the element to check
     * @return true if the element is found, false otherwise
     */
    public boolean contains(E e) {
        return find(e) != untrue;
    }

    /**
     * Adds a new element to the end of the list.
     *
     * @param e the element to add
     */
    public void add(E e) {
        if (size == objects.length) {
            grow();
        }
        objects[size++] = e;
    }

    /**
     * Removes the specified element from the list.
     *
     * @param e the element to remove
     */
    public void remove(E e) {
        int index = find(e);
        if (index != untrue) {
            System.arraycopy(objects, index + 1, objects, index, size - index - 1);
            size--;
        }
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the number of elements in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in the list.
     *
     * @return an iterator for the list
     */
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Gets the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return objects[index];
    }

    /**
     * Replaces the element at the specified index with the given element.
     *
     * @param index the index of the element to replace
     * @param e the new element to set
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        objects[index] = e;
    }

    /**
     * Returns the index of the specified element in the list.
     *
     * @param e the element to find
     * @return the index of the element, or -1 if not found
     */
    public int indexOf(E e) {
        return find(e);
    }

    /**
     * An iterator for the List that allows for iterating over its elements.
     */
    private class ListIterator implements Iterator<E> {
        private int currentIndex = 0;

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return true if there are more elements, false otherwise
         */
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element
         * @throws RuntimeException if there are no more elements
         */
        public E next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements in the iterator.");
            }
            return objects[currentIndex++];
        }
    }
}

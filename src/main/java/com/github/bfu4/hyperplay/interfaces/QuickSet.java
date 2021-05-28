package com.github.bfu4.hyperplay.interfaces;

import java.util.Arrays;
import java.util.stream.Stream;

public final class QuickSet<E> {

    /**
     * Maximum size of elements before beginning the rotation.
     */
    private final int maxSize;

    /**
     * Elements.
     */
    private transient Object[] elements;

    /**
     * Concurrency lock.
     */
    private final Object lock;

    /**
     * Create a new quick set, maxed out at the integer max.
     */
    public QuickSet() {
        // Integer max
        this(Integer.MAX_VALUE);
    }

    /**
     * Create a new list.
     *
     * @param maximumElementSize max size of the list
     */
    public QuickSet(final int maximumElementSize) {
        this.maxSize = maximumElementSize;
        this.lock = new Object();

        elements = new Object[0];
    }

    /**
     * Set the item at the specified index.
     *
     * @param index index to set the element at
     * @param element element to set
     * @throws IndexOutOfBoundsException if the index is larger than the dynamic {@link QuickSet#size()}
     */
    public void set(final int index, final E element) throws IndexOutOfBoundsException {
        if (elements.length == 0) {
            elements = new Object[1];
            elements[0] = element;
        } else {
            if (index != elements.length && index < elements.length) {
                elements[index] = element;
                return;
            }
            throw new IndexOutOfBoundsException("Index is larger than the current length of the dynamic LimitedList!");
        }
    }

    /**
     * Add an element to the list.
     *
     * @param element element to add
     */
    public void add(final E element) {
        synchronized (lock) {
            // Anti-dupe, it IS a set after all
            if (indexOf(element) != -1) {
                return;
            }
            if (elements.length == maxSize) {
                // Scoot objects over
                for (int i = 1; i < elements.length; i++) {
                    elements[i - 1] = elements[i];
                }
                elements[maxSize - 1] = element;
            } else {
                int maxRealloc = (elements.length >= 1) ? elements.length + 1 : 1;
                Object[] tempArray = new Object[maxRealloc];

                for (int i = 0; i < elements.length; i++) {
                    tempArray[i] = elements[i];
                }

                tempArray[maxRealloc - 1] = element;
                elements = tempArray;
            }
        }
    }

    /**
     * Remove element from list.
     *
     * @param element element to remove
     */
    public void remove(final E element) {
        synchronized (lock) {
            int index = indexOf(element);
            if (index == -1) {
                return;
            }

            elements[index] = null;
            shiftOverNull();
        }
    }

    /**
     * Get element from list.
     *
     * @param index index of element
     * @return element
     */
    @SuppressWarnings("unchecked")
    public E get(final int index) {
        return (E) elements[index];
    }

    /**
     * Get the index of the given element.
     *
     * @param element element to get the index of
     * @return index of the element, or -1 if not found.
     */
    public int indexOf(final E element) {
        int index = -1;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                continue;
            }
            if (elements[i].equals(element)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Turn this list into an array.
     *
     * @param arr array to use as a type to cast
     * @return an array of the elements stored in this list.
     */
    @SuppressWarnings("unchecked")
    public E[] toArray(final E[] arr) {
        if (elements.length == 0 || elements[0] == null) {
            return null;
        }
        return (E[]) Arrays.copyOf(elements, elements.length, arr.getClass());
    }

    /**
     * Shift over null values.
     */
    private void shiftOverNull() {
        Object[] newArray = new Object[elements.length - nullElementAmount()];
        int currentIndex = 0;
        for (Object element: elements) {
            if (element != null) {
                newArray[currentIndex] = element;
                currentIndex++;
            }
        }
        elements = newArray;
    }

    /**
     * Get the amount of null elements.
     *
     * @return amount of elements null
     */
    private int nullElementAmount() {
        int amount = 0;
        for (Object element : elements) {
            if (element == null) {
                amount++;
            }
        }
        return amount;
    }

    /**
     * Get the amount of items stored in this list.
     *
     * @return amount of items stored in the list
     */
    public int size() {
        return elements.length;
    }

    /**
     * Clear the entire list.
     */
    public void clear() {
        elements = new Object[0];
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    /**
     * Retrieve a stream of the elements.
     * @return      stream
     */
    public Stream<E> stream() {
        return (Stream<E>) Arrays.stream(elements);
    }

}

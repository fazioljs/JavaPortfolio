/*
 * Joseph Fazioli
 * 4/1/2024
 * CSE274 HA
 */

import java.util.Arrays;

/**
 * An implementation of the BagInterface using an array.
 */
public class ArrayBag implements BagInterface {
    private static final int DEFAULT_CAPACITY = 10;
    private String[] bag;
    private int size;

    /**
     * Creates an empty bag with a default capacity of 10.
     */
    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty bag with the specified capacity.
     *
     * @param capacity the initial capacity of the bag
     */
    public ArrayBag(int capacity) {
        bag = new String[capacity];
        size = 0;
    }

    /**
     * Returns the number of elements in the bag.
     *
     * @return the number of elements in the bag
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the bag is empty.
     *
     * @return true if the bag is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds a new entry to the bag.
     *
     * @param newEntry the new entry to add
     * @return true if the addition is successful, false otherwise
     */
    @Override
    public boolean add(String newEntry) {
        ensureCapacity();
        bag[size] = newEntry;
        size++;
        return true;
    }

    /**
     * Removes an unspecified entry from the bag.
     *
     * @return the removed entry, or null if the bag is empty
     */
    @Override
    public String remove() {
        if (isEmpty()) {
            return null;
        }
        size--;
        String removed = bag[size];
        bag[size] = null;
        return removed;
    }

    /**
     * Removes a specified entry from the bag.
     *
     * @param anEntry the entry to remove
     * @return true if the removal is successful, false otherwise
     */
    @Override
    public boolean remove(String anEntry) {
        for (int i = 0; i < size; i++) {
            if (bag[i].equals(anEntry)) {
                size--;
                bag[i] = bag[size];
                bag[size] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all entries from the bag.
     */
    @Override
    public void clear() {
        Arrays.fill(bag, null);
        size = 0;
    }

    /**
     * Gets the frequency of a specified entry in the bag.
     *
     * @param anEntry the entry to check for frequency
     * @return the frequency of the entry in the bag
     */
    @Override
    public int getFrequencyOf(String anEntry) {
        int frequency = 0;
        for (int i = 0; i < size; i++) {
            if (bag[i].equals(anEntry)) {
                frequency++;
            }
        }
        return frequency;
    }

    /**
     * Checks if the bag contains a specified entry.
     *
     * @param anEntry the entry to check for
     * @return true if the bag contains the entry, false otherwise
     */
    @Override
    public boolean contains(String anEntry) {
        for (int i = 0; i < size; i++) {
            if (bag[i].equals(anEntry)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts the bag into an array.
     *
     * @return an array containing all the entries in the bag
     */
    @Override
    public String[] toArray() {
        return Arrays.copyOf(bag, size);
    }

    /**
     * Removes duplicate entries from the bag.
     */
    @Override
    public void removeDuplicates() {
        String[] uniqueElements = new String[size];
        int uniqueIndex = 0;
        for (int i = 0; i < size; i++) {
            if (!contains(uniqueElements, bag[i])) {
                uniqueElements[uniqueIndex] = bag[i];
                uniqueIndex++;
            }
        }
        bag = Arrays.copyOf(uniqueElements, uniqueIndex);
        size = uniqueIndex;
    }

    /**
     * Checks if the bag contains all the entries in another bag.
     *
     * @param aBag the other bag to check against
     * @return true if this bag contains all entries in the other bag, false otherwise
     */
    @Override
    public boolean containsAll(BagInterface aBag) {
        String[] otherBag = aBag.toArray();
        for (String entry : otherBag) {
            if (!contains(entry)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if this bag and another bag have the same items with the same frequencies.
     *
     * @param aBag the other bag to compare
     * @return true if the bags have the same items and frequencies, false otherwise
     */
    @Override
    public boolean sameItems(BagInterface aBag) {
        if (size != aBag.size()) {
            return false;
        }
        for (String entry : bag) {
            if (aBag.getFrequencyOf(entry) != getFrequencyOf(entry)) {
                return false;
            }
        }
        return true;
    }

    private void ensureCapacity() {
        if (size == bag.length) {
            bag = Arrays.copyOf(bag, size * 2);
        }
    }

    private boolean contains(String[] array, String element) {
        for (String e : array) {
            if (e != null && e.equals(element)) {
                return true;
            }
        }
        return false;
    }
}

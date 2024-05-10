/*
 * Joseph Fazioli
 * 4/1/2024
 * CSE274 HA
 */
public class LinkedBag implements BagInterface {
    private Node firstNode;
    private int size;

    /**
     * Creates an empty bag.
     */
    public LinkedBag() {
        firstNode = null;
        size = 0;
    }

    /**
     * Gets the number of entries in the bag.
     *
     * @return The integer number of entries currently in the bag.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the bag is empty.
     *
     * @return True if the bag is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds a new entry to the bag.
     *
     * @param newEntry The entry to add to the bag.
     * @return True if the addition was successful, false otherwise.
     */
    @Override
    public boolean add(String newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        size++;
        return true;
    }

    /**
     * Removes an unspecified entry from the bag.
     *
     * @return The removed entry, or null if the bag is empty.
     */
    @Override
    public String remove() {
        if (isEmpty()) {
            return null;
        }
        String removed = firstNode.data;
        firstNode = firstNode.next;
        size--;
        return removed;
    }

    /**
     * Removes a specified entry from the bag.
     *
     * @param anEntry The entry to remove from the bag.
     * @return True if the removal was successful, false otherwise.
     */
    @Override
    public boolean remove(String anEntry) {
        Node current = firstNode;
        Node previous = null;
        while (current != null) {
            if (current.data.equals(anEntry)) {
                if (previous == null) {
                    firstNode = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /**
     * Removes all entries from the bag.
     */
    @Override
    public void clear() {
        firstNode = null;
        size = 0;
    }

    /**
     * Gets the frequency of a specified entry in the bag.
     *
     * @param anEntry The entry to check for frequency.
     * @return The number of times the entry appears in the bag.
     */
    @Override
    public int getFrequencyOf(String anEntry) {
        int frequency = 0;
        Node current = firstNode;
        while (current != null) {
            if (current.data.equals(anEntry)) {
                frequency++;
            }
            current = current.next;
        }
        return frequency;
    }

    /**
     * Checks if the bag contains a specified entry.
     *
     * @param anEntry The entry to check for in the bag.
     * @return True if the bag contains the entry, false otherwise.
     */
    @Override
    public boolean contains(String anEntry) {
        Node current = firstNode;
        while (current != null) {
            if (current.data.equals(anEntry)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Converts the bag into an array.
     *
     * @return An array containing all the entries in the bag.
     */
    @Override
    public String[] toArray() {
        String[] result = new String[size];
        Node current = firstNode;
        int index = 0;
        while (current != null) {
            result[index++] = current.data;
            current = current.next;
        }
        return result;
    }

    /**
     * Removes duplicate entries from the bag.
     */
    @Override
    public void removeDuplicates() {
        Node current = firstNode;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.data.equals(current.data)) {
                    runner.next = runner.next.next;
                    size--;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    /**
     * Checks if the bag contains all the entries in another bag.
     *
     * @param aBag The other bag to check against.
     * @return True if this bag contains all entries in the other bag, false otherwise.
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
     * @param aBag The other bag to compare.
     * @return True if the bags have the same items and frequencies, false otherwise.
     */
    @Override
    public boolean sameItems(BagInterface aBag) {
        if (size != aBag.size()) {
            return false;
        }
        String[] otherBag = aBag.toArray();
        for (String entry : otherBag) {
            if (getFrequencyOf(entry) != aBag.getFrequencyOf(entry)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Represents a node in the linked list.
     */
    private class Node {
        private String data;
        private Node next;

        /**
         * Creates a new node with the specified data.
         *
         * @param data The data to store in the node.
         */
        private Node(String data) {
            this.data = data;
            this.next = null;
        }
    }
}


/**
 * A max-heap implementation for storing strings, where each string's natural ordering determines its priority.
 * The heap is implemented as a binary tree, but physically stored in an array to efficiently manage memory.
 * The size of the internal storage array is set to the closest power of two that is greater than or equal to a specified capacity.
 */

import java.util.ArrayList;

/**
 * Exception thrown when attempting to add to a full heap.
 * @author Mirza
 */
class HeapFullException extends RuntimeException {
    public HeapFullException(String message) {
        super(message);
    }
}

/**
 * Exception thrown when attempting to remove from an empty heap.
 * @author Mirza
 */
class HeapEmptyException extends RuntimeException {
    public HeapEmptyException(String message) {
        super(message);
    }
}

public class Heap {
	private String[] heap; // Array to hold the heap elements
	private int capacity; // Maximum number of elements heap can hold
	private int size; // Current number of elements in the heap

	/**
	 * Constructs a new Heap with an internal storage array sized to the nearest
	 * power of two above the specified capacity.
	 *
	 * @param capacity The initial capacity of the heap.
	 * @author Mirza
	 */
	public Heap(int capacity) {
	    this.capacity = capacity; // Assigning the passed capacity to the class field
	    this.heap = new String[this.capacity]; // Initializing the heap array with the correct capacity
	    this.size = 0;
	}


	/**
	 * Constructs a heap from an ArrayList of elements, ensuring the resultant array
	 * respects the heap property.
	 * 
	 * @param elements The list of elements to be added to the heap.
	 * @author Tyler
	 */
	public Heap(ArrayList<String> elements) {
		this(elements.size());
		for (String element : elements) {
			add(element);
		}
	}

	/**
	 * Adds a new item to the heap, maintaining the heap property.
	 * 
	 * @param item The item to add to the heap.
	 * @throws HeapFullException if the heap is at capacity and cannot add more items.
	 * @author Tyler
	 */
	public void add(String item) {
		if (isFull()) {
			throw new HeapFullException("Heap is full");
		}
		heap[size] = item;
		size++;
	}

	/**
	 * Removes and returns the element with the highest priority (root of the heap).
	 * 
	 * @return The item with the highest priority.
	 * @throws HeapEmptyException if the heap is empty and there is no element to remove.
	 * @author Joseph
	 */
	public String remove() {
		if (isEmpty()) {
			throw new HeapEmptyException("Heap is empty");
		}
		String item = heap[0];
		heap[0] = heap[size - 1];
		size--;
		return item;
	}

	/**
	 * Checks if the heap is empty.
	 * 
	 * @return true if the heap is empty, false otherwise.
	 * @author Joseph
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Checks if the heap is full.
	 * 
	 * @return true if the heap is at full capacity, false otherwise.
	 * @author Joseph
	 */
	public boolean isFull() {
		return size == capacity;
	}

	/**
	 * Returns the number of elements currently in the heap.
	 * 
	 * @return The current size of the heap.
	 * @author Joseph

	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the element with the highest priority without removing it.
	 * 
	 * @return The current highest priority element.
	 * @throws HeapEmptyException if the heap is empty.
	 * @author Tyler
	 */
	public String peek() {
		if (isEmpty()) {
			throw new HeapEmptyException("Heap is empty");
		}
		return heap[0];
	}

	/**
	 * Provides a string representation of the heap's contents, useful for debugging
	 * and visualization. Format: "index:value index:value ...".
	 * 
	 * @return A string representing the current state of the heap.
	 * @author Tyler
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(i + 1).append(":").append(heap[i]).append(" ");
		}
		return sb.toString().trim();
	}

}

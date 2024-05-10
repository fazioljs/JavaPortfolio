import java.util.ArrayList;

/**
 * A test class for the Heap class. This class checks how the Heap handles
 * adding and removing strings, and tests what happens when you try to add too
 * many items or remove items from an empty heap.
 * @author Tyler, Mirza, Joseph
 */
public class HeapDriver {
	public static void main(String[] args) {
		// Create a new Heap that can hold up to 5 items initially (but really 8 because
		// of internal adjustments).
		Heap heap = new Heap(5);

		// Try to add some strings to the heap.
		try {
			System.out.println("Adding elements:");
			heap.add("Mike");
			heap.add("John");
			heap.add("Mirza");
			heap.add("Tyler");
			heap.add("Joseph");
			heap.add("Ethan"); // Since heap size is really 8, this does not cause an error yet.
		} catch (HeapFullException e) {
			System.out.println("Caught exception: " + e.getMessage());
		}

		// Show what's currently in the heap.
		System.out.println("Heap contents: " + heap.toString());

		// Try to remove all items from the heap one by one.
		try {
			System.out.println("Removing elements:");
			while (!heap.isEmpty()) {
				System.out.println("Removed: " + heap.remove());
			}
			// Now try to remove one more item, which should fail because the heap is empty.
			System.out.println("Attempt to remove from empty heap:");
			heap.remove();
		} catch (HeapEmptyException e) {
			System.out.println("Caught exception: " + e.getMessage());
		}

		// Check if the heap is empty, if it's full, and how many items are in it.
		System.out.println("Is heap empty? " + heap.isEmpty());
		System.out.println("Is heap full? " + heap.isFull());
		System.out.println("Heap size: " + heap.size());

		// Now create a heap from a list of strings and show how this new heap looks.
		ArrayList<String> list = new ArrayList<>();
		list.add("Ali");
		list.add("Paul");
		list.add("Carter");

		Heap heapFromArray = new Heap(list);
		System.out.println("Heap from array: " + heapFromArray.toString());
	}
}
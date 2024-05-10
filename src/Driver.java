import java.util.Arrays;

public class Driver {

	public static void main(String[] args) {
		
		// create instance of WordList
		WordList myList = new WordList();
		
		// test adding multiple Strings
		myList.add("I");
		myList.add("Love");
		myList.add("Java");
		
		// print to see if strings added properly
		System.out.println("Test 1- adding 3 strings: " );
		printArray(myList.toArray());
		
		//test 2 add in a particular location
		myList.add(1, "don't");
		System.out.println("Test 2- adding a String at a particular location: " );
		printArray(myList.toArray());

		
		// remove by value
		myList.remove("don't");
		
		System.out.println("Test 3 - removing don't : " );
		printArray(myList.toArray());
		
		// check size method
		System.out.println("Test 4- size method - should be 3 : " + myList.size());
		
		
		// test remove at an index
		myList.remove(0);
		System.out.println("Test 5- remove at a particular location : " );
		printArray(myList.toArray());;
		
		//test get
		System.out.println("Test 6- get method - should return Java: " + myList.get(1));
		
		// test set
		myList.set(0, "Like");
		System.out.println("Test 7 - set method - should replace Love with Like :" );
		printArray(myList.toArray());
		
		// test getLocation
		System.out.println("Test 8 - get location of Java- should be 1: " + myList.getLocation("Java"));
		
		// test clear
		myList.clear();
		System.out.println("Test 9 - clear- should print empty array " );
		printArray(myList.toArray());
		
		// Do your own testing here. 
		

	}
	
	public static void printArray(String[] array)
	{
		System.out.print("[");
		for (String s: array)
		{
			System.out.print(s + " ");
		}
		System.out.println("]");
	}


}

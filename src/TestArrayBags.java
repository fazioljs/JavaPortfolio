import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/*
 * You can use this JUnit tester as a starting point for testing your implementations.
 * This JUnit tester is NOT complete.  It contains very basic tests.  You should
 * modify it to fit your needs.  Don't forget to test:
 * - Edge cases
 * - Proper resizing of the array (both growing and shrinking)
 * 
 * You can write more tests if you want.  Just follow the structure below, and 
 * put @Test before each test.
 */


public class TestArrayBags {

	BagInterface b1, b2;
	
	// This code here will be run before each test.  You can use these
	// bags in all your testers.
	// You can change the code below to say LinkedBag() instead of ArrayBag().
	@Before
    public void setUpArrayBags() {
        b1 = new ArrayBag(); 
        b2 = new ArrayBag(5); // this constructor only makes sense in ArrayBag
	}
	
	// This next test only makes sense for ArrayBag. It tests to make sure
	// your code is throwing the proper exception.  You can comment out this
	// one test when testing LinkedBag.
	@Test(expected = IllegalArgumentException.class)
	public void intConstructorThrowsProperException00() {
		b2 = new ArrayBag(-14);
	}
	
	// All of the tests below should work correctly for ArrayBag and for LinkedBag
	
	@Test
	public void testSize01() {
		assertEquals(0, b1.size());
		b1.add("a");
		assertEquals(1, b1.size());
	}

	@Test
	public void testIsEmpty02() {
		assertTrue(b1.isEmpty());
		b1.add("a");
		assertFalse(b1.isEmpty());
	}

	@Test
	public void testAdd03() {
		b1.add("a");
		assertTrue(b1.contains("a"));
		assertFalse(b1.contains("b"));
		assertFalse(b1.isEmpty());
		assertEquals(1, b1.size());
	}
	
	@Test
	public void testAddMany04() {
		for(int i = 0; i < 100; i++)
			b1.add("a");
		assertTrue(b1.contains("a"));
		assertFalse(b1.contains("b"));
		assertFalse(b1.isEmpty());
		assertEquals(100, b1.size());
		assertEquals(100, b1.getFrequencyOf("a"));
		b1.removeDuplicates();
		assertEquals(1, b1.size());
	}
	
	@Test
	public void testAddManyAB05() {
		for(int i = 0; i < 100; i++)
			b1.add("a");
		for(int i = 0; i < 100; i++)
			b1.add("b");
		assertTrue(b1.contains("a"));
		assertTrue(b1.contains("b"));
		assertFalse(b1.isEmpty());
		assertEquals(200, b1.size());
		assertEquals(100, b1.getFrequencyOf("a"));
		b1.removeDuplicates();
		assertEquals(2, b1.size());
		assertTrue(b1.contains("a"));
		assertTrue(b1.contains("b"));
	}

	@Test
	public void testRemove06() {
		b1.add("a");
		assertTrue(b1.contains("a"));
		b1.remove();
		assertFalse(b1.contains("a"));
		assertEquals(0, b1.size());
	}

	@Test
	public void testRemoveString07() {
		b1.add("a");
		assertTrue(b1.contains("a"));
		b1.remove("a");
		assertFalse(b1.contains("a"));
		assertEquals(0, b1.size());
	}
	
	@Test
	public void testRemoveStringNotInBag08() {
		b1.add("b");
		assertFalse(b1.contains("a"));
		assertFalse(b1.remove("a"));
		assertTrue(b1.contains("b"));
		assertEquals(1, b1.size());
	}
	
	
	@Test
	public void testRemoveMany09() {
		for(int i = 0; i < 100; i++)
			b1.add("a");
		for(int j = 0; j < 100; j++) {
			assertEquals("a", b1.remove());
			assertEquals(99-j, b1.size());
			assertEquals(99-j, b1.getFrequencyOf("a"));
			
		}
		assertEquals(null, b1.remove());
	}

	@Test
	public void testClear10() {
		b1.add("a");
		b1.add("b");
		b1.clear();
		assertEquals(0, b1.size());
		assertFalse(b1.contains("a"));
		assertFalse(b1.contains("b"));
	}

	// Note: using new String("a") instead of just "a" is helpful because
	// it will help you make sure you used equals() rather than == in your method.
	@Test
	public void testGetFrequencyOf11() {
		b1.add("a");
		b1.add("a");
		assertEquals(2, b1.getFrequencyOf(new String("a")));
	}

	@Test
	public void testContains12() {
		b1.add("a");
		assertTrue(b1.contains(new String("a")));
		assertFalse(b1.contains(new String("b")));
	}

	@Test
	public void testToArray13() {
		b1.add("a");
		String[] ar = b1.toArray();
		assertEquals(1, ar.length);
		assertEquals("a", ar[0]);
		
		b1.add("a");
		ar = b1.toArray();
		assertEquals(2, ar.length);
		assertEquals("a", ar[0]);
		assertEquals("a", ar[1]);
	}

	@Test
	public void testRemoveDuplicates14() {
		
		String[] data = {"a", "b", "b", "a", "c"};
		String[] result = {"a", "b", "c"};
		
		for (String s : data) {
			b1.add(s);
		}
		
		b1.removeDuplicates();
		assertEquals(result.length, b1.size());
		for (String s : result) {
			assertTrue(b1.contains(s));
		}
	}
	
	@Test
	public void testRemoveDuplicatesNoDuplicates15() {
		
		String[] data = {"a", "b","c"};
		String[] result = {"a", "b", "c"};
		
		for (String s : data) {
			b1.add(s);
		}
		
		b1.removeDuplicates();
		assertEquals(result.length, b1.size());
		for (String s : result) {
			assertTrue(b1.contains(s));
		}
	}

	@Test
	public void testContainsAllTrue16() {
		String[] s1 = {"A", "B", "C"};
		String[] s2 = {"A", "A", "B", "A"};
		
		for (String s : s1) b1.add(s);
		for (String s : s2) b2.add(s);
		
		assertTrue(b1.containsAll(b2));
	}
	
	@Test
	public void testContainsAllFalse17() {
		String[] s1 = {"A", "D", "C"};
		String[] s2 = {"A", "A", "B", "A"};
		
		for (String s : s1) b1.add(s);
		for (String s : s2) b2.add(s);
		
		assertFalse(b1.containsAll(b2));
	}

	@Test
	public void testSameItemsTrue18() {
		
		String[] s1 = {"B", "A", "B", "C"};
		String[] s2 = {"C", "A", "B", "B"};
		
		for (String s : s1) b1.add(s);
		for (String s : s2) b2.add(s);
		
		assertTrue(b1.sameItems(b2));
	}
	
	@Test
	public void testSameItemsFalse19() {
		
		String[] s1 = {"B", "A", "B", "C"};
		String[] s2 = {"C", "A", "D", "B"};
		
		for (String s : s1) b1.add(s);
		for (String s : s2) b2.add(s);
		
		assertFalse(b1.sameItems(b2));
	}

}

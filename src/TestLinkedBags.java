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
 * put @Test(timeout=2000) before each test.
 */


public class TestLinkedBags {

	LinkedBag b1, b2;
	
	// This code here will be run before each test.  You can use these
	// bags in all your testers.
	// You can change the code below to say LinkedBag() instead of ArrayBag().
	@Before
    public void setUpLinkedBags() {
        b1 = new LinkedBag(); 
        b2 = new LinkedBag(); // this constructor only makes sense in ArrayBag
	}
	
	
	// All of the tests below should work correctly for ArrayBag and for LinkedBag
	
	@Test(timeout=2000)
	public void testSize01() {
		assertEquals(0, b1.size());
		b1.add("a");
		assertEquals(1, b1.size());
	}

	@Test(timeout=2000)
	public void testIsEmpty01() {
		assertTrue(b1.isEmpty());
		b1.add("a");
		assertFalse(b1.isEmpty());
	}

	@Test(timeout=2000)
	public void testAdd03() {
		b1.add("a");
		assertTrue(b1.contains("a"));
		assertFalse(b1.contains("b"));
		assertFalse(b1.isEmpty());
		assertEquals(1, b1.size());
	}
	
	@Test(timeout=2000)
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
	
	@Test(timeout=2000)
	public void testAddManyAB05() {
		for(int i = 0; i < 200; i++)
			b1.add("a");
		for(int i = 0; i < 100; i++)
			b1.add("b");
		
		assertTrue(b1.contains("a"));
		assertTrue(b1.contains("b"));
		assertFalse(b1.isEmpty());
		
		assertEquals(300, b1.size());
		assertEquals(200, b1.getFrequencyOf("a"));
		assertEquals(100, b1.getFrequencyOf("b"));
		
		b1.removeDuplicates();
		assertEquals(2, b1.size());
		assertTrue(b1.contains("a"));
		assertTrue(b1.contains("b"));
	}

	@Test(timeout=2000)
	public void testRemove06() {
		b1.add("a");
		assertTrue(b1.contains("a"));
		b1.remove();
		assertFalse(b1.contains("a"));
		assertEquals(0, b1.size());
	}

	@Test(timeout=2000)
	public void testRemoveString07() {
		b1.add("a");
		assertTrue(b1.contains("a"));
		b1.remove("a");
		assertFalse(b1.contains("a"));
		assertEquals(0, b1.size());
	}
	
	@Test(timeout=2000)
	public void testRemoveStringNotInBag08() {
		b1.add("b");
		assertFalse(b1.contains("a"));
		assertFalse(b1.remove("a"));
		assertTrue(b1.contains("b"));
		assertEquals(1, b1.size());
	}
	
	
	@Test(timeout=2000)
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

	@Test(timeout=2000)
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
	@Test(timeout=2000)
	public void testGetFrequencyOf11() {
		b1.add("a");
		b1.add("a");
		assertEquals(2, b1.getFrequencyOf(new String("a")));
	}

	@Test(timeout=2000)
	public void testContains12() {
		b1.add("a");
		assertTrue(b1.contains(new String("a")));
		assertFalse(b1.contains(new String("b")));
		
	}

	@Test(timeout=2000)
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

	@Test(timeout=2000)
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
	
	@Test(timeout=2000)
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

	@Test(timeout=2000)
	public void testContainsAllTrue16() {
		String[] s1 = {"A", "B", "C"};
		String[] s2 = {"A", "A", "B", "A"};
		
		for (String s : s1) b1.add(s);
		for (String s : s2) b2.add(s);
		
		assertTrue(b1.containsAll(b2));
	}
	
	@Test(timeout=2000)
	public void testContainsAllFalse17() {
		String[] s1 = {"A", "D", "C"};
		String[] s2 = {"A", "A", "B", "A"};
		
		for (String s : s1) b1.add(s);
		for (String s : s2) b2.add(s);
		
		assertFalse(b1.containsAll(b2));
	}

	@Test(timeout=2000)
	public void testSameItemsTrue18() {
		
		String[] s1 = {"B", "A", "B", "C"};
		String[] s2 = {"C", "A", "B", "B"};
		
		for (String s : s1) b1.add(s);
		for (String s : s2) b2.add(s);
		
		assertTrue(b1.sameItems(b2));
	}
	
	@Test(timeout=2000)
	public void testSameItemsFalse19() {
		
		String[] s1 = {"B", "A", "B", "C"};
		String[] s2 = {"C", "A", "D", "B"};
		
		for (String s : s1) b1.add(s);
		for (String s : s2) b2.add(s);
		
		assertFalse(b1.sameItems(b2));
	}

}

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

public class MyListIteratorCustomTester {

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    private MyLinkedList listLen0, listLen2;
    private MyLinkedList.MyListIterator listLen0Iter, listLen2Iter;
    @Before
    public void setUp() throws Exception {
        listLen0 = new MyLinkedList();
        listLen0Iter = listLen0.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();
        listLen0Iter.left=listLen0.head;
        listLen0Iter.right=listLen0.tail;
        listLen2Iter.left=listLen2.head;
        listLen2Iter.right=listLen2.head.getNext();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        listLen2Iter.left=listLen2.head.getNext().getNext();
        listLen2Iter.right=listLen2.tail;
        listLen2Iter.idx=2;
        assertThrows(NoSuchElementException.class, ()->{listLen2Iter.next();});
        assertEquals("Index after called next", 2, listLen2Iter.idx);
        assertFalse("Not able to remove node", listLen2Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen2Iter.forward);
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        assertThrows(NoSuchElementException.class, ()->{listLen2Iter.previous();});
        assertEquals("Index of iterator after 0 previous()", 0,
                listLen2Iter.idx);
        assertFalse("Not able to remove node", listLen2Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen2Iter.forward);
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {
        assertThrows(NullPointerException.class,()-> {listLen2Iter.add(null);});
        assertEquals("Index of iterator after add null", 0, listLen0Iter.idx);
        assertFalse("Not able to remove node", listLen0Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen2Iter.forward);
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        assertThrows(IllegalStateException.class,()->{listLen2Iter.set("no");});
        assertEquals("Index of iterator after set", 0, listLen0Iter.idx);
        assertFalse("Not able to remove node", listLen0Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen2Iter.forward);
        }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test
    public void testSetInvalid() {
        assertThrows(NullPointerException.class,()->{listLen2Iter.set(null);});
        assertEquals("Index of iterator after set", 0, listLen0Iter.idx);
        assertFalse("Not able to remove node", listLen0Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen2Iter.forward);
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.tail.getPrev();
        listLen2Iter.idx = 1;
        assertThrows(IllegalStateException.class,()->{listLen2Iter.remove();});
        assertEquals("Index of iterator after remove", 0, listLen0Iter.idx);
        assertFalse("Not able to remove node", listLen0Iter.canRemoveOrSet);
        assertTrue("Direction is forward", listLen2Iter.forward);
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen2Iter.next();
        listLen2Iter.next();
        assertFalse("End of the list hasNext()", listLen2Iter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertFalse("Start of the list hasPrevious()",listLen2Iter.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals("Start of the list previousIndex()", -1, listLen2Iter.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen2Iter.next();
        assertEquals("check next index at end for len2", 1, listLen2Iter.nextIndex());
    }
}

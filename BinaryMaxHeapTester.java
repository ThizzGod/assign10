package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class BinaryMaxHeapTester {

	@Test
	public void testEmptyHeapSize() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
	}

	@Test
	public void testAddSingleElement() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
		heap.add(10);
		assertEquals(1, heap.size());
		assertEquals(10, heap.peek());
	}

	@Test
	public void testAddMultipleKeepsMax() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
		heap.add(10);
		heap.add(40);
		heap.add(5);
		heap.add(30);
		assertEquals(40, heap.peek());
	}

	@Test
	public void testExtractMax() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
		heap.add(10);
		heap.add(30);
		heap.add(20);
		heap.add(600);
		heap.add((-12));
		heap.add(14);
		heap.add(-50);
		assertEquals(600, heap.extractMax());
		assertEquals(30, heap.extractMax());
		assertEquals(20, heap.extractMax());
		assertEquals(14, heap.extractMax());
		assertEquals(10, heap.extractMax());
		assertEquals(-12, heap.extractMax());
		assertEquals(-50, heap.extractMax());
		assertTrue(heap.isEmpty());
	}
	

	@Test
	public void testPeek() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
		heap.add(50);
		heap.add(10);
		assertEquals(50, heap.peek());
		assertEquals(2, heap.size());
	}

	@Test
	public void testClear() {
		BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
		heap.add(5);
		heap.add(10);
		heap.add(1);
		heap.clear();
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
	}

	@Test
    public void testBuildHeapConstructor() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(100);
        list.add(3);
        list.add(20);

        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>(list);

        assertEquals(100, heap.peek());
        assertEquals(list.size(), heap.size());
    }
        
        // Tests for K largest

	@Test
	public void testKEqualsSizeReturnsAll() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> result = FindKLargest.findKLargestSort(list, 4);

        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        expected.add(3);
        expected.add(2);
        expected.add(1);

        assertEquals(expected, result);
    }

	@Test
	public void testKZeroReturnsEmptyList() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        List<Integer> result = FindKLargest.findKLargestHeap(list, 0);

        assertTrue(result.isEmpty());
    }

	@Test
	public void testDuplicates() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(1);
        list.add(5);
        list.add(3);
        list.add(5);

        List<Integer> result = FindKLargest.findKLargestHeap(list, 2);

        List<Integer> expected = new ArrayList<>();
        expected.add(5);
        expected.add(5);

        assertEquals(expected, result);
    }

	@Test
	public void testAlreadySorted() {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(80);
        list.add(60);
        list.add(40);
        list.add(20);

        List<Integer> result = FindKLargest.findKLargestSort(list, 3);

        List<Integer> expected = new ArrayList<>();
        expected.add(100);
        expected.add(80);
        expected.add(60);

        assertEquals(expected, result);
    }

}

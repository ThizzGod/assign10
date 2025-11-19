package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Binary max heap implementation of PriorityQueue interface
 * 
 * @param <E> the type of elements stored in the heap
 * @author Max Barker & Josi Gac
 * @version 11/19/25
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
	private E[] heap;
	private int size;
	private Comparator<? super E> cmp;

	/**
	 * Creates an empty max heap that uses the natural ordering
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		size = 0;
		this.heap = ((E[]) new Object[25]);
		this.cmp = null;
	}

	/**
	 * Creates an empty max heap that uses the given comparator to order its
	 * elements
	 * 
	 * @param cmp the comparator used to compare elements
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		size = 0;
		this.heap = ((E[]) new Object[25]);
		this.cmp = cmp;
	}

	/**
	 * Creates a max heap containing all elements from the given list using their
	 * natural ordering
	 * 
	 * @param list the list of elements to add to the heap
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		this.size = list.size();
		buildHeap(list);
	}

	/**
	 * Creates a max heap containing all elements from the given list using the
	 * given comparator for ordering
	 * 
	 * @param list the list of elements to add to the heap
	 * @param cmp  he comparator used to compare elements
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		this.cmp = cmp;
		this.size = list.size();
		buildHeap(list);
	}

	/**
	 * Adds the given item to the heap
	 * 
	 * @param item the item to add
	 */
	@Override
	public void add(E item) {
		if (size >= heap.length) growHeap();
		heap[size] = item;
		percolateUp(item, size);
		size++;
	}

	/**
	 * Returns the max element in the heap without removing it
	 * 
	 * @return the max element
	 * @throws NoSuchElementException if the heap is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return heap[0];
	}

	/**
	 * Returns and removes the max element in the heap
	 * 
	 * @return the max element
	 * @throws NoSuchElementException if the heap is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		E value = heap[0];
		E replacement = heap[size - 1];
		heap[0] = replacement;
		heap[size - 1] = null;
		size--;
		percolateDown(replacement, 0);

		return value;
	}

	/**
	 * Returns the number of elements in the heap
	 * 
	 * @return the heap size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if the heap contains no elements
	 * 
	 * @return true if empty false if not
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Empties this priority queue of items
	 */
	@Override
	public void clear() {
		size = 0;
	}

	/**
	 * Creates and returns an array of the items in this priority queue, in the same
	 * order they appear in the backing array.
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = heap[i];
		}
		return array;
	}

	/**
	 * Moves the given item upward until heap order is restored
	 * 
	 * @param item the item you're moving
	 * @param node the index from where you're percolating
	 */
	private void percolateUp(E item, int node) {
		int parent = (node - 1) / 2;
		if (innerCompare(item, heap[parent]) <= 0) {
			return;
		} else {
			E temp = heap[parent];
			heap[parent] = item;
			heap[node] = temp;
			percolateUp(item, parent);
		}
	}

	/**
	 * Moves the given item down until heap order is restored
	 * 
	 * @param item the item you're moving
	 * @param node the index from where you're percolating
	 */
	private void percolateDown(E item, int node) {
		int left = node * 2 + 1;
		int right = node * 2 + 2;

		if (right >= size && left >= size) {
			return;
		} else if (left >= size) {
			if (innerCompare(heap[node], heap[right]) < 0) {
				E temp = heap[right];
				heap[right] = item;
				heap[node] = temp;
				percolateDown(item, right);
			} else
				return;

		} else if (right >= size) {
			if (innerCompare(heap[node], heap[left]) < 0) {
				E temp = heap[left];
				heap[left] = item;
				heap[node] = temp;
				percolateDown(item, left);
			} else
				return;

		} else if (innerCompare(heap[left], heap[right]) > 0) {
			if (innerCompare(heap[node], heap[left]) < 0) {
				E temp = heap[left];
				heap[left] = item;
				heap[node] = temp;
				percolateDown(item, left);
			} else
				return;
		} else {
			if (innerCompare(heap[node], heap[right]) < 0) {
				E temp = heap[right];
				heap[right] = item;
				heap[node] = temp;
				percolateDown(item, right);
			} else
				return;
		}
	}

	/**
	 * Compares two elements using either the comparator or natural ordering
	 * 
	 * @param a the first element to compare
	 * @param b the second element to compare
	 * @return a negative value if a < b, zero if a equals b or a positive value if a > b
	 */
	@SuppressWarnings({ "unchecked" })
	private int innerCompare(E a, E b) {
		if (cmp != null) {
			return cmp.compare(a, b);
		}

		return ((Comparable<? super E>) a).compareTo(b);
	}

	/**
	 * Builds a valid heap from the given list of elements
	 * 
	 * @param list the list of items to build the heap
	 */
	@SuppressWarnings("unchecked")
	private void buildHeap(List<? extends E> list) {
		this.heap = (E[]) list.toArray();
		int start = (size - 2) / 2;
		for (int i = start; i >= 0; i--) {
			percolateDown(list.get(i), i);
		}

	}
	
	@SuppressWarnings("unchecked")
	private void growHeap() {
		Object[] newArray = new Object[heap.length * 2];
		for (int i = 0; i < size; i++) {
			newArray[i] = heap[i];
		}
		
		heap = ((E[]) newArray);
	}
}

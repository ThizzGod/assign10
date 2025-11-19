package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * @param <E>
 */
public class BinaryMaxHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    private E[] heap;
    private int size;
    private boolean isComparatorType;
    
    /**
     * 
     */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		size = 0;
		this.heap = ((E[]) new Comparable[25]);
	}
	
	/**
	 * 
	 * @param cmp
	 */
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		
	}
	
	/**
	 * 
	 * @param list
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		
	}

	/**
	 * 
	 * @param list
	 * @param cmp
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		
	}

	/**
	 * 
	 */
	@Override
	public void add(E item) {
		heap[size] = item;
		percolateUp(item, size);
		size++;
	}

	/**
	 * 
	 */
	@Override
	public E peek() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException();
        return heap[0];
	}

	/**
	 * 
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
		
		percolateDown(replacement, 0);
		size--;
		return value;
	}

	/**
	 * 
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * 
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 
	 */
	@Override
	public void clear() {
		size = 0;
	}

	/**
	 * 
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = heap[i];
		}
		return array;
	}
	
	private void  percolateUp(E item, int node) {
		int parent = (node - 1) / 2;
		if (item.compareTo(heap[parent]) <= 0) {
			return;
		}
		if (item.compareTo(heap[parent]) > 0) {
			E temp = heap[parent];
			heap[parent] = item;
			heap[node] = temp;
			percolateUp(item, parent);
		}
	}
	
	private void percolateDown(E item, int node) {
		int left = node * 2 + 1;
		int right = node * 2 + 2;
		if (heap[left] != null) {
			if (item.compareTo(heap[left]) >= 0 ) {
				return;
			}
		} 
		
		if (heap[right] != null) {
			if (item.compareTo(heap[right]) >= 0) {
				return;
			}
		}
		
		if (heap[right] == null && heap[left] == null) {
			return;
		}
		
		
		if (heap[left].compareTo(heap[right]) > 0) {
			E temp = heap[left];
			heap[left] = item;
			heap[node] = temp;
			percolateDown(item, left);
		} else {
			E temp = heap[right];
			heap[right] = item;
			heap[node] = temp;
			percolateDown(item, right);
		}
	}
}

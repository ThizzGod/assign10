package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * @param <E>
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
    private E[] heap;
    private int size;
    private Comparator<? super E> cmp;
    
    /**
     * 
     */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap() {
		size = 0;
		this.heap = ((E[]) new Object[25]);
		this.cmp = null;
	}
	
	/**
	 * 
	 * @param cmp
	 */
	@SuppressWarnings("unchecked")
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		size = 0;
		this.heap = ((E[]) new Object[25]);
		this.cmp = cmp;
	}
	
	/**
	 * 
	 * @param list
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		this.size = list.size();
		buildHeap(list);
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
		if (size >= heap.length) growHeap();
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
		size--;
		percolateDown(replacement, 0);
		
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
		if (innerCompare(item, heap[parent]) <= 0) {
			return;
		} else {
			E temp = heap[parent];
			heap[parent] = item;
			heap[node] = temp;
			percolateUp(item, parent);
		}
	}
	
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
			} else return;

		} else if (right >= size) {
			if (innerCompare(heap[node], heap[left]) < 0) {
				E temp = heap[left];
				heap[left] = item;
				heap[node] = temp;
				percolateDown(item, left);			
			} else return;

		} else if (innerCompare(heap[left], heap[right]) > 0) {
			if (innerCompare(heap[node], heap[left]) < 0) {
				E temp = heap[left];
				heap[left] = item;
				heap[node] = temp;
				percolateDown(item, left);
			} else return;
		} else {
			if (innerCompare(heap[node], heap[right]) < 0) {
				E temp = heap[right];
				heap[right] = item;
				heap[node] = temp;
				percolateDown(item, right);
			} else return;
		} 
	} 
	
	@SuppressWarnings({ "unchecked" })
	private int innerCompare(E a, E b) {
		if (cmp != null) {
			return cmp.compare(a, b);
		}
		
		return ((Comparable<? super E>) a).compareTo(b);
	}
	
	@SuppressWarnings("unchecked")
	private void buildHeap(List<? extends E> list) {
		this.heap = (E[]) list.toArray();
		int start = (size - 2)/2 ;
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

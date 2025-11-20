package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.print.attribute.Size2DSyntax;

/**
 * This class contains generic static methods for finding the k largest items in
 * a list.
 * 
 * @author Erin Parker & Max Barker & Josi Gac
 * @version 11/19/25
 */
public class FindKLargest {

	/**
	 * Determines the k largest items in the given list, using a binary max heap and
	 * the natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k)
			throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();

		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items);

		List<E> result = new ArrayList<>();
		for (int i = 0; i < k; i++)
			result.add(heap.extractMax());

		return result;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp)
			throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();

		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items, cmp);

		List<E> result = new ArrayList<>();
		for (int i = 0; i < k; i++)
			result.add(heap.extractMax());

		return result;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine
	 * and the natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k)
			throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		Collections.sort(items);
		
		List<E> largest = new ArrayList<E>();
		
		for (int i = 0; i < k; i++) {
			largest.add(items.get(items.size() - 1 - i));
		}
		return largest;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp)
			throws IllegalArgumentException {
		if (k < 0 || k > items.size())
			throw new IllegalArgumentException();
		Collections.sort(items, cmp);
		
		List<E> largest = new ArrayList<E>();
		
		for (int i = 0; i < k; i++) {
			largest.add(items.get(items.size() - 1 - i));
		}
		return largest;
	}
}
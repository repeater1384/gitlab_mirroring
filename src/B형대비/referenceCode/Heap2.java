package B형대비.referenceCode;

import java.util.Arrays;
import java.util.Comparator;

public class Heap2<E> {
	Comparator<E> comp;
	int MAX_SIZE;
	int size = 0;
	Object[] data;

	public Heap2(Comparator<E> comp, int MAX_SIZE) {
		super();
		this.comp = comp;
		this.MAX_SIZE = MAX_SIZE;
		this.data = new Object[MAX_SIZE];
	}

	void add(E val) {
		data[++size] = val;
		int idx = size;
		while (idx > 1) {
			E parent = (E) data[idx / 2];
			if (comp.compare(val, parent) > 0)
				break;
			data[idx / 2] = data[idx];
			data[idx] = parent;
			idx /= 2;
		}
	}

	E pop() {
		if (size == 0)
			return null;
		E returnVal = (E) data[1];
		data[1] = data[size];
		data[size--] = null;

		int idx = 1;
		while (idx * 2 <= size) {
			E cur = (E) data[idx];
			int nextIdx = idx * 2;
			E next = (E) data[nextIdx];
			if (idx * 2 + 1 <= size && comp.compare(next, (E) data[idx * 2 + 1]) > 0) {
				nextIdx = idx * 2 + 1;
				next = (E) data[nextIdx];
			}

			if (comp.compare(cur, next) < 0)
				break;
			data[idx] = next;
			data[nextIdx] = cur;
			idx = nextIdx;
		}
		return returnVal;
	}

	E peek() {
		return (E) data[1];
	}

	public static void main(String[] args) {
		Heap2<Integer> heap = new Heap2<>((a, b) -> (int) b - (int) a, 100);
		int[] first = { 1, 5, 3, 2, 4, 6, 7, 4, 1, 2, 5, 8, 11, 13, 3 };
		for (int f : first) {
			heap.add(f);
			System.out.println(Arrays.toString(heap.data));
		}
		while (heap.size > 0) {
			System.out.println(heap.pop());
		}

	}
}

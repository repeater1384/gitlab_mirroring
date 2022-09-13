package B형대비.referenceCode;

import java.util.Comparator;

public class Heap3<E> {
	E[] data;
	int size;
	int MAX_SIZE;
	Comparator<E> comp;

	public Heap3(Comparator<E> comp, int MAX_SIZE) {
		this.comp = comp;
		this.MAX_SIZE = MAX_SIZE;
		this.data = (E[]) new Object[this.MAX_SIZE + 1];
		this.size = 0;
	}

	void add(E val) {
		data[++size] = val;
		int idx = size;
		while (idx > 1) {
			E parent = data[idx / 2];
			E cur = data[idx];
			if (comp.compare(parent, cur) < 0) {
				break;
			}
			data[idx / 2] = cur;
			data[idx] = parent;
		}
	}

	E pop() {
		if (size == 0)
			return null;
		E returnVal = data[1];
		data[1] = data[size--];

		int idx = 1;
		while (idx * 2 <= size) {
			E cur = data[idx];
			E child = data[idx * 2];
			int nextIdx = idx * 2;
			if (idx * 2 + 1 <= size && comp.compare(child, data[idx * 2 + 1]) > 0) {
				child = data[idx * 2 + 1];
				nextIdx = idx * 2 + 1;
			}
			if (comp.compare(cur, child) < 0)
				break;
			data[idx] = child;
			data[nextIdx] = cur;
		}

		return returnVal;
	}

	E peek() {
		return data[1];
	}
}

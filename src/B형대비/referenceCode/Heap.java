package B형대비.referenceCode;

import java.util.Arrays;
import java.util.Scanner;

public class Heap {

	int MAX_SIZE;
	int size = 0;
	int[] data;

	public Heap(int mAX_SIZE) {
		super();
		MAX_SIZE = mAX_SIZE;
		data = new int[MAX_SIZE + 1];
	}

	void add(int val) {
		data[++size] = val;
		int idx = size;
		while (idx > 1) {
			int parent = data[idx / 2];
			if (parent <= data[idx])
				break;
			data[idx / 2] = data[idx];
			data[idx] = parent;
			idx /= 2;
		}
	}

	int pop() {
		if (size == 0)
			return -1;
		int returnValue = data[1];

		data[1] = data[size];
		data[size--] = 0;
		int idx = 1;
		while (idx * 2 <= size) {
			int cur = data[idx];
			int nextIdx = idx * 2;
			int next = data[nextIdx];
			if (idx * 2 + 1 <= size && next < data[idx * 2 + 1]) {
				nextIdx = idx * 2 + 1;
				next = data[nextIdx];
			}
			if (cur < next)
				break;
			data[idx] = next;
			data[nextIdx] = cur;
			idx = nextIdx;
		}
		return returnValue;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		Heap pq = new Heap(N);
		while (N-- > 0) {
			pq.add(sc.nextInt());
		}
		while (M-- > 0) {
			int a = pq.pop();
			int b = pq.pop();
			pq.add(a + b);
			pq.add(a + b);
		}
		long answer = 0;
		for (int i = 0; i < pq.data.length; i++) {
			answer += pq.data[i];
		}
		System.out.println(answer);
	}
}

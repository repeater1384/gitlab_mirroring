package B형대비.referenceCode;

public class boundPrac {
	static int[] prac = { 0, 1, 1, 1, 3, 3, 4, 5, 5, 6, 6, 7 };

	static int lowerBound(int key) {
		int start = 0;
		int end = prac.length;
		while (start < end) {
			int mid = (start + end) / 2;
			if (prac[mid] < key) {
				start = mid + 1;
			} else
				end = mid;
		}
		return end;
	}

	static int upperBound(int key) {
		int start = 0;
		int end = prac.length;
		while (start < end) {
			int mid = (start + end) / 2;
			if (prac[mid] <= key) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return end;
	}

	public static void main(String[] args) {
		System.out.println(lowerBound());
	}
}

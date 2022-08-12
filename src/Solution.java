import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int answer = 1;
			while (N-- > 0) {
				String[] arr = br.readLine().split(" ");
				char value = arr[1].charAt(0);
				boolean isOp = value == '+' || value == '-' || value == '*' || value == '/';
				if (isOp && arr.length != 4)
					answer = 0;
				if (!isOp && arr.length != 2)
					answer = 0;
			}

			System.out.printf("#%d %d\n", tc, answer);
		}
	}

}
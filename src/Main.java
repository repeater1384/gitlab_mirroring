import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] arr = new int[N][2];
		for (int i = 0; i < N; i++)
			arr[i] = new int[] { sc.nextInt(), sc.nextInt() };
		Arrays.sort(arr, (a, b) -> a[1] - b[1]);
		
		int answer = 1;
		int cur = arr[0][1];
		for(int i = 1 ; i<N;i++) {
			if(cur < arr[i][0]) {
				cur = arr[i][0];
				answer++;
			}
		}
		System.out.println(answer);
		sc.close();
	}
}
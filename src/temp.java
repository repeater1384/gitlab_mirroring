import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
 
public class temp {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			List<Integer> arr = new ArrayList<>();
			for (int i = 0; i < N; i++)
				arr.add(Integer.parseInt(st.nextToken()));
			Collections.sort(arr);
			System.out.println(arr.get(N-1)+arr.get(N-2)-arr.get(0)-arr.get(1));
		}
	}
 
}
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class temp {

	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write("1\n");
		bw.write("100000 99999\n");
		for (int j = 2; j <= 100000; j++)
			bw.write(String.format("%d %d %d\n", 1, j, 1000));

		bw.close();
//		Random r = new Random();
//		System.out.println(20);
//		for(int i = 0 ; i<20;i++) {
//			for(int j = 0 ;j<20;j++) {
//				System.out.print(r.nextInt()%100+" ");
//			}
//			System.out.println();
//		}
//		Queue<Integer> temp = new LinkedList<>();
//		temp.add(1);
//		temp.add(2);
//		temp.add(3);
//		for (Integer integer : temp) {
//			System.out.println(integer);
//		}
//		
//		System.out.println();
//		dfs(0,new int[3]);
//	}
//	static void dfs(int depth, int[] result) {
//		if(depth==3) {
//			System.out.println(Arrays.toString(result));
//			return;
//		}
//		
//		for(int i = 0 ;i<4;i++) {
//			result[depth] = i;
//			dfs(depth+1,result);
//		}
//	}
	}
}
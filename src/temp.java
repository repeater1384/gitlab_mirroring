import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class temp {
	static void a(int[] result) {
		result[3] += 4;
	}
	public static void main(String[] args) throws IOException {
		
		int[] result = {0,1,2,3};
		System.out.println(Arrays.toString(result));
		a(result);
		System.out.println(Arrays.toString(result));
		
//		Random r = new Random();
//		File file = new File("input.txt");
//		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//		bw.write("100 100 10000\n");
//		for(int i = 1 ;i<101;i++)
//			for(int j = 1 ;j<101;j++)
//				bw.write(String.format("%d %d %d %d %d\n", i,j,1000,Math.abs(r.nextInt())%4+1,i*100+j));
//
//		bw.close();
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
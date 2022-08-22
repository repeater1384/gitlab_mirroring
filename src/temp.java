import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
 
public class temp {
 
	public static void main(String[] args) throws IOException {
		Queue<Integer> temp = new LinkedList<>();
		temp.add(1);
		temp.add(2);
		temp.add(3);
		for (Integer integer : temp) {
			System.out.println(integer);
		}
		
		System.out.println();
		dfs(0,new int[3]);
	}
	static void dfs(int depth, int[] result) {
		if(depth==3) {
			System.out.println(Arrays.toString(result));
			return;
		}
		
		for(int i = 0 ;i<4;i++) {
			result[depth] = i;
			dfs(depth+1,result);
		}
	}
}
package algorythm;

public class SubSet_BinaryCounting {

	public static void main(String[] args) {
		char[] arr = {'A','B','C','D','E'};
		int size = arr.length;
		for(int i = 0 ; i<(1<<size);i++) {
			System.out.print("[ ");
			for(int j = 0 ;j<size;j++) {
				if((i & (1<<j)) != 0) 
					System.out.print(arr[j]+" ");
			}
			System.out.println("]");
		}
	}
	
}

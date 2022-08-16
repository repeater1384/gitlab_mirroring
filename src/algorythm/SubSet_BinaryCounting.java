package algorythm;

public class SubSet_BinaryCounting {

	public static void main(String[] args) {
		char[] arr = {'A','B','C','D'};
		int size = arr.length;
		for(int i = 0 ; i<(1<<size);i++) {
			for(int j = 0 ;j<size;j++) {
				if((i & (1<<j)) != 0) 
					System.out.print(arr[j]+" ");
			}
			System.out.println();
		}
	}
	
}

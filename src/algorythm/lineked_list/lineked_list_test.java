package algorythm.lineked_list;

public class lineked_list_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SsafyStack<Integer> stack = new SsafyStack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
//		stack.print_stack();
		System.out.println(stack.pop());
		stack.push(4);
		stack.print_stack();
	}

}

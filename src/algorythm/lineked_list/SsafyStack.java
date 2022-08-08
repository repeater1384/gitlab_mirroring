package algorythm.lineked_list;

public class SsafyStack<T> implements IStack<T> {

	private Node<T> top = null;

	@Override
	public void push(T data) {
		top = new Node<T>(data,top);
	}

	@Override
	public T pop() {
		Node<T> pop_node = top;
		top = pop_node.link;
		pop_node.link = null;
		return pop_node.data;
	}

	@Override
	public T peek() {
		
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public int size() {
		int count = 0;
		for(Node<T> cur = top;cur !=null ; cur = cur.link)count++;
		return count;
	}
	
	public void print_stack() {
		for(Node<T>cur = top;cur!=null;cur=cur.link)
			System.out.println(cur.data);
	}

}

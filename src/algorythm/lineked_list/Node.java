package algorythm.lineked_list;

public class Node<T> {
	
	T data;
	Node<T> link;
	public Node(T data, Node<T> link) {
		super();
		this.data = data;
		this.link = link;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + ", link=" + link + "]";
	}
	
	
	
	
}

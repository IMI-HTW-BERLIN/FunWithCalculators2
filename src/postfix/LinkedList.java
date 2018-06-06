package postfix;

public class LinkedList<T> {
	private Node<T> head;
	
	
	public LinkedList() {
		
		head = null;
		
	}
	
	public void addAtFront(T t) {
		head = new Node<>(t, head);
		
	}
	
	public void remove() {
		if(isEmpty())return;
		head = head.next; // removes the first Element
	}
	
	public boolean isEmpty() {
		return head==null;
		
	}
	
	public T head() {
		return head.data;
		
	}
	
	public String print() {
		String result ="";
		Node<T> tmp = head;
		while(!(head==null)) {
			result = result + head.data  +"-";
			head = head.next;
		}
		result = result.replaceAll("-$", "");
		head = tmp;
		return result;
	}

	@Override
	public String toString() {
		Node<T> tmp = head;
		StringBuilder sb = new StringBuilder();
		while(!(head == null)){
			sb.append(head.data).append(",");
			head = head.next;
		}
		head = tmp;
		String s = sb.toString();
		return s = s.replaceAll("-$","");
	}
}
	
	



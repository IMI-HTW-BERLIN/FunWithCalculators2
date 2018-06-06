package postfixOLD;

public class Stack<T> {
	private T t;
	private LinkedList<T> stack;
	
	public Stack() {
		empty();
	}


	
	public void empty() {
		stack = new LinkedList<>();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public void pop() throws StackUnderflow{
		if(stack.isEmpty()) throw new StackUnderflow();
		else {
			stack.remove();
		}
	}
	
	public void push(T t) {
		stack.addAtFront(t);
	}






	public T top() throws StackUnderflow{
		if(stack.isEmpty()) throw new StackUnderflow();
		else return stack.head();
	}
	
	public String print() {
		return stack.print();
	}

	public T get() {
		return t;
	}

	public void set(T t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return stack.toString();
	}
}

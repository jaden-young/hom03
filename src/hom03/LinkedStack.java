package hom03;

/**
 *
 * @author Jaden Young
 * @param <T> Data type of the stack
 */
public class LinkedStack<T> {

	private LinkedBag<T> list = new LinkedBag<>();

	public LinkedStack() {
		//do nothing
	}

	public int getSize() {
		return list.getCurrentSize();
	}

	public boolean isEmpty() {
		return list.getCurrentSize() == 0;
	}

	public void push(T element) {
		list.add(element);
	}

	public T pop() {
		return list.remove();
	}

	public T top() {
		return list.getItem(0);
	}
	
	public LinkedStack<T> reverse() {
		LinkedStack<T> buffer = new LinkedStack<>();
		while(!isEmpty()) {
			buffer.push(this.pop());
		}
		return buffer;
	}
}

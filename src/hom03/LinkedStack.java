package hom03;

/**
 *
 * @author Jaden Young
 * @param <T> Data type of the stack
 */
public class LinkedStack<T> {

	private LinkedBag<T> list = new LinkedBag<>();
	private int size = 0;

	public LinkedStack() {
		//do nothing
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
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

}

package hom03;

/**
 *
 * @author jaden
 */
public class CircularQueue<T> {
	private CircularLinkedList<T> list = new CircularLinkedList<>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void enqueue(T element) {
		list.addFirst(element);
	}
	
	public T dequeue() {
		return list.removeFirst();
	}
	
	public T first() {
		return list.first();
	}
	
	public void rotate() {
		list.rotate();
	}
}

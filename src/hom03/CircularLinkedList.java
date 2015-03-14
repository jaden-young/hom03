package hom03;

/**
 * This is the circularly linked list class taken straight from the book example
 * @author Jaden YOung
 */

public class CircularLinkedList<E> {
	// Nested node class
    private static class Node<T> {
        private T element;
        private Node<T> next;
        public Node( T newElement, Node <T> newNext) {
            element = newElement;
            next = newNext;
        }
        
        /** Returns the element stored in the node */
        public T getElement() {
            return element;
        }
        
        /** Returns the reference to the next node */
        public Node<T> getNext() {
            return next;
        }
        
        /** Sets the element of the node */
        public void setElement(T newElement) {
            element = newElement;
        }
        
        /** Sets the reference to the next node in the list */
        public void setNext(Node<T> newNext) {
            next = newNext;
        }
    }

	private Node<E> tail = null; 
	private int size = 0; 

	public CircularLinkedList() {
		//do nothing
	} 
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() { 
		if (isEmpty()) {
			return null;
		}
		return tail.getNext().getElement();
	}

	public E last() { 
		if (isEmpty()) {
			return null;
		}
		return tail.getElement();
	}

	// update methods
	public void rotate() { 
		if (tail != null) 
		{
			tail = tail.getNext(); 
		}
	}

	public void addFirst(E e) { 
		if (size == 0) {
			tail = new Node<>(e, null);
			tail.setNext(tail); 
		} else {
			Node<E> newest = new Node<>(e, tail.getNext());
			tail.setNext(newest);
		}
		size++;
	}

	public void addLast(E e) { 
		addFirst(e); 
		tail = tail.getNext(); 
	}

	public E removeFirst() { 
		if (isEmpty()) {
			return null; 
		}
		Node<E> head = tail.getNext();
		if (head == tail) {
			tail = null; 
		} else {
			tail.setNext(head.getNext()); 
			size--;
		}
	return head.getElement();
	}
}

package hom03;

/**
 * Defines a singly linked list with a specified data type.<BR>
 * Implements the Bag interface and all of its methods.
 * @author Jaden Young
 * @param <T> Data type for the bag
 */
public class LinkedBag<T> implements Bag<T>{

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
    
    private Node<T> bagHead = null;
    private Node<T> bagTail = null;
    private int count;
    
    /**
     * Creates an empty list
     */
    public LinkedBag(){
        // empty list
    }
    
    /** Returns the current number of nodes in the list */
     @Override
    public int getCurrentSize() {
        return count;
    }

    /** Returns true if there are no nodes in the list, false if there are */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * This method must be implemented from the bag interface, otherwise java 
     * will have a heart attack. There's nothing to really do here, as a linked
     * list can never be "full". The method simply always returns true, and 
     * does nothing else.
     * @return True. Always. The only line of the method is "return true;"
     */
    @Override
    public boolean isFull() {
        return true;
    }

    /**
     * Adds a new node at the beginning of the list.
     * @param newElement Data for the node to contain
     * @return True if addition was successful (hint: it always will be)
     */
    @Override
    public boolean add(T newElement) {
        bagHead = new Node<>(newElement, bagHead);
        if(isEmpty())
            bagTail = bagHead;
        count++;
        return true;
    }

    /**
     * Removes the first node in the linked list
     * @return The removed node at the head of the list
     */
    @Override
    public T remove() {
        if(isEmpty())
            return null;
        T temp = bagHead.getElement();
        bagHead = bagHead.getNext();
        count--;
        if(isEmpty())
            bagTail = null;
        return temp;
    }

    /**
     * Removes the first item that is equal to the specified item. The data in 
     * the node is set to the element contained in head, and then the head is 
     * removed.
     * @param item Item to be removed 
     * @return True if the item was removed successfully, false if the item was 
     * not removed or was not found in the list.
     */
    @Override
    public boolean remove(T item) {
        Node<T> temp = bagHead.getNext();
        for(int i = 1; i < count; i++) {
            if(item.equals(temp.element)){
                temp.setElement(bagHead.element);
                bagHead = bagHead.getNext();
                count--;
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /** Sets head and tail equal to null, clearing the list */
    @Override
    public void clear() {
        bagHead = null;
        bagTail = null;
    }

    /** Returns the number of times a specified item is present in the list */
    @Override
    public int getFrequencyOf(T item) {
        Node<T> temp = bagHead;
        int countOfItem = 0;
        for(int i = 0; i < count; i++) {
            if(item.equals(temp.element))
                countOfItem++;
            temp = temp.getNext();
        }
        return countOfItem;
    }

    /** Returns true if the item is in the list, false if not */
    @Override
    public boolean contains(T item) {
        Node<T> temp = bagHead;
        for(int i = 0; i < count; i++) {
            if(item.equals(temp.element))
                return true;
            temp = temp.getNext();
        }
        return false;
    }
    
    /**
     * Returns the item stored at the specified index position.
     * @param i index of the item to be returned
     * @return Item at specified index
     * @throws IllegalArgumentException Thrown if the index is negative or if 
     * the index is greater than the size of the list
     */
    public T getItem(int i) throws IllegalArgumentException {
        if(i >= count)
            throw new IllegalArgumentException("There aren't that many nodes"
                    + " in the list");
        if(i < 0)
            throw new IllegalArgumentException("No such thing as a negative "
                    + "index position");
        Node<T> temp = bagHead;
        for(int j = 0; j < i; j++) {
            temp = temp.getNext();
        }
        return temp.element;
    }
    
    /** Returns an array of the elements held in the nodes of the linked list */
    public T[] toArray() {
        Object[] tempArray = new Object[count];
        T []listArray = (T[])tempArray;
        Node<T> tempNode = bagHead;
        for(int i = 0; i < count; i++) {
            listArray[i] = tempNode.element;
            tempNode = tempNode.getNext();
        }
        return listArray;
    }
}

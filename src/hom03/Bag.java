package hom03;

/**
 * Generic interface that defines methods for use with a bag. The bag can hold
 * any data types, does not order the items and does not guard against 
 * duplicate entries.
 * @param T Datatype of the bag
 * @author Jaden Young
 */
public interface Bag<T> {
    
    /** Returns the  current number of items in the bag */
    public int getCurrentSize();
    
    /** Tells whether or not the bag is empty */
    public boolean isEmpty();
    
    /** Tells whether or not the bag is full */
    public boolean isFull();
    
    /**
     * Adds an item to the bag.
     * Returns true if the item was added successfully, false if it failed
     * @param item Item to be added to the bag
     */
    public boolean add(T item);
    
    /**
     * Removes a random item from the bag, if the bag is not empty.
     * Returns the item that was removed
     */
    public T remove();
    
    /**
     * Removes the first occurrence of the specified item in the bag.
     * Returns true if removal was successful, false if not.
     * @param item Item to be removed
     */
    public boolean remove(T item);

    /** Clears the bag, removing all items */
    public void clear();
    
    /** 
     * Returns the number of times a specified object appears in the bag 
     * @param item Item to be searched for
     */
    public int getFrequencyOf(T item);
    
    /** 
     * Returns true if specified item is present in the bag, false if not 
     * @param item Item to be checked for
     */
    public boolean contains(T item);
}

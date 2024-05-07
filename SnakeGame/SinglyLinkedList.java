import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - A list where every node contains the object
 *  	it is meant to store and the reference of the next node
 *
 *	@author	Qi Huang
 *	@since	4/29/2024
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		
		// the oldList is copied as deeply as possible. E is a generic
		// type, so I can't deep copy the objects of type E, even if its 
		// a mutable object and I want to. 
		ListNode<E> tempNode = new ListNode<>(oldList.get(0).getValue());
		head = tempNode;
		
		for (int i = 1; i < oldList.size(); i++) {
			tempNode = new ListNode<>(oldList.get(i).getValue());
			this.get(i - 1).setNext(tempNode);
		}
		
		tail = tempNode;
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head = null;
		tail = null;
	}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		
		try {
			ListNode<E> toAdd = new ListNode<E>(obj);
			if (size() == 0) {
				head = toAdd;
				tail = toAdd;
			} else {
				tail.setNext(toAdd);
				tail = toAdd;
			}
			return true;
		} catch (OutOfMemoryError e) {
			return false;
		}
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		try {
			ListNode<E> toAdd = new ListNode<E>(obj);
			ListNode<E> prev = null;
			ListNode<E> next = null;
			ListNode<E> temp = null;
			
			if (index == 0) {
				next = head;
				head = toAdd;
				toAdd.setNext(next);
			} else if (0 < index && index < size()) {
				temp = head;
				
				for (int i = 0; i < index - 1; i++) {
					temp = temp.getNext();
				}
				
				prev = temp;
				next = prev.getNext();
				prev.setNext(toAdd);
				toAdd.setNext(next);
			} else if (index == size()) {
				add(obj);
			} else {
				throw new NoSuchElementException();
			}
			
		} catch (OutOfMemoryError e) {
			return false;
		}
		
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		int count = 0;
		ListNode<E> temp = head;
		
		while (temp != null) {
			temp = temp.getNext();
			count++;
		}
		
		return count;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		ListNode<E> theNode = head;
		
		if (index < 0 || index >= size()) {
			throw new NoSuchElementException();
		}
		
		for (int i = 0; i < index; i++) {
			theNode = theNode.getNext();
		}
		
		return theNode;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		
		// note to self: the code is less intuitive without ListNode<E>
		// next, but it uses less variables.
		ListNode<E> newElement = new ListNode<>(obj);
		ListNode<E> temp = head;
		ListNode<E> prev = null;
		ListNode<E> oldElement = null;
		
		if (index == 0) {
			temp = temp.getNext();
			newElement.setNext(temp);
			oldElement = head;
			head = newElement;
		} else if (0 < index && index <= size() - 1) {
			for (int i = 0; i < index - 1; i++) {
				temp = temp.getNext();
			}
			
			prev = temp;
			for (int i = 0; i < 2; i++) {
				temp = temp.getNext();
			}
			
			newElement.setNext(temp);
			oldElement = prev.getNext();
			prev.setNext(newElement);
		} else if (index == size() - 1) {
			oldElement = tail;
			tail = newElement;
		}
		
		if (oldElement != null) {
			return oldElement.getValue();
		}
		
		return null;
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		ListNode<E> temp = head;
		ListNode<E> prev = null;
		ListNode<E> removed = null;
		
		if (index == 0) {
			removed = temp;
			temp = temp.getNext();
			head = temp;
		} else if (0 < index && index < size()) {
			for (int i = 0; i < index - 1; i++) {
				temp = temp.getNext();
			}
			
			prev = temp;
			
			if (index == size() - 1) {
				// the tail is the element being removed
				removed = tail;
				tail = prev;
				prev.setNext(null);
			} else {
				// the element being removed 
				temp = temp.getNext();
				removed = temp;
				
				// the element after the one being removed
				temp = temp.getNext();
				prev.setNext(temp);
			}
		} else {
			throw new NoSuchElementException();
		}
		
		return removed.getValue();
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		boolean isContained = false;
		ListNode<E> temp = head;
		
		for (int i = 0; i < size(); i++) {
			E nodeValue = temp.getValue();
			if (nodeValue.equals(object)) {
				isContained = true;
			}
			temp = temp.getNext();
		}
		
		return isContained;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		int index = -1;
		ListNode<E> temp = head;
		E nodeValue = null;
		
		for (int i = 0; i < size(); i++) {
			nodeValue = temp.getValue();
			if (nodeValue.equals(element) && index == -1) {
				index = i;
			}
			temp = temp.getNext();
		}
		
		return index;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
}

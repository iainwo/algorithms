package com.iain.practice;

import java.util.AbstractQueue;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
* SLList -
* Singly-Linked List implementation of a minimal AbstractQueue.
*/
public class SLList<T> extends AbstractQueue<T> {
    public static void main(String[] args) {
        SLList<String> list = new SLList<>();
        list.add("abc");
        list.size();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        list.remove();
        list.peek();
        list.poll();
    }
	/**
	* Node -
	* Internal Node class which are linked together to form Lists.
	*/
	class Node {
		Node next; // single link to the next Node in the list
		T x; // the data element in this Node
	}

	private int n; // number of elements
	Node head, tail; // ref. to head and tail of Queue

	/**
	* Iterator
	* @return an iterator of the SLList
	*/
	public Iterator<T> iterator() {
		
		/**
		* SLIterator
		* Iterator class over a Singly Linked List
		*/
		class SLIterator implements Iterator<T> {
			
			protected Node c; // current node in iteration

			/**
			* Constructor
			* Initialize iterator pointing to the next available node.
			*/
			SLIterator() {
				c = head;
			}

			/**
			* next -
			* Get the next available element in the iteration.
			* @return the next element
			*/
			public T next() {
				T y = c.x;
				c = c.next;
				return y;
			}

			/**
			* UNSUPPORTED METHOD
			* remove -
			* Remove the previous element in the iteration from the SLList.
			*/
			public void remove() { throw new UnsupportedOperationException(); }

			/**
			* hasNext -
			* Determine if there is another element to iterate.
			* @return true if an element exists
			*/
			public boolean hasNext() { return (null != c); }
		}
		return new SLIterator();
}
	
/**
	* Pop
	* Remove the head of the Linked List - Stack style.
	* @return the head of the SLList.
	*/
	public T pop() {
		if (0 == n) return null;
		T y = head.x;
		head = head.next;
		if (0 == --n) tail = null;
		return y;
	}

	/**
	* Push
	* Add the element to the head of the Queue
	* @param x the element to add to the head of the Queue
	* @return the pushed element
	*/
	public T push(T x) {
		Node u = new Node();
		u.x = x;
		if (0 == n) {
			head = u;
			tail = u;
		} else {
			u.next = head;
			head = u;
		}
		n++;
		return x;
	}

	/**
	* Poll
	* Get the head of the Queue.
	* @return the head of the Queue or null if Queue is empty.
	*/
	public T poll() {
		if (0 == n) return null;
		T y = head.x;
		head = head.next;
		if (0 == --n) tail = null;
		return y;
	}

	/**
	* Offer -
	* Add the given element to the Queue if there is capacity.
	* @param x the element to add
	* @return true if successful
	*/
	public boolean offer(T x) { return add(x); }

	/**
	* Remove -
	* Splice out the head of the Queue.
	* @return the head of the queue
	*/
	public T remove() {
		if (0 == n) throw new NoSuchElementException();
	T y = head.x;
	head = head.next;
	n--;
	if (0 == n) tail = null;
	return y;
}

	/**
	* Add -
	* Add the element to the underlying Queue if there is capacity.
	* @param x the element to add
	* @return true if the operation succeeds
	*/
	public boolean add(T x) {
		Node u = new Node();
		u.x = x;

		if (0 == n) {
			head = u;
			tail = u;
		} else {
			tail.next = u;
			tail = u;
		}
		n++;
		return true;
	}

	/**
	* Peek -
	* Retrieve the head of the Queue without removing the element.
	* @return the head of the Queue.
	*/
	@Override
	public T peek() { return (null == head) ? null : head.x; }

	/**
	* Get the size of the underlying Queue
	* @return the number of elements in the queue
	*/
	public int size() { return n; }
}
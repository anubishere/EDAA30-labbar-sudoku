package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> node;
		
		if(size==0) {
			node=new QueueNode<E> (e);
			last=node;
			last.next=node;
		}
		else {
			node=new QueueNode<E> (e);
			node.next=last.next;
			last.next=node;
			last=node;
		}
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last==null)
			return null;
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(isEmpty()) {
			return null;
		}
		QueueNode<E> node=last.next;
		last.next=node.next;
		if(size==1) {
			last.next=null;
			size--;
			return node.element;
		}

		size--;
		return node.element;

	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 *  to this queue. The specified queue (q) is empty after the call.
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical*/
	
	public void append(FifoQueue<E> q) {
		if(q==this || (q.last==null && this.last==null)) {
			throw new IllegalArgumentException(); //jämför listorna, pekar de på samma objekt throw
		}																		//om båda listorna är tomma throw
		if (last==null) { //om listan är tom behöver endast last  och size ändras
			last=q.last;
			size=q.size;
		}
		else if(!q.isEmpty()) {  //om listan inte är tom samt parameterlistan inte är tom, 
			QueueNode<E> node = last.next; //temporär nod, första i listan.
			last.next=q.last.next;  //Sista i listan pekar nu på första  i parameterlistan
			q.last.next=node;		//sista i parameterlistan pekar  nu på första i listan
			last=q.last;					//sista i listan är nu sista i parameterlistan
			size+=q.size;				//öka size	
		}
		q.size=0;
		q.last=null;			
	}
	
	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		private QueueIterator() {
			pos=null;
		}
		
		@Override
		public boolean hasNext() {
			if(pos==null &&last!=null) { //om vi inte har börjat iterera och listan inte är tom ger den true
				return true;
			}
			else if(pos!=last&&last!=null) {//om vi inte är på sista och listan inte är tom
				return true;
			}
				return false;
		}

		@Override
		public E next() {
			
		/**	if(!hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				QueueNode<E> node = pos;
				pos=node.next;
				return node.element;
			}*/
			
			
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			if(pos==null && last!=null) {//kollar om det är första iterationen samt att listan inte är tom
				pos=last.next; //pos  är nu först i listan
				return pos.element;
			}
			else if (pos.next!=last.next && last!=null) { //kollar om nästa i listan är skiljt från första i listan och att 
				pos=pos.next;//hoppar fram ett steg         //listan inte  är tom
				return pos.element;
			}
			throw new NoSuchElementException(); 
		}
		
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}

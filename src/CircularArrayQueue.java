/**
 * This class represents a Queue implementation using a circular array as the
 * underlying data structure. This class must implement the QueueADT and work
 * with the generic type (T).
 * 
 * @author Abdul Khan
 *
 * @param <T> a Generic that indicates any type of object may be stored in the
 *            queue and use the class
 */
public class CircularArrayQueue<T> {
	private int front;
	private int rear;
	private int count;
	private T[] queue;
	private final int DEFAULT_CAPACITY = 20;

	/**
	 * Constructor class creates a CircularArrayQueue with the default capacity,
	 * initializes front to 1, rear to the default capacity and the count to 0
	 */
	public CircularArrayQueue() {
		front = 1;
		rear = DEFAULT_CAPACITY;
		count = 0;
		queue = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	/**
	 * Constructor class creates a CircularArrayQueue with the inputed capacity,
	 * initializes front to 1, rear to the capacity inputed and the count to 0
	 * 
	 * @param initialCapacity
	 */
	public CircularArrayQueue(int initialCapacity) {
		front = 1;
		rear = initialCapacity;
		count = 0;
		queue = (T[]) (new Object[initialCapacity]);
	}

	/**
	 * Enqueue method adds an element to the rear of the queue
	 * 
	 * @param element the element to be added to the queue
	 */
	public void enqueue(T element) {
		if (size() == queue.length) {
			expandCapacity();
		}
		rear = (rear + 1) % queue.length;
		queue[rear] = element;
		count++;
	}

	/**
	 * Dequeue method removes the element at the front of the queue
	 * 
	 * @return the element removed
	 */
	public T dequeue() {
		if (count == 0) {
			throw new EmptyCollectionException("queue");
		}
		T result = queue[front];
		front = (front + 1) % queue.length;
		count--;
		return result;
	}

	/**
	 * The first method returns the first object in the queue and throws an
	 * EmptyCollectionException if the queue is empty
	 * 
	 * @return
	 */
	public T first() {
		if (size() == 0) {
			throw new EmptyCollectionException("queue");
		}
		return queue[front];
	}

	/**
	 * The isEmpty method returns true if the queue is empty and false if the queue
	 * is not
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * The size method returns the number of elements in the queue
	 * 
	 * @return the number of elements in the queue
	 */
	public int size() {
		return count;
	}

	/**
	 * The accessor method getFront returns the front element in the queue
	 * 
	 * @return the front element in the queue
	 */
	public int getFront() {
		return front;
	}

	/**
	 * The accessor method getRear returns the last element in the queue
	 * 
	 * @return the last element in the queue
	 */
	public int getRear() {
		return rear;
	}

	/**
	 * The accessor method getLength returns the length of the queue array
	 * 
	 * @return the length of the queue array
	 */
	public int getLength() {
		return queue.length;
	}

	/**
	 * The toString method returns a String representation of the queue
	 * 
	 * @return a String representation of the queue
	 */
	public String toString() {
		if (size() == 0) {
			return "The queue is empty";
		}
		String str = "QUEUE: " + queue[front];
		for (int i = front + 1; i < rear + 1; i++) {
			str += ", ";
			str += queue[i % queue.length];
		}
		str += ".";
		return str;
	}

	/**
	 * The expandCapacity method expands the capacity of the queue array if it
	 * reaches full capacity, by increasing its capacity by 20
	 */
	public void expandCapacity() {
		int originalLength = queue.length;
		T[] newArray = (T[]) (new Object[queue.length + 20]);
		front = 1;
		rear = count;
		for (int i = front; i <= size(); i++) {
			newArray[i] = queue[i % (originalLength)];
		}
		queue = newArray;

	}

}

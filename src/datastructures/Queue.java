package datastructures;

class QNode<T> {
	
	private T key;
	QNode<T> next = null;

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}
}


public class Queue<T> {
	
	QNode<T> head = new QNode<T>();
	QNode<T> tail = new QNode<T>();
	QNode<T> a = new QNode<T>();
	int i = 0;

	
	public Queue() {
		head.next = tail;
		tail.next = null;
	}

	public void enqueue(T e) {
		QNode<T> n = new QNode<T>();
		n.setKey(e);
		n.next = tail;

		if(i == 0) {
			head = n;
			a = n;
			i++;
		} else {
			a.next = n;
			a = n;
			i++;
		}
	}

	public T dequeue() {
		T answer = head.getKey();
		head = head.next;
		i--;
		return answer;
	}
	
	public boolean isEmpty() {
		if(i == 0) return true;
		else return false;
	}
}

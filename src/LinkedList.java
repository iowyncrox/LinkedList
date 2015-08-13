import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

	private Node<T> head;

	public LinkedList() {
		head = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void clear() {
		head = null;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();

		for (Iterator<T> i = this.iterator(); i.hasNext();) {
			result.append((Object) i.next() + " ");
		}
		return result.toString();
	}

	/*******************************************************
	 *
	 * first element
	 *
	 ********************************************************/
	public void addFirst(T item) {
		head = new Node<T>(item, head);
	}

	public T getFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		return head.data;
	}

	public T deleteFirst() {
		T temp = getFirst();
		head = head.next;
		return temp;
	}

	/*******************************************************
	 *
	 * last element
	 *
	 ********************************************************/

	public void addLast(T item) {
		if (isEmpty()) {
			addFirst(item);
		} else {
			Node<T> temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node<T>(item, null);
		}
	}

	public T getLast() {
		if (isEmpty())
			throw new NoSuchElementException();

		Node<T> temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		return temp.data;
	}

	public T deleteLast() {
		T item = getLast();

		Node<T> current = head;
		Node<T> previous = null;
		while (current.next != null) {
			previous = current;
			current = current.next;
		}
		previous.next = null;
		return item;
	}

	/*******************************************************
	 *
	 * contains
	 *
	 ********************************************************/
	public boolean contains(T item) {
		for (Iterator<T> i = this.iterator(); i.hasNext();) {
			if (item.equals(i.next()))
				return true;
		}
		return false;
	}

	/*******************************************************
	 *
	 * reverse
	 *
	 ********************************************************/
	public void reverse() {
		head = reverseHelper(head);
	}

	private Node<T> reverseHelper(Node<T> head) {
		if (head == null || head.next == null)
			return head;

		Node<T> currentNode = head;
		Node<T> nextNode = null;
		Node<T> previousNode = null;

		while (currentNode != null) {
			nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}

		return previousNode;
	}

	/*******************************************************
	 *
	 * total number of elements
	 *
	 ********************************************************/
	public int totalNumberOfElements() {
		int count = 0;
		if (isEmpty())
			return count;
		for (Iterator<T> i = this.iterator(); i.hasNext();) {
			i.next();
			++count;
		}
		return count;
	}

	/*******************************************************
	 *
	 * get element at a specified position
	 *
	 ********************************************************/
	public T get(int position) {
		return getElementAtSpecifiedPosition(position);
	}

	private T getElementAtSpecifiedPosition(int position) {
		if (isEmpty())
			throw new NoSuchElementException();

		if (position > totalNumberOfElements() || position < 1)
			throw new NoSuchElementException();

		Node<T> temp = head;
		for (int i = 1; i < position; i++) {
			temp = temp.next;
		}

		return temp.data;
	}

	/*******************************************************
	 *
	 * insert after & insert before
	 *
	 ********************************************************/
	public void insertAfter(T key, T item) {
		if (isEmpty())
			throw new NoSuchElementException();

		Node<T> temp = head;

		while (temp != null && !key.equals(temp.data))
			temp = temp.next;

		if (temp != null)
			temp.next = new Node<T>(item, temp.next);
	}

	public void insertBefore(T key, T item) {
		if (isEmpty())
			throw new NoSuchElementException();

		if (head.next.equals(item)) {
			addFirst(item);
			return;
		}

		Node<T> currentNode = head;
		Node<T> previousNode = null;

		while (currentNode != null && !currentNode.data.equals(item)) {
			previousNode = currentNode;
			currentNode = currentNode.next;
		}

		if (currentNode != null) {
			previousNode.next = new Node<T>(item, currentNode);
		}
	}

	/*******************************************************
	 *
	 * insert item at specified position in a linked list
	 *
	 ********************************************************/
	public void addAtSpecifiedPosition(T item, int position) {
		if (isEmpty() && !(position == 1))
			throw new NoSuchElementException();

		if (position < 1 && position > totalNumberOfElements() + 1)
			throw new NoSuchElementException();

		if (position == 1) {
			addFirst(item);
		} else if (position == totalNumberOfElements() + 1) {
			addLast(item);
		} else {
			Node<T> currentNode = head;
			Node<T> previousNode = null;

			int tempPosition = 1;

			while (currentNode != null && tempPosition != position) {
				previousNode = currentNode;
				currentNode = currentNode.next;
				tempPosition++;
			}

			if (currentNode != null) {
				previousNode.next = new Node<T>(item, currentNode);
			}
		}
	}

	/*******************************************************
	 *
	 * node class
	 *
	 ********************************************************/
	private static class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}

	/*******************************************************
	 *
	 * iterator class
	 *
	 ********************************************************/
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {

		private Node<T> temp;

		public LinkedListIterator() {
			temp = head;
		}

		@Override
		public boolean hasNext() {
			return temp != null;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T item = temp.data;
			temp = temp.next;
			return item;
		}
	}

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("D");
		list.addFirst("C");
		list.addFirst("B");
		list.addFirst("A");
		System.out.println(list);

		list.addLast("s");
		Iterator<String> itr = list.iterator();
		while (itr.hasNext())
			System.out.print(itr.next() + " ");
		System.out.println();

		System.out.println(list.deleteLast());

		System.out.println(list.contains("s"));
		list.reverse();
		for (Object x : list)
			System.out.print(x + " ");
		System.out.println();

		System.out.println(list.contains("h"));
		System.out.println(list.totalNumberOfElements());
		System.out.println(list.get(1));
		System.out.println(list.get(4));

		list.addAtSpecifiedPosition("x", 6);
		for (Object x : list)
			System.out.print(x + " ");
		System.out.println();

		list.addAtSpecifiedPosition("x", 4);
		for (Object x : list)
			System.out.print(x + " ");
		System.out.println();

	}
}
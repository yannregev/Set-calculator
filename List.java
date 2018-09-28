public class List<E extends Comparable> implements ListInterface<E>{

	private class Node {

		E data;
		Node prior,
		next;

		public Node(E d) {
			this(d, null, null);
		}

		public Node(E data, Node prior, Node next) {
			this.data = data == null ? null : data;
			this.prior = prior;
			this.next = next;
		}

	};

	private Node first,current,last;
	private int size;

	public List() {
		first = current = last = null;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return first == null ? true : false;
	}

	@Override
	public ListInterface<E> init() {
		first = current = last = null;
		size = 0;
		return this;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ListInterface<E> insert(E d) {
		if (isEmpty()) {
			first = current = last = new Node(d);
		} else if (current == last){
			current = last = last.next = new Node(d, current, null);
		} else {
			current = current.next = current.next.prior = new Node(d, current, current.next);
		}
		size++;
		return null;
	}

	@Override
	public E retrieve() {
		return current.data;
	}

	@Override
	public ListInterface<E> remove() {
		if (isEmpty()) {
			return null;
		}

		if (current == last) {
			current.prior.next = current.next;
			current.next.prior = current.prior;
		} else {
			last = current = current.prior;
		}
		return this;
	}

	@Override
	public boolean find(E d) {
		Node node = first;
		while (node != null) {
			if (d.equals(node.data)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	@Override
	public boolean goToFirst() {
		if (isEmpty()) {
			return false;
		}
		current = first;
		return true;
	}

	@Override
	public boolean goToLast() {
		if (isEmpty()) {
			return false;
		}
		current = last;
		return true;
	}

	@Override
	public boolean goToNext() {
		if (isEmpty() || current == last) {
			return false;
		}
		current = current.next;
		return true;
	}

	@Override
	public boolean goToPrevious() {
		if (isEmpty() || current == first) {
			return false;
		}
		current = current.prior;
		return true;
	}

	@Override
	public ListInterface<E> copy() {
		List<E> temp = new List<E>();
		Node node = first;
		while (node != null) {
			temp.insert(node.data);
			node = node.next;
		}
		return temp;
	}
}

public class List<E extends Comparable<E>> implements ListInterface<E>{

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
		this.first = this.current = this.last = null;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public ListInterface<E> init() {
		this.first = this.current = this.last = null;
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
		} else if (first.data.compareTo(d) > 0) {
			current = first = first.prior = new Node (d, null, first);	
		} else  {
			current = first;
			while (current != last) {
				if (current.next.data.compareTo(d) > 0) {
					break;
				}
				current = current.next;
			}
			if (current == last){
				current = last = last.next = new Node(d, current, null);
			} else {
				current = current.next = current.next.prior = new Node(d, current, current.next);
			}
		}

		size++;
		return this;
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
		if (first == last) {
			first = current = last = null;
			size--;
			return null;
		}

		if (current == last) {
			last = current = current.prior;
			current.next = null;
		} else {
			current.prior.next = current.next;
			current.next.prior = current.prior;
			current = current.next;
		}
		size--;
		return this;
	}

	@Override
	public boolean find(E d) {
		if (isEmpty()) return false;
		current = first;
		if (current.data.compareTo(d) > 0) return false;
		if (current.data.compareTo(d) == 0) return true;
		current = current.next;
		while (current != null) {
			if (current.data.compareTo(d) == 0) return true;
			if (current.data.compareTo(d) > 0) {
				current = current.prior;
				return false;
			}
			current = current.next;
		}
		current = last;
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

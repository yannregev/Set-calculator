import java.util.*;

public class Set<E extends Comparable<E>> {
    	private LinkedList<E> elements;
	private int size;

	public Set() {
		this.elements = new LinkedList<E>();
		this.size = 0;
	}
	public Set(Set<E> set) {
		this.elements = new LinkedList<E>();
		@SuppressWarnings("unchecked")
		E[] array = (E[])set.elements.toArray();
		for (E temp: array) {
			this.elements.push(temp);
		}
	}

}

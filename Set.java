import java.util.*;

public class Set<E extends Comparable<E>> {
    	private ListInterface<E> elements;
	private int size;

	public Set() {
		this.elements = new List<E>();
		this.size = 0;
	}
	public Set(Set<E> set) {
		this.elements = set.elements.copy();
		this.size = set.size;
	}

	public void append(E rhs) {
		if (!elements.find(rhs)) {
			this.elements.insert(rhs);
			size++;
		}
	}

	public int getSize() {
		return size;
	}

	public boolean contains(E rhs) {
		return elements.find(rhs);
	}

	
	public Set<E> intersection(Set<E> s) {
		Set<E> intersectionSet = new Set<E>();
		this.elements.goToFirst();
		for(int i = 0; i < this.size; i++) {
		    	if(s.contains(this.elements.retrieve())) {
		        	intersectionSet.append(this.elements.retrieve());
		    	}
			this.elements.goToNext();
		}
		return intersectionSet;
	}

	public Set<E> difference(Set<E> s) {
		Set<E> differenceSet = new Set<E>();
		this.elements.goToFirst();
		for(int i = 0; i < this.size; i++) {
		    	if(!s.contains(this.elements.retrieve())) {
		        	differenceSet.append(this.elements.retrieve());
		    	}
			this.elements.goToNext();
		}
		return differenceSet;
	}
    
    	public Set<E> union(Set<E> s) {
        	Set<E> unionSet = new Set<E>(s);
		this.elements.goToFirst();
		for(int i = 0; i < this.size; i++) {
			if(!unionSet.contains(this.elements.retrieve())) {
				unionSet.append(this.elements.retrieve());
			}
			this.elements.goToNext();
		}
		return unionSet;
    	}
    
	public Set<E> symmetricDifference(Set<E> s) {
		Set<E> unionSet = intersection(s);
		Set<E> symmDiff = new Set<E>();
		this.elements.goToFirst();
		for(int i = 0; i < this.size; i++) {
			if(!unionSet.contains(this.elements.retrieve())) {
				symmDiff.append(this.elements.retrieve());
			}
			this.elements.goToNext();
		}
		s.elements.goToFirst();
		for(int i = 0; i < s.size; i++) {
			if(!unionSet.contains(s.elements.retrieve())) {
				symmDiff.append(s.elements.retrieve());
			}
			s.elements.goToNext();
		}
		return symmDiff;
	}

	public String toString() {
		StringBuffer temp = new StringBuffer("{");
		this.elements.goToFirst();
		for (int i = 0; i < this.size - 1; i++) {
				temp.append(this.elements.retrieve() + " ");
				this.elements.goToNext();
		}
		if (this.size > 0) {
			temp.append(this.elements.retrieve() + "}");
		} else {
			temp.append("}");
		}
		return temp.toString();
	}

}

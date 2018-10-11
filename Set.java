import java.util.*;

public class Set<E extends Comparable<E>> implements SetInterface<E>{
    	private ListInterface<E> elements;
	private int size;

	public Set() {
		this.elements = new List<E>();
		this.size = 0;
	}
	public Set(SetInterface<E> s) {
		Set<E> set = (Set<E>)s;
		this.elements = set.elements.copy();
		this.size = set.size;
	}

	public SetInterface<E> init() {
		this.elements = new List<E>();
		this.size = 0;
		return this;
	}

	public SetInterface<E> append(E rhs) {
		if (!elements.find(rhs)) {
			this.elements = this.elements.insert(rhs);
			this.size++;
		}
		return this;
	}

	public int getSize() {
		return this.size;
	}

	public boolean contains(E rhs) {
		return this.elements.find(rhs);
	}

	
	public SetInterface<E> intersection(SetInterface<E> s) {
		SetInterface<E> intersectionSet = new Set<E>();
		this.elements.goToFirst();
		for(int i = 0; i < this.size; i++) {
		    	if(s.contains(this.elements.retrieve())) {
		        	intersectionSet.append(this.elements.retrieve());
		    	}
			this.elements.goToNext();
		}
		return intersectionSet;
	}

	public SetInterface<E> difference(SetInterface<E> s) {
		SetInterface<E> differenceSet = new Set<E>();
		this.elements.goToFirst();
		for(int i = 0; i < this.size; i++) {
		    	if(!s.contains(this.elements.retrieve())) {
		        	differenceSet.append(this.elements.retrieve());
		    	}
			this.elements.goToNext();
		}
		return differenceSet;
	}
    
    	public SetInterface<E> union(SetInterface<E> s) {
        	SetInterface<E> unionSet = new Set<E>(s);

		this.elements.goToFirst();
		for(int i = 0; i < this.size; i++) {
			if(!unionSet.contains(this.elements.retrieve())) {
				unionSet.append(this.elements.retrieve());
			}
			this.elements.goToNext();
		}
		return unionSet;
    	}
    
	public SetInterface<E> symmetricDifference(SetInterface<E> set) {
		Set<E> s = (Set<E>)set;
		SetInterface<E> unionSet = intersection(s);
		SetInterface<E> symmDiff = new Set<E>();
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

	@Override
	public String toString() {
		StringBuffer temp = new StringBuffer();
		this.elements.goToFirst();
		if (this.size > 0) {
			temp.append(this.elements.retrieve());
			this.elements.goToNext();
		}
		for (int i = 1; i < this.size; i++) {
				temp.append(" " + this.elements.retrieve());
				this.elements.goToNext();
		}
		return temp.toString();
	}

}

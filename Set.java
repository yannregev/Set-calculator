import java.util.*;
import javax.swing.text.html.StyleSheet.ListPainter;

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

	@Override
	public SetInterface<E> init() {
		this.elements = new List<E>();
		this.size = 0;
		return this;
	}

	@Override
	public SetInterface<E> append(E rhs) {
		if (!elements.find(rhs)) {
			this.elements = this.elements.insert(rhs);
			this.size++;
		}
		return this;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean contains(E rhs) {
		return this.elements.find(rhs);
	}

	@Override	
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

	@Override
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
    
	@Override
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
    
	@Override
	public SetInterface<E> symmetricDifference(SetInterface<E> set) {
		Set<E> s = (Set<E>)set;
		Set<E> symmDiff = new Set<E>();
		this.elements.goToFirst();
		for(int i = 0; i < this.size; i++) {
			if(!s.contains(this.elements.retrieve())) {
				symmDiff.append(this.elements.retrieve());
			}
			this.elements.goToNext();
		}
		s.elements.goToFirst();
		for(int i = 0; i < s.size; i++) {
			if(!this.contains(s.elements.retrieve())) {
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

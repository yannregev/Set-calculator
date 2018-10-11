public interface SetInterface<E extends Comparable<E>> {

    /*
	Elements    : identifiers of Type Identifier 
	Structure   : 
	Domain      : Identifiers up to the given size
	Constructors:	

	public Set();
		PRE -
		POST- A new Set object is created
			and value is the empty identifiers array with a default size of 20
	public Set(int size);
		PRE -
		POST- A new Set object is created
			and the value is a the empty identifiers array with a size of 'size'

	public Set(Set s);
		PRE -
		POST- A new Set object is created
			and the value is the copy of the set 's'

	public Init();
		PRE - 
		POST- the value of the identifiers array is reset to empty with a default size of 20

	Public Init(int size);
		PRE - 
		POST- the value of the identifiers array is reset to empty with a size of 'size'
    */

	public void append(E rhs);
	/*
		PRE -
		POST- Copys Identifier to the end of the array
	*/
	public int getSize();
	/*
		PRE -
		POST- The number of elements has been returned
	*/	
	public boolean contains(E rhs);
	/*
		PRE -
		POST- TRUE: the element exists in the list
			FALSE: the element does not exist in the list
	*/
	public SetInterface<E> intersection(SetInterface<E> s);
	/*
		PRE -
		POST- A new SetInterface is created and the value is the intersection of of the two SetInterfaces
	*/
	public SetInterface<E> difference(SetInterface<E> s);
	/*
		PRE -
		POST- A new SetInterface is created and the value is the difference of of the two SetInterfaces
	*/
	public SetInterface<E> union(SetInterface<E> s);
	/*
		PRE -
		POST- A new SetInterface is created and the value is the union of of the two SetInterfaces
	*/
	public SetInterface<E> symmetricDifference(SetInterface<E> s);
	/*
		PRE -
		POST- A new SetInterface is created and the value is the symmetric difference of of the two SetInterfaces
	*/
}

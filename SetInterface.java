public interface SetInterface<E extends Comparable<E>> {

    /*
	Elements    : generic type elements 
	Structure   : 
	Domain      : elements that extends comparable
	Constructors:	

	public SetInterface();
		PRE -
		POST- A new SetInterface object is created 

	public SetInterface(SetInterface s);
		PRE -
		POST- A new SetInterface object is created with a deep copy of s data
    */
	
	public SetInterface<E> init();

	/*PRE - 
	POST- the set is reset to empty 
	*/


	public SetInterface append(E rhs);
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

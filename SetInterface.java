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

	/*	
		PRE - 
		POST- the setInterface object is reset to empty 
	*/


	public SetInterface<E> append(E rhs);
	/*
		PRE -
		POST- a new element is added to the setInterface object
	*/
	public int getSize();
	/*
		PRE -
		POST- The number of elements has been returned
	*/	
	public boolean contains(E rhs);
	/*
		PRE -
		POST- TRUE: the element exists in the setInterface object
			FALSE: the element does not exist in the setInterface object
	*/
	public SetInterface<E> intersection(SetInterface<E> s);
	/*
		PRE -
		POST-  the intersection value of of the two SetInterface objects is returned
	*/
	public SetInterface<E> difference(SetInterface<E> s);
	/*
		PRE -
		POST- the difference value of of the two SetInterface objects is returned
	*/
	public SetInterface<E> union(SetInterface<E> s);
	/*
		PRE -
		POST- the union value of of the two SetInterface objects is returned
	*/
	public SetInterface<E> symmetricDifference(SetInterface<E> s);
	/*
		PRE -
		POST- the symmetricDifference value of of the two SetInterface objects is returned
	*/

	public ListInterface<E> copy();
	/*
		PRE -
		POST- A copy if the list is returned
	*/
}

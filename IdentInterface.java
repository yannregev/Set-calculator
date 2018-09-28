public interface IdentInterface extends Comparable<IdentInterface> {
    /*
	Elements    : String of type StringBuffer
	Structure   : linear
	Domain      : 

	Constructors:	
	public Identifier(char c);
		PRE -
		POST- 

	public Identifier(StringBuffer element);
		PRE -
		POST- 

	public Identifier(Identifier i);
		PRE -
		POST- 

	public init(char c);
		PRE -
		POST-
	Public init(StringBuffer element);
		PRE -
		POST-
    */
	public void append(char c);
	/*
		PRE-
		POST-A new character is added to the identifier instance
	*/
	public void setValue(StringBuffer s);
	/*
		PRE-
		POST-The value of the identifer is set to the stringbuffer
	*/
}

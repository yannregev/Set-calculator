public interface IdentInterface {
    /*
	Elements    : String of type StringBuffer
	Structure   : linear
	Domain      : 

	Constructors:	
	public Identifier(char c);
		PRE -
		POST- A new Identifier object is created with char c as the value

	public Identifier(StringBuffer element);
		PRE -
		POST- A new Identifier object is created as a copy of the stringbuffer

	public Identifier(Identifier i);
		PRE -
		POST- A new Identifier object is created as a copy of Identifier i


    */

	public IdentInterface init(char c);
	/*
		PRE -
		POST- IdenInterface object is reset with char c
	*/
	public IdentInterface append(char c);
	/*
		PRE-
		POST-A new character is added to the identifier instance
	*/
	public IdentInterface setValue(StringBuffer s);
	/*
		PRE-
		POST-The value of the identifer is set to the stringbuffer
	*/
}

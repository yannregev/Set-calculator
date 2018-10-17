import java.util.*;

public class Identifier implements IdentInterface{
	private StringBuffer element;

	public Identifier(char c) {
	  	this.element = new StringBuffer(c+"");
	}

	public Identifier(StringBuffer element) {
	  	this.element = new StringBuffer(element);
	}

	public Identifier(Identifier ide) {
	  	this.element = new StringBuffer(ide.element);
	}

	@Override
	public IdentInterface init(char c) {
		this.element = new StringBuffer(c+"");
		return this;
	}

	@Override
	public IdentInterface setValue(StringBuffer element) {
		this.element = new StringBuffer(element);
		return this;
	}

	@Override
	public IdentInterface append(char c) {
		this.element.append(c);
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Identifier i = (Identifier)obj;
		return i.element.toString().equals(this.element.toString());
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		return element.toString();
	}
}

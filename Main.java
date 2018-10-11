import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.Pattern;

public class Main {

	static PrintStream out;
	static Map<IdentInterface,SetInterface<BigInteger>> variables;

	char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

	boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c+""));
	}

	boolean nextCharIsDigit(Scanner in) {
		return in.hasNext("[0-9]");
	}

	boolean nextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}
	
	void findNext(Scanner in) {
		if (!in.hasNext())return;
		do {
			nextChar(in);
		} while (nextCharIs(in, ' '));
	}

	void skipSpaces(Scanner in) {
		while (nextCharIs(in, ' ')) {
			nextChar(in);
		}
	}

	BigInteger readElement(Scanner in) throws APException{
		StringBuffer ide = new StringBuffer();
		if (nextCharIs(in, '0')) {
			ide.append(nextChar(in));
			skipSpaces(in);
			return new BigInteger(ide.toString());
		}
		
		while (nextCharIsDigit(in)) {
			ide.append(in.next());
		}
		skipSpaces(in);
		if (ide.toString().isEmpty()) {
			throw new APException("number expected");
		}
		return new BigInteger(ide.toString());
	}

	SetInterface<BigInteger> expression(Scanner in) throws APException {
		SetInterface<BigInteger> set = term(in);
		while (nextCharIs(in,'+') || nextCharIs(in,'-') || nextCharIs(in,'|')){
			if (nextCharIs(in,'+')) {
				findNext(in);
				set = set.union(term(in));
			} else if (nextCharIs(in,'-')) {
				findNext(in);
				set = set.difference(term(in));
			} else if (nextCharIs(in,'|')) {
				findNext(in);
				set = set.symmetricDifference(term(in));
			}
		}
		return set;
	}
	
	SetInterface<BigInteger> term(Scanner in) throws APException {
		SetInterface<BigInteger> set = factor(in);
		skipSpaces(in);
		while (nextCharIs(in,'*')) {
			nextChar(in);
			skipSpaces(in);
			set = set.intersection(factor(in));
			skipSpaces(in);
		}
		return set;
	}

	IdentInterface varName(Scanner in) throws APException {
		IdentInterface identifier = new Identifier(nextChar(in));
		while (nextCharIsLetter(in)) {
			identifier.append(nextChar(in));
		}
		if (!variables.containsKey(identifier)) {
			throw new APException("undefined variable");
		}
		return identifier;
	} 

	SetInterface<BigInteger> factor(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			return variables.get(varName(in));
		} else if (nextCharIs(in, '{')) {
			nextChar(in);
			skipSpaces(in);
			return set(in);
		} else if (nextCharIs(in, '(')) {
			nextChar(in);
			skipSpaces(in);
			SetInterface<BigInteger> set = expression(in);
			skipSpaces(in);
			if (!nextCharIs(in, ')')) {
				throw new APException("missing parenthesis");
			}
			findNext(in);
			return set;
		} else {
			throw new APException("incomplete statement");
		}
	}

	SetInterface<BigInteger> set(Scanner in) throws APException {
		SetInterface<BigInteger> set = new Set<BigInteger>();

		if (nextCharIs(in, '}')) {
			findNext(in);
			return set;
		}
		set.append(readElement(in));
		while(nextCharIs(in,',')) {
			findNext(in);
			set.append(readElement(in));
		}
		if (!nextCharIs(in, '}')) {
			throw new APException("missing closing curly brace");
		}
		findNext(in);
		return set;
	}

	void storeVariable(Scanner in) throws APException {
		IdentInterface identifier = new Identifier(nextChar(in));
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
			identifier.append(nextChar(in));
		}
		
		skipSpaces(in);

		if (!nextCharIs(in,'=')) {
			throw new APException("equal sign expected");
		}
		findNext(in);
		SetInterface<BigInteger> set = expression(in);
		if (!nextCharIs(in, '\n')) {
			throw new APException("no end of line");
		}
		variables.put(identifier, set);
		skipSpaces(in);
		
	}

	void statement(Scanner in) throws APException {
		skipSpaces(in);
		if (nextCharIs(in, '?')) {
			nextChar(in);
			skipSpaces(in);
			SetInterface<BigInteger> set = expression(in);
			skipSpaces(in);
			if (!nextCharIs(in, '\n')) throw new APException("no end of line");
			out.printf("%s\n", set);
		} else if (nextCharIsLetter(in)) {
			storeVariable(in);
		} else if (nextCharIs(in,'/')) {
			// Nothing to do, comment
		} else {
			throw new APException("no statement");
		}
		
	}

    	private void start(String[] argv) {	
		out = new PrintStream(System.out);
		variables = new HashMap<IdentInterface, SetInterface<BigInteger>>();     	
		Scanner in = new Scanner(System.in).useDelimiter("");	
		//Scanner in = new Scanner("? {2, 3}\n").useDelimiter("");	
		while (in.hasNextLine()){
			try {
				statement(in);
				
			} catch (APException e) {
				out.printf("error %s\n", e.getMessage());
			}
			in.nextLine();
		}

    	}

    	public static void main(String[] argv) {
        	new Main().start(argv);
    	}
}

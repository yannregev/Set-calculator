import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.Pattern;

public class Main {

	static PrintStream out;
	static Map<String,Set<BigInteger>> variables;
	static int line = 1;

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

	BigInteger identifier(Scanner in) throws APException{
		StringBuffer ide = new StringBuffer();
		if (nextCharIs(in, '0')) {
			ide.append(nextChar(in));
			skipSpaces(in);
			return new BigInteger(ide.toString());
		}
		
		while (nextCharIsDigit(in)) {
			ide.append(nextChar(in));
		}
		if (ide.toString().isEmpty()) {
			throw new APException("number expected");
		}
		skipSpaces(in);
		return new BigInteger(ide.toString());
	}

	Set<BigInteger> expression(Scanner in) throws APException {
		Set<BigInteger> set = term(in);
		while (nextCharIs(in,'+') || nextCharIs(in,'-') || nextCharIs(in,'|')){
			if (nextCharIs(in,'+')) {
				findNext(in);
				set = set.union(term(in));
			}
			if (nextCharIs(in,'-')) {
				findNext(in);
				set = set.difference(term(in));
			}
			if (nextCharIs(in,'|')) {
				findNext(in);
				set = set.symmetricDifference(term(in));
			}
		}
		return set;
	}
	
	Set<BigInteger> term(Scanner in) throws APException {
		Set<BigInteger> set = factor(in);
		skipSpaces(in);
		while (nextCharIs(in,'*')) {
			nextChar(in);
			skipSpaces(in);
			set = set.intersection(factor(in));
			skipSpaces(in);	
		}
		return set;
	}

	String varName(Scanner in) throws APException {
		StringBuffer name = new StringBuffer();
		while (nextCharIsLetter(in)) {
			name.append(nextChar(in));
		}
		if (!variables.containsKey(name.toString())) throw new APException("undefined variable");
		return name.toString();
	} 

	Set<BigInteger> factor(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			return variables.get(varName(in));
		} else if (nextCharIs(in, '{')) {
			nextChar(in);
			skipSpaces(in);
			return set(in);
		} else if (nextCharIs(in, '(')) {
			nextChar(in);
			skipSpaces(in);
			Set<BigInteger> set = expression(in);
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

	Set<BigInteger> set(Scanner in) throws APException {
		Set<BigInteger> set = new Set<BigInteger>();

		if (nextCharIs(in, '}')) {
			findNext(in);
			return set;
		}
		set.append(identifier(in));
		while(nextCharIs(in,',')) {
			findNext(in);
			set.append(identifier(in));
		}
		if (!nextCharIs(in, '}')) {
			throw new APException("missing closing curly brace");
		}
		findNext(in);
		return set;
	}
	void storeVariable(Scanner in) throws APException {
		StringBuffer name = new StringBuffer();
		while (nextCharIsLetter(in)) {
			name.append(nextChar(in));
		}
		
		skipSpaces(in);

		if (!nextCharIs(in,'=')) {
			throw new APException("equal sign expected");
		}
		findNext(in);
		Set<BigInteger> set = expression(in);
		if (!nextCharIs(in, '\n')) {
			throw new APException("no end of line");
		}
		variables.put(name.toString(), set);
		skipSpaces(in);
		
	}

	void statement(Scanner in) throws APException {
		skipSpaces(in);
		if (nextCharIs(in, '?')) {
			nextChar(in);
			skipSpaces(in);
			Set<BigInteger> set = expression(in);
			skipSpaces(in);
			if (!nextCharIs(in, '\n')) throw new APException("no end of line");
			out.printf("%d:%s\n",line, set);
		} else if (nextCharIsLetter(in)) {
			storeVariable(in);
		} else if (nextCharIs(in,'/')) {
			// Nothing to do
		} else {
			throw new APException("no statement");
		}
		
	}

    	private void start(String[] argv) {
        	
		out = new PrintStream(System.out);
		
		variables = new HashMap<String, Set<BigInteger>>();     	
		try {
			Scanner in = new Scanner(new File(argv[0])).useDelimiter("");		
		//	Scanner in = new Scanner("APE = { 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42 }\n? APE\n").useDelimiter("");
			while (in.hasNextLine()){
				try {
					statement(in);
					
				} catch (APException e) {
					out.printf("%d:error %s\n",line, e.getMessage());
				}
				line++;
				in.nextLine();
			}
		} catch (Exception e) {

		}
    	}

    	public static void main(String[] argv) {
        	new Main().start(argv);
    	}
}

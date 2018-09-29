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
		in.next();
		while (nextCharIs(in, ' ')) {
			in.next();
		}
	}

	void skipSpaces(Scanner in) {
		while (nextCharIs(in, ' ')) {
			in.next();
		}
	}

	BigInteger identifier(Scanner in) throws APException{
		StringBuffer ide = new StringBuffer();
		while (nextCharIsDigit(in)) {
			ide.append(in.next());
		}
		skipSpaces(in);
		return new BigInteger(ide.toString());
	}

	Set<BigInteger> expression(Scanner in) throws APException {
		if (nextCharIs(in,'+') || nextCharIs(in,'-') || nextCharIs(in,'|')) {
			findNext(in);
		}
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
		while (nextCharIs(in,'*')) {
			findNext(in);
			set.intersection(factor(in));
		}
		return set;
	}

	String varName(Scanner in) {
		StringBuffer name = new StringBuffer();
		while (nextCharIsLetter(in)) {
			name.append(in.next());
		}
		skipSpaces(in);
		return name.toString();
	} 

	Set<BigInteger> factor(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			return variables.get(varName(in));
		} else if (nextCharIs(in, '{')) {
			findNext(in);
			return set(in);
		} else if (nextCharIs(in, '(')) {
			findNext(in);
			Set<BigInteger> set = expression(in);
			skipSpaces(in);
			if (!nextCharIs(in, ')')) {
				throw new APException("')' expected");
			}
			return set;
		} else {
			throw new APException("invalid factor");
		}
	}

	Set<BigInteger> set(Scanner in) throws APException {
		Set<BigInteger> set = new Set<BigInteger>();
		while (nextCharIsDigit(in)) {
			set.append(identifier(in));
			if (!nextCharIs(in, ','))break;
			findNext(in);
		}
		if (!nextCharIs(in, '}')) {
			throw new APException("'}' expected");
		}
		findNext(in);
		return set;
	}
	void storeVariable(Scanner in) throws APException {
		StringBuffer name = new StringBuffer();
		while (!nextCharIs(in,' ') && !nextCharIs(in,'=')) {
			name.append(in.next());
		}
		
		skipSpaces(in);

		if (!nextCharIs(in,'=')) {
			throw new APException("'=' expected");
		}
		findNext(in);
		variables.put(name.toString(), expression(in));

	}

	void statement(Scanner in) throws APException {
		skipSpaces(in);
		
		if (nextCharIs(in, '?')) {
			findNext(in);
			out.printf("%d:%s\n",line, expression(in));
		} else if (nextCharIsLetter(in)) {
			storeVariable(in);
		} else if (nextCharIs(in,'/')) {
			// Nothing to do
		} else {
			throw new APException("Invalid statement");
		}
		line++;
	}

    	private void start(String[] argv) {
        	
		out = new PrintStream(System.out);
		
		variables = new HashMap<String, Set<BigInteger>>();     	
		

		try {
			Scanner in = new Scanner(new File(argv[0])).useDelimiter("");
			while (in.hasNextLine()){
				statement(in);
				in.nextLine();
			}
		} catch (APException e) {
			out.printf("Error: %s\n",e.getMessage());
		} catch (Exception e) {

		}

		
    	}

    	public static void main(String[] argv) {
        	new Main().start(argv);
    	}
}

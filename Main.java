import java.util.*;
import java.io.*;
import java.math.*;

public class Main {

	static PrintStream out;

    	private void start() {
        	Scanner in = new Scanner(System.in);
		out = new PrintStream(System.out);
        	List<BigInteger> list = new List<BigInteger>();

		list.insert(new BigInteger("3"));
		list.insert(new BigInteger("2"));
		list.goToFirst();
		out.printf("%s\n",list.retrieve());
		list.goToNext();
		out.printf("%s\n",list.retrieve());
        // While there is input, read line and parse it.
    	}

    	public static void main(String[] argv) {
        	new Main().start();
    	}
}

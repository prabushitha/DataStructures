import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args){
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		FastScanner in = new FastScanner(inputStream);
		FastPrinter out = new FastPrinter(outputStream);
		Task solver = new Task();
		solver.solve(1, in, out);
		out.close();
	}

}

class Task{
	public void solve(int testNumber, FastScanner in, FastPrinter out){
            int num = in.nextInt();
            HashMap<Integer, Integer> hMap = new HashMap<Integer, Integer>();
            hMap.put(0, 0);
            hMap.put(1, 0);
            fib(num, hMap);
            
            System.out.print(Integer.toString(hMap.get(0))+ " " + Integer.toString(hMap.get(1)));

	}
        
        public int fib(int num, HashMap<Integer, Integer> hMap) {
            if(num == 0){
               hMap.put(0, hMap.get(0)+1);
               return 0;
            }
            else if(num == 1){
                hMap.put(1, hMap.get(1)+1);
                return 1;
            }
            return fib(num-1,hMap) + fib(num-2, hMap);
                
        }
	
}

class FastScanner extends BufferedReader {

	public FastScanner(InputStream is) {
		super(new InputStreamReader(is));
	}

	public int read() {
		try {
			int ret = super.read();
			boolean isEOF = false;
			if (isEOF && ret < 0) {
				throw new InputMismatchException();
			}
			isEOF = ret == -1;
			return ret;
		} catch (IOException e) {
			throw new InputMismatchException();
		}
	}

	static boolean isWhiteSpace(int c) {
		return c >= 0 && c <= 32;
	}

	public int nextInt() {
		int c = read();
		while (isWhiteSpace(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int ret = 0;
		while (c >= 0 && !isWhiteSpace(c)) {
			if (c < '0' || c > '9') {
				throw new NumberFormatException("digit expected " + (char) c
						+ " found");
			}
			ret = ret * 10 + c - '0';
			c = read();
		}
		return ret * sgn;
	}

	public String readLine() {
		try {
			return super.readLine();
		} catch (IOException e) {
			return null;
		}
	}

}

class FastPrinter extends PrintWriter {

	public FastPrinter(OutputStream out) {
		super(out);
	}

	public FastPrinter(Writer out) {
		super(out);
	}


}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastScanner in = new FastScanner(inputStream);
        FastPrinter out = new FastPrinter(outputStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Task solver = new Task();
        ArrayList<String> num = new ArrayList<String>();
        int cases = Integer.parseInt(in.readLine());
        for (int i = 0; i < cases; i++) {
            String word = in.readLine();
            solver.solve(num, in, out, word);
        }
        solver.pairing(num);
        out.close();
    }

}

class Task {

    public void solve(ArrayList<String> num, FastScanner in, FastPrinter out, String word) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        char count = 'a';
        String word_new = new String();
        for (int i = 0; i < word.length(); i++) {
            
                if (map.containsKey(word.charAt(i))) {
                    word_new += map.get(word.charAt(i)) ;
                } else {
                    map.put(word.charAt(i), count);
                    word_new += count;
                    count++;

                
            }
        }
        num.add(word_new.trim());
        //System.out.println(word_new.length());

    }

    public void pairing(ArrayList<String> num) {
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        ArrayList<String> num2 = num;
        Collections.sort(num2);
        Iterator<String> itr = num2.iterator();
        while (itr.hasNext()) {
            String now = itr.next();

            hmap.put(now, 0);
        }
        Iterator<String> itr2 = num2.iterator();
        while (itr2.hasNext()) {
            String now = itr2.next();
            //System.out.println(now);
            hmap.put(now, hmap.get(now) + 1);
        }
        //System.out.print(hmap.values());
        int pairs = 0;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.addAll(hmap.values());

        for (int i = 0; i < temp.size(); i++) {
            //System.out.println(temp.get(i));
            int n = temp.get(i) - 1;
            pairs += n * (n + 1) / 2;
        }

        System.out.println(pairs);
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

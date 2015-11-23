
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastScanner in = new FastScanner(inputStream);
        FastPrinter out = new FastPrinter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

}

class Task {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        HashSet<String> operators = new HashSet<String>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        int nodes = Integer.parseInt(in.readLine());
        String[] names = new String[nodes];

        for (int i = 0; i < nodes; i++) {
            names[i] = in.readLine();
        }
        Tree t = new Tree(names);
        ArrayList<String> fin = new ArrayList<String>();
        t.insertNode();
        t.inOrderTraverseTree(fin);
        String exp = "";
        Iterator<String> itr = fin.iterator();
        while(itr.hasNext()){
            exp += itr.next()+" ";
        }
        int exp2 = (int) eval(exp);
        System.out.println(exp2);

    }
    
    public static int eval(final String str) {
    class Parser {
        int pos = -1, c;

        void eatChar() {
            c = (++pos < str.length()) ? str.charAt(pos) : -1;
        }

        void eatSpace() {
            while (Character.isWhitespace(c)) eatChar();
        }

        int parse() {
            eatChar();
            int v = parseExpression();
            if (c != -1) throw new RuntimeException("Unexpected: " + (char)c);
            return v;
        }

        // Grammar:
        // expression = term | expression `+` term | expression `-` term
        // term = factor | term `*` factor | term `/` factor | term brackets
        // factor = brackets | number | factor `^` factor
        // brackets = `(` expression `)`

        int parseExpression() {
            int v = parseTerm();
            for (;;) {
                eatSpace();
                if (c == '+') { // addition
                    eatChar();
                    v += parseTerm();
                } else if (c == '-') { // subtraction
                    eatChar();
                    v -= parseTerm();
                } else {
                    return v;
                }
            }
        }

        int parseTerm() {
            int v = parseFactor();
            for (;;) {
                eatSpace();
                if (c == '/') { // division
                    eatChar();
                    v /= parseFactor();
                } else if (c == '*' || c == '(') { // multiplication
                    if (c == '*') eatChar();
                    v *= parseFactor();
                } else {
                    return v;
                }
            }
        }

        int parseFactor() {
            int v;
            boolean negate = false;
            eatSpace();
            if (c == '+' || c == '-') { // unary plus & minus
                negate = c == '-';
                eatChar();
                eatSpace();
            }
            if (c == '(') { // brackets
                eatChar();
                v = parseExpression();
                if (c == ')') eatChar();
            } else { // numbers
                StringBuilder sb = new StringBuilder();
                while ((c >= '0' && c <= '9') || c == '.') {
                    sb.append((char)c);
                    eatChar();
                }
                if (sb.length() == 0) throw new RuntimeException("Unexpected: " + (char)c);
                v = Integer.parseInt(sb.toString());
            }
            eatSpace();
            if (c == '^') { // exponentiation
                eatChar();
                v = (int) Math.pow(v, parseFactor());
            }
            if (negate) v = -v; // unary minus is applied after exponentiation; e.g. -3^2=-9
            return v;
        }
    }
    return new Parser().parse();
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

class Tree {

    private static class Node {

        public String value;
        public Node left, right;

        public Node(String value) {
            this.value = value;
            left = null;
            right = null;
            
        }

    }

    public Node rootNode;
    String[] names;
    int length;

    public Tree(String[] names) {
        rootNode = null;
        this.names = names;
        this.length = names.length;
    }

    public void insertNode() {
        
        rootNode = insertNode(0, rootNode);
    }

    public Node insertNode(int key, Node curNode) {
        String[] temp = names[key].split(" ");
        //System.out.println("HI");

        if (curNode == null) {
            //System.out.println("HI");

            curNode = new Node(temp[0]);

            if (temp.length > 1) {

                int keyLeft = Integer.parseInt(temp[1]);
                int keyRight = Integer.parseInt(temp[2]);

                curNode.left = insertNode(keyLeft, curNode.left);
                curNode.right = insertNode(keyRight, curNode.right);
                
            }

        }
        return curNode;

    }

    public void inOrderTraverseTree(ArrayList<String> fin) {
        //System.out.println(rootNode);

        inOrderTraverseTree(rootNode, fin);
        //System.out.println(rootNode.value);

    }

    public void inOrderTraverseTree(Node focusNode,ArrayList<String> fin) {
        if (focusNode != null) {

            inOrderTraverseTree(focusNode.left, fin);
            fin.add(focusNode.value);
            //System.out.print(focusNode.value + " ");
            inOrderTraverseTree(focusNode.right, fin);
        }
    }
}

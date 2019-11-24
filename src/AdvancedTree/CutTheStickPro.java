package AdvancedTree;

import java.io.*;
import java.util.StringTokenizer;

public class CutTheStickPro {
    static InputStream inputStream = System.in;
    static InputReader in = new InputReader(inputStream);
    
    public static void main(String[] args) {
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        int a, b;
        a = in.nextInt();
        b = in.nextInt();
        out.println(a + b);
        out.close();
    }
    
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
        
        public char[] nextCharArray() {
            return next().toCharArray();
        }
    }
}

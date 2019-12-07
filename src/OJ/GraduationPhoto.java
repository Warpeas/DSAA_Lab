package OJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GraduationPhoto {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static char[] str;
    
    static void graduation(int first, int len) {
        if (first == len - 1) {
            out.println(str);
        } else {
            for (int i = first; i < len; i++) {
                swap(first, i);
                graduation(first + 1, len);
                swapBack(first, i);
            }
        }
    }
    
    static void swap(int i, int j) {
        char tem = str[j];
//        char[] tema = str.clone();
//        for (int k = i; k < j; k++) {
//            str[k+1] = tema[k];
//        }
        for (int k = j; k > i; k--) {
            str[k] = str[k - 1];
        }
        str[i] = tem;
    }
    
    static void swapBack(int i, int j) {
        char tem = str[i];
        for (int k = i; k < j; k++) {
            str[k] = str[k + 1];
        }
        str[j] = tem;
    }
    
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            str = in.nextCharArray();
            Arrays.sort(str);
            graduation(0, n);
        }
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

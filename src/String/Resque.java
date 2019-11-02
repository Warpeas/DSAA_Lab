package String;

import java.io.*;
import java.util.StringTokenizer;

public class Resque {
    private static int[] next(StringBuilder bs) {
        int m = bs.length();
        int[] next = new int[m];
        next[0] = 0;
        int k = 0;
        
        for (int i = 1; i < m; i++) {
            while (k > 0 && bs.charAt(k) != bs.charAt(i)) {
                k = next[k - 1];
            }
            if (bs.charAt(k) == bs.charAt(i)) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }
    
    private static int KMP(String t, StringBuilder p) {
        int n = t.length();
        int m = p.length();
        int[] nextP = next(p);
        int mid = n / 2 - 1;
        int q = 0;
        for (int i = n / 2 - 1; i < n; i++) {
            while (q >= 0 && p.charAt(q) != t.charAt(i)) {
                if (q == 0) {
                    mid = i;
                    break;
                }
                q = nextP[q - 1];
                if (q == 0) {
                    mid = i;
                    break;
                }
            }
            if (p.charAt(q) == t.charAt(i)) {
                q++;
            }
            if (q == m) {
                break;
            }
        }
        return mid + 1;
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
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        char[] l = new char[26];
        char[] rl = new char[26];
        String s;
        StringBuilder bs = new StringBuilder();
        int len;
        for (int i = 0; i < 26; i++) {
            l[i] = in.next().charAt(0);
        }
        for (int i = 0; i < 26; i++) {
            rl[l[i] - 'a'] = (char) ('a' + i);
        }
        s = in.next();
        len = s.length();
        for (int i = 0; i < len; i++) {
            bs.append(rl[s.charAt(i) - 'a']);
        }
        int mid = KMP(s, bs);
        out.println(mid);
        out.close();
    }
}

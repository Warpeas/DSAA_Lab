package OJ;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SigmaSigma {
    static ArrayList<Long> sigma;
    
    static long sigma(int k, long n) {
        long sum = 0;
        int j = 2;
        for (; j < n; j++) {
            if (n % j == 0) {
                break;
            }
        }
        if (j == n) {
            sum += 1;
            sum += pow(k, n);
        } else if (n == 1) {
            sum += 1;
        } else {
            for (; j < n; j *= 2) {
                sum += pow(k, j);
            }
        }
        return sum;
    }
    
    static long pow(int k, long d) {
        long sum = 1;
        for (int i = 0; i < k; i++) {
            sum *= d;
        }
        return sum;
    }
    
    static long sum(int m, long n) {
        long sum = 0;
        if (m == 0) {
            return sigma.get((int) n - 1);
        } else {
            for (int i = 1; i <= n; i++) {
                sum += sum(m - 1, i);
            }
        }
        return sum;
    }
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int m, k;
        long n;
        m = in.nextInt();
        k = in.nextInt();
        n = in.nextLong();
        sigma = new ArrayList<>();
        for (long i = 1; i <= n; i++) {
            sigma.add(sigma(k, i));
        }
        out.println(sum(m, n) % 1000000007);
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

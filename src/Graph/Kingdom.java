package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class Kingdom {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] am = new int[n][n];
            int[][] p = new int[m][2];
            for (int j = 0; j < m; j++) {
                p[j][0] = in.nextInt();
                p[j][1] = in.nextInt();
            }
            for (int j = 0; j < m; j++) {
                am[p[j][0] - 1][p[j][1] - 1] += 1;
            }
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    out.print(am[j][k] + " ");
                }
                out.println();
            }
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

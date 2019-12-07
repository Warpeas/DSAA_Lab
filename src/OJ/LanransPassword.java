package OJ;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LanransPassword {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    
    static class chars{
        char c;
        int cnt;
        
        chars(char c){
            this.c = c;
            cnt = 1;
        }
    }
    
    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[] origin = new char[n];
        char[][] after = new char[n-m+1][n];
        for (int i = 0; i < n - m + 1; i++) {
            after[i] = in.nextCharArray();
        }
        ArrayList<chars> p = new ArrayList<>();
        ArrayList<Character> cs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            p.clear();
            cs.clear();
            for (int j = 0; j < n - m + 1; j++) {
                if (cs.isEmpty()||!cs.contains(after[j][i])){
                    p.add(new chars(after[j][i]));
                    cs.add(after[j][i]);
                }else {
                    p.get(cs.indexOf(after[j][i])).cnt++;
                }
            }
            chars max = p.get(0);
            for (int j = 1; j < p.size(); j++) {
            if (p.get(j).cnt > max.cnt){
                max = p.get(j);
            }
            }
            origin[i] = max.c;
        }
        out.println(origin);
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

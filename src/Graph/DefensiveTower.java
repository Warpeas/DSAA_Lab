package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DefensiveTower {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static ArrayList<city> cities;
    static int n, m, zeros, ones;
    
    static class city {
        int index;
        ArrayList<Integer> next;
        int visit;
        int mark;
        
        city(int position) {
            index = position;
            next = new ArrayList<>();
            visit = 0;
        }
    }
    
    static void buildGraph() {
        cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cities.add(new city(i + 1));
        }
        for (int i = 0; i < m; i++) {
            int index = in.nextInt() - 1;
            int nxt = in.nextInt() - 1;
            cities.get(index).next.add(nxt);
            cities.get(nxt).next.add(index);
        }
    }
    
    static void dp(int index, int mark){
        cities.get(index).visit = 1;
        cities.get(index).mark = mark;
        if (mark == 0){
            zeros++;
            mark = 1;
        }else {
            ones++;
            mark = 0;
        }
        for (int i = 0; i < cities.get(index).next.size(); i++) {
            if (cities.get(cities.get(index).next.get(i)).visit!=1)
            dp(cities.get(index).next.get(i),mark);
        }
    }
    
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            m = in.nextInt();
            zeros = 0;
            ones = 0;
            buildGraph();
            dp(0,0);
            if (zeros > ones){
                out.println(ones);
                for (int j = 0; j < n; j++) {
                    if (cities.get(j).mark == 1)
                        out.print(cities.get(j).index+" ");
                }
            }else {
                out.println(zeros);
                for (int j = 0; j < n; j++) {
                    if (cities.get(j).mark == 0)
                        out.print(cities.get(j).index+" ");
                }
            }
            out.println();
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

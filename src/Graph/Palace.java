package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Palace {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static cube[] cubes;
    static int n;
    static long o;
    static ArrayList<Integer> ins;
    
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            buildGraph();
            for (int j = 0; j < ins.size(); j++) {
                TravelB(ins.get(j));
                if (cubes[ins.get(j)].height > o)
                    o = cubes[ins.get(j)].height;
            }
            out.println(o);
        }
        out.close();
    }
    
    static void buildGraph() {
        n = in.nextInt();
        o = 0;
        ins = new ArrayList<>();
        cubes = new cube[n];
        for (int i = 0; i < n; i++) {
            cubes[i] = new cube(in.nextInt(), in.nextInt(), in.nextInt());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && cubes[i].canBePlacedOn(cubes[j])) {
                    cubes[i].add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (cubes[i].indegree == 0) {
                ins.add(i);
            }
        }
    }
    
    static void Travel(int index) {
        if (!cubes[index].next.isEmpty()) {
            for (int i = 0; i < cubes[index].next.size(); i++) {
                if (cubes[cubes[index].next.get(i)].max(cubes[index].height)) {
                    return;
                }
                Travel(cubes[index].next.get(i));
            }
        } else {
            if (cubes[index].height > o) {
                o = cubes[index].height;
            }
        }
    }
    
    static void TravelB(int index) {
        int max = 0;
        for (int i = 0; i < cubes[index].next.size(); i++) {
            if (cubes[cubes[index].next.get(i)].visit != 1)
                TravelB(cubes[index].next.get(i));
            if (cubes[cubes[index].next.get(i)].height >= max) {
                max = cubes[cubes[index].next.get(i)].height;
            }
        }
        cubes[index].visit = 1;
        cubes[index].height = max + cubes[index].h;
    }
    
    static class cube {
        int l;
        int w;
        int h;
        int height;
        int visit = 0;
        int indegree = 0;
        int outdegree = 0;
        ArrayList<Integer> next = new ArrayList<>();
        
        cube(int l, int w, int h) {
            this.l = l;
            this.w = w;
            this.h = h;
            this.height = h;
        }
        
        boolean canBePlacedOn(cube other) {
            return (this.l < other.l && this.w < other.w) || (this.l < other.w && this.w < other.l);
        }
        
        void add(int index) {
            this.next.add(index);
            this.outdegree++;
            cubes[index].indegree++;
        }
        
        boolean max(int height) {
            if (this.h + height > this.height) {
                this.height = this.h + height;
                return false;
            } else
                return true;
        }
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

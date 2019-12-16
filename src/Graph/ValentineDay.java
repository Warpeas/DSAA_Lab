package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ValentineDay {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static ArrayList<city> cities;
    static Queue<BFS> nodes;
    static int distance = -1;
//    static ArrayList<Integer> visit;
    static int n, m, addition = 0;
    
    static class city {
        int index;
        ArrayList<Integer> next;
        int visit;
        city() {
            next = new ArrayList<>();
            visit = 0;
        }
        city(int position) {
            index = position;
            next = new ArrayList<>();
            visit = 0;
        }
    }
    
    static void buildGraph() {
        cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cities.add(new city(i+1));
        }
        for (int i = 0; i < m; i++) {
            int index = in.nextInt() - 1;
            int nxt = in.nextInt() - 1;
            int dis = in.nextInt();
            if (dis == 2) {
                cities.add(new city());
                cities.get(index).next.add(n + addition);
                cities.get(n + addition).next.add(nxt);
                addition++;
            } else {
                cities.get(index).next.add(nxt);
            }
        }
    }
    
    static class BFS {
        int index;
        ArrayList<BFS> next = new ArrayList<>();
        
        BFS(int index) {
            this.index = index;
        }
    }
    
    static BFS buildBFSTree(int index) {
        BFS root = new BFS(index);
        nodes = new LinkedList<>();
        cities.get(index).visit = 1;
        nodes.add(root);
        while (!nodes.isEmpty()) {
            BFS top = nodes.poll();
            for (int i = 0; i < cities.get(top.index).next.size(); i++) {
                if (cities.get(cities.get(top.index).next.get(i)).visit != 1) {
                    cities.get(cities.get(top.index).next.get(i)).visit = 1;
                    BFS next = new BFS(cities.get(top.index).next.get(i));
                    nodes.add(next);
                    top.next.add(next);
                }
            }
        }
        return root;
    }
    
    static void findN(BFS root, int cnt) {
        if (root.next != null) {
            cnt++;
            for (BFS sub : root.next) {
                if (sub.index == n - 1) {
                    distance = cnt;
                } else {
                    findN(sub, cnt);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        buildGraph();
        findN(buildBFSTree(0), 0);
        out.println(distance);
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

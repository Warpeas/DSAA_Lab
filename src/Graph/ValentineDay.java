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
    static int[] visit;
    static int n, m, addtion = 0;
    
    static class city {
        ArrayList<Integer> next;
        
        city() {
            next = new ArrayList<>();
        }
        
        ;
    }
    
    static void buildGraph() {
        cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cities.add(new city());
        }
        for (int i = 0; i < m; i++) {
            int index = in.nextInt() - 1;
            int nxt = in.nextInt();
            int dis = in.nextInt();
            if (dis == 2) {
                cities.add(new city());
                cities.get(index).next.add(n + addtion);
                cities.get(n + addtion).next.add(nxt);
                addtion++;
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
        visit[index] = 1;
        nodes.add(root);
        while (!nodes.isEmpty()) {
            BFS top = nodes.poll();
            for (int i = 0; i < cities.get(top.index).next.size(); i++) {
                if (visit[cities.get(top.index).next.get(i)] != 1) {
                    visit[cities.get(top.index).next.get(i)] = 1;
                    nodes.add(new BFS(cities.get(top.index).next.get(i)));
                    root.next.add(cities.get(top.index).next.get(i));
                }
            }
        }
        return root;
    }
    
    static int findN(BFS root, int cnt) {
        cnt++;
        if (root.next != null) {
            for (BFS sub :
                    root.next) {
                if (sub.index == n - 1) {
                    return cnt;
                } else {
                    return findN(sub, cnt + 1);
                }
            }
        } else {
            return -1;
        }
    }
    
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        buildGraph();
        out.println(findN(buildBFSTree(0), 0));
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

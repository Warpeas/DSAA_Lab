package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TheSwordofDamocles {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static Monster[] monsters;
    static AdjacencyList[] adjacencyLists;
    static ArrayList<BFS> bfsTrees;
    static Queue<Integer> nodes;
    static int[] visit;
    static int m, n, k;
    
    static class Monster {
        double x;
        double y;
        double s;
        
        Monster(double x, double y, double s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }
    
    static class AdjacencyList {
        int index;
        ArrayList<Integer> next = new ArrayList<>();
        
        AdjacencyList(int index) {
            this.index = index;
        }
    }
    
    static void creatAdjacencyList() {
        adjacencyLists = new AdjacencyList[k];
        for (int i = 0; i < monsters.length; i++) {
            adjacencyLists[i] = new AdjacencyList(i);
            for (int j = 0; j < monsters.length; j++) {
                if (j != i) {
                    if (isConnected(monsters[i], monsters[j]) <= 0) {
                        adjacencyLists[i].next.add(j);
                    }
                }
            }
        }
    }
    
    static double isConnected(Monster m1, Monster m2) {
        return Math.sqrt(Math.pow(m1.x - m2.x, 2) + Math.pow(m1.y - m2.y, 2)) - m1.s - m2.s;
    }
    
    static boolean isConnected() {
        for (int i = 0; i < bfsTrees.size(); i++) {
        
        }
        return false;
    }
    
    static boolean isConnectedLeft(int index) {
        return monsters[index].x <= monsters[index].s || n - monsters[index].y <= monsters[index].s;
    }
    
    static boolean isConnectedRight(int index) {
        return m - monsters[index].x <= monsters[index].s || monsters[index].y <= monsters[index].s;
    }
    
    static class BFS {
        int index;
        ArrayList<BFS> next = new ArrayList<>();
        
        BFS(int index) {
            this.index = index;
        }
    }
    
    static ArrayList<BFS> buildBFSTrees() {
        bfsTrees = new ArrayList<>();
        for (int i = 0; i < adjacencyLists.length; i++) {
            if (visit[i] != 1)
                bfsTrees.add(buildBFSTree(1));
        }
        return bfsTrees;
    }
    
    static BFS buildBFSTree(int index) {
        BFS root = new BFS(index);
        visit[index] = 1;
        for (int i = 0; i < adjacencyLists[index].next.size(); i++) {
            if (visit[adjacencyLists[index].next.get(i)] != 1) {
                nodes.add(adjacencyLists[index].next.get(i));
                visit[adjacencyLists[index].next.get(i)] = 1;
            }
        }
        while (!nodes.isEmpty()){
            root.next.add(buildBFSTree(nodes.poll()));
        }
        return root;
    }
    
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
            creatAdjacencyList();
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

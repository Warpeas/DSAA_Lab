package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
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
        int isConnectedLeft ;
        int isConnectedRight ;
        for (int i = 0; i < bfsTrees.size(); i++) {
        isConnectedLeft = 0;
        isConnectedRight = 0;
            for (int j = 0; j < bfsTrees.get(i).nodes.size(); j++) {
                if (isConnectedLeft(bfsTrees.get(i).nodes.get(j)))
                    isConnectedLeft = 1;
                if (isConnectedRight(bfsTrees.get(i).nodes.get(j)))
                    isConnectedRight = 1;
            }
            if (isConnectedLeft==1&&isConnectedRight==1){
                return true;
            }
        }
        return false;
    }
    
    static boolean isConnectedLeft(int index) {
        return monsters[index].x <= monsters[index].s || m - monsters[index].y <= monsters[index].s;
    }
    
    static boolean isConnectedRight(int index) {
        return n - monsters[index].x <= monsters[index].s || monsters[index].y <= monsters[index].s;
    }
    
    static class BFS {
//        int index;
        ArrayList<Integer> nodes = new ArrayList<>();
        
        BFS(int index) {
            this.nodes.add(index);
        }
    }
    
    static void buildBFSTrees() {
        bfsTrees = new ArrayList<>();
        for (int i = 0; i < adjacencyLists.length; i++) {
            if (visit[i] != 1)
                bfsTrees.add(buildBFSTree(i));
        }
    }
    
    static BFS buildBFSTree(int index) {
        BFS root = new BFS(index);
        nodes = new LinkedList<>();
        visit[index] = 1;
        nodes.add(index);
        while (!nodes.isEmpty()) {
            int top = nodes.poll();
            for (int i = 0; i < adjacencyLists[top].next.size(); i++) {
                if (visit[adjacencyLists[top].next.get(i)]!=1) {
                    visit[adjacencyLists[top].next.get(i)] = 1;
                    nodes.add(adjacencyLists[top].next.get(i));
                    root.nodes.add(adjacencyLists[top].next.get(i));
                }
            }
        }
        return root;
    }
    
    static void creatMonster(){
        monsters = new Monster[k];
        visit = new int[k];
        for (int i = 0; i < k; i++) {
            monsters[i] = new Monster(in.nextInt(),in.nextInt(),in.nextInt());
        }
    }
    
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
            creatMonster();
            creatAdjacencyList();
            buildBFSTrees();
            if (!isConnected())
                out.println("Yes");
            else
                out.println("No");
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

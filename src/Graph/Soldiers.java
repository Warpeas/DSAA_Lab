package Graph;

import java.io.*;
import java.util.*;

public class Soldiers {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n, m;
    static AdjacencyList[] adjacencyLists;
    static ArrayList<Integer> entrance;
    static Stack<Integer> output;
    static PriorityQueue<Integer> heap;
    
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            creatAdjacencyList();
            buildBinaryHeapFix();
            buildOutputStackFix();
            output();
        }
        out.close();
    }
    
    static class AdjacencyList {
        int index;
        ArrayList<Integer> next;
        int visit;
        int indegree;
        
        AdjacencyList(int position) {
            index = position;
            next = new ArrayList<>();
            visit = 0;
            indegree = 0;
        }
        
    }
    
    static void creatAdjacencyList() {
        n = in.nextInt();
        m = in.nextInt();
        adjacencyLists = new AdjacencyList[n];
        entrance = new ArrayList<>();
        heap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (int i = 0; i < n; i++) {
            adjacencyLists[i] = new AdjacencyList(i);
        }
        int index;
        int nxt;
        for (int i = 0; i < m; i++) {
            index = in.nextInt() - 1;
            nxt = in.nextInt() - 1;
            adjacencyLists[nxt].next.add(index);
            adjacencyLists[index].indegree++;
        }
        for (int i = 0; i < n; i++) {
            if (adjacencyLists[i].indegree == 0)
                entrance.add(i);
        }
    }
    
    static void buildBinaryHeapFix() {
        for (int i = 0; i < entrance.size(); i++) {
            if (adjacencyLists[entrance.get(i)].indegree == 0) {
                heap.add(entrance.get(i));
                addHeap(i);
            }
        }
        entrance.clear();
    }
    
    static void buildOutputStackFix() {
        output = new Stack<>();
        int tmp;
        int outs = 0;
        while (outs < n) {
            tmp = heap.poll();
            for (int j = 0; j < adjacencyLists[tmp].next.size(); j++) {
                adjacencyLists[adjacencyLists[tmp].next.get(j)].indegree--;
            }
            for (int j = 0; j < adjacencyLists[tmp].next.size(); j++) {
                if (adjacencyLists[adjacencyLists[tmp].next.get(j)].visit != 1 && adjacencyLists[adjacencyLists[tmp].next.get(j)].indegree == 0) {
                    entrance.add(adjacencyLists[tmp].next.get(j));
                }
            }
            for (int i = 0; i < entrance.size(); i++) {
                heap.add(entrance.get(i));
                addHeap(i);
            }
            entrance.clear();
            output.push(tmp + 1);
            outs++;
        }
    }
    
    private static void addHeap(int i) {
        adjacencyLists[entrance.get(i)].visit = 1;
//        adjacencyLists[entrance.get(i)].indegree--;
    }
    
    static void output() {
        while (!output.isEmpty()) {
            out.print(output.pop() + " ");
        }
        out.println();
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

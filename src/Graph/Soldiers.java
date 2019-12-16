package Graph;

import java.io.*;
import java.util.*;

public class Soldiers {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n, m, nodes;
    static AdjacencyList[] adjacencyLists;
    static ArrayList<DFS> dfsTrees;
    static Stack<DFS> stack;
    static Stack<Integer> output;
    static TreeNode root;
    
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            creatAdjacencyList();
            buildDFSTrees();
            buildBinaryHeap();
            buildOutputStack();
            output();
        }
    }
    
    static class AdjacencyList {
        int index;
        ArrayList<Integer> next = new ArrayList<>();
        int visit;
        
        AdjacencyList() {
            next = new ArrayList<>();
            visit = 0;
        }
        
        AdjacencyList(int position) {
            index = position;
            next = new ArrayList<>();
            visit = 0;
        }
    }
    
    static void creatAdjacencyList() {
        n = in.nextInt();
        m = in.nextInt();
        adjacencyLists = new AdjacencyList[n];
        for (int i = 0; i < n; i++) {
            adjacencyLists[i] = new AdjacencyList(i + 1);
        }
        int index;
        int nxt;
        for (int i = 0; i < m; i++) {
            index = in.nextInt() - 1;
            nxt = in.nextInt() - 1;
            adjacencyLists[nxt].next.add(index);
        }
    }
    
    static class DFS {
        int index;
        ArrayList<DFS> nodes = new ArrayList<>();
        
        DFS(int index) {
            this.index = index;
        }
    }
    
    static void buildDFSTrees() {
        dfsTrees = new ArrayList<>();
        for (int i = 0; i < adjacencyLists.length; i++) {
            if (adjacencyLists[i].visit != 1)
                dfsTrees.add(buildDFSTree(i));
        }
    }
    
    static DFS buildDFSTree(int index) {
        DFS root = new DFS(index);
        stack = new Stack<>();
        adjacencyLists[index].visit = 1;
        stack.add(root);
        while (!stack.isEmpty()) {
            DFS top = stack.pop();
            for (int i = 0; i < adjacencyLists[top.index].next.size(); i++) {
                if (adjacencyLists[adjacencyLists[top.index].next.get(i)].visit != 1) {
                    adjacencyLists[adjacencyLists[top.index].next.get(i)].visit = 1;
                    DFS next = new DFS(adjacencyLists[top.index].next.get(i));
                    stack.push(next);
                    top.nodes.add(next);
                }
            }
        }
        return root;
    }
    
    static TreeNode buildBinaryHeap() {
        root = new TreeNode(dfsTrees.get(0));
        nodes++;
        for (int i = 1; i < dfsTrees.size(); i++) {
            TreeNode tmp = new TreeNode(dfsTrees.get(i));
            nodes++;
            root.insertMax(tmp);
        }
        return root;
    }
    
    static void buildOutputStack() {
        output = new Stack<>();
        while (true) {
            DFS tmp = root.deleteMax();
            output.push(tmp.index + 1);
            for (int i = 0; i < tmp.nodes.size(); i++) {
                root.insertMax(new TreeNode(tmp.nodes.get(i)));
            }
            if (root == null && tmp.nodes.isEmpty()) {
                break;
            }
        }
    }
    
    static void output() {
        while (!output.empty()) {
            out.print(output.pop() + " ");
        }
        out.println();
    }
    
    private static class TreeNode {
        DFS index;
        TreeNode father;
        TreeNode left;
        TreeNode right;
        
        TreeNode(DFS index) {
            this.index = index;
        }
        
        void insertMax(TreeNode node) {
            if (root.index.index == -1) {
                root = node;
            } else {
                String str = toBinary(nodes);
                TreeNode tmp = this;
                for (int i = 1; i < str.length() - 1; i++) {
                    if (str.charAt(i) == '0') {
                        tmp = tmp.left;
                    } else
                        tmp = tmp.right;
                }
                if (str.charAt(str.length() - 1) == '0') {
                    tmp.left = node;
                    tmp.left.father = tmp;
                    tmp = tmp.left;
                } else {
                    tmp.right = node;
                    tmp.right.father = tmp;
                    tmp = tmp.right;
                }
                while (tmp != this) {
                    if (tmp.index.index > tmp.father.index.index) {
                        int i = tmp.index.index;
                        tmp.index.index = tmp.father.index.index;
                        tmp.father.index.index = i;
                        tmp = tmp.father;
                    } else {
                        break;
                    }
                }
            }
        }
        
        static String toBinary(int cnt) {
            String str = "";
            while (cnt != 0) {
                str = cnt % 2 + str;
                cnt /= 2;
            }
            return str;
        }
        
        DFS deleteMax() {
            String str = toBinary(nodes);
            TreeNode tmp = root;
            DFS output = root.index;
            nodes--;
            if (nodes == 1) {
                tmp.index.index = tmp.left.index.index;
                tmp.left = null;
                return output;
            } else if (nodes == 0) {
                root.index.index = -1;
                return output;
            }
//            tmp.maintain();
            for (int i = 1; i < str.length() - 1; i++) {
                if (str.charAt(i) == '0') {
                    tmp = tmp.left;
                } else
                    tmp = tmp.right;
            }
            if (str.charAt(str.length() - 1) == '0') {
                root.index.index = root.left.index.index;
                tmp.left = null;
            } else {
                root.index.index = root.right.index.index;
                tmp.right = null;
            }
            tmp = root;
            tmp.maintain();
            return output;
        }
        
        void maintain() {
            TreeNode tmp = this;
            while (tmp.left != null || tmp.right != null) {
                if (tmp.right != null && tmp.left != null) {
                    if (tmp.right.index.index > tmp.left.index.index) {
                        if (tmp.index.index > tmp.right.index.index) {
                            break;
                        } else {
                            int i = tmp.index.index;
                            tmp.index.index = tmp.right.index.index;
                            tmp.right.index.index = i;
                            tmp = tmp.right;
                        }
                    } else {
                        if (tmp.index.index > tmp.left.index.index) {
                            break;
                        } else {
                            int i = tmp.index.index;
                            tmp.index.index = tmp.left.index.index;
                            tmp.left.index.index = i;
                            tmp = tmp.left;
                        }
                    }
                } else if (tmp.left != null) {
                    if (tmp.index.index > tmp.left.index.index) {
                        break;
                    } else {
                        int i = tmp.index.index;
                        tmp.index.index = tmp.left.index.index;
                        tmp.left.index.index = i;
                        tmp = tmp.left;
                    }
                }
            }
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

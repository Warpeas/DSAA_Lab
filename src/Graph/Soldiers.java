package Graph;

import java.io.*;
import java.util.*;

public class Soldiers {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n, m, nodes;
    static long cnt;
    static AdjacencyList[] adjacencyLists;
    static ArrayList<DFS> dfsTrees;
    static Stack<Integer> stack;
    static int[] visit;
    
    static class AdjacencyList {
        int index;
        ArrayList<Integer> next = new ArrayList<>();
        
        AdjacencyList(int index) {
            this.index = index;
        }
    }
    
    static void creatAdjacencyList() {
        adjacencyLists = new AdjacencyList[n];
        int index;
        int nxt;
        for (int i = 0; i < m; i++) {
            index = in.nextInt()-1;
            nxt = in.nextInt()-1;
            adjacencyLists[nxt].next.add(index);
        }
    }
    
    static class DFS {
        //        int index;
        ArrayList<Integer> nodes = new ArrayList<>();
        
        DFS(int index) {
            this.nodes.add(index);
        }
    }
    
    static void buildDFSTrees() {
        dfsTrees = new ArrayList<>();
        for (int i = 0; i < adjacencyLists.length; i++) {
            if (visit[i] != 1)
                dfsTrees.add(buildDFSTree(i));
        }
    }
    
    static DFS buildDFSTree(int index) {
        DFS root = new DFS(index);
        stack = new Stack<>();
        visit[index] = 1;
        stack.push(index);
        while (!stack.isEmpty()) {
            int top = stack.pop();
            for (int i = 0; i < adjacencyLists[top].next.size(); i++) {
                if (visit[adjacencyLists[top].next.get(i)] != 1) {
                    visit[adjacencyLists[top].next.get(i)] = 1;
                    stack.push(adjacencyLists[top].next.get(i));
                    root.nodes.add(adjacencyLists[top].next.get(i));
                }
            }
        }
        return root;
    }
    
    private static class TreeNode {
        long index;
        int depth = 0;
        TreeNode father;
        TreeNode left;
        TreeNode right;
        
        TreeNode(long index) {
            this.index = index;
        }
        
        static TreeNode buildBinaryHeap() {
            long index = in.nextLong();
            TreeNode root = new TreeNode(index);
            nodes++;
            for (int i = 1; i < n; i++) {
                index = in.nextLong();
                TreeNode tmp = new TreeNode(index);
                nodes++;
                root.insertMin(tmp);
            }
            return root;
        }
        
        void insertMin(TreeNode node) {
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
                if (tmp.index < tmp.father.index) {
                    long i = tmp.index;
                    tmp.index = tmp.father.index;
                    tmp.father.index = i;
                    tmp = tmp.father;
                } else {
                    break;
                }
            }
        }
        
        void insertMax(TreeNode node) {
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
                if (tmp.index > tmp.father.index) {
                    long i = tmp.index;
                    tmp.index = tmp.father.index;
                    tmp.father.index = i;
                    tmp = tmp.father;
                } else {
                    break;
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
        
        void deleteMin() {
            String str = toBinary(nodes);
            TreeNode tmp = this;
            nodes--;
            if (this.left != null && this.right != null) {
                if (this.left.index < this.right.index) {
                    cnt += this.index + this.left.index;
                    this.left.index += this.index;
                    tmp = this.left;
                } else {
                    cnt += this.index + this.right.index;
                    this.right.index += this.index;
                    tmp = this.right;
                }
            } else {
                cnt += this.index + this.left.index;
            }
            tmp.maintain();
            tmp = this;
            for (int i = 1; i < str.length() - 1; i++) {
                if (str.charAt(i) == '0') {
                    tmp = tmp.left;
                } else
                    tmp = tmp.right;
            }
            if (str.charAt(str.length() - 1) == '0') {
                this.index = tmp.left.index;
                tmp.left = null;
            } else {
                this.index = tmp.right.index;
                tmp.right = null;
            }
            tmp = this;
            tmp.maintain();
        }
        
        void maintain() {
            TreeNode tmp = this;
            while (tmp.left != null || tmp.right != null) {
                if (tmp.right != null && tmp.left != null) {
                    if (tmp.right.index < tmp.left.index) {
                        if (tmp.index < tmp.right.index) {
                            break;
                        } else {
                            long i = tmp.index;
                            tmp.index = tmp.right.index;
                            tmp.right.index = i;
                            tmp = tmp.right;
                        }
                    } else {
                        if (tmp.index < tmp.left.index) {
                            break;
                        } else {
                            long i = tmp.index;
                            tmp.index = tmp.left.index;
                            tmp.left.index = i;
                            tmp = tmp.left;
                        }
                    }
                } else {
                    if (tmp.index < tmp.left.index) {
                        break;
                    } else {
                        long i = tmp.index;
                        tmp.index = tmp.left.index;
                        tmp.left.index = i;
                        tmp = tmp.left;
                    }
                }
            }
        }
        
        void deleteMax() {
            String str = toBinary(nodes);
            TreeNode tmp = this;
            nodes--;
            if (!str.equals("1")) {
                for (int i = 1; i < str.length() - 1; i++) {
                    if (str.charAt(i) == '0') {
                        tmp = tmp.left;
                    } else
                        tmp = tmp.right;
                }
                if (str.charAt(str.length() - 1) == '0') {
                    this.index = tmp.left.index;
                    tmp.left = null;
                } else {
                    this.index = tmp.right.index;
                    tmp.right = null;
                }
            } else {
                return;
            }
            tmp = this;
            while (tmp.left != null || tmp.right != null) {
                if (tmp.right != null && tmp.left != null) {
                    if (tmp.right.index > tmp.left.index) {
                        if (tmp.index > tmp.right.index) {
                            break;
                        } else {
                            long i = tmp.index;
                            tmp.index = tmp.right.index;
                            tmp.right.index = i;
                            tmp = tmp.right;
                        }
                    } else {
                        if (tmp.index > tmp.left.index) {
                            break;
                        } else {
                            long i = tmp.index;
                            tmp.index = tmp.left.index;
                            tmp.left.index = i;
                            tmp = tmp.left;
                        }
                    }
                } else {
                    if (tmp.index > tmp.left.index) {
                        break;
                    } else {
                        long i = tmp.index;
                        tmp.index = tmp.left.index;
                        tmp.left.index = i;
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

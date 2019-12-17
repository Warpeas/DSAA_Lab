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
    //    static ArrayList<DFS> dfsTrees;
    static ArrayList<Integer> ins;
    //    static Stack<DFS> stack;
    static Stack<Integer> output;
    static TreeNode root;
    
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            creatAdjacencyList();
//            buildDFSTrees();
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
    
    static boolean allVisited(int index) {
        int flag = 1;
        for (int i = 0; i < adjacencyLists[index].next.size(); i++) {
            if (adjacencyLists[adjacencyLists[index].next.get(i)].visit != 1 && adjacencyLists[adjacencyLists[index].next.get(i)].indegree == 0)
                flag = 0;
        }
        if (flag == 1) {
            return true;
        } else
            return false;
    }
    
    static void creatAdjacencyList() {
        n = in.nextInt();
        m = in.nextInt();
        adjacencyLists = new AdjacencyList[n];
        ins = new ArrayList<>();
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
                ins.add(i);
        }
    }

//    static class DFS {
//        int index;
//        ArrayList<DFS> nodes = new ArrayList<>();
//
//        DFS(int index) {
//            this.index = index;
//        }
//
//        DFS(DFS other) {
//            index = other.index;
//            nodes = other.nodes;
//        }
//    }
//
//    static void buildDFSTrees() {
//        dfsTrees = new ArrayList<>();
//        for (int i = 0; i < ins.size(); i++) {
//            dfsTrees.add(buildDFSTree(ins.get(i)));
//        }
//    }

//    static DFS buildDFSTree(int index) {
//        DFS root = new DFS(index);
//        stack = new Stack<>();
//        adjacencyLists[index].visit = 1;
//        for (int i = 0; i < adjacencyLists[index].next.size(); i++) {
//            if (adjacencyLists[adjacencyLists[index].next.get(i)].visit!=1)
//            adjacencyLists[adjacencyLists[index].next.get(i)].indegree--;
//        }
//        stack.add(root);
//        while (!stack.empty()) {
//            DFS top = stack.peek();
//            while (adjacencyLists[top.index].next.isEmpty() || allVisited(top.index)) {
//                stack.pop();
//                if (stack.empty())
//                    break;
//                top = stack.peek();
//            }
//            if (stack.empty())
//                break;
//            for (int i = 0; i < adjacencyLists[top.index].next.size(); i++) {
//                if (adjacencyLists[adjacencyLists[top.index].next.get(i)].visit != 1 && adjacencyLists[adjacencyLists[top.index].next.get(i)].indegree == 0) {
//                    adjacencyLists[adjacencyLists[top.index].next.get(i)].visit = 1;
//                    for (int j = 0; j < adjacencyLists[adjacencyLists[top.index].next.get(i)].next.size(); j++) {
//                        adjacencyLists[adjacencyLists[adjacencyLists[top.index].next.get(i)].next.get(j)].indegree--;
//                    }
//                    DFS next = new DFS(adjacencyLists[top.index].next.get(i));
//                    stack.push(next);
//                    top.nodes.add(next);
//                }
//            }
//        }
//        return root;
//    }
//
//    static TreeNode buildBinaryHeap() {
//        root = new TreeNode(dfsTrees.get(0));
//        nodes++;
//        for (int i = 1; i < dfsTrees.size(); i++) {
//            TreeNode tmp = new TreeNode(dfsTrees.get(i));
//            root.insertMax(tmp);
//        }
//        return root;
//    }
    
    static void buildBinaryHeapFix() {
        nodes = 0;
        for (int i = 0; i < ins.size(); i++) {
            TreeNode.insertMax(new TreeNode(ins.get(i)));
        }
    }
    
    static void buildOutputStack() {
        output = new Stack<>();
        while (root.index != -1) {
            int tmp = TreeNode.deleteMax();
            output.push(tmp + 1);
            for (int i = 0; i < adjacencyLists[tmp].next.size(); i++) {
                TreeNode.insertMax(new TreeNode(adjacencyLists[tmp].next.get(i)));
            }
            if (root == null && adjacencyLists[tmp].next.isEmpty()) {
                break;
            }
        }
    }
    
    static void buildOutputStackFix() {
        output = new Stack<>();
        while (output.size() <= n) {
            int tmp = TreeNode.deleteMax();
            for (int j = 0; j < adjacencyLists[tmp].next.size(); j++) {
                if (adjacencyLists[adjacencyLists[tmp].next.get(j)].visit != 1 && adjacencyLists[adjacencyLists[tmp].next.get(j)].indegree == 0) {
                    TreeNode.insertMax(new TreeNode(adjacencyLists[tmp].next.get(j)));
                }
            }
            output.push(tmp + 1);
            if (root==null)
                break;
        }
    }
    
    static void output() {
        while (!output.empty()) {
            out.print(output.pop() + " ");
        }
        out.println();
    }
    
    private static class TreeNode {
        int index;
        TreeNode father;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int index) {
            this.index = index;
            adjacencyLists[index].visit = 1;
            for (int i = 0; i < adjacencyLists[index].next.size(); i++) {
                adjacencyLists[adjacencyLists[index].next.get(i)].indegree--;
            }
        }
        
        static void insertMax(TreeNode node) {
            if (root == null) {
                nodes++;
                root = node;
            } else {
                String str = toBinary(++nodes);
                TreeNode tmp = root;
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
                while (tmp != root) {
                    if (tmp.index > tmp.father.index) {
                        int i = tmp.index;
                        tmp.index = tmp.father.index;
                        tmp.father.index = i;
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
            if (str.equals(""))
                return "0";
            return str;
        }
        
        static int deleteMax() {
            String str = toBinary(nodes--);
            int output = root.index;
            if (nodes == 0) {
                root = null;
                return output;
            }
            if (nodes == 1) {
                root.index = root.left.index;
                root.left = null;
                return output;
            }
            TreeNode tmp = root;
//            tmp.maintain();
            for (int i = 1; i < str.length() - 1; i++) {
                if (str.charAt(i) == '0') {
                    tmp = tmp.left;
                } else
                    tmp = tmp.right;
            }
            if (str.charAt(str.length() - 1) == '0') {
                root.index = tmp.left.index;
                tmp.left = null;
            } else {
                root.index = tmp.right.index;
                tmp.right = null;
            }
            TreeNode.maintain();
            return output;
        }
        
        static void maintain() {
            TreeNode tmp = root;
            while (tmp.left != null || tmp.right != null) {
                if (tmp.right != null && tmp.left != null) {
                    if (tmp.right.index > tmp.left.index) {
                        if (tmp.index > tmp.right.index) {
                            break;
                        } else {
                            int i = tmp.index;
                            tmp.index = tmp.right.index;
                            tmp.right.index = i;
                            tmp = tmp.right;
                        }
                    } else {
                        if (tmp.index > tmp.left.index) {
                            break;
                        } else {
                            int i = tmp.index;
                            tmp.index = tmp.left.index;
                            tmp.left.index = i;
                            tmp = tmp.left;
                        }
                    }
                } else if (tmp.left != null) {
                    if (tmp.index > tmp.left.index) {
                        break;
                    } else {
                        int i = tmp.index;
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

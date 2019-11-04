package Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class KPeopleTravelOnATree {
    static TreeNode[] tree;
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            tree = new TreeNode[n + 1];
            for (int j = 1; j <= n; j++) {
                tree[j] = new TreeNode(j);
            }
            for (int j = 0; j < n - 1; j++) {
                int t1 = in.nextInt();
                int t2 = in.nextInt();
                tree[t1].relations.add(t2);
                tree[t2].relations.add(t1);
            }
            tree[1].StructTree(1);
            for (int j = 0; j < k; j++) {
                tree[in.nextInt()].f = 1;
            }
            out.println(TreeNode.findFar(1) / 2);
        }
        out.close();
    }
    
    
    private static class TreeNode {
        int index;
        int f = 0;
        int father;
        int distance = 0;
        int far = 0;
        ArrayList<Integer> relations = new ArrayList<>();
        
        //For the main root, you can write such like root.StructTree(root), that dose no wrong
        void StructTree(int father) {
            this.father = father;
            for (int i = 0; i < relations.size(); i++) {
                if (relations.get(i) != father) {
                    tree[relations.get(i)].StructTree(this.index);
                }
            }
        }
        
        void travel(int father) {
            this.father = father;
            if (relations.size() == 1 && relations.get(0) == father && f == 1) {
                far = index;
            }
            for (int i = 0; i < relations.size(); i++) {
                if (relations.get(i) != father) {
                    tree[relations.get(i)].travel(index);
                    if (tree[relations.get(i)].distance > distance && tree[relations.get(i)].far != 0) {
                        distance = tree[relations.get(i)].distance;
                        far = tree[relations.get(i)].far;
                    }
                }
            }
            if (far == 0 && f == 1) {
                far = index;
            }
            distance++;
        }
        
        static int findFar(int first) {
            int second;
            tree[first].travel(first);
            second = tree[first].far;
            for (int i = 1; i < tree.length; i++) {
                tree[i].distance = 0;
                tree[i].far = 0;
            }
            tree[second].travel(second);
            return tree[second].distance;
        }
        
        TreeNode(int index) {
            this.index = index;
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

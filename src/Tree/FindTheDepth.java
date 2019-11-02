package Tree;

import java.io.*;
import java.util.*;

public class FindTheDepth {
    static TreeNode[] tree;
    private static class TreeNode {
        int index;
        int depth = 0;
//        int relation_amount = 0;
        int father;
        ArrayList<Integer> relations = new ArrayList<>();
        
        //For the main root, you can write such like root.StructTree(root), that dose no wrong
        void StructTree(int father) {
            this.father = father;
            for (int i = 0; i < relations.size(); i++) {
                if (relations.get(i) != father) {
                    tree[relations.get(i)].depth = depth + 1;
                    tree[relations.get(i)].StructTree(this.index);
                }
            }
        }
        TreeNode(int index) {
            this.index = index;
        }
    }
    
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
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
//                for (int j = 1; j <= n; j++) {
//                    for (int k = 0; k < tree[j].relation_amount; k++) {
//                        if (tree[j].relations.get(k) != tree[j].father) {
//                            tree[tree[j].relations[k]].father = j;
//                            tree[tree[j].relations[k]].depth = tree[j].depth + 1;
//                        }
//                    }
//                }
            tree[1].StructTree(1);
            for (int j = 1; j <= n; j++) {
                out.print(tree[j].depth + " ");
            }
            out.println();
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

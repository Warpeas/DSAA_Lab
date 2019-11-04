package Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CutTheTree {
    static TreeNode[] tree;
    static int red_amount, blue_amount, valuable;
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            red_amount = 0;
            blue_amount = 0;
            valuable = 0;
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
            for (int j = 1; j <= n; j++) {
                tree[j].p = in.nextInt();
                if (tree[j].p == 1) {
                    red_amount++;
                } else if (tree[j].p == 2) {
                    blue_amount++;
                }
            }
            tree[1].StructTree(1);
//            tree[1].Travel();
            out.println(valuable);
        }
        out.close();
    }
    
    private static class TreeNode {
        int index;
        int father;
        int p;
        int red = 0;
        int blue = 0;
        ArrayList<Integer> relations = new ArrayList<>();
        
        //For the main root, you can write such like root.StructTree(root), that dose no wrong
        void StructTree(int father) {
            this.father = father;
                if (p == 1) {
                    red++;
                } else if (p == 2) {
                    blue++;
                }
                for (int i = 0; i < relations.size(); i++) {
                    if (relations.get(i) != father) {
                        tree[relations.get(i)].StructTree(this.index);
                        red += tree[relations.get(i)].red;
                        blue += tree[relations.get(i)].blue;
                    }
                }
            
            if ((red == red_amount && blue == 0) || (blue == blue_amount && red == 0)) {
                valuable++;
            }
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

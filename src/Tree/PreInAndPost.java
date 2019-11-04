package Tree;

import java.io.*;
import java.util.StringTokenizer;

public class PreInAndPost {
    static OutputStream outputStream = System.out;
    static PrintWriter out = new PrintWriter(outputStream);
    
    private static class TreeNode {
        int index;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int index) {
            this.index = index;
        }
        
        void postorder() {
            if (left != null) {
                left.postorder();
            }
            if (right != null) {
                right.postorder();
            }
            out.print(index + " ");
        }
        
        public static TreeNode Construct(int[] preo, int[] ino, int pStart, int pEnd, int iStart, int iEnd) {
            TreeNode tree = new TreeNode(preo[pStart]);
            if (pStart == pEnd && iStart == iEnd) {
                return tree;
            }
            int root;
            for (root = iStart; root < iEnd; root++) {
                if (preo[pStart] == ino[root]) {
                    break;
                }
            }
            int leftLength = root - iStart;
            int rightLength = iEnd - root;
            if (leftLength > 0) {
                tree.left = Construct(preo, ino, pStart + 1, pStart + leftLength, iStart, root - 1);
            }
            if (rightLength > 0) {
                tree.right = Construct(preo, ino, pStart + leftLength + 1, pEnd, root + 1, iEnd);
            }
            return tree;
        }
    }
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] preo = new int[n];
            int[] ino = new int[n];
            for (int j = 0; j < n; j++) {
                preo[j] = in.nextInt();
            }
            TreeNode tree;
            for (int j = 0; j < n; j++) {
                ino[j] = in.nextInt();
            }
            tree = TreeNode.Construct(preo, ino, 0, n - 1, 0, n - 1);
            tree.postorder();
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

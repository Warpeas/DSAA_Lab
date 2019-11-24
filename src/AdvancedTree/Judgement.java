package AdvancedTree;

import java.io.*;
import java.util.StringTokenizer;

public class Judgement {
    static int n, flag;
    static InputStream inputStream = System.in;
    static InputReader in = new InputReader(inputStream);
    
    public static void main(String[] args) {
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            flag = 1;
            out.print("Case #" + (i + 1) + ": ");
            TreeNode tree = TreeNode.buildTree();
            if (flag == 1 && TreeNode.isCompleteBinaryTree_TA(tree, 1) && (TreeNode.isMinheap(tree) || TreeNode.isMaxheap(tree))) {
                out.println("YES");
            } else {
                out.println("NO");
            }
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
    
    private static class TreeNode {
        int index;
        int depth = 0;
        TreeNode father;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int index) {
            this.index = index;
        }
        
        //        static TreeNode buildTree() {
//            int[] A = new int[100005];
//            int n;
//            n = in.nextInt();
//            int l, r;
//            ArrayList<TreeNode> treeNodes= new ArrayList<>();
//            for(int i=1; i<= n; i++)
//            {
//                A[i] = -1;
//                TreeNode tmp = new TreeNode(i);
//                treeNodes.add(tmp);
//            }
//            for(int i=1; i<= n; i++)
//            {
//                l = in.nextInt();
//                r = in.nextInt();
//                if(l!=0)
//                    treeNodes.get(i).left = treeNodes.get(l);
//                if(r!=0)
//                    treeNodes.get(i).right = treeNodes.get(r);
//                A[l] = i;
//                A[r] = i;
//            }
//            for(int i=1; i<= n; i++) {
//                if(A[i] == -1)
//                    return treeNodes.get(i);
//            }
//            return null;
//        }
        static TreeNode buildTree() {
//            int[] A = new int[100005];
            n = in.nextInt();
            int p, c;
            TreeNode[] treeNodes = new TreeNode[n + 1];
            treeNodes[0] = new TreeNode(0);
            for (int i = 1; i <= n; i++) {
//                A[i] = -1;
                int index = in.nextInt();
                treeNodes[i] = new TreeNode(index);
            }
            for (int i = 1; i < n; i++) {
                p = in.nextInt();
                c = in.nextInt();
                if (treeNodes[p].left == null) {
                    treeNodes[p].left = treeNodes[c];
                    treeNodes[c].father = treeNodes[p];
                } else if (treeNodes[p].right == null) {
                    treeNodes[p].right = treeNodes[c];
                    treeNodes[c].father = treeNodes[p];
                } else {
                    flag = 0;
                }
            }
            for (int i = 1; i <= n; i++) {
//                if (A[i] == -1)
                if (treeNodes[i].father == null)
                    return treeNodes[i];
            }
            return null;
        }
        
        static boolean isCompleteBinaryTree_TA(TreeNode root, int ind) {
            if (ind > n) return false;
            if (root.left != null) {
                if (!isCompleteBinaryTree_TA(root.left, ind * 2)) {
                    return false;
                }
            }
            if (root.right != null) {
                return isCompleteBinaryTree_TA(root.right, ind * 2 + 1);
            }
            return true;
        }
        
        static boolean isMinheap(TreeNode root) {
            if (root.left == null && root.right == null) {
                return true;
            } else {
                if (root.left != null && root.right == null) {
                    if (root.index > root.left.index)
                        return false;
                    return isMinheap(root.left);
                } else if (root.left == null) {
                    if (root.index > root.right.index) {
                        return false;
                    }
                    return isMinheap(root.right);
                } else {
                    return isMinheap(root.left) && isMinheap(root.right);
                }
            }
        }
        
        static boolean isMaxheap(TreeNode root) {
            if (root.left == null && root.right == null) {
                return true;
            } else {
                if (root.left != null && root.right == null) {
                    if (root.index < root.left.index)
                        return false;
                    return isMinheap(root.left);
                } else if (root.left == null) {
                    if (root.index < root.right.index) {
                        return false;
                    }
                    return isMinheap(root.right);
                } else {
                    return isMinheap(root.left) && isMinheap(root.right);
                }
            }
        }
    }
}

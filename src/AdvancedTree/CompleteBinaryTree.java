package AdvancedTree;

import java.io.*;
import java.util.StringTokenizer;

public class CompleteBinaryTree {
    static int n;
    static InputStream inputStream = System.in;
    static InputReader in = new InputReader(inputStream);
    
    public static void main(String[] args) {
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            TreeNode tree = TreeNode.buildTree();
            if (TreeNode.isCompleteBinaryTree_TA(tree,1)){
                out.println("Yes");
            }else {
                out.println("No");
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
            int l, r;
            TreeNode[] treeNodes = new TreeNode[n + 1];
            for (int i = 0; i <= n; i++) {
//                A[i] = -1;
                treeNodes[i] = new TreeNode(i);
            }
            for (int i = 1; i <= n; i++) {
                l = in.nextInt();
                r = in.nextInt();
                if (l != 0)
                    treeNodes[i].left = treeNodes[l];
                if (r != 0)
                    treeNodes[i].right = treeNodes[r];
//                A[l] = i;
//                A[r] = i;
                treeNodes[l].father = treeNodes[i];
                treeNodes[r].father = treeNodes[i];
            }
            for (int i = 1; i <= n; i++) {
//                if (A[i] == -1)
                if (treeNodes[i].father==null)
                    return treeNodes[i];
            }
            return null;
        }
        static boolean isCompleteBinaryTree_TA(TreeNode root, int ind)
        {
            if(ind>n) return false;
            if(root.left!=null){
                if(!isCompleteBinaryTree_TA(root.left,ind*2)){
                    return false;
                }
            }
            if(root.right!=null){
                    return isCompleteBinaryTree_TA(root.right,ind*2+1);
            }
            return true;
        }
    }
}

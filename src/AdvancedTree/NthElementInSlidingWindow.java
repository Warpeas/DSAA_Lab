package AdvancedTree;

import java.io.*;
import java.util.StringTokenizer;

public class NthElementInSlidingWindow {
    /*
    k -> BBST O(nlogn)
    ni -> log(h)
    delete O(logn)
    Insert O(logn)
         */
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int m = in.nextInt();
        int k = in.nextInt();
        TreeNode tree = null;
        int n;
        long[] w = new long[m];
        for (int i = 0; i < m; i++) {
            w[i] = in.nextLong();
        }
        for (int i = 0; i < m; i++) {
            if (i >= k) {
                n = in.nextInt();
                out.println(tree.topk(tree, tree, n));
                tree.delete(tree, w[i - k]);
            }
            tree = TreeNode.insert(tree, w[i]);
        }
        n = in.nextInt();
        out.println(tree.topk(tree, tree, n));
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
        long index;
        long size = 1;
        TreeNode left;
        TreeNode right;
        
        TreeNode(long index) {
            this.index = index;
        }
        
        public static TreeNode insert(TreeNode root, long key) {
            if (root == null) {
                root = new TreeNode(key);
            } else {
                root.size++;
                if (key > root.index) {
                    root.right = insert(root.right, key);
                } else {
                    root.left = insert(root.left, key);
                }
                root = root.maintain(root, key > root.index);
            }
            return root;
        }
        
        public TreeNode delete(TreeNode root, long key) {
            root.size--;
            if (key > root.index) {
                root.right = delete(root.right, key);
            } else if (key < root.index) {
                root.left = delete(root.left, key);
            } else {
                if (root.left == null && root.right == null) {
                    root = null;
                } else if (root.left == null) {
                    root = root.right;
                } else if (root.right == null) {
                    root = root.left;
                } else {
                    TreeNode tmp = root.right;
                    while (tmp.left != null) {
                        tmp = tmp.left;
                    }
                    root.index = tmp.index;
                    root.right = delete(root.right, tmp.index);
                }
            }
            return root;
        }
        
        long topk(TreeNode root, TreeNode node, long k) {
            long cnt;
            if (node.left != null) {
                cnt = node.left.size + 1;
            } else {
                cnt = 1;
            }
            if (cnt == k) {
                return node.index;
            } else if (cnt > k) {
                if (node.left == null)
                    return topk(root, root, k);
                return topk(root, node.left, k);
            } else {
                if (node.right == null)
                    return topk(root, root, k - cnt);
                return topk(root, node.right, k - cnt);
            }
        }
        
        TreeNode maintain(TreeNode node, boolean flag) {
            if (node != null) {
                if (!flag) {
                    if (node.left != null && node.right == null && node.left.left != null) {
                        node = LL(node);
                    } else if ((node.left != null && node.right != null && node.left.left != null && node.left.left.size > node.right.size)) {
                        node = LL(node);
                    } else if (node.left != null && node.right != null && node.left.right != null && node.left.right.size > node.right.size) {
                        node.left = LR(node.left);
                        node = LL(node);
                    } else
                        return node;
                } else {
                    if (node.right != null && node.left == null && node.right.right != null) {
                        node = LR(node);
                    } else if (node.right != null && node.left != null && node.right.right != null && node.right.right.size > node.left.size) {
                        node = LR(node);
                    } else if (node.right != null && node.left != null && node.right.left != null && node.right.left.size > node.left.size) {
                        node.right = LL(node.right);
                        node = LR(node);
                    } else
                        return node;
                }
                node.left = maintain(node.left, false);
                node.right = maintain(node.right, true);
            }
            return node;
        }
        
        TreeNode LL(TreeNode node) {
            long nls = 0, nrs = 0;
            TreeNode tmp = node.left;
            node.left = tmp.right;
            if (node.left != null)
                nls = node.left.size;
            if (node.right != null)
                nrs = node.right.size;
            tmp.right = node;
            tmp.size = node.size;
            node.size = nls + nrs + 1;
            return tmp;
        }
        
        TreeNode LR(TreeNode node) {
            long nls = 0, nrs = 0;
            TreeNode tmp = node.right;
            node.right = tmp.left;
            if (node.left != null)
                nls = node.left.size;
            if (node.right != null)
                nrs = node.right.size;
            tmp.left = node;
            tmp.size = node.size;
            node.size = nls + nrs + 1;
            return tmp;
        }
    }
}

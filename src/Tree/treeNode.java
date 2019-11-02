package Tree;

public class treeNode {
    static int k = 2;
    
    private static class data {
        int index;
        int depth;
        
        data(int index, int depth) {
            this.index = index;
            this.depth = depth;
        }
    }
    
    private static class TreeNode {
        int index;
        int depth = 0;
        int child_amount = -1;
        int relation_amount = 0;
        data field = new data(index, depth);
        TreeNode father;
        TreeNode[] child = new TreeNode[k];
        TreeNode[] relations = new TreeNode[k + 1];
        
        TreeNode(int index) {
            this.index = index;
        }
        
//        void StructTree() {
//            for (int i = 0; i < root.relation_amount; i++) {
//                child[++child_amount] = relations[i];
//                this.StructTree(relations[i]);
//            }
//        }
        
        //For the main root, you can write such like root.StructTree(root), that dose no wrong
        void StructTree(TreeNode father) {
            this.father = father;
            for (int i = 0; i < relation_amount; i++) {
                if (relations[i] != father) {
                    relations[i].depth = depth + 1;
                    child[++child_amount] = relations[i];
                    relations[i].StructTree(this);
                }
            }
        }
        
        static void relate(TreeNode t1, TreeNode t2) {
            t1.relations[++t1.relation_amount] = t2;
            t2.relations[++t2.relation_amount] = t1;
        }
    }
}

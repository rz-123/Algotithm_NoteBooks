package Binary_Tree二叉树16道题;

// 完全二叉树的节点个数
// https://leetcode.cn/problems/count-complete-tree-nodes/
public class Code09_CountCompleteTreeNodes {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static int countNodes(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return f(head, 1, mostLeft(head, 1));
    }

    // 整个树的高度
    public static int mostLeft(TreeNode cur , int level){
        while (cur != null){
            level ++ ; // 层数
            cur = cur.left ; // 左节点
        }
        return level - 1 ;
    }

    // 递归
    // cur：当前节点； level：当前层； h：整棵树的高度
    public static int f(TreeNode  cur , int level , int h) {
        if (level == h){
            return 1 ;
        }
        // 如果右子树的最左节点等于整棵树的高度
        if (mostLeft(cur.right , level + 1) == h){
            // 那么就说明左子树就是完全二叉树
            // 完全二叉树计算节点的公式：2的层数次方
            return (1 << (h - level)) + f(cur.right , level + 1 , h) ;
        } else { // 如果右子树的最左节点不等于整棵树的 高度，就说明右子树是完全二叉树
            return (1 << (h - level - 1)) + f(cur.left , level + 1 , h) ;
        }
    }
}

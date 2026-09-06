package Binary_Tree二叉树16道题;

// 普通二叉树上寻找两个节点的最近公共祖先（如果两个节点在一个子树上，先遇到谁谁就是他俩最近的公共祖先
// 测试链接 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
public class Code01_LowestCommonAncestor {
    public static class TreeNode{
        public int val ;
        public TreeNode left ;
        public TreeNode right ;
    }


    public static TreeNode lowestCommonAncestor(TreeNode root , TreeNode q , TreeNode p){
        if (root == null || root == q || root == p){
            return root ;
        }
        // 左右子树递归
        TreeNode l = lowestCommonAncestor(root.left , q , p) ;
        TreeNode r = lowestCommonAncestor(root.right , q , p) ;
        // 如果两侧都有，就返回他们共同祖先
        if (l != null && r != null){
            return root ;
        }
        // 如果两侧都没有，那就返回空，说明俩节点不在这棵树上
        if (l == null && r == null){
            return null ;
        }
        // 如果一个为空一个不为空，就返回不为空的那个
        return l != null ? l : r ;
    }
}

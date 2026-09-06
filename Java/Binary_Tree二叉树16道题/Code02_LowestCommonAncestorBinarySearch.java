package Binary_Tree二叉树16道题;

// 搜索二叉树上寻找两个节点的最近公共祖先
// https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
public class Code02_LowestCommonAncestorBinarySearch {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        // 由于搜索二叉树的性质：
        // 先遇到q，q就是祖先；先遇到p，p就是祖先；
        // 如果qp在root的两侧，那么root就是公共祖先
        // 如果root < q、p，那么root往右移动
        // 如果root > q、p，那么root往左移动
        while (root.val != q.val && root.val != p.val){
            // 如果root在q和p之间，就跳出循环
            if (root.val < Math.max(q.val , p.val) && Math.min(q.val , p.val) < root.val) {
                break;
            }
            root = root.val < Math.min(q.val , p.val) ? root.right : root.left ;
        }
        return root;
    }
}

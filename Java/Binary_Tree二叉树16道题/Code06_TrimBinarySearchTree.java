package Binary_Tree二叉树16道题;


// 修剪搜索二叉树
// 测试链接 : https://leetcode.cn/problems/trim-a-binary-search-tree/
public class Code06_TrimBinarySearchTree {

    public static class TreeNode{
        public int val ;
        public TreeNode left ;
        public TreeNode right ;
    }
    // 给一个根节点，一个低一个高
    public static TreeNode trimBST(TreeNode root , int low , int high){
        if (root == null){
            return null ;
        }
        // 如果root<low,那么就往右子树找
        if (root.val < low){
            return trimBST(root.right , low , high) ;
        }
        // 如果root>high,那么久往左子树找
        if (root.val > high){
            return trimBST(root.left , low , high) ;
        }
        // 如果 root在 low跟high范围之间，那就左树找左，右树找右
        root.left = trimBST(root.left , low , high) ;
        root.right = trimBST(root.right , low , high);
        return root ;
    }
}

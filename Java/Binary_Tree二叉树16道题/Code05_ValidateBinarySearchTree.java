package Binary_Tree二叉树16道题;

// 验证搜索二叉树
// 测试链接 : https://leetcode.cn/problems/validate-binary-search-tree/
public class Code05_ValidateBinarySearchTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 方法1
    public static TreeNode[] stack = new TreeNode[10001] ;
    public static int r ; // 右边界，后进后出
    public static boolean isValidBST(TreeNode head){
        if (head == null){
            return true ;
        }
        TreeNode pre = null ; // 前一个节点
        r = 0 ;

        while (r >0 || head != null){
            if (head != null){
                stack[r++] = head ;
                head = head.left ;
            } else {
                head = stack[--r];  // 弹出一个数
                if (pre != null && pre.val >= head.val){
                    return false ;
                }
                pre = head ;
                head = head.right ;
            }
        }
        return true ;
    }

    // 方法2
    public static long min , max ;

    public static boolean isValidBST2(TreeNode head){
        if(head == null) {
            // 目的是防止干扰，即使节点为空也满足左孩子<节点<右孩子
            min = Long.MAX_VALUE ; // 最小值设为最大
            max = Long.MIN_VALUE ; // 最大值设为最小
            return true ;
        }
        boolean lok = isValidBST2(head.left) ; // 左树搜索
        long lmin = min ;
        long lmax = max ;

        boolean rok = isValidBST2(head.right) ; // 右树搜索
        long rmin = min ;
        long rmax = max ;
        min = Math.min(Math.min(lmin , rmin) , head.val) ; // 最小值
        max = Math.max(Math.max(lmax , rmax) , head.val) ; // 最大值
        return lok && rok && lmax < head.val && rmin > head.val ;
    }
}

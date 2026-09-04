package Binary_Tree二叉树16道题;


import java.util.ArrayList;
import java.util.List;

// 二叉树的锯齿形层序遍历
// 第一层从左往右，第二层从右往左...
// https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
public class Code02_ZigzagLevelOrderTraversal {
    public static class TreeNode{
        public int val ;
        public TreeNode left ;
        public TreeNode right ;
    }

    public static int MAX = 2001 ;
    public static TreeNode[] queue = new TreeNode[MAX] ;
    public static int l , r ;

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> ans = new ArrayList<>() ;
        if (root != null){
            l = r = 0 ;
            queue[r++] = root ;
            boolean reverse = false ; // 控制翻转，为false时，从左往右；为true时，从右往左
            while(l < r) {
                int size = r - l ; // 计算队列中的数量
                ArrayList<Integer> list = new ArrayList<>();

                // 判断 如果reverse为false，就从左往右存，i = l --- r-1 收集size个 i + 1
                //     如果reverse为True，就从右往左存，i = r-1 --- l 收集size个  i - 1
                for (int i = reverse ? r - 1 : l , j = reverse ? -1 : 1 , k = 0 ;
                     k < size ;
                     i += j , k++){
                    TreeNode treeNode = queue[i];
                    list.add(treeNode.val);
                }
                // 当前节点，有左加左，有右加右
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = queue[l++];
                    if ( treeNode.left != null ){
                        queue[r++] = treeNode.left;
                    }
                    if (treeNode.right != null) {
                        queue[r++] = treeNode.right ;
                    }
                }
                ans.add(list);
                reverse = ! reverse ;
            }
        }
        return ans ;
    }
}

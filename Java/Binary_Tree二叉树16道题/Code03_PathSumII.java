package Binary_Tree二叉树16道题;

import java.util.ArrayList;
import java.util.List;

// 收集累加和等于aim的所有路径
// 测试链接 : https://leetcode.cn/problems/path-sum-ii/
public class Code03_PathSumII {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 递归，递归完了之后要清除数据
    // cur头结点； aim固定累加和 ；sum过程累加；path 存放临时节点的列表；ans 最终返回的列表
    public static void f(TreeNode cur, int aim, int sum, List<Integer> path, List<List<Integer>> ans){
        // 如果左右俩孩子都是0了
        if (cur.left == null && cur.right == null ){
            // 如果叶子节点加上之前的路径的累加和正好等aim
            if (cur.val + sum == aim){
                path.add(cur.val) ;
                copy(path,ans);
                path.removeLast() ; // 复制完吧临时列表的数值清除
            }
        } else {
            path.add(cur.val) ; // 加入临时列表
            // 如果左边不是空值就去左边递归
            if (cur.left != null) {
                f(cur.left , aim , sum + cur.val , path , ans);
            }
            // 如果右边不是空值，就去右边递归
            if (cur.right != null){
                f(cur.right , aim , sum + cur.val , path , ans);
            }
            // 往上返回时 擦掉自己的数据
            path.removeLast();
        }
    }
    public static List<List<Integer>> pathSum(TreeNode root , int aim){
        List<List<Integer>> ans = new ArrayList<>() ;
        if (root != null) {
            List<Integer> path = new ArrayList<>() ;
            f(root , aim , 0 , path , ans) ;
        }
        return ans ;
    }

    public static void copy(List<Integer> path , List<List<Integer>> ans) {
        List<Integer> copy = new ArrayList<>();
        for (Integer num : path) {
            copy.add(num);
        }
        ans.add(copy);
    }

}

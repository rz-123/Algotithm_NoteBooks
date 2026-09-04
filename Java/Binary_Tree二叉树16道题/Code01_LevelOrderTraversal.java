package Binary_Tree二叉树16道题;

import java.util.*;

// 二叉树的层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
public class Code01_LevelOrderTraversal {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
    // 方法一 HashMap 加 队列
    public static List<List<Integer>> levelOrder1( TreeNode root ) {
        // 存放节点的列表 ， 列表 套 列表，
        List<List<Integer>> ans = new ArrayList<>() ;
        // 判断边界
        if ( root != null ){
            // 初始化化队列，先进先出
            Queue<TreeNode> queue = new LinkedList<>() ;
            // 初始化哈希表
            HashMap<TreeNode , Integer> levels = new HashMap<>() ;
            // 将root节点加入这俩结构中
            queue.add(root) ;
            levels.put(root , 0) ;
            // 判断此时的队列是否为空，如果不扣就弹出
            while (!queue.isEmpty()){
                TreeNode poll = queue.poll(); // 弹出节点
                int level = levels.get(poll); // 获取该节点在哈希表中的地址
                // 如果大列表中的数量与上边的i相等，就说明当前大列表中没有此层的列表，新建
                if (ans.size() == level) {
                    ans.add(new ArrayList<>()) ;
                }
                ans.get(level).add(poll.val) ; // 将弹出的数据添加到ans中所对应的位置
                // 左边不为空，从左找
                if (poll.left != null) {
                    queue.add(poll.left) ; // 加队列
                    levels.put(poll.left , level + 1) ;  // 加哈希表
                }
                // 右边不为空，从右找
                if (poll.right != null) {
                    queue.add(poll.right);
                    levels.put(poll.right, level + 1);
                }
            }
        }
        return ans ;
    }



    // 方法二  size控制层数

    // 设置全局变量
    public static int MAX = 2001 ;
    public static TreeNode[] queue = new TreeNode[MAX] ; // 队列
    public static int l , r ; // 队列左右边界
    public static List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> ans = new ArrayList<>();
        // 如果传入的root不等于0
        if (root != null){
            l = r = 0 ; // 左右边界同时为0，添加数据r++，弹出数据l++
            queue[r++] = root ; // 头结点放入
            // 当队列中还有东西
            while (l < r){
                int size = r - l ; // 队列中的数量
                ArrayList<Integer> list = new ArrayList<>() ;
                for (int i = 0; i < size; i++) {
                    // queue弹出的数据加到list中
                    TreeNode treeNode = queue[l++];
                    list.add(treeNode.val);
                    // 左找，加到队列
                    if (treeNode.left != null){
                        queue[r++] = treeNode.left ;
                    }
                    // 右找，加到队列
                    if (treeNode.right != null){
                        queue[r++] = treeNode.right ;
                    }
                }
                ans.add(list); // size次循环完，列表加入大链表，
            }
        }
        return ans ;
    }
}

package Binary_Tree二叉树16道题;

import java.util.HashMap;

// 根据一组先序与中序遍历序列 构造二叉树
// https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
public class Code07_PreorderInorderBuildBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    // 可知 先序遍历序列的头结点一定是二叉树的头结点
    public static TreeNode buildTree(int[] pre , int[] in){
        // 如果俩数组有一个为空或者俩数组长度不相等 就直接返回空
        if (pre == null || in == null || pre.length != in.length){
            return null ;
        }
        // 创建哈希map，方便在中序数组中快速获取先序中头结点的位置
        HashMap<Integer , Integer> map = new HashMap<>() ;
        for (int i = 0; i < in.length; i++) {
            map.put(in[i] , i) ;// 存入中序数组。
        }
        return f(pre , 0 , pre.length-1 , in , 0 , in.length-1 , map) ;
    }

    public static TreeNode f (int[] pre , int l1 , int r1 , int[] in , int l2 , int r2 , HashMap<Integer,Integer> map){
        // 判断边界
        if (l1 > r1){
            return null ;
        }
        // 设置二叉树头结点
        TreeNode head = new TreeNode(pre[l1]) ;
        if (l1 == r1){
            return head ;
        }
        // 获取该节点在中序数组的位置
        int k = map.get(pre[l1]) ;
        // 左树递归
        head.left = f(pre , l1 + 1 , l1 + (k-l2) , in , l2 , k - 1 , map) ;
        // 右树递归
        head.right = f(pre,l1 + (k-l2)+1 , r1 , in,k+1 , r2 , map) ;
        return head ;
    }
}

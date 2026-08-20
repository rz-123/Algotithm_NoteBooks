package Binary_Tree二叉树;

import class017.BinaryTreeTraversalRecursion;

// 用递归实现二叉树的三种序
public class BT_Recurive {

    // 创建二叉树
    public static class TreeNode{
        public int val ;
        public TreeNode left ;
        public TreeNode right ;
        public TreeNode(int v){
            val = v ;
        }
    }


    // 先序：中 左 右
    public static void preOrder(TreeNode head){
        // 判断传入打的head是否为空
        if (head == null) {
            return ;
        }
        System.out.println(head.val + " ");
        preOrder(head.left); // 遍历所有左子树
        preOrder(head.right); // 遍历所有右子树
    }

    // 中序：左 中 右
    public static void inOrder(TreeNode head){
        // 判断传入打的head是否为空
        if (head == null) {
            return ;
        }
        inOrder(head.left);// 遍历所有左子树
        System.out.println(head.val + " ");
        inOrder(head.right); // 遍历所有右子树
    }

    // 后序： 左 右 中
    public static void posOrder(TreeNode head){
        // 判断传入打的head是否为空
        if (head == null) {
            return ;
        }
        inOrder(head.left);// 遍历所有左子树
        inOrder(head.right); // 遍历所有右子树
        System.out.println(head.val + " ");
    }
}

package Binary_Tree二叉树;


import class018.BinaryTreeTraversalIteration;

import java.util.Stack;

// 用栈实现三种序
public class BT_Stack {

    // 创建二叉树
    public static class TreeNode{
        public int val ;
        public TreeNode left ;
        public TreeNode right ;
        public TreeNode(int v){
            val = v ;
        }
    }

    // 先序
    public static void preOrder(TreeNode head){
        if (head != null) {
            // 创建栈
            Stack<TreeNode> stack = new Stack<>() ;
            // 先存入头
            stack.push(head) ;
            // 判断栈是否为空
            while(! stack.isEmpty()){
                head = stack.pop() ; // 弹出头
                System.out.println(head.val + " "); // 输出头
                // 因为先序是左中右，所以压栈要从右子树开始压，这样左子树才能先弹出
                if (head.right != null) {
                    stack.push(head.right) ; // 如果有，右子树压入栈中
                }
                if (head.left != null) {
                    stack.push(head.left)  ; // 如果有，左子树压入栈中
                }
            }
            System.out.println();
        }
    }

    // 中序 左 中 右
    public static void inOrder(TreeNode head){

        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null){
                // 如果head不等于 那么head先压入栈，然后再让head来它的左子树
                if (head != null){
                    stack.push(head);
                    head = head.left;
                }else {
                    // 当head的左边为null时，head赋值给栈顶数值(该二叉树最左子树)，并且将栈顶数据弹出
                    head = stack.pop();
                    System.out.println(head.val + ' ');
                    // 此时 head来到head的右子树
                    head = head.right;
                }
            }
            System.out.println();
        }
    }

    // 后序 左 右 中
    // 两个栈实现，一个栈以 中 右 左 的顺序压入栈，然后另一个栈负责弹出
    public static void posOrderTwoStacks(TreeNode head){
        if (head != null) {
            // 创建栈
            Stack<TreeNode> stack = new Stack<>() ;
            Stack<TreeNode> collect = new Stack<>() ;
            // 先存入头
            stack.push(head) ;
            // 判断栈是否为空
            while(! stack.isEmpty()){
                head = stack.pop() ; // 弹出头
                System.out.println(head.val + " "); // 输出头
                // 因为先序是左中右，所以压栈要从右子树开始压，这样左子树才能先弹出
                if (head.left != null) {
                    stack.push(head.left) ; // 如果有，右子树压入栈中
                }
                if (head.right != null) {
                    stack.push(head.right)  ; // 如果有，左子树压入栈中
                }
            }
            // 如果collect不是空 就弹出
            while (!collect.isEmpty()){
                System.out.println(collect.pop().val + " ");
            }
            System.out.println();
        }
    }
    // 一个栈完成后序，用h来控制
    public static void posOrderOneStacks(TreeNode h){
        if (h != null) {
            Stack<TreeNode> stack = new Stack<>();
            // 压入h
            stack.push(h) ; // 后边h变量会跟着打印的节点走，h始终是当前打印的节点
            while (!stack.isEmpty()){
                TreeNode cur = stack.peek() ; // 查看栈顶数据
                if (cur.left != null && h != cur.left && h != cur.right){
                    // 压入左子树数据
                    stack.push(cur.left);

                } else if (cur.right != null && h != cur.right) {
                    stack.push(cur.right);  // 压入右子树数据
                }else {
                    System.out.print(cur.val + " ");
                    h = stack.pop();  // 将弹出的数据赋值给h
                }
            }
            System.out.println();
        }
    }
}

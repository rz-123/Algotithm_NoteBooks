package Linked_Six_Pr链表6道高频;

import class034.Code04_PalindromeLinkedList;

/*
判断是否为回文结构，例：1345431、
https://leetcode.cn/problems/palindrome-linked-list/
 */
public class Code04_PalindromeLinked {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static boolean isPalindrome(ListNode head){
        if (head == null || head.next == null){
            return true ;
        }
        ListNode one = head , two = head;
        // 寻找中点，one一次走一步，two一次走两步
        while (two.next != null && two.next.next != null){
            one = one.next ;
            two = two.next.next ;
        }// while循环结束之后 one变量就是中点位置
        // 设置变量指针
        ListNode pre = one ; // pre始终控制着cur的前一个节点
        ListNode cur = pre.next ; // cur 控制当前节点
        ListNode next = null ; //  next 控制 cur的下一个节点
        pre.next = null ; // 将中点的下一个节点指向设为空
        while (cur != null){
            next = cur.next ; // next来到cur的下一个节点
            cur.next = pre ; // 将 cur节点的下一个节点指向pre（上一个节点）
            pre = cur ; // pre 来到 cur
            cur = next ; //cur 来到 next
        }
        // 上边循环结束后 得到：head ->.....-> one <-.....<- pre

        boolean ans = true; // 默认是回文
        ListNode left= head ; // 左边头结点
        ListNode right = pre ;  // 右边头结点
        while(left != null && right != null){
            if (left.val != right.val){
                ans = false ;
                break;
            }
            left = left.next ;
            right = right.next ;
        }

        // 结束后，将右半边还原成原来的样子
        cur = pre.next ; // cur 来到倒数第二个位置
        pre.next = null ; // 右边界指向空
        next = null ; // 记录cur的下一个节点位置
        while (cur != null){
            next = cur.next ;
            cur.next = pre ;
            pre = cur ;
            cur = next ;
        }
        return ans ;
    }
}

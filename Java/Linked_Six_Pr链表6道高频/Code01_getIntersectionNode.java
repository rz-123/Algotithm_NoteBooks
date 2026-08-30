package Linked_Six_Pr链表6道高频;

/*
给你两个单链表的头节点 headA 和 headB ，
请你找出并返回两个单链表相交的起始节点。
如果两个链表不存在相交节点，返回 null 。
https://leetcode.cn/problems/intersection-of-two-linked-lists/description/
 */

public class Code01_getIntersectionNode {

    // 创建链表
    public static class ListNode{
        public int val ;
        public ListNode next ;
    }

    public static ListNode getIntersectionNode(ListNode L1 ,ListNode L2){
        // 判断俩链表是否为空
        if (L1 == null || L2 == null){
            return null ;
        }
        // 设置两个变量节点，x,y
        ListNode x = L1 , y = L2 ;
        int diff = 0 ; // 俩链表长度差的绝对值
        // 遍历俩链表获取链表长度
        while (x.next != null){
            x = x.next ;
            diff ++ ;
        }
        while (y.next != null) {
            y = y.next ;
            diff -- ;
        }
        if ( x != y){
            return null ;
        }
        // 将最长链表的头结点找到复制给x
        x = diff >= 0 ? L1 : L2 ;
        y = x == L1 ? L2 : L1 ;
        diff = Math.abs(diff) ; // 转成绝对值
        // 长的链表先走差值绝对值的步数
        while (diff -- != 0) {
            x = x.next ;
        }
        while (x != y){
            x = x.next ;
            y = y.next ;
        }
        return x ;
    }

}

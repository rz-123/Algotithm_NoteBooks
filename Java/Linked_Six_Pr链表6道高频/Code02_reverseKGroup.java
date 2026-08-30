package Linked_Six_Pr链表6道高频;

import class034.Code02_ReverseNodesInkGroup;
import class034.Code06_SortList;

/*
给定链表，要求按k个节点一组翻转链表
https://leetcode.cn/problems/reverse-nodes-in-k-group/
 */
public class Code02_reverseKGroup {

    public static class ListNode {
        public int val;
        public ListNode next;
    }
    // 创建数出k个节点的方法
    public static ListNode endK(ListNode s, int k){
        while (--k != 0 && s != null){
            s = s.next ;
        }
        return s ;
    }
    // 翻转方法 传入起始节点跟结束节点
    public static void reverse(ListNode a , ListNode b) {
        b = b.next ; // 结束节点要记住下一组的起始节点
        ListNode pre = null , next = null , cur = a ;
        // cur没到结束节点时进入循环
        // next控制下一个节点，cur控制当前节点指向，pre保存上一个节点位置
        while(cur != b) {
            next = cur.next ;
            cur.next = pre ;
            pre = cur ;
            cur = next ;
        }
        // 最后要把传入的起始节点（翻转之后为该组最后一个节点）的下一个指针指向下一组的起始节点
        a.next = b ;
    }
    // 主方法
    public static ListNode reverseKGroup(ListNode h , int k){

        ListNode start = h ;  //第一组起始节点
        ListNode end = endK(start , k) ;  // 第一组结束节点
        if (end == null ){
            return h ;
        }

        h = end ;  // 第一组的结束节点会变成翻转之后整个链表的的头结点
        reverse(start , end); // 翻转第一组
        ListNode lastEnd = start ; // 翻转之后 第一组的头结点 变为第一组尾节点
        while (lastEnd.next != null){
            start = lastEnd.next ; // 这一组的头结点
            end = endK(start ,k) ; // 这一组的尾节点
            if (end==null){
                return h ;
            }
            reverse(start , end); // 翻转
            lastEnd.next = end ;  // 翻转之后，尾节点变成该组头结点，所以上一组的尾节点指向翻转后的头结点
            lastEnd = start ;  // 此时 lastEnd来到该组翻转后的尾节点，也就是之前的头结点
        }
        return h ;
    }
}

package Linked_Six_Pr链表6道高频;

import class034.Code05_LinkedListCycleII;
import class034.Code06_SortList;

/*
返回链表的第一个入环节点
测试链接 : https://leetcode.cn/problems/linked-list-cycle-ii/
 */
public class Code05_LinkedListOneNode {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode detectCycle(ListNode head){
        // 判断边界
        if (head == null || head.next == null || head.next.next == null){
            return null ;
        }
        ListNode slow = head.next ;
        ListNode fast = head.next.next ;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null ;
            }
            slow = slow.next ;
            fast = fast.next.next ;
        }
        // 循环完毕 中点节点slow停在原地，fast节点回起点
        fast = head ;
        while (slow != fast){
            slow = slow.next ;
            fast = fast.next ;
        }
        return slow ;
    }
}

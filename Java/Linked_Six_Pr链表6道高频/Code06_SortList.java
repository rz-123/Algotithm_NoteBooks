package Linked_Six_Pr链表6道高频;

/*
排序链表
要求时间复杂度O(n*logn)，额外空间复杂度O(1)，还要求稳定性
数组排序做不到，链表排序可以
测试链接 : https://leetcode.cn/problems/sort-list/
 */
public class Code06_SortList {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    // 纯模仿步长来进行排序
    public static ListNode sortList(ListNode head){
        int n = 0 ; // 链表长度
        ListNode cur = head ;
        // 计算链表长度
        while (cur != null) {
            n ++ ;
            cur = cur.next ;
        }
        // 两组左右边界、下一组的头结点，上一组的尾节点
        ListNode l1, r1, l2, r2, nextHead, lastTeamEnd;
        // 步长每次乘2
        for (int step = 1 ; step < n ; step <<= 1){
            l1 = head ;
            r1 = findEnd(l1,step) ;
            l2 = r1.next ;
            r2 = findEnd(l2,step) ;  // 修正：从l2开始找右组尾，不是从r1开始
            nextHead = r2.next; // 记录下一组的头结点
            r1.next = null ; // 右边界的下一个指向全部值为空
            r2.next = null ;
            merge(l1,r1,l2,r2);  // 排序
            head = start ; // 排完序，将排序好的头结点复制给总头部
            lastTeamEnd = end; // 尾节点
            while (nextHead != null) {
                l1 = nextHead ;
                r1 = findEnd(l1 , step) ;
                l2 = r1.next ;
                if (l2 == null){
                    lastTeamEnd.next = l1 ;  // 如果L2等于空，就把上组结尾链接L1上，跳出循环
                    break;
                }
                r2 = findEnd(l2,step) ;
                nextHead = r2.next ;  // 修正：下一组头是r2.next，不是l2.next
                r1.next = null ;
                r2.next = null ;
                merge(l1 , r1 , l2 ,r2);
                // 排序完成后，上一组的结束位置连接到排完序的头结点
                lastTeamEnd.next = start ;
                // 上一组结束位置的变量来到排序完的尾节点位置
                lastTeamEnd = end ;
            }
        }
        return head ;
    }
    // 找k个节点
    public static ListNode findEnd(ListNode s , int k) {
        while (s.next != null &&--k != 0){
            s = s.next ;
        }
        return s;
    }

    public static ListNode start ;  // k个节点的范围中 开始节点
    public static ListNode end ; // k个节点的范围中 结束节点

    // 排序方法，注意最后要把全局变量的头设置为整体的头，全局变量的尾设置成整体尾部
    public static void merge(ListNode l1, ListNode r1, ListNode l2, ListNode r2){
        ListNode pre ; // 控制指针
        // 为保证数据稳定性，俩数如果相等，左边先拷贝
        // 单独处理头结点
        if (l1.val <= l2.val){
            start = l1 ;
            pre = l1 ;
            l1 = l1.next ;
        } else {
            start = l2 ;
            pre = l2 ;
            l2 = l2.next ;
        }
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val){
                pre.next = l1 ;
                pre = l1 ;
                l1 = l1.next ;
            } else {
                pre.next = l2 ;
                pre = l2 ;
                l2 = l2.next ;
            }
        }
        // 处理尾节点
        if (l1 != null){
            pre.next = l1 ;
            end = r1 ;  // 修正：l1段有剩余，尾巴是r1
        } else {
            pre.next = l2 ;
            end = r2 ;  // 修正：l2段有剩余，尾巴是r2
        }
    }
}

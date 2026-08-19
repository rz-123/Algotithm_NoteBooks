package Linked_Stack_Queue链表_栈_队列;

public class Linked01_Bp {


    // 单链表
    public static class ListNode {
        public int val ;
        public ListNode next ;
        public ListNode(int val) {
            this.val = val ;
        }
        public ListNode(int val , ListNode next){
            this.val = val ;
            this.next = next ;
        }
    }

    class Solution{
        // 合并有序链表，两个有序链表合成一个有序的大链表，返回大列表头结点
        public static ListNode mergeTwoLists(ListNode head1 , ListNode head2){
            // 判断传入的俩头结点是否为空
            if (head1 == null || head2 == null){
                // head1为空直接返回head2，否则返回head1
                return head1 == null ? head2 : head1 ;
            }
            // 都为空就先判断谁当大链表头结点
            ListNode head = head1.val <= head2.val ? head1 : head2 ;
            ListNode cur1 = head.next ; // 头结点的下一个
            ListNode cur2 = head == head1 ? head2 : head1 ; // 另一个小链表头结点
            ListNode pre = head ; // 控制指针指向
            // 循环条件，cur1跟cur2都不是空
            while (cur1 != null && cur2 != null){
                // 当cur1小于cur2时，pre指向cur1，cur1跳到cur1的下一个
                if (cur1.val <= cur2.val){
                    pre.next = cur1 ;
                    cur1 = cur1.next ;
                }else {
                    // 否则就是cur2小，pre指向cur2的地址，cur2跳到下一个
                    pre.next = cur2 ;
                    cur2 = cur2.next ;
                }
                // 判断结束后，pre来到pre指向的位置
                pre = pre.next ;
            }
            // 当cur1或cur2有一个为null时，跳出了循环
            // 把另一个小链表的最后一位挂上
            pre.next = cur1 != null ? cur1 : cur2;
            return head ;
        }

        // 两个非空链表相加，比如1->3->4 + 3->4->5 = 4->7->9
        public static ListNode addTwoNumber(ListNode h1 , ListNode h2){
            // 添加节点变量ans(新链表头结点)和cur(新链表控制节点)
            ListNode ans = null, cur = null;
            int carry = 0 ;  // 设置进位变量

            //进入循环
            for (int sum , val ; h1 != null || h2 != null; // 终止条件为任意链表的节点为0就终止
                 h1 = h1 == null ? null : h1.next,h2 = h2 == null ? null : h2.next // 两个链表节点的跳转
            ){
                // 计算两个节点相加 + 进位
                sum = (h1 == null ? 0 : h1.val) + (h2 == null ? 0 : h2.val)
                        + carry ;

                // 计算个位数
                val = sum % 10 ;
                carry = sum / 10 ; // 计算是否进位

                // 判断头结点是否为空，为空就加数据
                // 头结点不为空就跳到下一个节点加数据
                if (ans == null) {
                    ans = new ListNode(val) ;
                    cur = ans ; //cur来到头结点
                } else{
                    cur.next = new ListNode(val) ; //将cur指向下一个新节点
                    cur = cur.next;  // cur跳到新建节点上
                }
            }
            // 循环完成，最后判断carry还是否有进位，
            if (carry == 1) {
                cur.next = new ListNode(1) ;
            }
            return ans;
        }

        // 给一个无序列表和一个数x，要求链表中<x的数放左边，>=x的数放右边。最初的顺序不能变
        public static ListNode partition(ListNode head , int x) {
            ListNode leftHead = null, leftTail = null; // < x的区域
            ListNode rightHead = null, rightTail = null; // >=x的区域
            ListNode next = null ;

            while (head != null){
                next = head.next ; // next跳到head的像一个位置
                head.next = null ; //head的指向空
                // 如果head的值<x就填到左边
                if (head.val < x){
                    if (leftHead == null){
                        // 空的话直接填到左边头结点
                        leftHead = head ;
                    }else {
                        // 说明左边有值，所以将尾节点指向小于x的那个数
                        leftTail.next = head ;
                    }
                    // 判断结束后，尾节点来到head的位置
                    leftTail = head ;
                }else {
                    if (rightHead == null){
                        rightHead = head ;
                    }else {
                        rightTail.next = head ;
                    }
                    rightTail = head ;
                }
                head = next ;
            }
            // 左右进行连接
            if (leftHead == null){
                return rightHead ; // 如果左边为空 直接返回右边头结点
            }
            leftTail.next = rightHead ; //左边尾节点指向右边头结点
            return leftHead; // 返回左边头结点
        }
    }
}

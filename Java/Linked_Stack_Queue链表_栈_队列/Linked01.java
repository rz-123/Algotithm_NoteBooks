package Linked_Stack_Queue链表_栈_队列;


// 链表
public class Linked01 {

    // 单链表节点
    public static class ListNode{
        public int val ;
        // next指向下一个ListNode 所以next类型ListNode
        // 存放下一个节点的内存地址
        public ListNode next ;

        // 适用于先创建节点而目前不知道下一个节点是谁的情况。
        public ListNode(int val){
            this.val = val;
        }

        public ListNode(int val , ListNode next){
            this.val = val ;
            this.next = next ;
        }

    }

    // 单链表翻转（将整个链表翻转，最后返回翻转后的头结点），增加变量pre
    public static ListNode reverseList(ListNode head){
        ListNode pre = null ;
        ListNode next = null ;
        while(head != null){
            next = head.next ;  // next指向第二个节点
            head.next = pre ;  // head的指针指向pre的值 此时为null
            pre = head ;  // 此时pre来到head的位置
            head = next ; // head跳到下一个节点
            // 周而复始，知道head==null时 返回pre 翻转后的头结点
        }
        return pre ;
    }

    // 双链表节点
    public static class DoubleListNode{
        public int value ;
        public DoubleListNode last ; // 上一个节点地址
        public DoubleListNode next ; // 下一个节点地址

        public DoubleListNode(int v){
            this.value = v ;
        }
        // 为什么不写指向上一个节点跟下一个节点构造方法？
        // 双向链表传参比较麻烦，所以在创建节点的时候直接指定上下节点。
    }

    public static DoubleListNode reverseDouble(DoubleListNode head){
        DoubleListNode next = null ;
        DoubleListNode pre = null ;

        while (head != null){
            next = head.next ;
            head.next = pre ;
            head.last = next ;  // 该节点的指向上一个节点的指针变为指向下一个节点的指针，
            pre = head ;
            head= next ;
        }
        return  pre ;
    }
}

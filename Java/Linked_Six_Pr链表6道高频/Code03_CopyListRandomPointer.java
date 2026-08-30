package Linked_Six_Pr链表6道高频;

import class034.Code03_CopyListWithRandomPointer;

/*
复制带随机指针的链表
测试链接 : https://leetcode.cn/problems/copy-list-with-random-pointer/
 */
public class Code03_CopyListRandomPointer {

    public static class Node {
        public int val;
        public Node next;
        public Node random; // 随机指针

        public Node(int val) {
            val = val ;
        }
    }

    public static Node copyRandomList(Node h){
        if (h == null){
            return null ;
        }
        // 每个新增节点都在该节点的下一个
        // 1->3->7->8->9  变为 1->1->3->3->7->7->8->8->9->9
        Node cur = h ; // 控制原有的节点
        Node nextnode = null ; // 控制原有节点的下一个节点
        while (cur != null){
            nextnode = cur.next ; // nextnode来到第二个节点
            cur.next = new Node(cur.val) ;  // 第一个节点指向新建的节点
            cur.next.next = nextnode ;  // 新建的节点指向之前的第二个节点
            cur = nextnode ; // cur来到之前的第二个节点
        }
        // 添加完成之后cur来到头结点
        cur = h ;
        Node copy  = null ; // 设置拷贝指针
        // 利用上边的节点关系进行拷贝random指针
        while (cur != null){
            nextnode = cur.next.next ;  // 记住原先第二个节点的位置
            copy = cur.next ; // copy节点来到复制的第一个节点
            // 如果该节点的random指针不为空，就找出random的下一个节点赋值给复制节点random
            copy.random = cur.random != null ? cur.random.next : null ;
            cur = nextnode ; // cur来到原先的的第二个节点
        }
        // 拷贝random指针完成后，分离链表
        Node ans = h.next ; // ans 来到大链表的第二个节点，也就是复制链表的头结点
        cur = h ;
        while (cur != null) {
            nextnode = cur.next.next ; // nextnode始终跟着原始链表中的节点
            copy = cur.next ; // copy 来到 复制的第一个节点
            cur.next = nextnode ; // 第一个节点指向原链表的第二个节点
            // 复制的第一个节点指向原链表中第二节点的下一个节点
            copy.next = nextnode != null ? nextnode.next : null ;
            cur = nextnode ; // 此时 cur 来到 原链表 第二节点，循环
        }
        return ans ;  // 返回复制、分离出来的链表的头结点
    }

}

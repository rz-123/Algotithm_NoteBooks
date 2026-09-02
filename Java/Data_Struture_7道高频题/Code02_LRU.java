package Data_Struture_7道高频题;

import java.util.HashMap;

/*
Code02_LRU
使用最少的值进行清除
测试链接 : https://leetcode.cn/problems/lru-cache/
 */
public class Code02_LRU {

    // 双向链表加哈希表，从左往右依次添加，
    // 最左边的就是最少操作次数的值

    // 双向链表
    class LRUCache{
        // 创建双向节点
        class DoubleNode{
            public int k;
            public int v ;
            public DoubleNode last ;
            public DoubleNode next ;
            public DoubleNode(int k , int v ){
                this.k = k ;
                this.v = v ;
            }
        }

        // 创建双向链表
        class DoubleList{
            private DoubleNode head ;  // 头结点
            private DoubleNode tail ; // 尾节点
            public DoubleList(){
                head = null ;
                tail = null ;
            }
            // 添加节点方法
            public void addNode(DoubleNode newNode){
                if (newNode == null){
                    return ; // 新来节点为空，直接返回
                }
                if (head == null){
                    // 如果head为空说明链表没东西，此时将头尾俩节点都是新来的节点
                    head = newNode ;
                    tail = newNode ;
                } else {// 上边都不符合，就从尾节点开始添加节点
                    tail.next = newNode ;
                    newNode.last = tail ;
                    tail = newNode ; // 连接完成后 将尾节点启动到新来的节点上
                }
            }
            // 操作节点后，需要将节点移动到尾节点方法
            public void moveNodeToTail(DoubleNode node){
                if (tail == node){
                    return;
                }if (head == node){
                    // 如果node等与头结点，那么头结点来到第二个位置，
                    // 第二个位置的上一个指针判空
                    head = node.next ;
                    head.last = null ;
                } else {
                    // 节点在中间的情况
                    node.last.next = node.next ; // 让该节点的上一个节点的下一个指针指向该节点的下一个
                    node.next.last = node.last ; // 让该节点的下一个节点的上一个指针指向该节点的上一个
                }
                // 连接尾节点，并将尾节点设置为node
                node.last = tail ;
                node.next =  null ;
                tail.next = node ;
                tail = node ;
            }

            // 删除节点方法
            // 从头结点开始删,返回删除的头结点
            public DoubleNode remoHNode(){
                if (head == null){
                    return null;
                }
                DoubleNode ans = head ; // 头结点控制指针
                if (head == tail){ // 头尾节点是一个 就全部值为空
                    head = null ;
                    tail = null ;
                } else {
                    head = ans.next ;  // head 来到 头结点的下一个节点
                    ans.next = null ;  // 此时的头结点的下一个指针置为空
                    head.last = null ; // 第二个节点的上一个指针值为空
                    // 到此 头结点断连
                }
                return ans ;
            }
        }

        // 主方法
        private HashMap<Integer , DoubleNode> keyNodeMap ; // 创建哈希map，存地址跟节点
        private DoubleList nodeList ; // 创建双向链表
        private final int cap ;
        // 初始化构造方法
        public LRUCache(int cap1){
            cap = cap1;  // HashMap中最大容量
            keyNodeMap = new HashMap<>() ;
            nodeList = new DoubleList() ;
        }
        // 根据key 获取value
        public int get(int key){
            if (keyNodeMap.containsKey(key)){
                DoubleNode doubleNode = keyNodeMap.get(key);
                nodeList.moveNodeToTail(doubleNode) ; // 重新操作了该节点，就将该节点调到尾节点
                return doubleNode.v ;
            }
            return -1 ;
        }

        // 添加数据
        public void put(int k , int v){
            // 先确定哈希表中是否有该k，如果有，就把value替换，
            // 然后在双向链表中将该节点调到尾节点
            if (keyNodeMap.containsKey(k)){
                DoubleNode doubleNode = keyNodeMap.get(k);
                doubleNode.v = v ;
                nodeList.moveNodeToTail(doubleNode);
            } else {
                // 先判断哈希表是否满，如果满了就清除双向链表的头结点跟哈希表中的数值
                if (keyNodeMap.size() == cap){
                    keyNodeMap.remove(nodeList.remoHNode().k) ;
                }
                // 如果上边的条件都不是，那就正常添加新增双向节点
                DoubleNode newnode= new DoubleNode(k,v) ;
                keyNodeMap.put(k , newnode) ;  // 哈希表中添加node节点
                nodeList.addNode(newnode);  // 双向链表中添加node节点
            }
        }
    }
}

package Data_Struture_7道高频题;


import java.util.HashMap;
import java.util.HashSet;

// 全O(1)的数据结构
// https://leetcode.cn/problems/all-oone-data-structure/
public class Code7_AllOne {

    class AllOne {

        // 构造桶
        class Bucket {
            public HashSet<String> set; //记录字符串，
            public int cnt; // 词频，
            public Bucket last;
            public Bucket next;

            public Bucket(String s, int c) {
                set = new HashSet<>();
                set.add(s);
                cnt = c;
            }
        }

        // 插入桶,在cur桶后边插入pos桶
        private void insert(Bucket cur, Bucket pos) {
            cur.next.last = pos;
            pos.next = cur.next;
            cur.next = pos;
            pos.last = cur;
        }

        // 删除桶
        private void remove(Bucket cur) {
            cur.last.next = cur.next;
            cur.next.last = cur.last;
        }

        // 设置头桶跟尾桶
        Bucket head;
        Bucket tail;
        // 全部用HashMap包起来
        HashMap<String, Bucket> map;

        // 初始化
        public AllOne() {
            head = new Bucket("", 0); // 头桶 词频为0
            tail = new Bucket("", Integer.MAX_VALUE);// 尾桶 词频为最大值
            // 俩桶先连接
            head.next = tail;
            tail.last = head;
            map = new HashMap<>();
        }

        // 添加数据
        public void inc(String key) {
            // 如果原先没有可以
            if (!map.containsKey(key)) {
                // 如果头桶的下一个桶的计数为1的桶已经有了，就直接添加到计数为1的桶中
                if (head.next.cnt == 1) {
                    map.put(key, head.next);
                    head.next.set.add(key);
                } else {
                    // 否则， 新增计数为1的桶，并且将数据加入进去
                    Bucket newBucket = new Bucket(key, 1);
                    map.put(key, newBucket); // 在map中加入数据以及该桶
                    insert(head, newBucket); // 在头桶后边加入该新建桶
                }
            } else { // 否则就是原先有该key
                Bucket bucket = map.get(key); // 根据key 找到所在的桶
                // 如果数据所在桶的下一个桶的计数正好是比当前桶大1，那就直接将数据移入该桶
                if (bucket.next.cnt == bucket.cnt + 1) {
                    map.put(key, bucket.next); // map中更新数据
                    bucket.next.set.add(key); // 下一个桶更新数据
                }
                // 否则就是在该桶后边新建一个比该桶大1的桶再加入
                else {
                    Bucket bucket1 = new Bucket(key, bucket.cnt + 1);
                    map.put(key, bucket1); // 更新map中的数据位置
                    insert(bucket, bucket1); // 新建桶连接上
                }
                // 全部完成后，原先桶的数据删除
                bucket.set.remove(key);
                // 如果该set删除key之后为空，就把整个桶删除
                if (bucket.set.isEmpty()) {
                    remove(bucket);
                }
            }
        }

        // 删除数据,确保该数据在里边
        public void dec(String key) {
            // 从map中取出该数据所在的桶
            Bucket bucket = map.get(key);
            // 如果该桶的计数为1，那就先把map中的数据删除
            if (bucket.cnt == 1) {
                map.remove(key);
            } else {
                // 如果数据所在桶的前一个桶的计数这都能刚好是-1，那就将数据移到前一桶中
                if (bucket.last.cnt == bucket.cnt - 1) {
                    map.put(key, bucket.last); // 更新map中的数据位置
                    bucket.last.set.add(key);
                }
                // 如果不是 就新建-1桶，连接桶
                else {
                    Bucket bucket1 = new Bucket(key, bucket.cnt - 1);
                    map.put(key, bucket1); // map中更新数据位置
                    insert(bucket.last, bucket1); // 连接新桶
                }
            }
            // 删除圆桶数据
            bucket.set.remove(key);
            if (bucket.set.isEmpty()) {
                remove(bucket);
            }
        }
        // 返回计数最小的字符串
        public String getMinKey() {
            return head.next.set.iterator().next();
        }
        // 返回计数最大的字符串
        public String getMaxKey() {
            return tail.last.set.iterator().next();
        }
    }
}

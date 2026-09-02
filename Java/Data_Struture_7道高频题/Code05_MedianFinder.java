package Data_Struture_7道高频题;

import java.util.PriorityQueue;

//  快速获得数据流的中位数的结构
// 用大小根堆来实现
// https://leetcode.cn/problems/find-median-from-data-stream/description/
public class Code05_MedianFinder {

    class MedianFinder{
        //定义大小根堆
        private PriorityQueue<Integer> maxHeap ; // 大根堆
        private PriorityQueue<Integer> minHeap ; // 小根堆
        public MedianFinder(){
            maxHeap = new PriorityQueue<>((a,b) -> b-a) ;
            minHeap = new PriorityQueue<>((a,b) -> a-b) ;
        }

        // 添加数据
        // 比较小的数在大根堆，比较大的数在小根堆，这样两堆的顶端才是列表的中间
        public void addNum(int num) {
            if (maxHeap.isEmpty() || maxHeap.peek() >= num){
                maxHeap.add(num) ;
            } else {
                minHeap.add(num) ;
            }
            // 确保两个堆的高度不能超过2
            if (Math.abs(maxHeap.size() - minHeap.size()) == 2){
                balance();
            }
        }
        // 两个堆的高度不能超过2
        public void balance(){
            if (maxHeap.size() > minHeap.size()){
                // 如果大根堆的数量多余小根堆，那就大根堆弹出添加到小根堆
                minHeap.add(maxHeap.poll()) ;
            } else {
                maxHeap.add(minHeap.poll()) ; // 否则 就换过来添加
            }
        }

        // 获取中位数
        public double findMedian(){
            // 如果两个堆的数量相等，就说明列表的数据数量是偶数，
            // 那么中位数就是俩堆顶部的数据相加除以2
            if (maxHeap.size() == minHeap.size()){
                return (double) (maxHeap.peek() + minHeap.peek()) / 2 ;
            } else {
                return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek() ;
            }
        }
    }

}

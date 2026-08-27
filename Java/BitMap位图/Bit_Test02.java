package BitMap位图;

// 不用算数运算符，实现位图的基本功能
// https://leetcode-cn.com/problems/design-bitset/

public class Bit_Test02 {

    class BitSet{
        // 设置变量以及常量
        private final int size;  // 位图大小
        private int[] set ;  // 数组
        private int zeros ;  // 0计数
        private int ones ;   // 1计数
        private boolean reverse;  // 是否翻转

        public BitSet(int n){
            set = new int[(n + 31) / 32];
            size = n;
            zeros = n;
            ones = 0;
            reverse = false;  // 翻转初始化为false代表没反转
        }

        // 添加数值
        public void fix(int size) {
            int index = size / 32 ; // 第几个32位
            int bit = size % 32 ;  // 具体第几位
            // 如果没反转
            if (!reverse) {
                // 判断第index个位置上的第bit上是否为0
                if ((set[index] & (1 << bit)) == 0){
                    zeros -- ;
                    ones ++ ;
                    set[index] |= (1 << bit) ;
                }
            } else {  // 如果翻转了那就反过来写
                if ((set[index] & (1 << bit)) != 0) {
                    zeros--;
                    ones++;
                    set[index] ^= (1 << bit);
                }
            }
        }

        // 移除该数
        public void unfix(int size) {
            int index = size / 32;
            int bit = size % 32;
            if (!reverse) {
                if ((set[index] & (1 << bit)) != 0){
                    ones -- ;
                    zeros ++ ;
                    set[index] ^= (1 << bit) ;
                }
            } else {
                if ((set[index] & (1 << bit)) == 0) {
                    ones--;
                    zeros++;
                    set[index] |= (1 << bit);
                }
            }
        }

        // 翻转所有位
        public void flip() {
            reverse = !reverse ;
            int tmp = ones ;
            ones = zeros ;
            zeros = tmp ;
        }
        // 是否所有位都是1
        public boolean all() {
            return ones == size ;
        }
        // 判断是否有一位为1
        public boolean one() {
            return ones > 0 ;
        }

        // 返回所有位1 的数量
        public int count() {
            return ones ;
        }

        // 返回所有位的状态
        public String toString() {
            StringBuilder builder = new StringBuilder() ;
            // 挨个遍历，判断位置上是0还是1
            for (int i = 0 , k = 0 , number , status; i < size; k++) {
                number = set[k] ;
                for (int j = 0; j < 32 && i < size ; j++ , i++) {
                    status = (number >> j) & 1 ; // 0并上1是0 , 1并上1是1
                    // 判断是否为翻转，如果为true，就0变1,1变0
                    // 等价于 status = status ^ (reverse ? 1 : 0)
                    status ^= reverse ? 1 : 0 ;
                    builder.append(status);
                }
            }
            return builder.toString();
        }
    }
}

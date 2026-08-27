package XOR_Bit异或与位运算;

/// 题目1 交换两个数
/// 题目2 不用任何判断语句和比较操作，返回两个数的最大值
/// 题目3 找到缺失的数字
/// 题目4 数组中1种数出现了奇数次，其他的数都出现了偶数次，返回出现了奇数次的数
///
/// Brian Kernighan算法 - 提取出二进制状态中最右侧的1
/// 题目5 数组中有2种数出现了奇数次，其他的数都出现了偶数次，返回这2种出现了奇数次的数
///
/// 题目6 数组中只有1种数出现次数少于m次，其他数都出现了m次，返回出现次数小于m次的那种数
public class XorTest {

    // 用异或交换俩数（前提是俩个数的内存地址必须不一样）
    public static void swap(int[] arr, int a , int b ){
        a = a ^ b ;
        b = a ^ b ;  // 因为a == a ^ b，所以b = a ^ b ^ b == a
        a = a ^ b ;  // 因为a = a ^ b并且b == a , 所以 a = a ^ b ^ a == b
    }

    // 求俩数最大值(有溢出风险)
    public static int GetMax(int x , int y){
        int c = x - y ; // c为负数，y最大；c为正数，x最大
        int returnA = c >>> 31 ^ 1 ;  // 让左移31位之后再异或1，如果c为负数，returnA = 0,如果c为非负，返回1
        int returnB = returnA ^ 1 ;  // 如果returnA为0，returnB就为1；如果returnA为1，returnB就为0
        return x * returnA + y * returnB ;
    }
    // 求俩数最大值(防溢出，先判断符号位)
    // https://www.nowcoder.com/practice/d2707eaf98124f1e8f1d9c18ad487f76
    public static int getMAx(int x, int y){
        int c = x - y ;
        // 让以下三个数，非负但会1，负数返回0
        int sx = x >>> 31 ^ 1 ;
        int sy = y >>> 31 ^ 1 ;
        int sc = c >>> 31 ^ 1 ;
        // 判断sx 和 sy 的符号一不一样，一样返回0，不一样返回1
        int diffXY = sx ^ sy;
        int sameXY = diffXY ^ 1 ; // 这个变量与diffXY互斥

        // 以下两个变量互斥
        int returnX = diffXY * sx + sameXY * sc;
        int returnY = returnX ^ 1;
        return x * returnX + y * returnY;
    }

    // 给一个数组，找到缺失的数值并返回（全部数组的异或和 异或 实际存在的异或和）
    // 前提条件：nums[i]在0-n范围内，i也在0-n范围内，
    // 也就是说数组中的下标跟数值都在0-n范围内，所以它俩异或就必然会有两两相等的情况
    // 两两相等就消掉，剩余的那一个数就是确实的数
    // https://leetcode.cn/problems/missing-number/description/
    public static int missingNumber(int[] nums ){
        // 设置两个变量，实际异或和与全部异或和
        int xorAll = 0 , xorHas = 0 ;
        for (int i = 0; i < nums.length; i++) {
            xorAll ^= i ;
            xorHas ^= nums[i];
        }
        xorAll ^= nums.length;
        return xorAll ^ xorHas ;
    }

    // Brian Kernighan算法 - 提取出二进制状态中最右侧的1
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & -n);  // -n == ~n+1
    }

    // 数组中一个数出现奇数次，其余的数出现偶数次，返回出现奇数次的数。
    // https://leetcode.cn/problems/single-number/
    // 遍历数组，挨个异或，剩下的就是出现奇数次的数
    public static int singNumber(int[] arr) {
        int xor = 0 ;
        for (int num : arr){
            xor ^= num ;
        }
        return xor ;
    }

    // 数组中有俩数出现奇数次，其余的出现偶数次，返回出现奇数次的俩数
    // https://leetcode.cn/problems/single-number-iii/
    public static int[] singNumber02(int[] arr) {
        int xor = 0 ;
        for (int num : arr){
            xor ^= num ;
        }
        // 经过上边的循环得出:xor == a ^ b

        // 获取xor的最右侧的1
        // 既然知道xor是a^b的结果，那么xor的最右侧的1，a和b一定互斥
        // 也就是说通rightOne来分开俩阵营，
        int rightOne = xor & (-xor);
        int xor2 = 0 ;  // 第二个数
        for (int num : arr){
            // 寻找num&rightOne==0的num 与 xor2 异或
            // 出现偶数次的必然被消掉，而最终剩下的num就是xor2 也就是a或者b
            if ((num & rightOne) == 0){
                xor2 ^= num ;
            }
        }
        return new int[] {xor2 , xor ^ xor2} ;
    }

    // 数组中只有一个数出现少于m次，其他数都出现m次,返回少于m次的数
    // https://leetcode.cn/problems/single-number-ii/description/
    // 先统计0-32位，每个位置的1有几个。
    // 然后再循环这个数组，模除m不等0的那个位置就是出现次数少于m次的数
    public static int singNumber03(int[] arr , int m) {
        // 设置32位数组
        int[] cnts = new int[32];
        for (int num : arr){
            for (int i = 0; i < 32; i++) {
                // 累加，如果num的二进制在i位置为1时，就在cnts[i]的数值加1
                cnts[i] = cnts[i] + ((num >> i) & 1 ) ;
            }
        }
        int ans = 0 ;
        for (int i = 0; i < 32; i++) {
            // 如果cnts[i]的数值除不尽m，就说明该位置是少于m次的
            // 那么就将该位置变为1
            if (cnts[i] % m != 0){
                ans = ans | 1 << i ;
            }
        }
        return ans ;
    }



}

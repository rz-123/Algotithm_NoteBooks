package BitMap位图;

// 位图 就是32个bit位，上边是0或1
public class Bit_Test01 {
    public int[] set ;  // 创建一个数组
    // 数组长度取决于传入的数据除以32向上取整，因为如果有余数还要多一个bit位来存放
    // n个数字 : 0~n-1
    public void Bitset(int n) {
        // a/b如果结果想向上取整，可以写成 : (a+b-1)/b
        // 前提是a和b都是非负数
        set = new int[(n + 31) / 32];
    }
    // 添加
    public void add(int num) {
        // 添加数值 就是找到此数在二进制的位置，然后将位置上的0变为1
        // 找到数组中具体的数字，然后让1左移余数就是那个位置变为1
        set[num / 32] = set[num / 32] | 1 << (num % 32) ;
    }

    // 删除
    public void remove(int num) {
        // 无需判断是否存在该数，1左移到位置后，取反，1变为0，0与谁都等于0
        set[num / 32] = set[num / 32] & ~(1<< (num % 32)) ;
    }

    // 翻转开关，之前有的给整没，之前没的给整有
    public void reverse(int num) {
        set[num / 32] ^= 1 << (num % 32) ;
    }

    // 查询此数是否存在
    public boolean contains(int num) {
        // 找到位置，让该位置的数与1，如果是0与1为0，如果是1与1为1.
        return ((set[num / 32] >> (num % 32)) & 1) == 1;
    }
}

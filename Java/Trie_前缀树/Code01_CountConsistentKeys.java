package Trie_前缀树;

import java.util.Arrays;

// 给定n个密钥a的数组跟m个密钥b的数组，要求b[i+1] - b[i] == a[i+1] - a[i]才符合条件，b的长度不能超过a
// 请返回长度为m的结果数组ans，表示每个密钥b有多少一致的密钥a
// https://www.nowcoder.com/practice/c552d3b4dfda49ccb883a6371d9a6932
public class Code01_CountConsistentKeys {

    // 让a数组的差值构建前缀树，在前缀树中找到有多少个以b数组的差值为前缀的数组

    // 如果将来增加了数据量，就改大这个值
    public static int MAXN = 2000001;

    public static int[][] tree = new int[MAXN][12];

    public static int[] pass = new int[MAXN];

    public static int cnt;

    public static void build() {
        cnt = 1;
    }

    // 将字符转换成数值
    public static int path(char cha){
        if (cha == '#'){
            return 10 ;
        } else if (cha == '-') {
            return 11;
        } else {
            return cha - '0' ;
        }
    }

    // 在前缀树中增加数值方法
    public static void insert(String pre){
        int cur = 1 ;
        pass[cur]++ ;
        for (int i = 0 , path; i < pre.length(); i++) {
            path = path(pre.charAt(i)) ;
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt ;  // 在该位置上赋值，意味着增加节点
            }
            cur = tree[cur][path] ;  // 如果该位置不为空 cur就移到这个位置
            pass[cur]++ ;  // 每次 pass在下标都加1，然后下标移到下一个位置
        }
    }
    // 在前缀树中寻找已某个数字为前缀的字符串
    public static int count(String pre){
        int cur = 1 ;
        for (int i = 0 , path; i < pre.length(); i++) {
            path = path(pre.charAt(i)) ;
            if (tree[cur][path] == 0) {
                return 0 ;
            }
            cur = tree[cur][path] ;
        }
        return pass[cur] ; // 最终返回pass的下标，表示有多少个字符串以pre为前缀
    }

    // 清空前缀树
    public static void clear(){
        for (int i = 1 ; i <= cnt ; i++){
            Arrays.fill(tree[i] , 0);
            pass[i] = 0;
        }
    }

    // 构建差值前缀树
    public static int[] countConsistentKeys(int[] [] b , int[] [] a){
        build(); // 初始化一个前缀树

        StringBuilder stringBuilder = new StringBuilder() ; // 临时拼接容器
        // a数组中的小数组循环,将a数组中的差值加到前缀树中
        for (int[] nums : a){
            stringBuilder.setLength(0); // 每次循环都清空该容器
            for (int i = 1; i < nums.length; i++) {
                // 每一个差值后边跟一个#符号，防止混乱
                stringBuilder.append(String.valueOf(nums[i] - nums[i-1] + "#")) ;
            }
            insert(stringBuilder.toString()) ; // 增加到前缀树中
        }
        // ans 保存pass数字，也是最终返回的结果数组
        int[] ans = new int[b.length] ;
        for (int i = 0; i < b.length; i++) {
            stringBuilder.setLength(0);
            int[] nums = b[i] ;
            for (int j = 1; j < nums.length; j++) {
                stringBuilder.append(String.valueOf(nums[j] - nums[j - 1]) + "#");
            }
            ans[i] = count(stringBuilder.toString()) ; // 每次都返回一个数
        }
        clear();
        return ans ;
    }
}

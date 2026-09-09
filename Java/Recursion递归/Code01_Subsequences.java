package Recursion递归;


import java.util.HashSet;

// 返回字符串全部子序列，子序列要求去重。时间复杂度O(2^n * n)
// https://www.nowcoder.com/practice/92e6247998294f2c933906fdedbc6e6a
public class Code01_Subsequences {

    /*
"ab"
复制
返回值：
["","a","ab","b"]
返回["","b","a","ab"]
 */
    public static String[] generatePermutation1(String str){
        char[] chars = str.toCharArray() ; // 字符串拆成字符数组
        HashSet<String> set = new HashSet<>() ; // 哈希set去重
        f1(chars , 0 , new StringBuilder() , set) ; // 递归
        int m = set.size() ;
        String[] ans = new String[m] ; // 新建数组
        int i = 0 ;
        for (String cur : set){
            ans[i++] = cur ;  // 把set的字符串添加到ans数组中
        }
        return ans ;
    }
    // 递归1，往回返的时候减去添加的那个值
    // chars:传进来的数组；i：选中的位置；path：历史记录；set：最终返回
    public static void f1(char[] chars , int i , StringBuilder path , HashSet<String> set){
        if (i == chars.length){
            set.add(path.toString()) ; // 如果i==chars长度就把历史记录添加到set中
        } else {
            path.append(chars[i]) ; // 将该位置的数值加到路径中
            f1(chars , i+1 , path , set);
            path.deleteCharAt(path.length()-1) ; // 移除刚才的数
            f1(chars , i+1 , path , set);
        }
    }

    public static String[] generatePermutation2(String str){
        char[] chars = str.toCharArray() ;
        HashSet<String> set = new HashSet<>() ;
        f2(chars , 0 , 0 , new char[chars.length] , set) ;
        String[] ans = new String[set.size()];
        int i = 0;
        for (String cur : set){
            ans[i++] = cur ;
        }
        return ans ;
    }
    // 递归2：用size来控制要不要该数
    // i:到第几个字符；size：选中第几个字符
    public static void f2(char[] chars , int i , int size , char[] path , HashSet<String> set){
        if (i == chars.length) {
            set.add(String.valueOf(path,0 , size)) ;
        } else {
            path[size] = chars[i];
            f2(chars , i + 1 , size + 1 , path , set);
            f2(chars , i + 1 , size  , path , set);
        }
    }
}

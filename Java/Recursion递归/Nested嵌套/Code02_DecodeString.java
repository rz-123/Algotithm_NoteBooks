package Recursion递归.Nested嵌套;

// 含有嵌套字符串解码
// https://leetcode.cn/problems/decode-string/description/
public class Code02_DecodeString {

    public static int where ;

    public static String decodeString(String str){
        where = 0 ;
        return f(str.toCharArray() , 0) ;
    }

    public static String f(char[] s , int i){
        StringBuilder path = new StringBuilder() ; // 保存字符串的容器
        int cnt = 0 ; // 存储临时数值
        while (i < s.length && s[i] != ']'){
            // 如果该位置的值是大小写字母，就去写进去
            if (s[i] >= 'A' && s[i] <= 'Z' || s[i] >= 'a' && s[i] <= 'z'){
                path.append(s[i++]) ; // 写进去 i往后移一位
            } else if (s[i] >= '0' && s[i] <= '9'){
                // 如果先碰到数值类型，就赋值给cnt
                cnt = cnt * 10 + s[i++] - '0' ;
            } else {
                // 碰到“[”，该计算该中括号中的字符有几个了
                path.append(get(cnt , f(s, i+1))) ;
                i = where + 1 ; // i来到where+1位置
                cnt = 0 ; // cnt清空
            }
        }
        where = i ;
        return path.toString() ;
    }

    // 根据cnt的数量 , 将cnt个str存入builder中返回
    public static String get(int cnt , String str){
        StringBuilder builder = new StringBuilder() ;
        for (int i = 0; i < cnt; i++) {
            builder.append(str);
        }
        return builder.toString() ;
    }
}

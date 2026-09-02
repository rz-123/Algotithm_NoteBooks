package Data_Struture_7道高频题;


import java.io.*;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

/*
// setAll功能的哈希表,把哈希表里的Value全部替换成一个Value
// 测试链接 : https://www.nowcoder.com/practice/7c4559f138e74ceb9ba57d76fd169967
 */
public class Code01_SetAllHashMap {

    // 增加俩变量，一个值，一个时间戳

    public static HashMap<Integer , int[]> map = new HashMap<>() ;  // 哈希表
    public static int setAllValue ; // 要替换的值
    public static int setAllTime ; //  setAll方法的时间
    public static int cnt ;   // 时间戳

    // put方法：如果原先存在k，就去除value数组 修改值跟cnt
    // 如果没有 就新建数组，将v跟cnt存进去
    public static void put(int k , int v){
        if (map.containsKey(k)){
            int[] ints = map.get(k);
            ints[0] = v ;
            ints[1] = cnt ++ ;
        } else {
            map.put( k , new int[] {v, cnt ++}) ;
        }


    }
    public static void setAll(int v){
        setAllValue = v ;
        setAllTime = cnt ++ ; // 每次加1
    }

    // get 方法 获取值，判断cnt是否小于setTime，如果小于等于setTime就直接用setValue进行返回
    public static int get(int k) {
        if ( ! map.containsKey(k)){
            return -1 ;
        }
        int[] value = map.get(k) ;
        if (value[1] <= setAllTime){
            return setAllValue ;
        } else {
            return value[0] ;
        }
    }
    public static int n, op, a, b;
    public static void main(String args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));


        // 循环读取，直到输入流到达末尾（EOF）为止
        while (in.nextToken() != StreamTokenizer.TT_EOF) { // nextToken 读取下一个 token，TT_EOF 表示文件结束
            map.clear();                 // 清空哈希表，准备处理新的一组测试数据
            setAllValue = 0;             // 重置 setAll 的目标值
            setAllTime = -1;             // 重置 setAll 的时间戳为 -1（小于所有正常时间戳 0,1,2...）
            cnt = 0;                     // 重置计数器，重新分配时间戳
            n = (int) in.nval;           // 读取本组测试的操作次数 n
            for (int i = 0; i < n; i++) { // 循环处理 n 条操作
                in.nextToken();          // 读取操作类型 op
                op = (int) in.nval;      // 将当前 token 转为整数，赋值给 op
                if (op == 1) {           // 操作 1：插入/更新（put）
                    in.nextToken();      // 读取 key
                    a = (int) in.nval;   // 赋值给 a
                    in.nextToken();      // 读取 value
                    b = (int) in.nval;   // 赋值给 b
                    put(a, b);           // 调用 put，把 (a, b) 写入哈希表
                } else if (op == 2) {    // 操作 2：查询（get）
                    in.nextToken();      // 读取 key
                    a = (int) in.nval;   // 赋值给 a
                    out.println(get(a)); // 调用 get 并打印结果
                } else {                 // 其他情况：操作 3，setAll
                    in.nextToken();      // 读取 setAll 的目标值
                    a = (int) in.nval;   // 赋值给 a
                    setAll(a);           // 调用 setAll，把所有 value 逻辑上替换成 a
                }
            }
        }

        out.flush();  // 把缓冲区里的所有内容一次性输出
        out.close();  // 关闭输出流（关闭前会自动 flush）
        br.close();   // 关闭输入流，释放资源
    }
}

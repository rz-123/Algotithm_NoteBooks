package Diff差分;

// 一维差分，航班问题
// 表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi]
// 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
// https://leetcode.cn/problems/corporate-flight-bookings/
public class Code01_CorporateFlightBookings {

    // [2,6,8]
    public static int[] corpFlightBookings(int[] [] books , int n){
        // 创建存储差分的数组
        int[] cnt = new int[n + 2] ;
        // 设置差分数组
        for (int[] book : books) {
            cnt[book[0]] += book[2] ;  // L位置加x
            cnt[book[1] + 1] -= book[2] ;  // L+1位置减x
        }

        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1] ; // 前缀和
        }

        // 创建返回数组
        int[] ans = new int[n] ;
        for (int i = 0; i < n; i++) {
            ans[i] = cnt[i+1] ;
        }
        return ans ;
    }
}

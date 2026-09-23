package Two_Binary二分答案法;

// 爱吃香蕉的珂珂
// 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉
// 警卫已经离开了，将在 h 小时后回来。
// 珂珂可以决定她吃香蕉的速度 k （单位：根/小时)
// 每个小时，她将会选择一堆香蕉，从中吃掉 k 根
// 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
// 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）
// 测试链接 : https://leetcode.cn/problems/koko-eating-bananas/

import javax.security.auth.login.CredentialNotFoundException;

public class Code01_KokoEatingBananas {


    public static int minEatingSpeed(int[] piles , int h){
        // 找出左右范围
        int l = 1 , r = 0 ;
        for (int pile : piles){
            r = Math.max(r, pile) ;  // 选出数组最大值作为右边界
        }

        int ans = 0 ;
        while (l <= r) {
            int m = l + ((r-l) >> 1) ; // 中点，防止溢出
            if (f(piles , m) <= h){
                ans = m ; // f函数返回的数如果小于给的h，说明达标，记录答案
                r = m - 1 ; // 需要最小，去左侧二分
            } else {
                l = m + 1;
            }
        }
        return ans ;
    }

    // 给你一组香蕉，一个速度k，返回吃完需要多少小时
    public static long f(int[] piles , int k){
        long ans = 0 ;
        for (int p : piles) {
            // 数组中的每一个数除以k向上取整
            ans += (p + k -1) / k ;
        }
        return ans ;
    }
}

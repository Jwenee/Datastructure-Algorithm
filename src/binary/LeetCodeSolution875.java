/**
 * question: 875. 爱吃香蕉的珂珂
 *
 * describe: 珂珂喜欢吃香蕉。这里有N堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在H小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度K（单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 * 示例 1:
 *
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 *
 * 示例 2：
 *
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 */

package binary;

import java.util.Arrays;

public class LeetCodeSolution875 {

    public int minEatingSpeed(int[] piles, int h) {
//        最慢每小时1，最快每小时最大堆数量
        int l = 1, r = Arrays.stream(piles).max().getAsInt();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (eatingTime(piles, mid) <= h)
                r = mid;
            else l = mid + 1;
        }
        return l;
    }
//    每小时吃k，需要res小时
    private int eatingTime(int[] piles, int k) {
        int res = 0;
        for (int pile : piles) {
            res += pile / k + (pile % k > 0 ? 1 : 0);
        }
        return res;
    }
}

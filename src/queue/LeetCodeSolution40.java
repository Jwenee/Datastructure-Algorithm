/**
 * question: 40. 剑指 Offer 40. 最小的k个数
 * linkedUrl: https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 */

package queue;

public class LeetCodeSolution40 {

    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            pq.enqueue(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (!pq.isEmpty() && arr[i] < pq.getFront()) {
                pq.dequeue();
                pq.enqueue(arr[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = pq.dequeue();
        return res;
    }
}

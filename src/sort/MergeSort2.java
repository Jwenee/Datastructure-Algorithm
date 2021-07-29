package sort;

import base.ArrayGenerator;
import base.SortingHelper;

import java.util.Arrays;

// 自底向上的归并排序
public class MergeSort2 {

    private MergeSort2() {}

    public static <E extends Comparable<E>> void sortBU(E[] arr) {
        int n = arr.length;
        E[] temp = Arrays.copyOf(arr, n);

//        遍历合并的区间长度
        for (int sz = 1; sz < n; sz += sz) {
//            遍历合并的两个区间的起始位置
//            合并[i, i + sz - 1], [i + sz, Math.min(i + sz + sz -1, n - 1)]
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0)
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz -1, n - 1), temp);
            }
        }
    }

    //    合并两个有序的区间[l, mid], [mid+1, r]，内存优化
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp) {
//        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int i = l, j = mid + 1;

//        每轮循环为arr[k]赋值
        for (int k = l; k <= r; k++) {
//            左半部分越界，元素已用完，直接取右半部分
            if (i > mid) {
                arr[k] = temp[j];
                j++;
//            右半部分越界，元素已用完，直接取左半部分
            } else if (j > r) {
                arr[k] = temp[i];
                i++;
//            左部比右部小
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
//            右部比左部小
            } else {
                arr[k] = temp[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("MergeSortBU", arr);
    }
}

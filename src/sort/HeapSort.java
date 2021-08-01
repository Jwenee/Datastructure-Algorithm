package sort;

import base.ArrayGenerator;
import base.SortingHelper;
import heap.MaxHeap;

import java.util.Arrays;

public class HeapSort {

    private HeapSort() {}

//    非原地排序
    public static <E extends Comparable<E>> void sort(E[] data) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for (E e : data) {
            maxHeap.add(e);
        }

        for (int i = data.length - 1; i >= 0; i--) {
            data[i] = maxHeap.extractMax();
        }
    }

//    原地排序
    public static <E extends Comparable<E>> void sort2(E[] data) {
        if (data.length <= 1) return;
//        最后一个元素的父节点，（index - 1）/ 2
//        heapify
        for (int i = (data.length - 2) / 2; i >= 0; i--)
//            data.length表示当前数组前n个元素为最大堆
            shiftDown(data, i, data.length);

        for (int i = data.length - 1; i >= 0; i--) {
//            交换堆顶最大值和最后元素,交换后最后一个元素为最大值，此时前last - 1个不再是最大堆
            swap(data, 0, i);
//            使前i个元素堆化,维护前n个元素
            shiftDown(data, 0, i);
        }
    }

//    对data[0, n)所形成的最大堆中索引k的元素，执行shiftDown
    private static <E extends Comparable<E>> void shiftDown(E[] data, int k, int n) {
//        2*k+1左子节点，n总共元素
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
//            j+1右子节点
            if (j + 1 < n && data[j + 1].compareTo(data[j]) > 0)
                j++;
//            data[j]是k左右节点中的最大值

            if (data[k].compareTo(data[j]) >= 0) break;
            swap(data, k, j);
            k = j;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("MergeSort2", arr);
        SortingHelper.sortTest("QuickSort", arr2);
        SortingHelper.sortTest("QuickSort2", arr3);
        SortingHelper.sortTest("HeapSort", arr4);
        SortingHelper.sortTest("HeapSort2", arr4);
    }
}

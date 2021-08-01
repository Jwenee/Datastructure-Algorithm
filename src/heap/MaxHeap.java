package heap;

import array.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
//        先找到最后一个非叶子节点，即最后节点的父节点,遍历所有非叶子节点，执行下沉操作
//        heapify,堆化
        for (int i = parent(arr.length - 1); i >= 0; i--)
            shiftDown(i);
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void add(E e) {
        data.addLast(e);
        shiftUp(data.getSize() - 1);
    }

//    确保添加元素小于等于父元素，大于时执行上浮操作
    private void shiftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMax() {
        if (data.isEmpty())
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

//    取出最大元素
    public E extractMax() {
        E ret = findMax();
//        交换第一个元素和最后一个元素
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        shiftDown(0);
        return ret;
    }

//    确保交换的元素满足父元素大于等于子元素，执行下沉操作
    private void shiftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k); // 左子节点
//            j+1 右子节点
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChile(k);
//            data[j]是leftChild和rightChild中的最大值

            if (data.get(k).compareTo(data.get(j)) >= 0) break;

            data.swap(k, j);
            k = j;
        }
    }

//    取出最大元素，并且替换成元素e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        shiftDown(0);
        return ret;
    }

//    返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 does not have parent");
        return (index - 1) / 2;
    }

//    返回完全二叉树的数组表示中，一个索引所表示的左孩子节点的索引
    private int leftChild(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Illegal index");
        return index * 2 + 1;
    }

//    返回完全二叉树的数组表示中，一个索引所表示的右孩子节点的索引
    private int rightChile(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Illegal index");
        return index * 2 + 2;
    }
}

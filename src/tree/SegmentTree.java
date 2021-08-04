package tree;

import java.util.Arrays;

public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merge<E> merge;
    public SegmentTree() {}

    public SegmentTree(E[] arr, Merge<E> merge) {
        this.merge = merge;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

//    在treeIndex的位置创建表示区间[l, ... r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
//        当前treeIndex的左右子树区间
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

//        自定义父节点如何处理左右子节点
        tree[treeIndex] = merge.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Illegal index.");
        return data[index];
    }

//    返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
            queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Illegal arguments.");
        return query(0,0, data.length - 1, queryL, queryR);
    }

//    在以treeIndex为root的线段树中[l, ...r]的范围内，搜索区间[queryL, queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

//        查找区间分别完全匹配到左右子树区间的情况
        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

//        查找区间存在于左右子树区间
        E leftRes = query(leftTreeIndex, l, mid, queryL, mid);
        E rightRes = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merge.merge(leftRes, rightRes);
    }

    public void set(int index, E e) {
        if (index < 0 || index > data.length)
            throw new IllegalArgumentException("Illegal index.");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1) set(rightTreeIndex, mid + 1, r, index, e);
        else set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merge.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

//    返回索引所表示的元素左子树节点索引(完全二叉树）
    private int leftChild(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Illegal index.");
        return 2 * index + 1;
    }

//    返回索引所表示的元素右子树节点索引（完全二叉树）
    private int rightChild(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Illegal index.");
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) sb.append(tree[i]);
            else sb.append("null");

            if (i != tree.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

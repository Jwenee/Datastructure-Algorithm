package tree;

public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    public SegmentTree() {}

    public SegmentTree(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[arr.length * 4];
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Illegal index.");
        return data[index];
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
}

package tree;

//  性质1. 结点是红色或黑色。
//  性质2. 根结点是黑色。
//  性质3. 所有叶子都是黑色。（叶子是NIL结点）
//  性质4. 每个红色结点的两个子结点都是黑色。（从每个叶子到根的所有路径上不能有两个连续的红色结点）
//  性质5. 从任一节结点其每个叶子的所有路径都包含相同数目的黑色结点。
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node() {}
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isRed(Node node) {
        if (node == null) return BLACK; // 空节点为black
        return node.color;
    }

//         node                         x
//         /  \         左旋           /   \
//        T1   x      -------->      node   T3
//            /  \                  /    \
//           T2  T3                T1    T2

    private Node leftRotate(Node node) {
        Node nodeR = node.right;

        node.right = nodeR.left;
        nodeR.left = node;

        nodeR.color = node.color;
        node.color = RED;
        return nodeR;
    }

//         node                         x
//         /  \         右旋           /   \
//        x    T3      -------->     T1    node
//       /  \                              /   \
//      T1   T2                           T2   T3

    private Node rightRotate(Node node) {
        Node nodeL = node.left;

        node.left = nodeL.right;
        nodeL.right = node;

        nodeL.color = node.color;
        node.color = RED;
        return nodeL;
    }

//    颜色翻转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

//    添加新元素过程(类比2-3树中2节点添加元素)
//                                  root(black)         root(black)
//         root(black)               /         左旋          /     右旋                    颜色翻转
//        /              -->      rootL(red)   -->    rootL(red)  -->     root(black)      -->   root(red)
//     rootL(red)                    \                   /                   / \                    /   \
//                                 +rootLL(red)    +rootLL(red)      rootL(red) +rootR(red) rootL(black)  rootR(black)
//                               1.添加处于2节点中间   2.添加处于2节点最小    3.添加处于2节点最大

//    向红黑树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 根节点始终是黑色节点
    }

//    向以node为root的红黑树中插入元素，递归算法
//    返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value); // 默认插入红节点
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else return getNode(node.right, key); // > 0
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newVal) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "does not exist.");
        node.value = newVal;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }
}

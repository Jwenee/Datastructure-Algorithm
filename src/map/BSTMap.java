package map;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node() {}
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else node.value = value; // equal
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

//    删除掉以node为root的二分搜索树中键为key的节点，递归算法
//    返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { // key is equal

//            待删除节点左子树为空
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
//            待删除节点右子树为空
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }

//            待删除节点左右子树均不为空的情况
//            找到比删除节点大的最小节点，即待删除节点右子树的最小节点
//            用该节点代替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newVal) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "does not exist.");
        node.value = newVal;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(Node node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else return getNode(node.right, key); // > 0
    }

//    返回以node为root的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

//    删除掉以node为root的二分搜索树中的最小节点
//    返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }
}

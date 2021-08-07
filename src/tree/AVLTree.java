package tree;

import java.util.ArrayList;

// 带有平衡条件：每个结点的左右子树的高度之差的绝对值（平衡因子）最多为1。
// 本质上是带了平衡功能的二叉查找树
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left, right;

        public Node() {}
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            height = 1;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

//    判断该二叉树是否是二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
//        二分搜索树的中序遍历是有序数组
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        }
        return true;
    }

//    判断该二叉树是否是平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

//    获取节点node的高度
    private int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

//    获取节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

//  对节点y进行左旋操作，返回旋转后的新的根节点x  (T1<y<T2<x<T3<z<T4)
//              y                                  x
//            /  \                               /   \
//           T1   x         左旋y               y       z
//               /  \    ------------->       /  \    / \
//              T2   z                       T1  T2 T3   T4
//                  / \
//                 T3  T4
    private Node leftRotate(Node y) {
        Node yR = y.right;
        Node yRL = yR.left;
//        Node x = y.right;
//        Node T2 = x.left;
//
//        x.left = y;
//        y.right = T2;

//        左旋过程
        yR.left = y;
        y.right = yRL;

//        更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        yR.height = Math.max(getHeight(yR.left), getHeight(yR.right)) + 1;
        return yR;
    }

//  对节点y进行右旋操作，返回旋转后的新的根节点x  (T1<z<T2<x<T3<y<T4)
//              y                                  x
//            /  \                            /        \
//           x    T4      右旋y              z           y
//         /   \       ------------->     /   \       /   \
//        z    T3                       T1    T2    T3    T4
//      /   \
//    T1    T2
    private Node rightRotate(Node y) {
        Node yL = y.left;
        Node yLR = yL.right;
//        Node x = y.left;
//        Node T3 = x.right;
//
//        x.right = y;
//        y.left = T3;

//        右旋过程
        yL.right = y;
        y.left = yLR;

//        更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        yL.height = Math.max(getHeight(yL.left), getHeight(yL.right)) + 1;
        return yL;
    }

//    向二分搜索树中添加新的元素
    public void add(K key, V value) {
        root = add(root, key, value);
    }

//    向以node为root的二叉搜索树中插入元素，递归算法
//    返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else node.value = value; // key equal, update value

//        更新node的height
        node.height = Math.max(getHeight(node.left), getHeight(node.right));
//        计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            System.out.println("Unbalanced: " + balanceFactor);

//        平衡维护
//        1.插入的元素在不平衡节点的左侧的左侧，右旋 （节点LL)
//        2.插入的节点在不平衡节点的右侧的右侧，左旋 （节点RR)
//        3.插入的节点在不平衡节点的左侧的右侧      （节点LR)
//        4.插入的节点在不平衡节点的右侧的左侧      （节点RL)
//
//             y          y                y           y
//            /            \              /             \
//           x              x            x               x
//          /                \            \             /
//         z                  z            z           z

//      LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node); // 依次返回，平衡父节点
//      RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node); // 依次返回，平衡父节点
//      LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left); // 转换为LL
            return rightRotate(node);
        }
//      RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right); // 转换为RR
            return leftRotate(node);
        }

        return node;
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

//    从二分搜索树中删除键为key的节点
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;

        Node retNode;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else { // equal

//            待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
//           待删除节点右子树为空的情况
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }

//            待删除节点左右子树均不为空的情况
//            找到比删除节点大的最小节点，即待删除节点右子树的最小节点
//            用该节点代替待删除节点的位置
            else {
                Node successor = minimum(node.right);
//            successor.right = removeMin(node.right); // 没有维护平衡
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null) return null;
//        更新node的height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right));
//        计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        if (Math.abs(balanceFactor) > 1)
            System.out.println("Unbalanced: " + balanceFactor);

//      LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode); // 依次返回，平衡父节点
//      RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode); // 依次返回，平衡父节点
//      LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left); // 转换为LL
            return rightRotate(retNode);
        }
//      RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right); // 转换为RR
            return leftRotate(retNode);
        }
        return retNode;
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
}

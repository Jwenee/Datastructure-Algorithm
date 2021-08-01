package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {}

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }
        root = add(root, e);
    }

//    向以node为root的二叉搜索树中插入元素e，递归算法
//    private void add(Node node, E e) {
//        if (e.compareTo(node.e) == 0) return;
//        else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//        if (e.compareTo(node.e) < 0) add(node.left, e);
//        else add(node.right, e); // > 0
//    }
//    向以node为root的二叉搜索树中插入元素e，递归算法
//    返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

//    向以node为root的二叉搜索树中查找元素e，递归算法
    private boolean contains(Node node, E e) {
        if (node == null) return false;

        if (e.compareTo(node.e) == 0) return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else return contains(node.right, e); // > 0
    }

//    二分搜索树的前序遍历，递归算法
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
//        另一种写法
//        if (node != null) {
//            System.out.println(node.e);
//            preOrder(node.left);
//            preOrder(node.right);
//        }
    }

//    二分搜索树的非递归前序遍历
    public void preOrderNR() {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node cur = nodeStack.pop();
            System.out.println(cur.e);
//            后进先出,先访问left
            if (cur.right != null) nodeStack.push(cur.right);
            if (cur.left != null) nodeStack.push(cur.left);
        }
    }

//    二分搜索树的中序遍历，递归算法
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

//    二分搜索树的后序遍历，递归算法
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.e);
    }

//    二叉搜索树的层序遍历
    public void levelOrder() {
        Queue<Node> nodeQ = new LinkedList<>();
        nodeQ.add(root);
        while (!nodeQ.isEmpty()) {
            Node cur = nodeQ.remove();
            System.out.println(cur.e);

            if (cur.left != null) nodeQ.add(cur.left);
            if (cur.right != null) nodeQ.add(cur.right);
        }
    }

//    寻找二叉搜索树的最小元素
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }

//    返回以node为root的二叉搜索树的最小值所在节点
    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

//    寻找二叉搜索树的最大元素
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return maximum(root).e;
    }

//    返回以node为root的二叉搜索树的最大值所在节点
    private Node maximum(Node node) {
        if (node.right == null) return node;
        return maximum(node.right);
    }

//    从二叉搜索树中删除最小值所在节点，并返回最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

//    删除以node为root的二叉搜索树中的最小值节点
//    返回删除节点后新的二叉搜索树的根
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

//    从二叉搜索树中删除最大值所在节点，并返回最大值
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

//    删除以node为root的二叉搜索树中的最大值节点
//    返回删除节点后新的二叉搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

//    从二叉搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

//    删除以node为root的二叉搜索树中值为e的节点，递归算法
//    返回删除节点后新的二叉搜索树的根
    private Node remove(Node node, E e) {
        if (node == null) return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // equal
            // 只存在右子树，右子树可为空
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            // 只存在左子树，左子树可为空
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            // 左右子树均不为空
            // 找到比删除节点大的最小节点，即删除节点右子树的最小节点（或者找到比删除节点小的最大节点，即左子树的最大节点）
            // 用找到的最小节点替代删除节点的位置
            Node successor = minimum(node.right); // 右子树的最小节点
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

//    生成以node为根节点，深度为depth的描述二叉树的字符串
    public void generateBSTString(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }
        sb.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, sb);
        generateBSTString(node.right, depth + 1, sb);
    }

    public String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }
}

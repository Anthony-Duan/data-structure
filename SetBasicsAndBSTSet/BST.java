package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ Description: 二分搜索树的实现
 * @ Date: Created in 08:59 20/07/2018
 * @ Author: Anthony_Duan
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        private E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;


    public BST() {
        root = null;
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 向以node为根的二分搜索树中插入元素e 递归算法
     *
     * @param node 当前要插入二分搜索树的根
     * @param e    元素e
     * @return 当前调用过程中的二分搜索树的根
     */
    private Node add(Node node, E e) {

        //递归的终止条件，如果当前要插入树为空则该节点就是要如节点的地方
        if (node == null) {
            size++;
            return new Node(e);
        }

        //如果小于当前节点则把左子树传入递归方法
        if (e.compareTo(node.e) < 0) {
            //需要注意的是这里返回的node.left 每次add方法返回的都会有一个节点
            //如果是终止条件返回的是插入元素的节点，如果是中间的递归程序返回的是 当前节点node 并不会改变当前节点node的地址
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        //这里很重要
        return node;
    }

    /**
     * 用户调用这个方法
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 用户调用这个方法
     * 看二分搜索树中是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 二分搜索树的前序遍历
     *
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }


    /**
     * 用栈完成二分搜索树的非递归前序遍历
     */
    private void preOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public void preOrder() {
        preOrder(root);
    }


    /**
     * 后序遍历
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void postOrder() {
        postOrder(root);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }


    //二分搜索树的层序遍历
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.add(cur);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 寻找二分搜索树的最小元素
     *
     * @return
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }

    /**
     * 返回二分搜索树中最小值所在的节点
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树中的最大元素
     *
     * @return
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值所在的节点
     *
     * @param node
     * @return
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在的节点，返回最小值
     *
     * @return
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除二分搜索树中最大值所在的节点
     *
     * @return
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点
     * 返回删除后的新二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.right = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    /**
     * 从二分搜索树中删除元素为e 的节点
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e 的节点   递归算法
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {//e.compareTo(node.e)==0

            //如果左子树为空
            if (node.left == null) {
                Node righNode = node.right;
                node.right = null;
                size--;
                return righNode;
            }
            //如果右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            /**
             * 左右子树都不为空的情况下
             * 首先找到比待删除节点大的最小的节点  即待删除节点右子树中的最小节点
             * 用这个节点替代删除节点的位置
             */
            Node successor = minimum(node.right);
            successor.left = node.left;
            //这里removeMax方法中已经有了size--，所以这里不需要再对size进行维护
            successor.right = removeMax(node.right);

            node.left = node.right = null;
            return successor;


        }
    }


    private void generaterString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generaterDepthString(depth) + "null\n");
            return;
        }


        res.append(generaterDepthString(depth) + node.e + "\n");
        generaterString(node.left, depth + 1, res);
        generaterString(node.right, depth + 1, res);
    }

    private String generaterDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generaterString(root, 0, res);
        return res.toString();
    }
}

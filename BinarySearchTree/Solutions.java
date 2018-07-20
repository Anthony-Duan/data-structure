package BinarySearchTree;

/**
 * @ Description: LeetCode144 二叉树的前序遍历 https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 * @ Date: Created in 10:26 20/07/2018
 * @ Author: Anthony_Duan
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return linkedList;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            linkedList.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return linkedList;
    }
}

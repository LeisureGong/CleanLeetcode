package coding;

import java.util.LinkedList;
import java.util.Queue;

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class BinaryTree {
    //104 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int leftDepth = 0;
        int rightDepth = 0;
        leftDepth = maxDepth(root.left) + 1;
        rightDepth = maxDepth(root.right) + 1;
        return Math.max(leftDepth,rightDepth);
    }
}

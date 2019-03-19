import BinaryTree.TreeNode;
import BinaryTree.linked.MyBinaryTree;

/**
 * @Author: cuizhe
 * @Date: 2019/3/19 10:18
 */
public class TreeDepth {
    /**
     * 题目：
     * 输入一棵二叉树，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     */

    public static int treeDepth(TreeNode root) {
        return getHeight(root);
    }

    private static int getHeight(TreeNode root) {
        if (root==null){
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return left>right?(left+1):(right+1);
    }

    /**
     * 一棵树：
     *         1
     *       /  \
     *      2    3
     *    /  \    \
     *   4    5    6
     *       /
     *      7
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7,null,null);
        TreeNode node4 = new TreeNode(4,null,null);
        TreeNode node5 = new TreeNode(5,node7,null);
        TreeNode node6 = new TreeNode(6,null,null);
        TreeNode node2 = new TreeNode(2,node4,node5);
        TreeNode node3 = new TreeNode(3,null,node6);
        TreeNode node1 = new TreeNode(1,node2,node3);
        MyBinaryTree binaryTree = new MyBinaryTree();
        binaryTree.setRoot(node1);
        System.out.println(treeDepth(binaryTree.getRoot()));
    }
}

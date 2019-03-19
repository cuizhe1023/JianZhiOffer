import BinaryTree.TreeNode;
import BinaryTree.linked.MyBinaryTree;

/**
 * @Author: cuizhe
 * @Date: 2019/3/19 14:44
 */
public class IsBalanced {
    /**
     * 题目：
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     */


    /**
     * 思路：
     * 平衡二叉树，每个结点的左子树和右子树的高度相差不超过 1。
     *
     * @param root
     * @return
     */
    public static boolean IsBalanced_1(TreeNode root) {
        if (root==null){
            return true;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        int diff = left - right;
        if (diff>1 || diff<-1){
            return false;
        }
        return IsBalanced_1(root.left) && IsBalanced_1(root.right);
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
     * 思路：
     * 上面的思想是从上往下遍历，每遍历一个结点的时候，总是会遍历他们下边的子树，会造成多的遍历。
     * 如果从下往上遍历，则可以省去很多时间。
     * 如果子树是平衡二叉树，则返回子树的高度；如果发现子树不是平衡二叉树，则直接停止遍历，这样至多只对每个结点访问一次。
     * @param root
     * @return
     */
    public static boolean IsBalanced_2(TreeNode root) {
        return getDepth(root) != -1;
    }

    private static int getDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = getDepth(root.left);
        if (left == -1){
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1){
            return -1;
        }
        return Math.abs(left - right)>1?-1:Math.max(left,right)+1;
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
        System.out.println(IsBalanced_2(binaryTree.getRoot()));
    }
}

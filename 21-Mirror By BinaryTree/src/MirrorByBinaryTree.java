import BinaryTree.TreeNode;
import BinaryTree.linked.MyBinaryTree;

/**
 * @Author: cuizhe
 * @Date: 2019/3/1 12:42
 */
public class MirrorByBinaryTree {
    /**
     * 题目：
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * 例如，
     * 二叉树的镜像定义：
     * 源二叉树
     *     	     8
     *     	   /   \
     *       6     10
     *     /  \   /  \
     *    5   7  9   11
     *     	镜像二叉树
     *     	     8
     *     	   /   \
     *       10     6
     *     /  \   /  \
     *    11  9  7    5
     *    
     */

    /**
     * 思路：
     * 通过左右结点之间的交换完成树的镜像，以
     *     	     8
     *     	   /   \
     *       6     10
     *     /  \   /  \
     *    5   7  9   11  为例
     * 先交换根节点的左右结点
     *     	     8
     *     	   /   \
     *       10     6
     *     /  \   /  \
     *    9   11 5    7
     * 再交换根节点左结点的左右结点 和 交换根节点右结点的左右结点
     *     	     8
     *     	   /   \
     *       10     6
     *     /  \   /  \
     *    11  9  7    5
     *
     * @param root
     */
    public static void mirror(TreeNode root) {
        if (root==null || (root.left==null || root.right==null)){
            return;
        }
        //进行左右结点之间的交换
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        if (root.left!=null){
            mirror(root.left);
        }
        if (root.right!=null){
            mirror(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5,null,null);
        TreeNode node7 = new TreeNode(7,null,null);
        TreeNode node9 = new TreeNode(9,null,null);
        TreeNode node11 = new TreeNode(11,null,null);
        TreeNode node6 = new TreeNode(6,node5,node7);
        TreeNode node10 = new TreeNode(10,node9,node11);
        TreeNode node_root = new TreeNode(8,node6,node10);
        MyBinaryTree binaryTree = new MyBinaryTree();
        binaryTree.setRoot(node_root);
        binaryTree.preOrderTraverse();

        mirror(binaryTree.getRoot());
        binaryTree.preOrderTraverse();
    }
}

import BinaryTree.TreeNode;
import BinaryTree.linked.MyBinaryTree;

/**
 * @Author: cuizhe
 * @Date: 2019/4/5 10:13
 */
public class TheTreeIsSymmetrical {
    /**
     * 题目：
     * 请实现一个函数，用来判断一颗二叉树是不是对称的。
     * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */

    /**
     * 思路:
     * 对于一个二叉树从根开始遍历，
     * 如果左右结点中有一个为 null，则这颗二叉树不为对称二叉树
     * 如果左右结点都不为 null，但是左右节点的值不相等，则也不是对称二叉树
     * 如果左右结点都不为空且相等：
     *      1.遍历左子树，遍历顺序：根、左、右
     *      2.遍历右子树，遍历顺序：根、右、左
     * 如果遍历左子树的序列和遍历右子树的序列一样的话，那么该树就是对称二叉树
     *
     * @param pRoot
     * @return
     */
    public static boolean isSymmetrical(TreeNode pRoot){
        return isSymmetrical(pRoot,pRoot);
    }

    private static boolean isSymmetrical(TreeNode pRoot, TreeNode pRoot1) {
        if (pRoot == null && pRoot1 == null){
            return true;// 左子树和右子树都为 null，则为叶子节点，返回 true
        }
        if (pRoot == null || pRoot1 == null){
            return false;// 左子树或者右子树为 null，那么肯定不对称，返回 false
        }
        if (pRoot.left != null && pRoot1.right != null && pRoot.left.val != pRoot1.right.val){
            return false;// 左右子树都不为空，但是值不相等，返回 false
        }
        if (pRoot.right != null && pRoot1.left != null && pRoot.right.val != pRoot1.left.val){
            return false; // 右左子树都不为空，但是值不相等，返回 false
        }
        // 进行递归判断，继续判断他们的左右子树和右左子树
        return isSymmetrical(pRoot.left,pRoot1.right) && isSymmetrical(pRoot.right,pRoot1.left);
    }

    /**
     *     	     8
     *     	   /   \
     *       6      6
     *     /  \    /  \
     *    5    7  7    5
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node5_1 = new TreeNode(5,null,null);
        TreeNode node7_1 = new TreeNode(7,null,null);
        TreeNode node5_2 = new TreeNode(5,null,null);
        TreeNode node7_2 = new TreeNode(7,null,null);
        TreeNode node6_1 = new TreeNode(6,node5_1,node7_1);
        TreeNode node6_2 = new TreeNode(6,node7_2,node5_2);
        TreeNode node_root = new TreeNode(8,node6_1,node6_2);
        MyBinaryTree binaryTree = new MyBinaryTree();
        binaryTree.setRoot(node_root);
        binaryTree.preOrderTraverse();
        System.out.println("是对称的："+isSymmetrical(node_root));
    }

}

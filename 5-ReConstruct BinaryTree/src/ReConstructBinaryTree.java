
import BinaryTree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/2/20 22:03
 */
public class ReConstructBinaryTree {

    /**
     * 题目：
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     */

    public static void main(String[] args) {
        int pre[] = {1,2,4,7,3,5,6,8};
        int in[] = {4,7,2,1,5,3,8,6};
        TreeNode root = reConstructBinaryTree(pre,in);
        levelOrderByStack(root);
    }

    /**
     * 思路：
     * 通过递归实现。
     * 前序遍历：1, 2, 4, 7, 3, 5, 6, 8
     *          ↑
     *        根节点
     * 中序遍历：4, 7, 2, 1, 5, 3, 8, 6
     *                   ↑
     *                 根节点
     * 在中序遍历中，被头结点分开的{4,7,2}和{5,3,8,6}分别是根节点1的左子树和右子树
     * 在以左子树为例
     * 前序遍历：2, 4, 7
     *          ↑
     *        根节点
     * 中序遍历：4, 7, 2
     *                ↑
     *              根节点
     * 有此可以看出以2为根的子树没有右子树。
     * 因此，通过前序遍历找到根节点，再通过中序遍历找到字数的左右子树，就能重新构建一颗二叉树。
     * 
     * @param pre 前序遍历数组
     * @param in 中序遍历数组
     * @return 头结点
     */
    private static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null){
            return null;
        }
        TreeNode root = null;
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]){
                root = new TreeNode(pre[0]);
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }
        return root;
    }


    public static void levelOrderByStack(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode curNode = root;
        queue.add(curNode);
        while (!queue.isEmpty()){
            curNode = queue.poll();
            if (curNode.val != null)
                System.out.print(curNode.val+" ");
            if (curNode.left!=null){
                queue.add(curNode.left);
            }
            if (curNode.right!=null){
                queue.add(curNode.right);
            }
        }
        System.out.println();
    }

}

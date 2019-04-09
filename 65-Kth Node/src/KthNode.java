import BinaryTree.TreeNode;
import BinaryTree.linked.MyBinaryTree;

import java.util.ArrayList;

/**
 * @Author: cuizhe
 * @Date: 2019/4/9 9:42
 */
public class KthNode {
    /**
     * 题目：
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。
     * 例如，（5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
     */

    /**
     * 思路：
     * 因为给定的是二叉搜索树，根据二叉搜索树的性质，中序遍历是按照从小到大的顺序。
     * 因此只要对其进行中序遍历就可以找到第 k 小的结点
     *
     * @param pRoot 二叉搜索树的根节点
     * @param k 查找的位置
     * @return 第 k 小的结点
     */
    public static TreeNode KthNode(TreeNode pRoot, int k){
        if (pRoot == null){
            return pRoot;
        }
        ArrayList<TreeNode> list = new ArrayList<>();// 存放中序遍历的结果

        //递归
        inOrder(pRoot,list);

        if(k > list.size()){
            return null;
        }
        return list.get(k-1);
    }

    private static void inOrder(TreeNode node,ArrayList<TreeNode> list){
        if (node != null){
            inOrder(node.left,list);
            list.add(node);
            inOrder(node.right,list);
        }
    }

    /**
     *         5
     *       /  \
     *      3    7
     *    /  \    \
     *   2    4    8
     *
     */
    public static void main(String[] args) {
        TreeNode node2 = new TreeNode(2,null,null);
        TreeNode node4 = new TreeNode(4,null,null);
        TreeNode node8 = new TreeNode(8,null,null);
        TreeNode node7 = new TreeNode(7,null,node8);
        TreeNode node3 = new TreeNode(3,node2,node4);
        TreeNode node5 = new TreeNode(5,node3,node7);
        MyBinaryTree tree = new MyBinaryTree();
        tree.setRoot(node5);
        tree.inOrderTraverse();
        System.out.println(KthNode(node5,3).val);
    }
}

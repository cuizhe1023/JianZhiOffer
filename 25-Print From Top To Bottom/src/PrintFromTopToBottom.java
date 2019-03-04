import BinaryTree.TreeNode;
import BinaryTree.array.ArrayBinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/4 18:48
 */
public class PrintFromTopToBottom {
    /**
     * 题目：
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     */

    /**
     * 思路：
     * 就是二叉树的层次遍历，可以通过队列来实现。
     * @param root
     * @return
     */
    public static ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root==null){
            return list;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            list.add(curNode.val);
            if (curNode.left!=null)
                queue.add(curNode.left);
            if (curNode.right!=null)
                queue.add(curNode.right);
        }
        return list;
    }

    public static void main(String[] args) {
        Integer[] tree = {};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree();
        arrayBinaryTree.createTree(tree);
        System.out.println(printFromTopToBottom(arrayBinaryTree.getRoot()).toString());
    }
}

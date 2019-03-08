import BinaryTree.TreeNode;
import BinaryTree.array.ArrayBinaryTree;

import java.util.ArrayList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/7 20:22
 */
public class ConvertBinaryTreeToList {

    /**
     * 题目：
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     */

    /**
     * 思路：
     *
     * @param pRootOfTree
     * @return
     */
    public static TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree==null || (pRootOfTree.left==null && pRootOfTree.right==null)){
            return pRootOfTree;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        inOrder(pRootOfTree,list);
        for (int i = 0; i < list.size(); i++) {
            if (i==0){
                list.get(i).right = list.get(i+1);
            }else if (i==list.size()-1){
                list.get(i).left = list.get(i-1);
            }else {
                list.get(i).left = list.get(i-1);
                list.get(i).right = list.get(i+1);
            }
        }
        return list.get(0);
    }

    public static void inOrder(TreeNode root, ArrayList<TreeNode> nodeList){
        if (root==null){
            return;
        }
        if (root.left!=null){
            inOrder(root.left,nodeList);
        }
        if (root!=null){
            nodeList.add(root);
        }
        if (root.right!=null){
            inOrder(root.right,nodeList);
        }
    }

    public static void main(String[] args) {
        Integer arr[] = {10,6,14,4,8,12,16};
        ArrayBinaryTree tree = new ArrayBinaryTree();
        tree.createTree(arr);
        TreeNode head = Convert(tree.getRoot());
        while (head!=null){
            System.out.print(head.val + " ");
            head = head.right;
        }
    }

}
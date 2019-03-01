package BinaryTree.array;

import BinaryTree.TreeNode;

import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/1 11:30
 */
public class ArrayBinaryTree {

    private TreeNode root;

    private LinkedList<TreeNode> list = null;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     *
     * @param array
     */
    public void createTree(Integer array[]) {
        list = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            TreeNode node = new TreeNode(array[i]);
            list.add(node);
        }
        for (int i = 0; i < array.length / 2 - 1; i++) {
            //左孩子
            if (list.get(i*2+1)!=null){
                list.get(i).left = list.get(i*2+1);
            }
            //右孩子
            if (list.get(i*2+2)!=null){
                list.get(i).right = list.get(i*2+2);
            }
        }
        int lastIndex = array.length/2-1;
        list.get(lastIndex).left =list.get(lastIndex*2+1);
        //右孩子,如果数组长度为奇数则存在右孩子,否则不存在右孩子
        if (array.length%2==1){
            list.get(lastIndex).right = list.get(lastIndex*2+2);
        }
        setRoot(list.get(0));
    }


    public void preOrderTraverse() {
        if (root!=null){
            preOrderTraverse(root);
            System.out.println();
        }
    }

    private void preOrderTraverse(TreeNode treeNode) {
        if (treeNode!=null){
            System.out.print(treeNode.val + " ");
            preOrderTraverse(treeNode.left);
            preOrderTraverse(treeNode.right);
        }
    }

    public void inOrderTraverse(){
        if (root!=null){
            inOrderTraverse(root);
            System.out.println();
        }
    }

    private void inOrderTraverse(TreeNode treeNode) {
        if (treeNode!=null){
            inOrderTraverse(treeNode.left);
            System.out.print(treeNode.val + " ");
            inOrderTraverse(treeNode.right);
        }
    }
}

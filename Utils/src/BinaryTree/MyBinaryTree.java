package BinaryTree;

/**
 * @Author: cuizhe
 * @Date: 2019/2/27 20:35
 */
public class MyBinaryTree {
    public TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
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
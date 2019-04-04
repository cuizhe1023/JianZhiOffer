package BinaryTree.linked;

import BinaryTree.TreeLinkNode;

/**
 * @Author: cuizhe
 * @Date: 2019/4/4 10:14
 */
public class LinkNodeTree {
    public TreeLinkNode root;

    public TreeLinkNode getRoot() {
        return root;
    }

    public void setRoot(TreeLinkNode root) {
        this.root = root;
    }

    public void preOrderTraverse() {
        if (root!=null){
            preOrderTraverse(root);
            System.out.println();
        }
    }

    private void preOrderTraverse(TreeLinkNode treeNode) {
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

    private void inOrderTraverse(TreeLinkNode treeNode) {
        if (treeNode!=null){
            inOrderTraverse(treeNode.left);
            System.out.print(treeNode.val + " ");
            inOrderTraverse(treeNode.right);
        }
    }

    /**
     *           1
     *       /     \
     *      2       3
     *    /  \    /  \
     *   4    5  6   7
     *      /  \
     *     8   9
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeLinkNode node1 = new TreeLinkNode(1);
        TreeLinkNode node2 = new TreeLinkNode(2);
        TreeLinkNode node3 = new TreeLinkNode(3);
        TreeLinkNode node4 = new TreeLinkNode(4);
        TreeLinkNode node5 = new TreeLinkNode(5);
        TreeLinkNode node6 = new TreeLinkNode(6);
        TreeLinkNode node7 = new TreeLinkNode(7);
        TreeLinkNode node8 = new TreeLinkNode(8);
        TreeLinkNode node9 = new TreeLinkNode(9);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node2.parent = node1;
        node3.left = node6;
        node3.right = node7;
        node3.parent = node1;
        node4.parent = node2;
        node5.left = node8;
        node5.right = node9;
        node5.parent = node2;
        node6.parent = node3;
        node7.parent = node3;
        node8.parent = node5;
        node9.parent = node5;

        LinkNodeTree binaryTree1 = new LinkNodeTree();
        binaryTree1.setRoot(node1);
        binaryTree1.inOrderTraverse();
    }
}

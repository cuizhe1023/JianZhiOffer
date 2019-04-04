import BinaryTree.TreeLinkNode;
import BinaryTree.linked.LinkNodeTree;

/**
 * @Author: cuizhe
 * @Date: 2019/4/4 9:52
 */
public class GetNext {
    /**
     * 题目：
     * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
     * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     */

    /**
     * 思路：
     * 一共有一下几种情况：
     * 1.二叉树为空，则返回 null
     * 2.结点的右孩子存在，则从该结点的右结点出发，一直沿着左结点直到找到叶子结点为止
     * 3.结点是叶子结点，如果该结点是父节点的左结点，则返回父节点。否则，返回父节点的父节点
     * @param pNode
     * @return
     */
    public static TreeLinkNode getNext(TreeLinkNode pNode){
        if (pNode == null){
            return pNode;
        }
        //结点有右孩子
        if (pNode.right != null){
            TreeLinkNode temp = pNode.right;
            while (temp.left != null){
                temp = temp.left;
            }
            return temp;
        }
        TreeLinkNode parent = pNode.parent; //父节点
        TreeLinkNode curNode = pNode; //当前结点
        while (parent != null) {
            //如果该结点是父节点的左孩子，那么父节点就是下一个结点，返回父节点
            if (parent.left == curNode) {
                return pNode.parent;
            }
            curNode = parent;
            parent = parent.parent;
        }

        return null;
    }

    /**
     *           1
     *       /     \
     *      2       3
     *    /  \    /  \
     *   4    5  6   7
     *         \
     *         8
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
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node2.parent = node1;
        node3.left = node6;
        node3.right = node7;
        node3.parent = node1;
        node4.parent = node2;
        node5.right = node8;
        node5.parent = node2;
        node6.parent = node3;
        node7.parent = node3;
        node8.parent = node5;

        LinkNodeTree binaryTree1 = new LinkNodeTree();
        binaryTree1.setRoot(node1);
        binaryTree1.inOrderTraverse();
        TreeLinkNode nextNode = getNext(node6);
        int nextValue = 0;
        if (nextNode == null){
            nextValue = -1;
        }else {
            nextValue = nextNode.val;
        }
        System.out.println("获取结点"+node6.val+"的下一个结点：\n" + nextValue);
    }
}

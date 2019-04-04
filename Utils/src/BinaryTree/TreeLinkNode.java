package BinaryTree;

/**
 * @Author: cuizhe
 * @Date: 2019/4/4 9:58
 */
public class TreeLinkNode {
    public int val;     //二叉树结点的值
    public TreeLinkNode left = null;//左结点
    public TreeLinkNode right = null;//右结点
    public TreeLinkNode parent = null;//父节点

    public TreeLinkNode(int val) {
        this.val = val;
    }
}

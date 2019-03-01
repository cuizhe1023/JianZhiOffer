package BinaryTree;

/**
 * @Author: cuizhe
 * @Date: 2019/2/27 20:33
 */
public class TreeNode {

    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(Integer val) {
        this.val = val;
    }

    public TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


}

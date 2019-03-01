import BinaryTree.array.ArrayBinaryTree;
import BinaryTree.linked.MyBinaryTree;
import BinaryTree.TreeNode;

/**
 * @Author: cuizhe
 * @Date: 2019/2/28 21:51
 */
public class HashSubtree {
    /**
     * 题目：
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     */

    /**
     * 思路：
     *
     * @param root1 二叉树
     * @param root2 需要判断的子树
     * @return 是否是子树
     */
    public static boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1!=null && root2!=null){
            if (root1.val==root2.val){
                result = doesTree1HasTree2(root1,root2);
            }
            if (!result){
                result = hasSubtree(root1.left,root2);
            }
            if (!result){
                result = hasSubtree(root1.right,root2);
            }
        }
        return result;
    }

    public static boolean doesTree1HasTree2(TreeNode root1,TreeNode root2){

        if (root2==null){
            return true;
        }
        if (root1==null){
            return false;
        }
        if (root1.val!=root2.val){
            return false;
        }
        return doesTree1HasTree2(root1.left,root2.left) && doesTree1HasTree2(root1.right,root2.right);
    }

    /**
     * 构建两棵树
     *           8                      8
     *       /     \                  |   \
     *      8       7                9     2
     *    |  \
     *   9    2
     *      |   \
     *     4     7
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node1_4 = new TreeNode(4,null,null);
        TreeNode node1_7_1 = new TreeNode(7,null,null);
        TreeNode node1_2 = new TreeNode(2,node1_4,node1_7_1);
        TreeNode node1_9 = new TreeNode(9,null,null);
        TreeNode node1_8 = new TreeNode(8,node1_9,node1_2);
        TreeNode node1_7_2 = new TreeNode(7,null,null);
        TreeNode node1_root = new TreeNode(8,node1_8,node1_7_2);
        MyBinaryTree binaryTree1 = new MyBinaryTree();
        binaryTree1.setRoot(node1_root);

        TreeNode node2_9 = new TreeNode(9,null,null);
        TreeNode node2_2 = new TreeNode(2,null,null);
        TreeNode node2_root = new TreeNode(8,node2_9,node2_2);
        MyBinaryTree binaryTree2 = new MyBinaryTree();
        binaryTree2.setRoot(node2_root);
        System.out.println(hasSubtree(binaryTree1.getRoot(),binaryTree2.getRoot()));

        Integer[] tree1 = {8,8,7,9,2,null,null,null,null,4,7};
        Integer[] tree2 = {8,9,2};

        ArrayBinaryTree arrayBinaryTree1 = new ArrayBinaryTree();
        arrayBinaryTree1.createTree(tree1);

        ArrayBinaryTree arrayBinaryTree2 = new ArrayBinaryTree();
        arrayBinaryTree2.createTree(tree2);
        System.out.println(hasSubtree(arrayBinaryTree1.getRoot(),arrayBinaryTree2.getRoot()));
    }
}

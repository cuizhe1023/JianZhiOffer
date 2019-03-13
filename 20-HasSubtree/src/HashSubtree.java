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
     * 先对根节点进行比较，如果根节点一样，比较左子树，再比较右子树。
     * 当比较的结点不一样时，将 root1 的左结点和 root2 的根节点比较
     * 以此类推。
     *
     * @param root1 二叉树
     * @param root2 需要判断的子树
     * @return 是否是子树
     */
    public static boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;

        //当 Tree1 和 Tree2 都不为空的时候，才进行比较。否则直接返回false
        if (root1!=null && root2!=null){
            //如果找到了对应于 Tree2 的根结点的点。
            if (root1.val==root2.val){
                //以这个根结点为起点，判断是否包含 Tree2.
                result = doesTree1HasTree2(root1,root2);
            }
            //如果没找到，那么用 root1 的左孩子当做起点，去判断是否包含 Tree2
            if (!result){
                result = hasSubtree(root1.left,root2);
            }
            //如果还没找到，那么用 root2 的左孩子当做起点，去判断是否包含 Tree2
            if (!result){
                result = hasSubtree(root1.right,root2);
            }
        }
        return result;
    }

    public static boolean doesTree1HasTree2(TreeNode root1,TreeNode root2){
        //如果 Tree2 已经遍历完了，还能对应上，则返回 true
        if (root2==null){
            return true;
        }
        //如果 Tree2 还没遍历完，但是 Tree1 已经遍历完了，则返回 false
        if (root1==null){
            return false;
        }
        //如果其中一个点没对上，则返回 false
        if (root1.val!=root2.val){
            return false;
        }
        //如果根节点对应上了，那么就分别判断子结点是否能对应上
        return doesTree1HasTree2(root1.left,root2.left) && doesTree1HasTree2(root1.right,root2.right);
    }

    /**
     * 构建两棵树
     *         8                   8
     *       /  \                /  \
     *      8   7               9    2
     *    /  \
     *   9    2
     *      /  \
     *     4     7
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

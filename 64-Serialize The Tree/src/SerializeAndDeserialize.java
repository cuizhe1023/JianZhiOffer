import BinaryTree.TreeNode;
import BinaryTree.linked.MyBinaryTree;

/**
 * @Author: cuizhe
 * @Date: 2019/4/8 9:58
 */
public class SerializeAndDeserialize {
    /**
     * 请实现两个函数，分别用来序列化和反序列化二叉。
     */

    /**
     * 思路：
     * 所谓序列化指的是遍历二叉树为字符串；
     * 所谓反序列化指的是依据字符串重新构造成二叉树。
     * 依据前序遍历序列来序列化二叉树，因为前序遍历序列是从根结点开始的。
     * 当在遍历二叉树时碰到空节点时，这些空节点被序列化为一个特殊的字符“#”。
     * 并且结点与结点之间根据","隔开
     */

    /**
     * 思路：
     * 通过递归来实现，用 StringBuffer 来保存字符串。
     * 如果 root 为空，StringBuffer 后面跟 "#,"
     * 如果不为空，StringBuffer 后面跟 "root.value,"
     * 然后递归左结点和右结点
     * @param root 结点
     * @return 序列化的二叉树
     */
    private static String serialize(TreeNode root) {
        StringBuffer str = new StringBuffer();
        if (root == null){
            str.append("#,");
            return str.toString();
        }
        str.append(root.val+",");
        str.append(serialize(root.left));
        str.append(serialize(root.right));
        return str.toString();
    }

    /**
     * 思路：
     * 将 str 通过 "," 进行分割，得到一个字符串数组，定义一个 index 来记录位置。
     * 如果一个结点有左结点，则在前序遍历中的位置在这个结点的后面。
     * 如果数组的 index 是 "#"，则说明该结点是 null 不做任何操作。
     * 如果不是 "#"，则说明该结点是有数字的，继续判断他的左右子树。
     *
     * @param str 序列化的二叉树
     * @return 头结点
     */
    static int index = -1; //记录数组下标
    private static TreeNode deserialize(String str) {
        index++;
        String[] strings = str.split(",");
        TreeNode node = null;
        if (!strings[index].equals("#")){
            node = new TreeNode(Integer.valueOf(strings[index]));
            node.left = deserialize(str);
            node.right = deserialize(str);
        }
        return node;
    }

    /**
     *     	     8
     *     	   /   \
     *       6      10
     *     /       /  \
     *    5       9   11
     *
     */
    public static void main(String[] args) {
        TreeNode node9 = new TreeNode(9,null,null);
        TreeNode node11 = new TreeNode(11,null,null);
        TreeNode node5 = new TreeNode(5,null,null);
        TreeNode node6 = new TreeNode(6,node5,null);
        TreeNode node10 = new TreeNode(10,node9,node11);
        TreeNode node8 = new TreeNode(8,node6,node10);
        MyBinaryTree tree = new MyBinaryTree();
        tree.setRoot(node8);
        tree.preOrderTraverse();
        String serialize = serialize(tree.getRoot());
        System.out.println("进行序列化：" + serialize);
        TreeNode head = deserialize(serialize);
        System.out.println("进行反序列化,头结点为：" + head.val);
        MyBinaryTree myBinaryTree = new MyBinaryTree();
        myBinaryTree.setRoot(head);
        System.out.print("进行前序遍历:");
       myBinaryTree.preOrderTraverse();
    }
}

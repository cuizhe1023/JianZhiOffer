import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/4/6 10:54
 */
public class PrintTreeInZigzag {
    /**
     * 题目：
     * 请实现一个函数按照之字形打印二叉树，
     * 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，
     * 其他行以此类推。
     */

    /**
     * 思路：
     * 和层次遍历的思路类似，不过需要判断奇偶层，
     * 奇数层正序输出，偶数层逆序输出
     * 那么问题就转换到，怎么判断是否需要逆序输出
     * 因为按照层次遍历，
     * 1.对于任意结点 curNode，先把该结点放入队列中
     * 2.从队中拿出结点，如果该结点的左右结点不为空，就把左右结点加入到队列中
     * 比如说，对于下图中的二叉树
     *     	     8
     *     	   /   \
     *       6      8
     *     /  \    /  \
     *    5    7  7    5
     * 我们遍历 8 这个结点，左右结点不为空，我们把 6、8 放入队列，
     * 但是在打印第二行时，我们应该先打印 8 再打印 6。
     * 这两个结点在容器中是先进后出的，因此可以通过栈实现。
     * 我们可以构建两个栈 oddStack、evenStack，oddStack 存奇数层，evenStack 存偶数层。
     * 设置一个数字 layer 用来保存二叉树的层数，初始化为 1.
     * 具体操作如下：
     * 1.先将根节点存入 oddStack 中
     * 2.判断层数，如果层数是奇数，将栈顶元素的左右结点压入 evenStack 中。
     *   如果层数是奇数，将栈顶元素的右左结点压入 oddStack 中。
     * 3.打印结点时，判断栈是否为空，为空 layer + 1
     *
     * @param pRoot 二叉树的根节点
     * @return
     */
    public static ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null){
            return result;
        }
        int layer = 1;
        TreeNode curNode = pRoot;
        LinkedList<TreeNode> oddStack = new LinkedList<>();
        oddStack.push(curNode);
        LinkedList<TreeNode> evenStack = new LinkedList<>();

        while (!oddStack.isEmpty() || !evenStack.isEmpty()){
            if (layer % 2 != 0){//奇数层
                ArrayList<Integer> list = new ArrayList<>();
                while (!oddStack.isEmpty()){
                    curNode = oddStack.pop();
                    list.add(curNode.val);
                    if (curNode.left != null){
                        evenStack.push(curNode.left);
                    }
                    if (curNode.right != null){
                        evenStack.push(curNode.right);
                    }
                }
                if (!list.isEmpty()){
                    layer++;
                    result.add(list);
                }
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                while (!evenStack.isEmpty()){
                    curNode = evenStack.pop();
                    list.add(curNode.val);
                    if (curNode.right != null){
                        oddStack.push(curNode.right);
                    }
                    if (curNode.left != null){
                        oddStack.push(curNode.left);
                    }
                }
                if (!list.isEmpty()){
                    layer++;
                    result.add(list);
                }
            }
        }
        return result;
    }

    /**
     *         1
     *       /  \
     *      2    3
     *    /  \    \
     *   4    5    6
     *       /
     *      7
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7,null,null);
        TreeNode node4 = new TreeNode(4,null,null);
        TreeNode node5 = new TreeNode(5,node7,null);
        TreeNode node6 = new TreeNode(6,null,null);
        TreeNode node2 = new TreeNode(2,node4,node5);
        TreeNode node3 = new TreeNode(3,null,node6);
        TreeNode node1 = new TreeNode(1,node2,node3);
        System.out.println(print(node1).toString());
    }
}

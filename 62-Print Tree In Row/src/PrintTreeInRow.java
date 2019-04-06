
import BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/4/6 9:13
 */
public class PrintTreeInRow {
    /**
     * 题目：
     * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     */

    /**
     * 思路：
     * 类似于二叉树的层次遍历，不过需要判断什么情况一层遍历完了
     * 二叉树的层次遍历需要通过队列来实现。因此这里也需要将要打印的结点保存在队列中。
     * 为了将每一行单独打印到一行里，我们需要两个变量：
     * 一个表示当前层中还没有打印的结点数 toBePrintedNumber；
     * 另一个变量表示下一层结点的数目 nextLevelNumber。
     * 具体操作如下：
     * 1.如果一个结点有子结点，则把每个子结点加入队列，同时 nextLevelNumber + 1。
     * 2.将 nextLevelNumber 赋值给 toBePrintedNumber。
     * 3.每打印一个结点 toBePrintedNumber - 1
     * 4.当 toBePrintedNumber == 0 时，表示当前层的结点已经打印完毕，则继续打印下一层
     *
     * @param pRoot 二叉树的根节点
     * @return
     */
    public static ArrayList<ArrayList<Integer>> print_1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null){
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode curNode = pRoot;
        queue.add(curNode);
        int nextLevelNumber = 0;
        int toBePrintedNumber = 1;
        while (!queue.isEmpty()){
            curNode = queue.poll();
            if (curNode.left != null){
                queue.add(curNode.left);
                nextLevelNumber++;
            }
            if (curNode.right != null){
                queue.add(curNode.right);
                nextLevelNumber++;
            }
            list.add(curNode.val);
            toBePrintedNumber--;
            if (toBePrintedNumber == 0){
                result.add(new ArrayList<>(list));
                toBePrintedNumber = nextLevelNumber;
                nextLevelNumber = 0;
                list.clear();
            }
        }
        return result;
    }


    /**
     * 思路：
     * 这是某位大佬的思路，用递归做。
     * 根据深度调整存的位置,利用递归的方法进行先序遍历，传递深度，
     * 递归深入一层扩容一层数组，先序遍历又保证了同层节点按从左到右入数组
     * 不过这样的技巧性太强，不容易想到。
     *
     * @param pRoot 二叉树的根节点
     * @return
     */
    public static ArrayList<ArrayList<Integer>> print_2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null){
            return  result;
        }
        print_2(pRoot,1,result);
        return result;
    }

    public static void print_2(TreeNode pRoot,int depth,ArrayList<ArrayList<Integer>> list) {
        if (pRoot == null){
            return;
        }
        if (depth > list.size()){
            list.add(new ArrayList<>());
        }
        list.get(depth - 1).add(pRoot.val);
        print_2(pRoot.left,depth + 1,list);
        print_2(pRoot.right,depth + 1,list);
    }

    /**
     *     	     8
     *     	   /   \
     *       6      6
     *     /  \    /  \
     *    5    7  7    5
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node5_1 = new TreeNode(5,null,null);
        TreeNode node7_1 = new TreeNode(7,null,null);
        TreeNode node5_2 = new TreeNode(5,null,null);
        TreeNode node7_2 = new TreeNode(7,null,null);
        TreeNode node6_1 = new TreeNode(6,node5_1,node7_1);
        TreeNode node6_2 = new TreeNode(6,node7_2,node5_2);
        TreeNode node_root = new TreeNode(8,node6_1,node6_2);
        System.out.println(print_2(node_root).toString());
    }

}

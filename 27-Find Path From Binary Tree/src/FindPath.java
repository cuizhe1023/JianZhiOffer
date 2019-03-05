import BinaryTree.TreeNode;
import BinaryTree.array.ArrayBinaryTree;

import java.util.ArrayList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/4 20:59
 */
public class FindPath {
    /**
     * 题目：
     * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * (注意: 在返回值的list中，数组长度大的数组靠前)
     */

    /**
     * 思路：
     * 先假设有一棵树：
     * 在给定一个整数 22
     *          10
     *         |  \
     *        5   12
     *       | \
     *      4  7
     * 二叉树中有两条路径的和是 22
     * 10,5,7  和  10,12
     * 因为路径是从根节点出发到叶子结点。也就是说路径总是以根节点为起点，因此我们首先需要遍历根节点。所以使用前序遍历。
     * 按照前序遍历，访问完根节点 10 后，就会访问左结点 5 。但是，当遍历到 5 的时候，我们不知道他的父亲是谁，也不知道前
     * 面遍历了那些结点。所以需要把前面的那些结点保存下来。每访问一个结点的时候，我们把当前结点添加到路径中去。
     * 到达结点 5 的时候，路径中包含 2 个点 10和5。接下来遍历到结点4，我们把 4 也添加到路径中。这个时候已经达到叶子结点了
     * 但路径上三个结点的值为 19 ，不等于 22。因此这条路径不符合要求。
     * 接下来需要遍历别的路径，在遍历下一个结点之前，我们需要回到 5 这个结点。再去遍历 5 的右子结点 7。我们需要把 4 从路径中删除。
     * 在访问 7 的时候，把该结点添加到路径中。此时路径中三个结点 10,5,7 的和是 22 ，是一条符合条件的路径。
     * 再去访问别的路径，以此类推。
     * 每当我们从子结点回到父节点时，都要在路径上删除子结点。
     *
     * 当用前序遍历的方式访问某一个结点的时候，我们把该结点添加到路径上，并累加该结点的值。
     * 如果该结点不是叶子结点，则继续访问其子结点。
     * 如果该结点是叶子结点，并且路径中的节点值的和等于输入的整数。则当前的路径符合要求。打印出来。
     * 如果该结点是叶子结点，但是路径中的节点值的和不等于输入的整数。则当前结点访问结束后，回到父结点。
     * 因此，我们需要在函数退出之前在路径上删除当前结点并且减去当前结点的值，以确保返回父结点时路径刚好是从根节点到父结点的路径。
     * 所以，不难看出。这个路径是用栈保存的。
     *
     * @param root 二叉树的根
     * @param target 整数
     * @return 满足整数的所有路径
     */
    static ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        if (root==null){
            return null;
        }
        ArrayList<Integer> path = new ArrayList<>();
        findPath(root,target,0,path);
        return resultList;
    }

    private static void findPath(TreeNode root, int target, int curSum, ArrayList<Integer> path) {
        if (root==null){
            return ;
        }
        curSum = curSum + root.val;
        path.add(root.val);
        boolean isLeaf = root.left==null && root.right==null;
        if (curSum == target &&isLeaf){
            resultList.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return ;
        }
        findPath(root.left,target,curSum,path);
        findPath(root.right,target,curSum,path);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        Integer[] tree = {10,5,12,4,7};
        ArrayBinaryTree binaryTree = new ArrayBinaryTree();
        binaryTree.createTree(tree);
        ArrayList<ArrayList<Integer>> lists = findPath(binaryTree.getRoot(),22);
        System.out.println(lists.toString());
    }
}

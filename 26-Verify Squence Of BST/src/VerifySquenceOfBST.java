import java.util.Arrays;

/**
 * @Author: cuizhe
 * @Date: 2019/3/4 19:21
 */
public class VerifySquenceOfBST {
    /**
     * 题目：
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     */

    /**
     * 思路：
     * 根据二叉搜索树后序遍历的特点，数组最后一个位置肯定是根节点的值。
     * 数组中前面的数组可以分为两部分：第一部分是根节点的左子树，他们都是比根节点的值小；第二部分是根节点的右子树，他们都比根节点的值大。
     * 我们可以根据数组大小判断出树的左右子树。
     * 例如：
     * {5,7,6,9,11,10,8}，可以得出这颗二叉树的根节点的值是 8。
     * 然后判断出第一个比 8 大的数，则之前的都是左子树，之后的都是右子树。
     * {5,7,6}是左子树 {9,11,10}是右子树。
     * 然后可以继续判断 在左子树中，6 是左子树的根节点的值。则5,7分别是他们的左结点和右结点的值。
     * 同理，在右子树中，10是右子树的根节点的值。则9,11分别是他们的左结点和右结点的值。
     * 再以{7,4,6,5}为例，可以得出这棵树的根节点的值是 5.
     * 第一个比他大的数是 7 ，则这颗树没有左子树，{7,4,6}是他的右子树。
     * 在{7,4,6}中，6是根节点，第一个比他大的数是 7 ，则{7,4,6}这颗树没有左子树。{7,4}是他的右子树。但是 4<6 不满足二叉搜索树的定义。
     * 因此{7,4,6,5}这棵树不是二叉搜索树的后序遍历。
     * 则，这个可以通过递归实现。
     *
     * @param sequence 数组
     * @return 是否是二叉搜索树的后序遍历的结果
     */
    public static boolean verifySquenceOfBST(int[] sequence) {
        if (sequence==null || sequence.length<=0){
            return false;
        }
        int root = sequence[sequence.length-1];
        //在二叉搜索树中搜索左子树
        int leftIndex = 0;
        for (; leftIndex < sequence.length - 1; leftIndex++) {
            if (root<sequence[leftIndex]){//如果某个位置的值大于 root ，则说明是其右子树
                break;
            }
        }
        int rightIndex = leftIndex;
        for (; rightIndex < sequence.length - 1; rightIndex++) {
            if (root>sequence[rightIndex]){
                return false;
            }
        }
        boolean left = true;
        if (leftIndex>0){
            left = verifySquenceOfBST(Arrays.copyOfRange(sequence,0,leftIndex));
        }
        boolean right = true;
        if (rightIndex<sequence.length-1){
            right = verifySquenceOfBST(Arrays.copyOfRange(sequence,leftIndex,sequence.length-1));
        }
        return left & right;
    }

    public static void main(String[] args) {
        int[] tree1 = {5,7,6,9,11,10,8};
        System.out.println(verifySquenceOfBST(tree1));
        int[] tree2 = {7,4,6,5};
        System.out.println(verifySquenceOfBST(tree2));
    }

}

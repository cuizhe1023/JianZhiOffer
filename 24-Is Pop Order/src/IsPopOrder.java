import java.util.Stack;

/**
 * @Author: cuizhe
 * @Date: 2019/3/4 15:40
 */
public class IsPopOrder {
    /**
     * 题目：
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     * （注意：这两个序列的长度是相等的）
     */

    /**
     * 思路：
     * 最直观的想法就是建立一个辅助栈，把输入的第一个序列中的数组依次压入该辅助栈，并按照第二个序列的顺序依次出栈。
     * 设置一个指针，计算第二个序列的长度，如果出栈的值与指针所指的指相同。则确定出栈了。
     * 举个例子：
     * pushA是 1,2,3,4,5 ，popA是 4,5,3,2,1
     * 指针 index = 0；
     * 因为出栈顺序第一个是 4，因此我们先让 1,2,3,4 入栈
     * 栈：      1  2  3  4        |
     * popA：    4  5  3  2  1
     *          ↑
     *         index
     * 入栈后，将 4 弹出，并将 popA 的指针向后移。
     * 栈：      1  2  3           |
     * popA：    4  5  3  2  1
     *             ↑
     *           index
     * 接下来弹出栈的顺序是 5 ，因为栈顶元素不是 5，因此我们让 5 入栈
     * 栈：      1  2  3  5        |
     * popA：    4  5  3  2  1
     *             ↑
     *           index
     * 在让 5 出栈，将 popA 的指针向后移。
     * 栈：      1  2  3           |
     * popA：    4  5  3  2  1
     *                ↑
     *              index
     * 需要弹出栈的是 3，栈顶元素是 3 直接弹出,将 popA 的指针向后移。
     * 栈：      1  2              |
     * popA：    4  5  3  2  1
     *                   ↑
     *                 index
     * 需要弹出栈的是 2，栈顶元素是 2 直接弹出,将 popA 的指针向后移。
     * 栈：      1                 |
     * popA：    4  5  3  2  1
     *                      ↑
     *                    index
     * 需要弹出栈的是 1，栈顶元素是 1 直接弹出。
     * 栈：                        |
     * popA：    4  5  3  2  1
     *                      ↑
     *                    index
     *
     * 再举个错误的例子：
     * pushA是 1,2,3,4,5 ，popA是 4,3,5,1,2
     * 因为出栈顺序第一个是 4，因此我们先让 1,2,3,4 入栈
     * 栈：      1  2  3  4        |
     * popA：    4  3  5  1  2
     *          ↑
     *        index
     * 入栈后，将 4 弹出，将 popA 的指针向后移。
     * 栈：      1  2  3           |
     * popA：    4  3  5  1  2
     *             ↑
     *           index
     * 需要弹出栈的是 3，栈顶元素是 3 直接弹出，将 popA 的指针向后移。
     * 栈：      1  2              |
     * popA：    4  3  5  1  2
     *                ↑
     *              index
     * 接下来弹出栈的顺序是 5 ，因为栈顶元素不是 5，因此我们让 5 入栈
     * 栈：      1  2  5           |
     * popA：    4  3  5  1  2
     *                ↑
     *              index
     * 在让 5 出栈，压入辅助栈中。
     * 栈：      1  2              |
     * popA：    4  3  5  1  2
     *                   ↑
     *                 index
     * 需要弹出栈的顺序是 1 ，因为栈顶元素不是 1，因此我们需要让 1 入栈。
     * 但是此时入栈序列的数字都已经入栈。因此操作无法继续。popA 不是 pushA 的弹出顺序。
     *
     * @param pushA 压入栈的顺序
     * @param popA 弹出栈的顺序
     * @return 判断能否以 popA 出栈
     */
    public static boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA==null || popA==null || pushA.length!=popA.length){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int index = 1;
        stack.push(pushA[0]);
        for (int i = 0; i < popA.length; i++) {
            while (index<pushA.length && stack.peek()!=popA[i]){
                stack.push(pushA[index]);
                index++;
            }
            if (index>=pushA.length && stack.peek()!=popA[i]){
                return false;
            }
            stack.pop();
        }
        return true;
    }

    public static void main(String[] args) {
        int[] push = {1,2,3,4,5};
        int[] pop = {5,4,3,2,1};
        System.out.println(IsPopOrder(push,pop));
    }
}

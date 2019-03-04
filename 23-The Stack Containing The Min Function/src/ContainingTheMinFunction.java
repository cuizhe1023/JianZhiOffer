import java.util.Stack;

/**
 * @Author: cuizhe
 * @Date: 2019/3/3 21:09
 */
public class ContainingTheMinFunction {
    /**
     * 题目：
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     */

    /**
     * 思路：
     * 一开始想的是添加一个成员变量保存最小的元素。
     * 每次压入一个新元素进栈的时候，如果该元素比当前最小的元素还要小，则更新最小元素。
     * 但是，如果找个最小的元素被弹出后，如何找到下一个最小的元素呢？
     * 因此在压入这个最小元素之前，我们应该把次小元素保存起来。
     *
     * 这里我们引入一个辅助栈来保存。这个辅助栈的作用是用来保存每次入栈时栈中的最小元素。
     * 举个例子：
     * 我们依次往栈中压入 4,2,5,3,1,7
     * 首先往栈中压入 4，显然，现在 4 也是最小值，因此我们也往辅助栈中压入数组 4.
     * 栈：      4                 |
     * 辅助栈：  4                 |
     * 接着压入 2，2比4小，因此我们也把 2 压入辅助栈中。
     * 栈：      4  2              |
     * 辅助栈：  4  2              |
     * 压入 5 的时候，我们发现 5 比 2 大。因此我们仍然往辅助栈中压入 2.
     * 栈：      4  2  5           |
     * 辅助栈：  4  2  2           |
     * 压入 3 时，我们发现 3 比 2 大，因此我们依旧往辅助栈中压入 2。
     * 栈：      4  2  5  3        |
     * 辅助栈：  4  2  2  2        |
     * 压入 1 时，我们发现 1 比 2 小，因此我们往辅助栈中压入 1。
     * 栈：      4  2  5  3  1     |
     * 辅助栈：  4  2  2  2  1     |
     * 压入 3 时，我们发现 3 比 2 大，因此我们依旧往辅助栈中压入 2。
     * 栈：      4  2  5  3  1  7  |
     * 辅助栈：  4  2  2  2  1  1  |
     * 在需要弹出时，我们将两个栈顶元素同时弹出。
     */

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    public void push(int node) {
        stack.push(node);
        if (minStack.size()==0 || node<=minStack.peek()){
            minStack.push(node);
        }else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    public void print(){
        System.out.println(stack.toString());
        System.out.println(minStack.toString());
    }

    public static void main(String[] args) {
        ContainingTheMinFunction stack = new ContainingTheMinFunction();
        stack.push(4);
        stack.push(2);
        stack.push(5);
        stack.push(3);
        stack.push(1);
        stack.push(7);
        stack.print();
        System.out.print(stack.top()+" ");
        System.out.println(stack.min()+" ");
        stack.pop();
        stack.print();
        System.out.print(stack.top()+" ");
        System.out.println(stack.min()+" ");
        stack.pop();
        System.out.print(stack.top()+" ");
        System.out.println(stack.min()+" ");
    }
}

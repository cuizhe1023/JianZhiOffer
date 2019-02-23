import java.util.Stack;

/**
 * @Author: cuizhe
 * @Date: 2019/2/23 15:39
 */
public class QueuesWithTwoStacks {
    /**
     * 题目：
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 思路：
     * 需要入队的时候，把值 push 进 stack1 中。
     * 需要出队时间，会有以下几种情况，
     * 1.stack2 是空栈，此时先将 stack1 里的值依次 pop() 并 push 进 stack2 中
     * 2.stack2 中有元素，只用将 stack2 中的元素出栈即可，直到 stack2 为空。
     */

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        QueuesWithTwoStacks queues = new QueuesWithTwoStacks();
        queues.push(1);
        queues.push(2);
        queues.push(3);
        System.out.println(queues.pop());
        System.out.println(queues.pop());
        queues.push(4);
        System.out.println(queues.pop());
    }

}

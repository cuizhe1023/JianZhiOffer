import java.util.Stack;

/**
 * @Author: cuizhe
 * @Date: 2019/2/23 15:39
 */
public class QueuesWithTwoStacks {
    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

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

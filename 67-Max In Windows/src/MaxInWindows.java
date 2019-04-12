import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/4/12 9:27
 * @Description:
 */
public class MaxInWindows {
    /**
     * 题目：
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
     * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
     * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
     * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     */

    /**
     * 思路：
     * 蛮力法
     * 窗口每次滑动，判断窗口里的最大值，存入数组中。
     *
     * @param num 整数数组
     * @param size 滑动窗口的大小
     * @return 所有滑动窗口里数值的最大值
     */
    public static ArrayList<Integer> maxInWindows_1(int [] num, int size){
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || size < 1){
            return list;
        }
        for (int i = 0; i < num.length - size + 1; i++) {
            int max = maxNumber(i,i+size-1,num);
            list.add(max);
        }
        return list;
    }

    private static int maxNumber(int start, int end, int[] num) {
        int max = num[start];
        for (int i = start; i <= end; i++) {
            System.out.print(num[i]+" ");
            if (max < num[i]){
                max = num[i];
            }
        }
        System.out.println();
        return max;
    }

    /**
     * 思路：
     * 一个滑动窗口可以看成一个队列。
     * 根据以下规则进行入队和出队操作:
     * 1.如果队列为空，则当前数字入队
     * 2.如果当前数字大于队尾数字，则删除队尾数字，直到当前数字小于等于队尾数字，或者队列为空，然后当前数字入队
     * 3.如果当前数字小于队尾数字，则直到入队
     * 4.如果队列头超出滑动窗口范围，则删除队列头
     *
     * @param num 整数数组
     * @param size 滑动窗口的大小
     * @return 所有滑动窗口里数值的最大值
     */
    public static ArrayList<Integer> maxInWindows_2(int [] num, int size){
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || size > num.length || size < 1){
            return list;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            if (!queue.isEmpty()){
                System.out.println(queue.peek());
                if (i >= queue.peek() + size){
                    queue.pop();
                }
                while (!queue.isEmpty() && num[i] >= num[queue.getLast()]){
                    queue.removeLast();
                }
            }
            queue.offer(i);

            if (i + 1 >= size){
                list.add(num[queue.peek()]);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int [] a = {2,3,4,2,6,2,5,1};
        System.out.println(maxInWindows_2(a,3).toString());
    }
}

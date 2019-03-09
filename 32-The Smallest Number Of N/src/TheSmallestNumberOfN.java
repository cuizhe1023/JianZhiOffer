import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: cuizhe
 * @Date: 2019/3/9 16:07
 */
public class TheSmallestNumberOfN {
    /**
     * 题目：
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     */

    /**
     * 思路：
     * 首先想到的，对数组进行排序，然后取出前 n 个。
     * 因为要排序，所以时间复杂度比较大。
     *
     * @param input 数组
     * @param n n 个数
     * @return 最小的n个数
     */
    public static ArrayList<Integer> getLeastNumbers_1(int [] input, int n) {
        ArrayList<Integer> list = new ArrayList();
        if (input==null || input.length<=0){
            return list;
        }
        if (n>input.length){
            System.out.println("n > 数组的长度");
        }
        Arrays.sort(input);
        for (int i = 0; i < n; i++) {
            list.add(input[i]);
        }
        return list;
    }

    /**
     * 思路：
     * 可以维护一个大小为 n 的容器，如果容器中已有的数字少于 n 个时，直接把这个数组放入容器中。
     * 如果容器中的数字已经满了，则将这个数字和容器中最大的数字进行比较。如果待插入的数字比容器中最大的数字小，则替换。
     * 因此，当容器满了，我们需要做三件事：
     * 1.在 n 个数中找到最大的数
     * 2.可以在这个容器中删除最大的数
     * 3.可以在容器中插入一个新的数。
     *
     * 使用一个大小为 n 的最大堆，堆里面最大的数是堆顶。
     * 然后每次比较堆顶的数和数组中的数，
     * 如果堆顶的数比数组中的数A大，那么就把堆顶的数弹出来，把数组中的数A进堆，这样子到最后堆里面的堆顶始终是比外面的数小，
     * 而堆里的其他数是小于堆顶的数（最大堆的性质），所以堆中的数就是最小的k个数。
     * PriorityQueue 保存队列元素的顺序并不是按加入队列的顺序，而是按队列元素的大小进行重新排序
     *
     * @param input 数组
     * @param n n 个数
     * @return 最小的n个数
     */
    public static ArrayList<Integer> getLeastNumbers_2(int [] input, int n) {
        ArrayList<Integer> list = new ArrayList();
        if (input==null || input.length <= 0){
            return list;
        }
        if (n > input.length){
            System.out.println("n > 数组长度");
            return list;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i :
                input) {
            if (maxHeap.size() != n) {
                maxHeap.add(i);
            }else if (maxHeap.peek()>i){
                maxHeap.poll();
                maxHeap.add(i);
            }
        }
        while (!maxHeap.isEmpty()){
            list.add(maxHeap.poll());
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {4,5,1,6,2,7,3,8};
        System.out.println(getLeastNumbers_2(arr,4).toString());
    }
}

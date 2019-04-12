import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: cuizhe
 * @Date: 2019/4/10 10:41
 * @Description:
 */
public class GetMedian {
    /**
     * 题目：
     * 如何得到一个数据流中的中位数？
     * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
     * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
     * 我们使用 Insert() 方法读取数据流，使用 GetMedian() 方法获取当前读取数据的中位数。
     */

    //小顶堆
    private static PriorityQueue<Integer> minHeap = new PriorityQueue();
    //大顶堆
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue(15, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    // 记录数据总数
    static int  count = 0;

    /**
     * 思路：
     * 需要不断去得到数据源写入的数据，因此可以通过构建 AVL 树进行中位数的获取。
     * 不过 AVL树得自己去实现，因此我们还是想想别的方法，
     * 如果一个容器里存放了我们想要的数据，那么中位数可以由 p1 和 p2 两个指向的数得到
     * 如果容器是奇数个，那么 p1,p2 会指向同一个数。
     * a1,a2,a3...an,an+1,...,a2n,a2n+1
     *                ↑ ↑
     *               p1 p2
     * 如果容器是偶数个，那么 p1,p2 会分别指向两个数，中位数就是这两个数的平均值。
     * a1,a2,a3...an,an+1,...,a2n-1,a2n
     *             ↑   ↑
     *             p1  p2
     * 我们发现，整个数据容器被分割成两部分，p1 左边的都比 p1 小，p2 右边的都比 p2 大。
     * 即使左右两边没有排序，也可以根据左边的最大值(p1)和右边的最小值(p2)得到中位数。
     * 所以，我们可以通过最大堆存储左边的数据，通过最小堆存储右边的数据。
     * 接下来考虑一些细节：
     * 1.首先保证数据平均分配到两个堆中。为了实现平均分配，我们可以交替插入最大堆，最小堆
     * 如：第一次插入最小堆，第二次插入最大堆，第三次插入最小堆，第四次插入最大堆
     * 我们可以定义 count 存储数据的总数，当 count 是偶数时，插入最小堆，否则插入最大堆。
     *
     * 2.还要保证最大堆的所有数据都小于最小堆中的数据。当数据的总数是偶数时，会将新插入的数
     * 分配给最小堆。如果此时这个新的数据比最大堆中的一些数据要小时，那该怎么办呢？
     * 我们可以把这个数插入最大堆中，接着把最大堆中的最大的数字拿出来插入最小堆。
     * 由于最终插入最小堆的数字是最大堆中最大的数字，这样就保证了最大堆的所有数据都小于最小堆中的数据。
     *
     * @param num 不断写入数据流的值
     */
    public static void Insert(Integer num) {

        System.out.println("num:" + num + ",count:"+count+",count%2="+count%2);
        // 个数为偶数的话，先插入最大堆，然后将最大堆的堆顶插入最小堆。
        if (count % 2 == 0){
            maxHeap.offer(num);
            int max = maxHeap.poll();
            minHeap.offer(max);
        }else {
            // 个数为奇数的话，先插入最小堆，然后将最小堆的堆顶插入最大堆
            minHeap.offer(num);
            int min = minHeap.poll();
            maxHeap.offer(min);
        }
        count++;
    }

    /**
     * 思路：
     * 取得最大堆和最小堆堆顶的元素，
     * 当前为偶数个，则取小顶堆和大顶堆的堆顶元素求平均
     * 当前为奇数个，则从最小堆取元素即可。
     * @return 中位数
     */
    public static Double GetMedian() {
        // 如果个数为偶数
        if (count % 2 == 0){
            return new Double((double)(maxHeap.peek() + minHeap.peek())/2);
        }else {
            return Double.valueOf(minHeap.peek());
        }
    }

    public static void main(String[] args) {
        int[] a = {5,2,3,4,1,6,7,0,8};
        for (int i = 0; i < a.length; i++) {
            Insert(a[i]);
            System.out.print(GetMedian());
        }
    }

}
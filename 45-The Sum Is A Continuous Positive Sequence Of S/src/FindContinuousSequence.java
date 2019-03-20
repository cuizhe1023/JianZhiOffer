import java.util.ArrayList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/20 10:42
 */
public class FindContinuousSequence {
    /**
     * 题目：
     * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
     * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
     * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
     * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
     *
     * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
     */

    /**
     * 思路：
     * 可以通过双指针来找，双指针相当于一个窗口，窗口的左右两边就是两个指针。
     * 我们可以根据窗口内值的和来确定窗口的大小,也就是说这两个指针分别代表序列的最大值和最小值。
     * 维持最小变量 small, 最大变量 big。
     * 如果 small 到 big 的和大于 sum：
     *      我们可以从序列中去掉较小的值，也就是增大 small 的值。
     * 如果 small 到 big 的和小于 sum：
     *      我们可以增大 big ，让序列包含更多的数字。
     * 因为这个序列最少要有两个数字，我们一直增加 small 到（1+sum）/2 为止
     *
     * 例如输入 15 (1 + 15)/2 = 8;
     * 我们先设置两个指针 small = 1，big = 2；表示指针的位置
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *    ↑  ↑
     * small big
     * 1 + 2 = 3 < 15,因此我们增加 big,big++;
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *    ↑     ↑
     * small   big
     * 1 + 2 + 3 = 6 < 15,big++；
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *    ↑        ↑
     * small      big
     * 1 + 2 + 3 + 4 = 10 < 15,big++；
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *    ↑           ↑
     * small         big
     * 1 + 2 + 3 + 4 + 5 = 15 = 15,记录之后，增加 small 的值；
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *       ↑        ↑
     *     small     big
     * 2 + 3 + 4 + 5 = 14 < 15,big++；
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *       ↑           ↑
     *     small        big
     * 2 + 3 + 4 + 5 + 6 = 20 > 15,small++；
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *          ↑        ↑
     *        small     big
     * 3 + 4 + 5 + 6 = 18 > 15,small++；
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *             ↑     ↑
     *           small  big
     * 4 + 5 + 6 = 15 = 15,small++；
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *                ↑  ↑
     *             small big
     * 5 + 6 = 11 < 15, big++;
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *                ↑     ↑
     *             small   big
     * 5 + 6 + 7 = 18 > 15,small++;
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *                   ↑  ↑
     *                small big
     * 6 + 7 = 13 < 15,big++;
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *                   ↑     ↑
     *                small   big
     * 6 + 7 + 8= 21 > 15,small++;
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *                      ↑  ↑
     *                   small big
     * 7 + 8 = 15 = 15,small++;
     *    1  2  3  4  5  6  7  8  9  10  11  12  13  14  15
     *                         ↑
     *                         ↑
     *                       small
     *                        big
     * 此时，small 和 big 指向同一个数字，且该数字为（1+sum）/2
     * 要是在往后，则之后的数都比 15 大，
     * 所以和为 15 的连续正数序列有 [1,2,3,4,5],[4,5,6],[7,8]
     *
     *
     * @param sum 要寻找的和
     * @return 所有和为 sum 的连续正数序列
     */
    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();

        int small = 1;
        int big = 2;
        while (small <= (1+sum)/2){
            //求和公式
            int curSum = (small + big)*(big - small + 1)/2;
            if (curSum == sum){
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = small; i <= big; i++) {
                    list.add(i);
                }
                resultList.add(list);
                small++;
            }else if (curSum < sum){
                big++;
            }else {
                small++;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(findContinuousSequence(15).toString());
    }

}

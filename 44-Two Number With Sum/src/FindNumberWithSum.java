import java.util.ArrayList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/20 10:27
 */
public class FindNumberWithSum {
    /**
     * 题目：
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s，
     * 如果有多对数字的和等于S，输出两个数的乘积最小的。
     */

    /**
     * 思路：
     * 利用排序数组的规律，定义两个指针，分别指向数组的首尾。
     * 如果两个指针所指的数字大于 sum，则尾指针 -1；如果小于 sum，则首指针 +1；
     * 如果等于 sum，则输出。如果两个指针相遇，仍找不到等于s的两个数，则说明数组中不存在和为s的两个数字。
     * 两个数相差越大乘积越小，又因为是从两边找的，因此只需要找到最开始和为 sum 的两个数就可以了
     * 如果是乘积最大的情况就是一直找到两个指针重合
     *
     * @param array 递增的数组
     * @param sum 和为 sum
     * @return 和为 sum 的两个数
     */
    public static ArrayList<Integer> findNumberWithSum(int [] array, int sum) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if (array == null || array.length <= 0){
            return resultList;
        }
        int start = 0;
        int end = array.length - 1;
        while (start<=end){
            if (array[start] + array[end] == sum){
                resultList.add(array[start]);
                resultList.add(array[end]);
                return resultList;
            }else if (array[start] + array[end] > sum){
                end--;
            }else if (array[start] + array[end] < sum){
                start++;
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8,9};
        System.out.println(findNumberWithSum(a,6).toString());
    }
}

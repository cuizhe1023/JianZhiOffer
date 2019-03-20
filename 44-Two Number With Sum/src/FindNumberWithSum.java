/**
 * @Author: cuizhe
 * @Date: 2019/3/20 10:27
 */
public class FindNumberWithSum {
    /**
     * 题目：
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s，
     * 如果有多对数字的和等于s，输出任意一对即可。
     */

    /**
     * 思路：
     * 进行遍历
     *
     * @param arr 递增的数组
     * @param sum 和为 sum
     * @param num1 保存第一个数
     * @param num2 保存第二个数
     */
    public static void findNumberWithSum_1(int[] arr,int sum,int[] num1,int[] num2){
        if (arr == null || arr.length <= 0){
            return ;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i]+arr[j]==sum){
                    num1[0] = arr[i];
                    num2[0] = arr[j];
                }
            }
        }
    }

    /**
     * 思路：
     * 利用排序数组的规律，定义两个指针，分别指向数组的首尾。
     * 如果两个指针所指的数字大于 sum，则尾指针 -1；如果小于 sum，则首指针 +1；
     * 如果等于 sum，则输出。如果两个指针相遇，仍找不到等于s的两个数，则说明数组中不存在和为s的两个数字。
     *
     * @param arr 递增的数组
     * @param sum 和为 sum
     * @param num1 保存第一个数
     * @param num2 保存第二个数
     */
    public static void findNumberWithSum_2(int[] arr,int sum,int[] num1,int[] num2){
        if (arr == null || arr.length <= 0){
            return ;
        }
        int first = 0;
        int end = arr.length - 1;
        while (first<=end){
            if (arr[first] + arr[end] == sum){
                num1[0] = arr[first];
                num2[0] = arr[end];
                return ;
            }else if (arr[first] + arr[end] > sum){
                end--;
            }else if (arr[first] + arr[end] < sum){
                first++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8,9};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        findNumberWithSum_2(a,6,num1,num2);
        System.out.println(num1[0] + " " + num2[0]);
    }
}

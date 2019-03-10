/**
 * @Author: cuizhe
 * @Date: 2019/3/9 21:44
 */
public class FindGreatestSumOfSubArray {
    /**
     * 题目：
     * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
     * 今天测试组开完会后,他又发话了:
     * 在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
     * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
     * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
     * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
     */

    /**
     * 思路：
     * 对于一个数 A，若是 A 的左边累计数非负，那么加上 A 能使得这个值不小于 A，则认为累计值最大；
     * 若加上 A 则这个值还小于 A ，也就是说从之前开始的子数组的和会小于从 A 开始的子数组的和，
     * 因此不考虑之前的，直接从 A 开始，重新计算。
     *
     * 也需要使用一个变量来保存最大值，若在某一步发现 A < 最大值1，但是 最大值1+A < 最大值1，则需要记录下最大值1，
     * 因为最大值1可能是目前最大的子数组的和。需要再与之后的最大值进行比较。
     *
     * @param array 数组
     * @return 子串的最大和
     */
    public static int findGreatestSumOfSubArray(int[] array) {
        if(array.length<=0 || array==null){
            return -1;
        }
        int max = array[0];
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (sum+array[i]>=array[i]){
                sum = sum + array[i];
            }else {
                sum = array[i];
            }
            if (sum>max){
                max = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {6,-3,-2,7,-15,1,2,2};
        System.out.println(findGreatestSumOfSubArray(a));
    }
}

/**
 * @Author: cuizhe
 * @Date: 2019/3/14 9:37
 */
public class InversePairsInArray {
    /**
     * 题目：
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
     *
     * 输入描述：
     * 题目保证输入的数组中没有的相同的数字
     * 数据范围：
     * 	对于%50的数据,size<=10^4
     * 	对于%75的数据,size<=10^5
     * 	对于%100的数据,size<=2*10^5
     *
     * 示例：
     * 输入：1,2,3,4,5,6,7,0
     * 输出：7
     */

    /**
     * 思路：
     * 前面的大于后面的，可以通过遍历来实现算法复杂度为 o(n²)；
     *
     * @param array 待计算数组
     * @return 逆序对数
     */
    public static int inversePairs_1(int [] array) {
        if (array==null || array.length<=0){
            return -1;
        }
        int count = 0;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i]>array[j]){
                    count++;
                }
            }
        }
        return count%1000000007;
    }

    /**
     * 思路：
     * 分治思想，采用归并排序处理。[7,5,6,4]分解成两个长度为2的子数组。
     * [7,5],[6,4],把长度为2的数组分解成两个长度为1的子数组[7],[5],[6],[4]。
     * 一边合并相邻的子数组，一边统计逆序对的数目。
     * 逆序对的总数 = 左边数组中的逆序对的数量 + 右边数组中逆序对的数量 + 左右结合成新的顺序数组时中出现的逆序对的数量；
     * [在牛客网上，需要进行取模运算]
     *
     * @param array 待计算数组
     * @return 逆序对数
     */
    public static int inversePairs_2(int [] array) {
        if (array == null || array.length<=1){
            return 0;
        }
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        return mergeSort(array,copy,0,array.length-1);
    }

    private static int mergeSort(int[] array, int[] copy,int start, int end) {
        if(start==end){
            copy[start] = array[start];
            return 0;
        }
        int mid = (start+end)>>1;
        int leftCount = mergeSort(copy, array, start, mid);
        int rightCount = mergeSort(copy, array, mid+1, end);

        int i = mid; //i初始化为前半段最后一个数字的下标
        int j = end; //j初始化为后半段最后一个数字的下标
        int index = end; //辅助数组复制的数组的最后一个数字的下标
        int count = 0; //计数--逆序对的数目

        while(i >= start && j >= mid + 1){
            if(array[i] > array[j]){
                copy[index--] = array[i--];
                count += j-mid;
            }else{
                copy[index--] = array[j--];
            }
        }
        for(;i >= start;i--){
            copy[index--] = array[i];
        }
        for(;j >= mid+1;j--){
            copy[index--] = array[j];
        }
        return leftCount + rightCount + count;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,0};
        System.out.println(inversePairs_2(arr));
    }

}

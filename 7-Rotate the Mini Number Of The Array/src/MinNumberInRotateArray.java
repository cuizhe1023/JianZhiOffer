/**
 * @Author: cuizhe
 * @Date: 2019/2/23 16:11
 */
public class MinNumberInRotateArray {
    /**
     * 题目：
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */

    /**
     * 思路：
     * 类似于二分查找的思路，先获取整个数组的中间值，并且让 start 和 end 指针分别指向第一个位置和最后一个位置
     *             7, 10, 13, 15, 2, 3, 4, 6
     *             ↑          ↑            ↑
     *          start        mid         end
     * 分别用 mid 和 start、end 比较。
     * mid 的值大于 start 说明 start-mid 之间是递增的。
     * 此时令 start = mid，mid 重新赋值。
     *             7, 10, 13, 15, 2, 3, 4, 6
     *                        ↑      ↑     ↑
     *                      start   mid   end
     * 再次比较 mid 和 start、end.
     * mid 的值小于 start 的值，也小于 end的值，说明 最小的值在 mid 附近，
     * 此时令 start = mid，mid 重新赋值。
     *             7, 10, 13, 15, 2, 3, 4, 6
     *                         ↑  ↑  ↑
     *                     start mid end
     * 再次比较 mid 和 start、end.
     * mid 的值小于 start 的值，也小于 end的值，说明 最小的值在 mid 附近，
     * 此时令 start = mid，mid 重新赋值。
     *             7, 10, 13, 15, 2, 3, 4, 6
     *                         ↑  ↑↑
     *                     start mid、end
     * 而此时 start+1=end，说明两个指针挨着的。已经确定了最小值，即为end.循环结束
     *
     * @param array 旋转的数组
     * @return 最小值
     */
    public static int minNumberInRotateArray(int[] array) {
        if (array.length==0){
            return 0;
        }
        if (array[0]<array[array.length-1]){
            return array[0];
        }
        int start = 0;
        int end = array.length-1;
        while (start+1 != end){
            int mid = (start+end)/2;
            if (array[mid]>array[start]){
                start = mid;
            }else if (array[mid]<array[end]){
                end = mid;
            }else {
                start++;
            }
        }
        return array[end];
    }

    public static void main(String[] args) {
        int a[] = {7,10,13,15,2,3,4,6};
        System.out.println(minNumberInRotateArray(a));
    }

}

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/2/27 17:12
 */
public class ReOrderArray {
    /**
     * 题目：
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     */

    /**
     * 思路：
     * 开辟新数组，将奇数保存在一个数组中，偶数保存在另一个数组中，之后进行合并。
     *
     * @param array 整型数组
     */
    public static void reOrderArray_1(int [] array) {
        LinkedList<Integer> odd = new LinkedList<>();
        LinkedList<Integer> even = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i]%2==1){
                odd.add(array[i]);
            }else {
                even.add(array[i]);
            }
        }
        for (int i = 0; i < odd.size(); i++) {
            array[i] = odd.get(i);
        }
        for (int i = 0; i < even.size(); i++) {
            array[odd.size()+i] = even.get(i);
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 上面的需要进行新建数组
     * 冒泡排序的一种改进，将奇数逐个“冒”出来。
     *
     * @param array
     */
    public static void reOrderArray_2(int [] array) {
        int m = array.length;
        int k = 0;//记录已经摆好位置的奇数的个数
        for (int i = 0; i < m; i++) {
            if (array[i] % 2 == 1) {
                int j = i;
                while (j > k) {
                    int tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                    j--;
                }
                k++;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int a[] = {1,2,3,5,7,6,8,9,10};
        int b[] = {1,2,3,4,5,6,7,8,9,10,12,13,14,16,18,12};
        reOrderArray_2(b);
    }
}

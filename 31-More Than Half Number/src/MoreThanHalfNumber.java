import java.util.Arrays;

/**
 * @Author: cuizhe
 * @Date: 2019/3/9 13:12
 */
public class MoreThanHalfNumber {

    /**
     * 题目：
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，
     * 因此输出2。如果不存在则输出0。
     */

    /**
     * 思路：
     * 数组排序后，如果符合条件的数存在，则一定是数组中间那个数。会修改原数组。
     *
     * @param array 待查找数组
     * @return 超过数组长度一半的数组
     */
    public static int moreThanHalfNum_1(int [] array) {
        if (checkArray(array)){
            return 0;
        }
        Arrays.sort(array);
        int result = array[array.length/2];
        if (checkMoreThanHalf(array,result)){

            return result;
        }
        return 0;
    }

    /**
     * 思路：
     * 使用一个计数count = 1，当前数num,每当数组中数和当前数num相同，那么count就加1，不相同就减一。
     * 因为是找出现的数超过数组的长度的一半，所以最后如果有出现的数超过数组长度一半的，count肯定是大于0的数
     *
     * @param array 待查找数组
     * @return 超过数组长度一半的数组
     */
    public static int moreThanHalfNum_2(int [] array) {
        if (checkArray(array)){
            return 0;
        }
        int count = 1;
        int number = array[0];
        for (int i = 1; i < array.length; i++) {
            if (count==0){
                number = array[i];
                count++;
            }else if (array[i]==number){
                count++;
            }else {
                count--;
            }
        }
        if (checkMoreThanHalf(array,number)){
            return number;
        }
        return 0;
    }

    public static boolean checkArray(int[] arr){
        if (arr.length<=0 || arr==null){
            return true;
        }
        return false;
    }

    public static boolean checkMoreThanHalf(int[] arr,int number){
        int count = 0;
        for (int a :
                arr) {
            if (a == number)
                count++;
        }
        return count > arr.length / 2;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,2,2,2,5,4,2};
        System.out.println(moreThanHalfNum_1(arr));
    }
}

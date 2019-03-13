import java.util.ArrayList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/12 11:39
 */
public class UglyNumber {
    /**
     * 题目：
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
     * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     */

    /**
     * 思路：
     * 一个数是丑数，那么他就只能被2、3和5整除。
     * 就是说如果一个数能被2整除，我们就把它连续除以2；
     * 如果能被3整数，就连续除以3；
     * 如果能被5整除，就除以连续5。
     * 如果最后我们得到的数字是1，那么这个数就是丑数，否则不是。
     * 但最大的问题是：
     * 每个整数都需要计算。即使一个数字不是丑数，我们还是需要对它做求余和除法操作。
     * 因此该算法的时间效率不够高，牛客网测试超时。
     *
     * @param index 位置
     * @return 第 index 个丑数
     */
    public static int GetUglyNumber_1(int index) {
        if(index<=0){
            return 0;
        }
        int count = 0;
        int number = 0;
        while (count<index){
            number++;
            if (isUglyNumber(number)){
                count++;
            }
        }
        return number;
    }

    private static boolean isUglyNumber(int number) {
        while (number%2==0){
            number /= 2;
        }
        while (number%3==0){
            number /= 3;
        }
        while (number%5==0){
            number /= 5;
        }
        return number == 1;
    }

    /**
     * 思路：
     * 首先从丑数的定义我们知道，一个丑数的因子只有 2,3,5，
     * 那么丑数 p = 2 ^ x * 3 ^ y * 5 ^ z，
     * 换句话说一个丑数一定由另一个丑数乘以 2 或者乘以 3 或者乘以 5 得到，
     * 那么我们从1开始乘以 2,3,5，就得到 2,3,5 三个丑数，
     * 在从这三个丑数出发乘以 2,3,5 就得到 4，6,10,6，9,15,10,15,25 九个丑数，
     * 我们发现这种方法会得到重复的丑数，而且我们题目要求第N个丑数，这样的方法得到的丑数也是无序的。
     * 那么我们可以维护三个队列：
     * （1）丑数数组：1
     * 乘以2的队列：2
     * 乘以3的队列：3
     * 乘以5的队列：5
     * 我们选三个队列头中最小的数 2 加入数组中。同时，将该数乘以 2、3、5 的结果放入队列中
     * （2）丑数数组：1,2
     * 乘以2的队列：4
     * 乘以3的队列：3,6
     * 乘以5的队列：5,10
     * 我们选三个队列头中最小的数 3 加入数组中。同时，将该数乘以 2、3、5 的结果放入队列中
     * （3）丑数数组：1,2,3
     * 乘以2的队列：4,6
     * 乘以3的队列：6,9
     * 乘以5的队列：5,10,15
     * 我们选三个队列头中最小的数 4 加入数组中。同时，将该数乘以 2、3、5 的结果放入队列中
     * （4）丑数数组：1,2,3,4
     * 乘以2的队列：6,8
     * 乘以3的队列：6,9,12
     * 乘以5的队列：5,10,15,20
     * 我们选三个队列头中最小的数 5 加入数组中。同时，将该数乘以 2、3、5 的结果放入队列中
     * （5）丑数数组：1,2,3,4,5
     * 乘以2的队列：6,8,10
     * 乘以3的队列：6,9,12,15
     * 乘以5的队列：10,15,20,25
     * 我们选三个队列头中最小的数 6 加入数组中。但我们发现，有两个对头都是 6，所以我们弹出两个队列的队头。
     * 同时，将该数乘以 2、3、5 的结果放入队列中。
     * （6）丑数数组：1,2,3,4,5,6
     * 乘以2的队列：8,10,12
     * 乘以3的队列：9,12,15,18
     * 乘以5的队列：10,15,20,25,30
     * 我们选三个队列头中最小的数 8 加入数组中。同时，将该数乘以 2、3、5 的结果放入队列中
     * （7）丑数数组：1,2,3,4,5,6,8
     * 乘以2的队列：8,10,12,16
     * 乘以3的队列：9,12,15,18,24,
     * 乘以5的队列：10,15,20,25,30,40
     * ......
     * 以此类推
     * 可以构建出一个只有丑数的数组。
     * 这样可以很大的减少时间的消耗，而且在实际编码中，不需要去真正的维护三个队列，只需要去记录三个指针指到了哪里。
     * （1）丑数数组：1
     * 2
     * ↑
     * 3
     * ↑
     * 5
     * ↑
     * 目前指针指向 0,0,0，队列头 arr[0] * 2 = 2,  arr[0] * 3 = 3,  arr[0] * 5 = 5
     * （1）丑数数组：1,2
     * 2 4
     *   ↑
     * 3 6
     * ↑
     * 5 10
     * ↑
     * 目前指针指向 1,0,0，队列头arr[1] * 2 = 4,  arr[0] * 3 = 3, arr[0] * 5 = 5
     * （1）丑数数组：1,2,3
     *  2 4 6
     *   ↑
     * 3 6 9
     *   ↑
     * 5 10 15
     * ↑
     * 目前指针指向1,1,0，队列头arr[1] * 2 = 4,  arr[1] * 3 = 6, arr[0] * 5 = 5
     *
     * @param index 位置
     * @return 第 index 个丑数
     */
    public static int GetUglyNumber_2(int index) {
        if (index<=0){
            return 0;
        }
        if (index==1){
            return 1;
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(1);
        int index2 = 0,index3 = 0,index5 = 0;
        int count = 1;
        while (count<index){
            int nextUglyNumber = min(result.get(index2)*2,result.get(index3)*3,result.get(index5)*5);
            result.add(nextUglyNumber);
            count++;
            while (result.get(index2)*2<=nextUglyNumber){
                index2++;
            }
            while (result.get(index3)*3<=nextUglyNumber){
                index3++;
            }
            while (result.get(index5)*5<=nextUglyNumber){
                index5++;
            }
        }
        return result.get(index-1);
    }

    private static int min(int i, int j, int k) {
        int temp = i>j?j:i;
        return temp>k?k:temp;
    }

    public static void main(String[] args) {
        System.out.println(GetUglyNumber_2(10));
    }

}

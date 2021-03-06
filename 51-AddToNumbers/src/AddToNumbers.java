/**
 * @Author: cuizhe
 * @Date: 2019/3/24 15:16
 */
public class AddToNumbers {
    /**
     * 题目：
     * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
     */

    /**
     * 思路：
     * 首先我们看一下十进制如何进行加法的：6 + 7 = 13
     * 第一步，相加各位的值，不进位得 3
     * 第二步，计算进位值，得到 10.如果这一步的进位值为 0，那么第一步得到的值就是最终结果。
     * 第三步，重复上述两步，只是相加的值变成上述两步的得到的结果 2 和 10，得到 12。
     *
     * 同样，我们来看一下二进制加法是如何实现的。6 --- 110，7 --- 111
     * 第一步，不考虑进位对每一位相加。0 加 0 得 0，1 加 0 得 1，1 加 1 得 0,这是不是与异或运算的结果一样
     * 110^111 = 001
     * 第二步，0 加 0、1 加 0、0 加 1 而言，都不会产生进位。只有 1 加 1 会产生进位。
     * 因此，我们可以想象成两个数先做与运算，然后再向左移动了一位。
     * (110 & 111) << 1 = 1100
     * 第三步，把前面两个步骤的结果相加。直到不产生进位为止。
     * 1100 + 001 = 1101 = 13
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return 和
     */
    public static int Add(int num1,int num2) {
        int sum = 0;
        int carry = 0;
        while (num2!=0){
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

    public static void main(String[] args) {
        System.out.println(Add(6,7));
    }

}

/**
 * @Author: cuizhe
 * @Date: 2019/2/26 10:38
 */
public class Power {
    /**
     * 题目：
     * 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent。求base的exponent次方。
     */

    /**
     * 思路：
     * 思路其实很简单，就是多次相乘。
     * 但是需要注意一些细节：
     * 1.指数小于 0 需要对结果求倒数。
     * 2.当底数是 0，指数小于 0 的时候，会出现对0求倒数。需要进行判断。
     *
     * @param base 底数
     * @param exponent 幂
     * @return base 的 exponent 次方
     */
    public static double power(double base,int exponent){
        double result = 1;
        boolean isMinus = false;
        if (exponent<0){
            exponent = exponent*(-1);
            isMinus = true;
        }
        while (exponent>0){
            result = result * base;
            exponent--;
        }
        if (!isMinus){
            return result;
        }else {
            return 1/result;
        }
    }

    /**
     * 还可以使用递归进行计算。有公式如下：
     *    |--- a^(n/2)*a^(n/2) ，n为偶数
     * a^n|
     *    |--- a^((n-1)/2)*a^((n-1)/2)，n为奇数
     * 我们用右移运算代替了除2，用与运算代替了取余运算判断是技术还是偶数。
     *
     * @param base 底数
     * @param exponent 幂
     * @return base 的 exponent 次方
     */
    public static double power1(double base,int exponent){
        if (exponent==0){
            return 1;
        }
        if (exponent==1){
            return base;
        }
        double result = power1(base,exponent>>1);
        result = result*result;
        if ((exponent&1) == 1){
            result = result*base;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(power1(13,2));
    }

}

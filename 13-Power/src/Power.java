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

    public static void main(String[] args) {
        System.out.println(power(122,0));
    }

}

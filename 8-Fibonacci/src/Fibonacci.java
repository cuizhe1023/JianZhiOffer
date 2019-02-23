/**
 * @Author: cuizhe
 * @Date: 2019/2/23 21:10
 */
public class Fibonacci {
    /**
     * 题目：
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     */

    /**
     * 递归实现斐波那契数列
     * @param n 斐波那契数列的第n位
     * @return 第n位的值
     */
    public static int fibonacci(int n) {
        if (n<0){
            return 0;
        }
        if (n==1 || n==2){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    /**
     * 但是，递归有个问题，当 n 特别大时，递归会导致栈溢出，并且运行时间也很长.
     * 若用循环，可以很好地解决这个问题
     * @param n 斐波那契数列的第n位
     * @return 第n位的值
     */
    public static int fibonacci1(int n){
        if (n<0){
            return 0;
        }
        if (n==1 || n==2){
            return 1;
        }
        int a = 0;
        int b = 1;
        int i = 2;
        int sum = 0;
        while (i<=n){
            sum  = a+b;
            a=b;
            b=sum;
            i++;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci1(46));
    }
}

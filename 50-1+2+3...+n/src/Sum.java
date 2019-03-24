/**
 * @Author: cuizhe
 * @Date: 2019/3/24 14:38
 */
public class Sum {
    /**
     * 题目：
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     */

    static int temp = 0;

    /**
     * 思路：
     * 题目的意思是不能用循环，那么只能使用递归进行运算了。
     * 把 n > 0 放在最开始的判断上，利用短路运算符(&&)的特性。
     * 只有在 n - 1 >= 0 的条件下，才会执行后面的语句。
     * temp 是一个全局变量，可以保留累加的和，temp = temp + n 是每次进行累加的过程。
     * 最后的 complex(n - 1) > 0 用来进入更深层次的递归里面。
     *
     * @param n 个数相加
     * @return 1-n 的和
     */
    public static int sum(int n) {
        complex(n);
        return temp;
    }

    private static int complex(int n) {
        boolean flag = n > 0 && (temp = temp + n) >0 && complex(n - 1)>0;
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(sum(100));
    }
}

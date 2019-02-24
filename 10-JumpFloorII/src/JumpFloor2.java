/**
 * @Author: cuizhe
 * @Date: 2019/2/23 22:38
 */
public class JumpFloor2 {
    /**
     * 题目：
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     */

    /**
     * 思路：
     * 我们把 n 阶台阶时的跳法，看成是 n 的函数，记为 f(n).因此有
     * f(1) = 1
     * f(2) = f(2-1) + f(2-2) = 2     f(2-2) 表示2阶一次跳 2 阶的次数
     * f(3) = f(3-1) + f(3-2) + f(3-3) = 4
     * ……
     * f(n) = f(n-1) + f(n-2) + …… + f(n-n)
     *
     * [说明]:
     * 1.这里的 f(n) 代表的是n个台阶有一次1,2,...n阶的跳法数。
     *
     * 2.n = 1 和 n = 0 时，只有一种跳法，f(0) = f(1) = 1
     *
     * 3.n = 2 时，会有两种跳法，一次1阶或者2阶，这就回归到了上一个问题[9-JumpFloor]。f(2) = f(2-1) + f(2-2)
     *
     * 4.n = 3时，会有三种跳的方式，一次1阶，一次2阶，一次3阶。
     * 那么就是第一次跳出一阶后面剩下 f(3-1);第二次跳出2阶，剩下 f(3-2) ;第一次3阶，那么剩下 f(3-3)
     * 因此，结论是 f(3) = f(3-1) + f(3-2) + f(3-3)
     *
     * 5.以此类推，可以得出结论
     * f(n) = f(n-1) + f(n-2) + …… + f(n-(n-1)) +f(n-n) => f(0) + f(1) + f(2) + …… + f(n-1)
     *
     * 6.继续化简
     * f(n-1) = f((n-1)-1) + f((n-1)-2) + …… + f((n-1)-(n-1)) = f(0) + f(1) + f(2) + …… + f(n-3) + f(n-2)
     * f(n) =  f(0) + f(1) + f(2) + …… + f(n-2) + f(n-1) = f(n-1) + f(n-1)
     * 整理得：
     * f(n) = 2 * f(n-1)
     *
     * 综上所述：
     *          |- 1          ,(n=0)
     * f(n) = -|- 1           ,(n=1)
     *        |- 2 * f(n-1)   ,(n=>2)
     *
     * 所以，可以用递归解决问题
     *
     * @param target 台阶数
     * @return 跳法
     */
    public static int jumpFloor1(int target) {
        if(target<=0){
            return 1;
        }else if (target==1){
            return 1;
        }else {
            return 2 * jumpFloor1(target - 1);
        }
    }

    /**
     * 上面我们得到了递归的规律，不过递归总是存在一些问题，如运行时间长，系统开销大等
     * 我们可以再看一下得到的结果
     *   n    1     2     3     4     5 ……
     * f(n)   1     2     4     8     16 ……
     *
     * 可以看出其中的规律 f(n) = 2^(n-1);
     *
     * @param target 台阶数
     * @return 跳法
     */
    public static int jumpFloor2(int target) {
        return (int) Math.pow(2,target - 1);
    }

    /**
     * 有大神用位运算..不敢多说话
     * @param target 台阶数
     * @return 跳法
     */
    public static int jumpFloor3(int target) {
        return  1<<--target;
    }

    public static void main(String[] args) {
        System.out.println(jumpFloor3(3));
    }
}

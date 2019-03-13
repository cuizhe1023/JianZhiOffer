/**
 * @Author: cuizhe
 * @Date: 2019/2/24 16:43
 */
public class RectCover {
    /**
     * 题目：
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     */

    /**
     * 思路:
     * 依然是斐波那契数列.
     * 2*n 的大矩阵，和n个 2*1 的小矩阵
     * 其中 target*2 为大矩阵的大小
     * 有以下几种情形：
     * 1.target <=0 ,大矩阵为 2*target <= 0.return 1;
     * 2.target = 1,大矩阵为 target*2 = 1*2.return 1;
     * 3.target = 2,大矩阵为 target*2 = 2*2.有两种摆法，return 2;
     * 4.target = n,大矩阵为 target*2 = n*2,分为两步考虑
     * (1).第一次摆放 2*1 的小矩阵，则摆放方法有 f(n-1) 种.(√√，表示2*1的小矩阵)
     * |√ |  |  |  |  |  |
     * |√ |  |  |  |  |  |
     * (2).第一次摆放 1*2 的小矩阵，则摆放方法有 f(n-2) 种.
     * |√ |√ |  |  |  |  |
     * |x |x |  |  |  |  |
     * 因为，当摆放了一块 1*2 的小矩阵，对应下方的 1*2 的小矩阵也确定了.
     * 因此，当 target = n 时，有 f(n) = f(n-1) + f(n-2).
     *
     * @param target 为大矩阵的宽度即，2*n
     * @return 方法数
     */
    public static int rectCover(int target) {
        if (target<=1){
            return 1;
        }
        if (target==2){
            return 1;
        }
        return rectCover(target-1)+rectCover(target-2);
    }

    public static void main(String[] args) {
        System.out.println(rectCover(5));
    }
}

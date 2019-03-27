import java.util.Arrays;

/**
 * @Author: cuizhe
 * @Date: 2019/3/27 15:24
 */
public class Multiply {
    /**
     * 题目：
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
     * 其中B中的元素B[i]=A[0]*A[1]*...·*A[i-1]*A[i+1]*...*A[n-1]。
     * 不能使用除法。
     */

    /**
     * 思路：
     * 首先找找规律，我们看到 B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
     * 也就是说，去除 A 中序号为 i 的元素，其他元素乘积就是 B[i]。
     * 那么，我们可以使用蛮力方法,对每个 B[i] 都重新算一遍,从而得到结果,复杂度是O(n^2).
     *
     * @param A 数组
     * @return B数组
     */
    public static int[] multiply_1(int[] A) {
        if (A==null || A.length<=0){
            return null;
        }
        int temp = 1;
        int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (j!=i){
                    temp = temp * A[j];
                }
            }
            B[i] = temp;
            temp = 1;
        }
        return B;
    }

    /**
     * 思路：
     * 可以把
     * B[i] = A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
     * 看成 A[0]*A[1]*...*A[i-1] 和 A[i+1]*...*A[n-1]*A[n-1] 两部分的乘积
     * B[i] 的值可以看作下图的矩阵中每行的乘积。
     * B0   1   A1  A2   ···   An-2  An-1
     * B1   A0  1   A2   ···   An-2  An-1
     * B2   A0  A1  1    ···   An-2  An-1
     * ···  A0  A1  ···  1     An-2  An-1
     * Bn-2 A0  A1  ···  An-3  1     An-1
     * Bn-1 A0  A1  ···  An-3  An-2  1
     * 下三角用连乘可以很容求得，上三角，从下向上也是连乘。
     * 因此我们的思路就很清晰了，
     * 先算下三角中的连乘，即我们先算出 B[i] 中的一部分，
     * 然后倒过来按上三角中的分布规律，把另一部分也乘进去。
     *
     * @param A 数组
     * @return B数组
     */
    public static int[] multiply_2(int[] A) {
        if (A==null || A.length<=0){
            return null;
        }
        int[] B = new int[A.length];
        B[0] = 1;
        //下三角矩阵
        for (int i = 1; i < A.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        int temp = 1;
        //上三角矩阵
        for (int i = A.length - 2; i >= 0; i--) {
            temp = temp * A[i + 1];
            B[i] = B[i] * temp;
        }
        return B;
    }

    public static void main(String[] args) {
        int a[] = {1,2,3,4,5};
        System.out.println(Arrays.toString(multiply_2(a)));
    }
}

import java.util.ArrayList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/1 13:38
 */
public class ClockwisePrintMatrix {
    /**
     * 题目：
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，
     * 如果输入如下4 X 4矩阵：
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     *
     */

    /**
     * 思路：
     * 由于是以外圈到内圈的顺序依次打印，我们可以把矩阵想成若干个圈，可以依次打印一个圈。
     * 1  → 2  → 3  → 4
     * ↑ 5     6     7     8   ↓
     * ↑ 9     10    11    12  ↓
     * ↑ 13 ← 14 ← 15 ← 16  ↓
     * 我们先找出循环结束的条件.当打印第一圈时，第一圈的起点坐标是(0,0)。第二圈的起点坐标是(1,1)，依次类推。
     * 假设这个矩阵行数是 rows，列数是 columns。我们也注意到起点的坐标中，行数和列数是相等的，记为(start，start)
     * 对一个 4X4 矩阵而言，最后一圈的起点坐标(1,1)有 rows>star*2,columns>start*2。
     * 对一个 5X5 矩阵而言，最后一圈只有一个数字坐标(2,2)也有 rows>star*2,columns>start*2.
     * 对一个 6X6 矩阵而言，最后一圈的起点坐标(2,2)也有 rows>star*2,columns>start*2.
     * 对一个 7X7 矩阵而言，最后一圈只有一个数字坐标(3,3)也有 rows>star*2,columns>start*2.
     * ...
     * 综上所述，我们可以判断出，对于 nXn 的矩阵而言，它的行数和列数总是大于起点坐标乘2。
     * 当它的行数和列数小于起点坐标乘2时，表明矩阵遍历完毕。
     * <p>
     * 接下来我们考据如何打印一圈的功能，
     * 根据上图，我们可以把打印一圈分为四步，
     * 第一步，是从左向右打印一行；第二步，是从上到下打印一行；
     * 第三步，是从右向左打印一行；第四步，是从下到上打印一行。
     * 每一步我们根据起始坐标和终止坐标用一个循环就能打印出一列。
     * 我们判断一下一个矩阵有哪几种情况 rows = columns ,rows < columns ,rows > columns,分别如下图所示
     * (→)代表第一部分的打印
     * 1     2     3     4        1     2     3         1     2     3     4
     * 5     6  → 7     8        5     6(→) 7         5     6  → 7     8
     * 9  ↑ 10 ← 11 ↓ 12       9     10 ↓ 11        9     10    11    12
     * 13    14    15    16       13    14    15
     * rows = columns           rows >columns          rows < columns
     * 最后一圈很有可能变为只有一行，只有一列，甚至只有一个数字。因此，打印这样的一圈就不再需要四步了。
     * 因此，需要仔细分析打印时，每一步的前提条件。
     * (以下所说的起点坐标的行号和列号、终止坐标的行号和列号都是对于单次循环，而不是一个圈的起点坐标和重点坐标)
     * 1.第一步总是需要的，因为要打印一圈至少需要一步。
     * <p>
     * 2.如果只有一行，那么就不需要第二步了。也就是说，需要第二步的前提条件是终止行号>起始行号。
     * 举个例子，
     * 对于一个 4X4 的矩阵，第一圈第二步的起始坐标是(1,3)，终止坐标是(3,3)。
     * 因为终止行号(3)>起始行号(1)，所以进行第二步。
     * <p>
     * 3.需要第三步的前提条件是圈内至少有两行两列。也就是说，除了要求终止行号>起始行号(第二步的前提条件)之外，
     * 还要求终止列号>起始列号。
     * 举个例子，
     * 对于一个 4X4 的矩阵，第一圈第三步的起始坐标是(3,0)，终止坐标是(3,2)。
     * 因为终止列号(2)>起始列号(0)，所以进行第三步
     * 【说明】：这里的起始坐标和重点坐标是按照数组从左到右的顺序，而不是按照打印的方向！
     * 比如，第三步所说的起始坐标是(3,0)、终止坐标是(3,3)。
     * 如果按照打印的顺序应该是从右到左，起始坐标应该是(3,2)，终止坐标应该是(3,0)。
     * <p>
     * 4.需要打印第四步的前提条件是至少有三行两列，因此要求终止行号比起始行号至少大2，同时终止行号大于起始行号。
     * (1).对于 4X4 的矩阵，第一圈第四步的起始坐标是(1,0)，终止坐标是(3,0)
     * 因为终止行号(3)-起始行号(1)>=2，且终止行号(3)>起始行号(1)，所以进行第四步。
     *
     * @param matrix 矩阵
     * @return 顺时针打印的结果
     */
    public static ArrayList<Integer> printMatrix_1(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();//用来存放顺时针打印结果
        if (matrix.length == 0) {//如果矩阵的长度为0，则直接返回 null
            return null;
        }
        int rows = matrix.length;//行数
        int columns = matrix[0].length;//列数
        if (rows == 0) {
            return result;
        }
        int start = 0;
        while (rows > start * 2 && columns > start * 2) {
            printMatrixInCircle(matrix, rows, columns, start);
            start++;
        }
        return result;
    }

    public static void printMatrixInCircle(int[][] matrix, int rows, int columns, int start) {
        int endX = columns - start - 1;//终止元素的列号
        int endY = rows - start - 1;//终止元素的行号
        //第一步，从左到右
        for (int i = start; i <= endX; i++) {
            int number = matrix[start][i];
            System.out.print(number + " ");
        }

        //第二步，从上到下打印
        //先判断是否满足前提条件，终止行号>起始行号
        if (endY > start) {
            for (int i = start + 1; i <= endY; i++) {
                int number = matrix[i][endX];
                System.out.print(number + " ");
            }
        }

        //第三步，从右到左打印
        //先判断是否满足前提条件，终止行号>起始行号 && 终止列号>起始列号
        if (endY > start && endX > start) {
            for (int i = endX - 1; i >= start; i--) {
                int number = matrix[endY][i];
                System.out.print(number + " ");
            }
        }

        //第四步，从上打下打印
        //先判断是否满足前提条件，终止行号>起始行号 && 终止行号 比 起始行号至少大2 => 终止行号-1 > 起始行号
        if (endY > start && endY - 1 > start) {
            for (int i = endY - 1; i >= start + 1; i--) {
                int number = matrix[i][start];
                System.out.print(number + " ");
            }
        }
    }

    /**
     * 第二种方法，
     * 设置四个参数分别表示圈的边界
     *    left = 0        right = matrix[0].length
     *     ↓              ↓
     *      1    2    3    4   ← top = 0
     *      5    6    7    8
     *      9    10   11   12
     *      13   14   15   16  ← bottom = matrix.length
     * 每当转完一圈，对四个参数进行重新赋值，收缩圈的大小。比如，当打印完第一圈，此时四个参数的值为
     *          left=1 right = matrix[0].length-1
     *           ↓   ↓
     *      1    2    3    4
     *      5    6    7    8   ← top = 1
     *      9    10   11   12  ← bottom = matrix.length-1;
     *      13   14   15   16
     * 像这样，然后不断循环打印，直到 left > right || top > bottom
     * 所以，打循环的条件是 left<=right && top <= bottom
     *
     * @param matrix 矩阵
     * @return 顺时针打印的结果
     */
    public static ArrayList<Integer> printMatrix_2(int[][] matrix){
        if (matrix.length == 0) {//如果矩阵的长度为0，则直接返回 null
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        while (left<=right && top<=bottom){
            //第一步，从左到右打印
            for (int i = left; i <= right; i++) {
                arrayList.add(matrix[top][i]);//列在变化，行不变
            }
            //第二步，从上到下打印
            if (bottom>top){
                for (int i = top + 1; i <= bottom ; i++) {
                    arrayList.add(matrix[i][right]);
                }
            }
            //第三步，从右到左打印
            if (left<right && bottom>top){
                for (int i = right - 1; i >= left ; i--) {
                    arrayList.add(matrix[bottom][i]);
                }
            }
            //第四步，从下到上打印
            if (bottom-1>top && right>left){
                for (int i = bottom - 1; i > top; i--) {
                    arrayList.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return arrayList;
    }


    public static void main(String[] args) {
        int a[][] = {{1,2,3},{5,6,7},{9,10,11},{13,14,15}};
        ArrayList list = printMatrix_2(a);
        System.out.println(list.toString());
    }
}

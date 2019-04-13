/**
 * @Author: cuizhe
 * @Date: 2019/4/13 11:29
 * @Description:
 */
public class TheRangeOfMotionOfTheRobot {
    /**
     * 题目：
     * 地上有一个 m 行和 n 列的方格。
     * 一个机器人从坐标 (0,0) 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格。
     * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当 k 为 18 时，机器人能够进入方格（35,37），因为 3+5+3+7 = 18。
     * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     */

    /**
     * 思路：
     * dfs,搜索四个方向，记录该方格是否被搜索过，
     * 1.从 (0,0) 开始走，每成功走一步标记当前位置为 true,然后从当前位置往四个方向探索，返回 1 + 4 个方向的探索值之和。
     * 2.探索时，判断当前节点是否可达的标准为：
     *    1).当前节点在矩阵内；
     *    2).当前节点未被访问过；
     *    3).当前节点满足limit限制。
     *
     * @param threshold 约束值
     * @param rows 行
     * @param cols 列
     * @return
     */
    public static int movingCount(int threshold, int rows, int cols){
        int flag[][] = new int[rows][cols]; //记录是否已经走过
        return helper(0, 0, rows, cols, flag, threshold);
    }

    /**
     * 递归回溯方法
     * @param i 当前处理的行号
     * @param j 当前处理的列号
     * @param rows 行号
     * @param cols 列号
     * @param flag 访问标记数组
     * @param threshold 约束值
     * @return
     */
    private static int helper(int i, int j, int rows, int cols, int[][] flag, int threshold) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || numSum(i) + numSum(j)  > threshold || flag[i][j] == 1) return 0;
        flag[i][j] = 1;
        return helper(i - 1, j, rows, cols, flag, threshold)
                + helper(i + 1, j, rows, cols, flag, threshold)
                + helper(i, j - 1, rows, cols, flag, threshold)
                + helper(i, j + 1, rows, cols, flag, threshold)
                + 1;
    }

    private static int numSum(int i) {
        int sum = 0;
        do{
            sum += i%10;
        }while((i = i/10) > 0);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(4,5,5));
    }
}

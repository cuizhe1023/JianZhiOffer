/**
 * @Author: cuizhe
 * @Date: 2019/4/13 10:06
 * @Description:
 */
public class PathsInTheMatrix {
    /**
     * 题目：
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
     * 如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
     * 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
     * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     */


    /**
     * 思路：
     * 通过回溯法解决。
     * 用一个状态数组保存之前访问过的字符，然后再分别按上，下，左，右递归
     *
     * @param matrix 目标数组
     * @param rows 行
     * @param cols 列
     * @param str 路径
     * @return matrix 中是否有 str 的路径
     */
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        //标志位，初始化为 false
        int flag [] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //循环遍历二维数组，找到起点等于 str 第一个元素的值，再递归判断四周是否有符合条件的----回溯法
                if (judge(matrix,rows,cols,i,j,str,0,flag)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断的方法
     *
     * @param matrix 初始矩阵
     * @param rows 索引行坐标 i
     * @param cols 索引列坐标 j
     * @param i 矩阵行数
     * @param j 矩阵列数
     * @param str 待判断的路径
     * @param k 字符串索引初始为0即先判断字符串的第一位
     * @param flag 标志位
     * @return
     */
    private static boolean judge(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        // 先根据 i 和 j 计算匹配的第一个元素转为一维数组的位置
        int index = i * cols + j;
        //递归终止条件
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1){
            return false;
        }
        //若 k 已经到达 str 末尾了，说明之前的都已经匹配成功了，直接返回 true 即可
        if(k == str.length - 1){
            return true;
        }
        // 要走的第一个位置置为 1，表示已经走过了
        flag[index] = 1;
        // 回溯，递归寻找，每次找到了就给 k 加一，找不到，还原
        if (judge(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || judge(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || judge(matrix, rows, cols, i, j - 1, str, k + 1, flag)
                || judge(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }
        // 走到这，说明这一条路不通，还原，再试其他的路径
        flag[index] = 0;
        return false;
    }

    // a b c e
    // s f c s
    // a d e e
    public static void main(String[] args) {
        char[] a = {'a','b','c','e','s','f','c','s','a','d','e','e'};
        char[] b = {'b','c','c','e','d'};
        System.out.println(hasPath(a,3,4,b));
    }
}

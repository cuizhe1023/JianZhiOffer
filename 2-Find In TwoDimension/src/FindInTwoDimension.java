/**
 * @Author: cuizhe
 * @Date: 2019/2/19 21:02
 */
public class FindInTwoDimension {
    /**
     * 题目:
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public static void main(String[] args) {
        int [][]arr = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.println(find_left_lower(arr,1));
    }

    /**
     * 比较 low 的方法，遍历找
     *
     * @param array 二维数组
     * @param number 目标元素
     * @return true/false
     */
    public static boolean find(int[][] array,int number){
        if (array==null){
            return false;
        }
        for (int[] a :
                array) {
            for (int b :
                    a) {
                if (b == number){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从右上角开始找，如果比右上角的数大，往下找，比右上角的数小，往左找
     *
     * @param array 二维数组
     * @param number 目标元素
     * @return true/false
     */
    public static boolean find_right_top(int[][] array,int number){
        if (array==null){
            return false;
        }

        //数列的行
        int rows = array.length;
        //数列的列
        int columns = array[rows-1].length;
        if (rows>0 && columns>0){
            int row = 0;
            int column = columns - 1;

            while (row < rows && column >=0){
                System.out.println(array[row][column]);
                if (array[row][column]==number){
                    return true;
                }else if (array[row][column]<number){
                    row++;
                }else {
                    column--;
                }
            }
        }
        return false;
    }

    /**
     * 从左下角开始找，如果比左下角的数大，往右找，比左下角的数小，往上找
     *
     * @param array 二维数组
     * @param number 目标元素
     * @return true/false
     */
    public static boolean find_left_lower(int[][] array,int number){
        if (array==null){
            return false;
        }
        //数列的行
        int rows = array.length;
        //数列的列
        int columns = array[rows-1].length;

        if (rows>0 && columns>0){
            int row = rows - 1;
            int column = 0;
            while (row >= 0 && columns > column){
                System.out.println(array[row][column]);
                if (array[row][column]==number){
                    return true;
                }else if (array[row][column]>number){
                    row--;
                }else {
                    column++;
                }
            }
        }

        return false;
    }

}

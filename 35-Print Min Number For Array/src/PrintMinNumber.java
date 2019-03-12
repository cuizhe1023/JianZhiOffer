import java.util.*;

/**
 * @Author: cuizhe
 * @Date: 2019/3/11 18:16
 */
public class PrintMinNumber {
    /**
     * 题目：
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     */

    /**
     * 思路：
     * 可以先对数组里的数根据 char 进行排序，放在新集合中，
     * 再将新集合拼成一个整数。
     * 这里用到了 collections 比较器，会对第一个字母进行比较。
     *
     * @param numbers 目标数组
     * @return 拼接出的所有数字中最小的一个
     */
    public static String PrintMinNumber_1(int [] numbers) {
        if (numbers==null || numbers.length==0){
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i :
                numbers) {
            list.add(i);
        }

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1+""+o2;
                String s2 = o2+""+o1;
                return s1.compareTo(s2);
            }
        });
        String s = "";
        for (Integer a:
             list) {
            s += a;
        }
        return s;
    }

    /**
     * 如果不通过比较器进行比较的话，也就只有通过循环进行比较了
     * 比较两个字符串 s1, s2 大小的时候，先将它们拼接起来，比较 s1+s2,和 s2+s1 那个大，
     * 如果 s1+s2 大，那说明 s2 应该放前面，所以按这个规则，s2 就应该排在 s1 前面
     *
     * @param numbers 目标数组
     * @return 拼接出的所有数字中最小的一个
     */
    public static String PrintMinNumber_2(int [] numbers) {
        if (numbers==null || numbers.length==0){
            return null;
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                int a = Integer.valueOf(numbers[i]+""+numbers[j]);
                int b = Integer.valueOf(numbers[j]+""+numbers[i]);
                if (a>b){
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        String s = "";
        for (int i :
                numbers) {
            s += i;
        }

        return s;
    }

    /**
     * 还有和[30-Permutation For String]题就基本类似的思路
     * 将所有的结果进行列举，对 numbers 里的数进行全排列，选出最小的
     * 用 TreeSet 对数据进行筛选，保存。
     *
     * @param numbers 目标数组
     * @return 拼接出的所有数字中最小的一个
     */
    public static String printMinNumber_3(int [] numbers) {
        if (numbers==null || numbers.length==0){
            return null;
        }
        TreeSet<String> treeSet = new TreeSet<>();
        printMinNumber_3(numbers,0,treeSet);
        return treeSet.pollFirst();
    }

    public static void printMinNumber_3(int [] numbers,int index,TreeSet<String> set) {
        if (index==numbers.length-1){
            String s = "";
            for (int i :
                    numbers) {
                s += i;
            }
            set.add(s);
        }else {
            for (int i = 0; i < numbers.length; i++) {
                swap(numbers,index,i);
                printMinNumber_3(numbers,index+1,set);
                swap(numbers,index,i);
            }
        }
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void main(String[] args) {
        int a[] = {3,32,321};
        System.out.print(printMinNumber_3(a));
    }
}

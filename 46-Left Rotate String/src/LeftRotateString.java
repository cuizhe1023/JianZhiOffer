/**
 * @Author: cuizhe
 * @Date: 2019/3/21 10:06
 */
public class LeftRotateString {
    /**
     * 题目：
     * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
     * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
     * 例如，字符序列 S = "abcXYZdef",要求输出循环左移3位后的结果，即 "XYZdefabc"。是不是很简单？OK，搞定它！
     */

    /**
     * 思路：
     * 通过 charAt(index) 将前 n 位添加到尾部，然后在删去前 n 位。
     *
     * @param str 字符串序列
     * @param n 需要左移的位数
     * @return 左移后的序列
     */
    public static String leftRotateString_1(String str,int n) {
        if (str == null || str.length() <= 0 || n < 0){
            return null;
        }
        if (n == 0){
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i = 0; i < n; i++) {
            stringBuffer.append(stringBuffer.charAt(i));
        }
        stringBuffer.delete(0,n);
        return stringBuffer.toString();
    }

    /**
     * 思路：
     * [注]：T为转置
     * 这道题考的核心是应聘者是不是可以灵活利用字符串翻转。
     * 假设字符串 abcdef，n=3，设 X = abc，Y = def，所以字符串可以表示成XY，如题干，问如何求得YX。
     * 假设 X 的翻转为 XT ， XT = cba，同理 YT = fed，那么 YX = (XT YT)T，三次翻转后可得结果。
     *
     * @param str 字符串序列
     * @param n 需要左移的位数
     * @return 左移后的序列
     */
    public static String leftRotateString_2(String str,int n) {
        if (str == null || str.length() <= 0 || n < 0){
            return null;
        }
        if (n == 0){
            return str;
        }
        char[] strChar = str.toCharArray();
        int start = 0;
        int end = strChar.length - 1;
        //先翻转整个字符串
        reverse(strChar,start,end);
        //再翻转前 n 个字符串
        reverse(strChar,start,end - n);
        reverse(strChar,end - n + 1,end);
        return String.valueOf(strChar);
    }

    private static void reverse(char[] strChar, int start, int end) {
        if (strChar==null || strChar.length <= 0){
            return ;
        }
        while (start < end){
            char temp = strChar[start];
            strChar[start] = strChar[end];
            strChar[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        String s = "abcXYZdef";
        System.out.println(leftRotateString_2(s,3));
    }
}

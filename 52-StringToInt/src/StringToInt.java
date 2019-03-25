/**
 * @Author: cuizhe
 * @Date: 2019/3/25 16:27
 */
public class StringToInt {
    /**
     * 题目：
     * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
     * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
     */

    /**
     * 思路：
     * 首先是遍历字符串中的每一个字符，有三种情况：
     * 1.首字符是正号，首字符是负号，首字符非正负号；
     * 2.然后遍历每个进行 num = num * 10 + charArray[i] - '0',
     * 在进行这个工作之前首先需要对 charArray[i] 进行判断是否为数字，循环遍历直到结束。
     *
     * 注意点：
     * 1.字符串是否为空
     * 2.字符串两边有空格自动屏蔽
     * 3.字符串是否溢出（大于最大值，小于最小值）
     * 4.字符串的符号怎么判断
     * 5.“+123”和”123”效果一样
     * 6.字符串的每一位的正确性
     *
     * @param str 字符串
     * @return
     */
    public static int StrToInt(String str) {
        if (str == null || str.length() <= 0){
            return 0;
        }
        String s = str.trim();
        int result = 0;
        int symbol = 1;//符号位
        int i = 0,len = s.length();
        char[] array = s.toCharArray();
        if (len > 0){
            char firstChar = s.charAt(0);
            if (firstChar < '0'){
                if (firstChar == '-'){
                    symbol = -1;
                }else if (firstChar != '+'){
                    return 0;
                }
                if (len == 1){
                    return 0;
                }
                i++;
            }
            for (int j = i; j < len; j++) {
                if (!('0' <= array[j] && array[j] <= '9')){
                    return 0;
                }
                result = result * 10 + array[j] - '0';
                if ((symbol == 1 && result > Integer.MAX_VALUE) || (symbol == -1 && result < Integer.MIN_VALUE)){
                    return 0;
                }
            }
        }else {
            return 0;
        }
        return result * symbol;
    }



    public static void main(String[] args) {
        System.out.println(StrToInt("1a33"));
    }
}

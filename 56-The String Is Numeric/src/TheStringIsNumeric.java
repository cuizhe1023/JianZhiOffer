/**
 * @Author: cuizhe
 * @Date: 2019/4/1 15:46
 */
public class TheStringIsNumeric {

    /**
     * 题目：
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
     * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     */

    /**
     * 思路：
     * 所有情况都考虑一遍
     * 1.e 后面一定得接数字，不能同时存在两个 e
     * 2.第一次出现 +- ，且不是在开头，则必须跟在e后面；第二次出现 +-，则必须紧接着 e
     * 3.e 后面不能跟小数点，小数点不能出现两次
     *
     * @param str 字符串
     * @return 是否是数组
     */
    public static boolean isNumeric(char[] str) {
        boolean sign = false;//标记
        boolean decimal = false;//标记小数点
        boolean hasE = false;//标记E

        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'e' || str[i] == 'E'){
                if (i == str.length - 1){
                    return false;//如果 e 出现在最后，则非法
                }
                if (hasE)
                    return false;//不能同时存在两个 e
                hasE = true;
            }else if (str[i] == '+' || str[i] == '-'){
                if (!sign && i > 0 && str[i - 1] != 'e' && str[i - 1] != 'E')
                    return false;//如果 +- 第一次出现，且不在字符串开头，则必须紧跟在 e 之后，
                if (sign && str[i - 1] != 'e' && str[i - 1] != 'E')
                    return false;//如果 +- 第二次出现，则必须跟在 e 之后
            }else if (str[i] == '.'){
                if (hasE || decimal)
                    return false;// e 后面不能跟小数点，且小数点只能出现一次
                decimal = true;
            }else if (str[i]<'0' || str[i]>'9'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "12e+5.4";
        System.out.println(isNumeric(s.toCharArray()));
    }
}

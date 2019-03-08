import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @Author: cuizhe
 * @Date: 2019/3/8 14:02
 */
public class PermutationOfString {
    /**
     * 题目：
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     */

    static ArrayList<String> result = new ArrayList<>();
    static TreeSet<String> set = new TreeSet<>();
    /**
     * 思路：
     * 通过递归求解，首先求出所有可能出现在第一位置的字母，即 begin 与后面所有与它不同的字母进行交换，
     * 固定第一个字母，求后面字母的全排列，即递归此时 begin = begin+1
     * @param str
     * @return
     */
    public static ArrayList<String> permutation(String str) {
        if(str==null || str.length()==0){
            return result;
        }
        char[] strArray = str.toCharArray();
        permutation(strArray,0);
        while (!set.isEmpty()){
            result.add(set.pollFirst());
        }
        return result;
    }

    public static void permutation(char[] strArray,int begin){
        if (begin==strArray.length-1){
            set.add(String.valueOf(strArray));
        }else {
            for (int i = begin; i < strArray.length; i++) {
                swap(strArray,begin,i);
                permutation(strArray,begin+1);
                swap(strArray,begin,i);
            }
        }
    }

    public static void swap(char[] strArray,int i,int j){
        char temp = strArray[i];
        strArray[i] = strArray[j];
        strArray[j] = temp;
    }

    public static void main(String[] args) {
        String a = "abc";
        System.out.println(permutation(a).toString());
    }
}

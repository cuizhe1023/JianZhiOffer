import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cuizhe
 * @Date: 2019/3/13 21:06
 */
public class FirstNotRepeatingChar {
    /**
     * 题目：
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
     * 如果没有则返回 -1（需要区分大小写）.
     */

    /**
     * 思路：
     * 字符串中的字符都是英文的字母，所以每一个字母都有一个ASCII码与其对应，
     * 建立一个整型数组长度是256，可以把每一个字符对应一个数组的下标。
     * 遍历字符串，整型数组的下标对应 +1；
     * 在遍历一遍，找出第一个只出现一次的字符。
     *
     * @param str 待查找的字符串
     * @return 第一个只出现一次的字符的位置
     */
    public static int firstNotRepeatingChar_1(String str) {
        if (str == null || str.length() <= 0){
            return -1;
        }
        char[] strArray = str.toCharArray();
        int[] charArray = new int[256];
        for (int i = 0; i < strArray.length; i++) {
            charArray[strArray[i]]++;
        }
        for (int i = 0; i < strArray.length; i++) {
            if (charArray[strArray[i]]==1){
                return i;
            }
        }
        return -1;
    }

    /**
     * 将字符与出现的次数组合成 map，遍历一次字符串就好了。
     *
     * @param str 待查找的字符串
     * @return 第一个只出现一次的字符的位置
     */
    public static int firstNotRepeatingChar_2(String str) {
        if (str == null || str.length() <= 0){
            return -1;
        }
        Map<Character,Integer> charMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (charMap.containsKey(str.charAt(i))){
                int count = charMap.get(str.charAt(i));
                count++;
                charMap.put(str.charAt(i),count);
            }else {
                charMap.put(str.charAt(i),1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (charMap.get(str.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = "strstring";
        System.out.println(firstNotRepeatingChar_1(str));
    }

}

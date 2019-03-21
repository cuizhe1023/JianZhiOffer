/**
 * @Author: cuizhe
 * @Date: 2019/3/21 11:25
 */
public class ReverseSentence {
    /**
     * 题目：
     * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
     * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
     * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
     * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
     */

    /**
     * 思路：
     * 先对整个字符串进行翻转，再通过空格对单个单词进行翻转
     * 比如："I am a student." 先翻转为 ".tneduts a ma I"
     * 在对 ".tneduts" 进行翻转，翻转为 "student."
     * 对 "ma" 进行翻转,翻转为 "am"
     * 依次类推。
     *
     * @param str
     * @return
     */
    public static String ReverseSentence(String str) {
        if (str == null || str.length() <= 0){
            return "";
        }
        char[] strChar = str.toCharArray();
        int start = 0;
        int end = str.length() - 1;
        //将整个字符进行翻转
        reverse(strChar,start,end);
        //对每个单词进行翻转以空格为分割
        for (int i = 0; i < strChar.length; i++) {
            if (strChar[i] == ' '){
                reverse(strChar,start,i - 1);
                start = i + 1;
            }
        }
        if (start < end){
            reverse(strChar,start,end);
        }
        return String.valueOf(strChar);
    }

    private static void reverse(char[] strChar, int start, int end) {
        if (strChar == null || strChar.length <= 0){
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
        String s = "I am a student.";
        System.out.println(ReverseSentence(s));
    }

}

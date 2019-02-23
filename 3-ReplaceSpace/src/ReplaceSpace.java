/**
 * @Author: cuizhe
 * @Date: 2019/2/20 20:19
 */
public class ReplaceSpace {
    /**
     * 题目:
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("We Are Happy");
        System.out.println(replaceSpace1(stringBuffer));
    }

    /**
     * 思路1：
     * charAt() 方法，遍历字符串每个位置的字符，如果遇见字符串 " ",则替换为 "%20".
     * @param stringBuffer 字符串
     * @return 替换之后的字符串
     */
    private static String replaceSpace(StringBuffer stringBuffer) {
        if (stringBuffer==null || stringBuffer.length()==0){
            return null;
        }
        String s = "";
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i)==' '){
                s = s + "%20";
            }else {
                s = s + stringBuffer.charAt(i);
            }
        }
        return s;
    }

    /**
     * 思路2：
     * 可以从字符串的末尾开始复制和替换，先准备两个指针，indexOld 和 indexNew。
     * indexOld 指向原来字符串的末尾，而 indexNew 指向替换之后的字符串的末尾，
     *                      |W|e| |A|r|e| |H|a|p|p|y|\0| | | | |
     *                                               ↑      ↑
     *                                           indexOld  indexNew
     * 接下来向前移动 indexOld 指针，逐个把它指向的字符复制到 indexNew 所指向的位置，直到碰到第一个空格。
     *                      |W|e| |A|r|e| | | | | |H|a|p|p|y|\0|
     *                                  ↑      ↑
     *                               indexOld  indexNew
     * 碰到第一个空格之后,把 indexOld 向前移动1格，在 indexNew 之前插入字符串“%20”。由于“%20”的长度是3，
     * 因此也要把 indexNew 往前移动3格.
     *                      |W|e| |A|r|e| | |%|2|0|H|a|p|p|y|\0|
     *                                ↑    ↑
     *                            indexOld indexNew
     * 接着复制，直到碰到第二个空格,
     *                      |W|e| | | |A|r|e|%|2|0|H|a|p|p|y|\0|
     *                          ↑    ↑
     *                     indexOld indexNew
     * 同上次一样，将“%20”插入在 indexNew 之前.并把 indexNew 往前移动3格
     *                      |W|e|%|2|0|A|r|e|%|2|0|H|a|p|p|y|\0|
     *                         ↑↑
     *                     indexOld
     *                     indexNew
     * 此时 indexOld 和 indexNew 指向同一个位置，表明所有空格已经替换完毕。
     * @param str 字符串
     * @return 替换之后的字符串
     */
    private static String replaceSpace1(StringBuffer str) {

        if (str==null || str.length()==0){
            return null;
        }
        //1.首先遍历字符串，求出空格数
        int spaceNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)==' '){
                spaceNum++;
            }
        }

        //2.计算出字符串替换之后的长度，确认数组下标
        int indexOld = str.length() - 1; //旧字符串最后一个字符的下标
        int newLength = str.length() + spaceNum * 2;//新字符串的长度
        int indexNew = newLength - 1;//新字符串最后一个字符的下标
        str.setLength(newLength);//重新设置字符串长度

        //进行替换
        for(;indexOld >= 0 && indexOld < indexNew;indexOld--){
            if (str.charAt(indexOld)==' '){
                str.setCharAt(indexNew--,'0');
                str.setCharAt(indexNew--,'2');
                str.setCharAt(indexNew--,'%');
            }else {
                str.setCharAt(indexNew--,str.charAt(indexOld));
            }
        }
        return str.toString();
    }

}

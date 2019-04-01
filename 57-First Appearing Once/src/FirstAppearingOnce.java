/**
 * @Author: cuizhe
 * @Date: 2019/4/1 17:25
 */
public class FirstAppearingOnce {
    /**
     * 题目：
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
     * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     */

    /**
     * 思路:
     * 因为使用字符，根据ASC码的规则，只有 256 个字符，所以我们建立一个数组长度的为 256 的整数数组 charArray;
     * 数组初始的时候，每一个数组下标对应一个字符，初始化都是0;
     * 同时有一个 index 标记，这个 index 用来表示如果字符 x 出现一次，那么 charArray[x] = index，
     * 同时下一次这个字符 x 再出现,那么 charArray[x] = -1;
     * 如果字符 k 在字符 x 后面，且出现了一次，由于 index 每次用完以后就 +1，
     * charArray[k] = index+1，这样就算k也出现了一次，
     * 但是我们可以通过比较 index 这个值，来确定谁先出现了，谁后出现了。
     */

    static int charArray[] = new int[256];
    static int index = 1;
    //Insert one char from stringstream
    public static void insert(char ch){
        if (charArray[ch]==0){
            charArray[ch] = index;
            index ++;
        }else {
            charArray[ch] = -1; //出现不止一次.置为 -1
        }
    }
    //return the first appearence once char in current stringstream
    public static char FirstAppearingOnce(){
        char result = '#';
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 256; i++) {
            if (charArray[i] > 0){
                //说明这些字符是第一次出现,我们需要找到 index 最小的。
                if (min > charArray[i]){
                    min = charArray[i];
                    result = (char) i;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "google";
        for (int i = 0; i < str.length(); i++) {
            insert(str.charAt(i));
            System.out.print(FirstAppearingOnce());
        }
    }
}

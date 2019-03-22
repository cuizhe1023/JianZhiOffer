import java.util.Arrays;

/**
 * @Author: cuizhe
 * @Date: 2019/3/22 8:36
 */
public class ThePokerCardIsContinuous {
    /**
     * 题目：
     * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
     * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
     * “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
     * LL不高兴了,他想了想,决定 大\小 王可以看成任何数字,并且 A 看作 1,J 为 11,Q 为 12,K 为13。
     * 上面的5张牌就可以变成 “1,2,3,4,5” (大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
     * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。
     * 如果牌能组成顺子就输出 true，否则就输出 false。为了方便起见,你可以认为大小王是0。
     */

    /**
     * 思路：
     * 我们把扑克牌看成一个数组，大小王是特殊数字，我们可以设为 0，这样就和其他牌区分开了。
     * 接下来我们需要判断是个数组是不是连续的，最直观的方法是把数组排序，
     * 由于 0 可以当成任意数字，我们可以用 0 去填补数组中空缺的数字。
     * 如果排序后的数组不是连续的,即相邻的两个数字相隔若干个数字。
     * 但只要我们有足够的 0 可以填满这两个数字之间的空缺，我们依然认为这个数组是连续的。
     * 比如说，给定数组 {0,1,3,4,5}，在 1 和 3 之间空缺了 2，刚好我们有一个0，我们依旧认为这个数组是连续的。
     * 因此，我们需要做三件事：
     * (1)对数组进行排序
     * (2)统计排序后 0 的个数
     * (3)最后统计排序之后的数组中相邻数字之间的空缺总数
     * 如果 空缺的总数 <= 0的个数，我们认为他是连续的；反之就不是连续的。
     * 最后，我们需要注意一点：如果数组中有两个相同的数字，则认为该数组不是连续的。有对子的话就肯定不是顺子。
     *
     * @param numbers 扑克牌的数组
     * @return 是否是顺子
     */
    public static boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length < 1){
            return false;
        }
        //对数组进行排序
        Arrays.sort(numbers);
        int zeroCount = 0;
        int needCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            //统计 0 的个数
            if (numbers[i]==0){
                zeroCount++;
            }else {
                if (i < numbers.length - 1){
                    //出现对子，不能算是顺子
                    if (numbers[i] == numbers[i+1]){
                        return false;
                    }
                    //统计相邻数字之间的空缺总数
                    needCount = needCount + (numbers[i+1] - numbers[i] -1 );
                }
            }
        }
        if (needCount <= zeroCount){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int a[] = {1,3,2,4,5};
        System.out.println(isContinuous(a));
    }
}

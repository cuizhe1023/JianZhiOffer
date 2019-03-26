/**
 * @Author: cuizhe
 * @Date: 2019/3/26 9:23
 */
public class Duplicate {

    /**
     * 题目：
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     */

    /**
     * 思路：
     * 笨办法，遍历
     *
     * @param numbers 整形数组
     * @param length 数组的长度
     * @param duplication 重复的数字
     * @return 将重复的数字保存在 duplication[0] 的位置
     */
    public static boolean duplicate_1(int numbers[],int length,int [] duplication) {
        if (numbers==null || length<=0){
            return false;
        }
        //判断数组是否合法
        for (int i = 0; i < length; i++) {
            if (numbers[i] < 0 || numbers[i] > length - 1){
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (numbers[i]==numbers[j]){
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 思路：
     * 上一个思路的时间复杂度比较高，可以通过 hash 法降低时间复杂度
     * 由于所有元素是有范围的，因此可以用一个长度为 n 的数组，下标表示序列中每一个值，下标对应的值表示该下标出现的次数。
     * 只需要扫描一次原数组，统计元素出现的次数
     * 再扫描一次 hash 数组，超出第一个出现次数大于 1 的，就可以了。
     *
     * @param numbers 整形数组
     * @param length 数组的长度
     * @param duplication 重复的数字
     * @return 将重复的数字保存在 duplication[0] 的位置
     */
    public static boolean duplicate_2(int numbers[],int length,int [] duplication) {
        if (numbers==null || length <=0){
            return false;
        }
        //判断是否有效
        for (int i = 0; i < length; i++) {
            if (numbers[i] < 0 || numbers[i]>length ){
                return false;
            }
        }

        //hash 数组
        int hash[] = new int[length];
        //统计原数组中每个元素出现的次数
        for (int i = 0; i < length; i++) {
            hash[numbers[i]]++;
        }
        for (int i = 0; i < hash.length; i++) {
            if (hash[i]>1){
                duplication[0] = i;
                return true;
            }
        }
        return false;
    }

    /**
     * 思路：
     * 第二个思路的时间复杂度虽然比第一个低，但是需要开辟额外的空间。
     * 那有没有时间复杂度低，而且也不用开辟额外空间的方法呢?
     * 我们可以对现有数组设置标志，当一个数字被访问过后，可以设置对应位上的数 + n
     * 之后再遇到相同的数时，会发现对应位上的数已经大于等于 n 了，那么直接返回这个数即可。
     *
     * 这个算法虽然时间复杂度低，但是会改变原来数组的元素。
     *
     * @param numbers 整形数组
     * @param length 数组的长度
     * @param duplication 重复的数字
     * @return 将重复的数字保存在 duplication[0] 的位置
     */
    public static boolean duplicate_3(int numbers[],int length,int [] duplication) {
        if (numbers == null || length <= 0){
            return false;
        }
        for (int i = 0; i < length; i++) {
            if ((numbers[i] < 0)||(numbers[i] > length)){
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            int index = numbers[i];
            if (index >= length){
                index -= length;
            }
            if (numbers[index] >= length){
                duplication[0] = index;
                return true;
            }
            numbers[index] = numbers[index] + length;
        }
        return false;
    }


    public static void main(String[] args) {
        int a[] = {2,3,1,0,2,5,3};
        int duplicate[] = {0};
        System.out.println(duplicate_3(a,a.length,duplicate)+","+duplicate[0]);
    }

}

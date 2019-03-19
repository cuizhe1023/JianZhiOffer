import java.util.*;

/**
 * @Author: cuizhe
 * @Date: 2019/3/19 15:22
 */
public class FindNumsAppearOnce {
    /**
     * 题目：
     * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
     *
     * num1,num2分别为长度为1的数组。传出参数
     * 将num1[0],num2[0]设置为返回结果
     */

    /**
     * 思路：
     * 通过 Map 存储数字出现的次数。之后在找到出现次数为 1 的。
     *
     * @param array 目标数组
     */
    public static void FindNumsAppearOnce_1(int [] array,int num1[] , int num2[]) {
        if (array==null || array.length<=0){
            return ;
        }
        if (array.length<=2){
            num1[0] = 0;
            num2[0] = 0;
            return ;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])){
                map.put(array[i],map.get(array[i])+1);
            }else {
                map.put(array[i],1);
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (map.get(array[i])==1){
                stack.add(array[i]);
            }
        }
        num1[0] = stack.pop();
        num2[0] = stack.pop();
        System.out.println(num1[0]+" "+num2[0]);
    }

    /**
     * 思路：
     * 通过 set 进行数字的存储，当存在时，进行 remove.
     *
     * @param array 目标数组
     */
    public static void FindNumsAppearOnce_2(int [] array,int num1[] , int num2[]) {
        if (array==null || array.length<=0){
            return ;
        }
        if (array.length<=2){
            num1[0] = 0;
            num2[0] = 0;
            return ;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (set.contains(array[i])){
                set.remove(array[i]);
            }else {
                set.add(array[i]) ;
            }
        }
        for (Integer i :
                set) {
            System.out.print(i+" ");
        }
    }

    /**
     * 思路:
     * 先介绍个知识点：
     * 位运算中异或的性质：两个相同数字异或=0，一个数和0异或还是它本身。
     * 先看看这个问题，如何在一个数组中找到一个只出现一次的数字呢？比如数组：{4,5,5}，唯一一个只出现一次的数字是4。
     * 我们从头到尾依次异或数组中的每一个数字，那么最终的结果刚好是那个只出现一次的数字。
     * 我们先用数组中的第一个元素4（二进制形式：0100）和数组中的第二个元素5（二进制形式：0101）进行异或操作，
     * 0100和0101异或得到0001，用这个得到的元素与数组中的三个元素5（二进制形式：0101）进行异或操作，
     * 0001和0101异或得到0100，正好是结果数字4。
     * 如果，我们可以将原始数组分成两个子数组，使得每个子数组包含一个只出现一次的数字，而其他数字都成对出现。
     * 这样，我们就可以用上述方法找到那个只出现一次的数了。
     * 现在我们考虑如何分成两个数组
     * 我们从头到尾异或数组中的每一个数字，那么最终得到的结果就是两个只出现一次的数组的异或结果。
     * 因为其他数字都出现了两次，在异或中全部抵消了。
     * 由于两个数字肯定不一样，那么异或的结果肯定不为0，也就是说这个结果数组的二进制表示至少有一个位为1。
     * 我们在结果数组中找到第一个为 1 的位 的位置，记为第n位。
     * 现在我们以第n位是不是 1 为标准把元数组中的数字分成两个子数组，
     * 第一个子数组中每个数字的第n位都是 1，而第二个子数组中每个数字的第n位都是0。
     *
     * 以{2,4,3,6,3,2,5,5}为例，
     * 元素2（二进制形式：0010）
     * 元素4（二进制形式：0100）
     * 元素3（二进制形式：0011）
     * 元素6（二进制形式：0110）
     * 元素3（二进制形式：0011）
     * 元素2（二进制形式：0010）
     * 元素5（二进制形式：0101）
     * 元素5（二进制形式：0101）
     * 进行异或运算，得到的结果为：0010.
     * 异或得到结果中的倒数第二位是1，于是我们根据数字的倒数第二位是不是1分为两个子数组。
     * 第一个子数组{2,3,6,3,2}中所有数字的倒数第二位都是1，而第二个子数组{4,5,5}中所有数字的倒数第二位都是0。
     * 接下来只要分别两个子数组求异或，就能找到第一个子数组中只出现一次的数字是6，而第二个子数组中只出现一次的数字是4。
     *
     * @param array
     * @param num1
     * @param num2
     */
    public static void FindNumsAppearOnce_3(int[] array, int num1[], int num2[]) {
        if (array == null || array.length == 0) {
            return;
        }
        if (array.length < 2) {
            num1[0] = 0;
            num2[0] = 0;
            return;
        }
        int bitResult = 0;
        for (int i = 0; i < array.length; i++) {
            bitResult = bitResult ^ array[i];
        }
        int firstBit = 0;
        while ((bitResult & 1)==0 && firstBit < 32){
            firstBit++;
            bitResult = bitResult >> 1;
        }
        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < array.length; i++) {
            if (isBit(array[i],firstBit)){
                num1[0] = num1[0] ^ array[i];
            }else {
                num2[0] = num2[0] ^ array[i];
            }
        }
        System.out.println(num1[0]+" "+num2[0]);
    }

    private static boolean isBit(int i, int firstBit) {
        return ((i >> firstBit ) & 1) == 1;
    }


    public static void main(String[] args) {
        int[] a = {2,4,3,7,3,2,5,5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        FindNumsAppearOnce_3(a,num1,num2);
    }
}

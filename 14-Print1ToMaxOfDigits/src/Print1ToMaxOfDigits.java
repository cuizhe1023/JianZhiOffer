/**
 * @Author: cuizhe
 * @Date: 2019/2/26 17:24
 */
public class Print1ToMaxOfDigits {
    /**
     * 题目：
     * 输入数字 n，按顺序打印出 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。
     */

    /**
     * 思路：
     * 确定了最大数后，进行遍历输出
     * @param n n位数
     */
    public static void printToMaxOfDigits_1(int n){
        if(n <= 0){
            System.out.println("输入的n没有意义");
            return;
        }
        int maxNumber = 1;
        int i = 0;
        while (i++<n){
            maxNumber = maxNumber *10;
        }
        for (int j = 1; j < maxNumber; j++) {
            System.out.print(j+"\t");
            if (j%10==0){
                System.out.println();
            }
        }
    }

    /**
     * 但是，上面方法有个缺点，就是鲁棒性不够强。
     * 当 n = 18 的时候就溢出了。
     * 因此，我们在遇到比长整型还大的数时，上面的代码是不能成功执行的，即基本类型已经存储不下的数字。
     * 这时我们需要考虑用字符串或者数组来表示大数。
     * 因为最大是 n 位的，因此需要一个长度为 n+1 的字符串。
     * 首先我们把字符串初始化，每个数字都初始化为 '0' 。然后每一次为字符串表示的数字加 1，在打印。
     * 因此，我们需要做两件事：1.在字符串表达的数字上模拟加法；2.把字符串表达的数字打印出来。
     *
     * @param n n 位数
     */
    public static void printToMaxOfDigits(int n){
        if(n <= 0){
            System.out.println("输入的n没有意义");
            return;
        }
        //声明字符数组,用来存放一个大数
        char number[] = new char[n];
        for (int i = 0; i < number.length; ++i) { //放字符 '0' 进行初始化
            number[i] = '0';
        }
        while(!incrementNumber(number)){ //如果大数自加，直到自溢退出
            printNumber(number); //打印大数
        }
    }

    /**
     * 在字符串表达的数字上模拟加法。
     * 设置 是否溢出标志，是否进位标志。
     * 1.遍历这个字符数组，在末位进行 +1 操作。
     * 如果末位字符在 +1 后变为不小于 10 的数字，我们将末尾减去 10 加上 '0' 字符赋值为末位，进位标志设为 1。
     * 在循环位次时 +1，然后在判断是否小于 10,是的话重复上述步骤。直到判断高位是否小于0，是的话数组溢出。
     * 2.如果末位字符 +1 后依旧小于 10，直接加上 '0' 赋值给末位，跳出当前循环，返回没有溢出。
     *
     * @param number 字符串数组
     * @return 是否溢出
     */
    public static boolean incrementNumber(char[] number) {
        boolean isOverflow = false;//是否溢出标志
        int nTakeOver = 0;//是否进位标志
        for (int i = number.length - 1; i >= 0 ; i--) {
            int nSum = number[i]-'0'+nTakeOver;//nSum 是数组中每个位置的数。将其转换为数字
            if (i == number.length - 1){//如果是数组最后一个位置，则 nSum 进行自加。
                nSum++;
            }
            if (nSum>=10){
                if(i==0){
                    //如果 i=0 了，表示已经到了数组 0 下标的位置，说明已经溢出。
                    isOverflow = true;
                }else {
                    nSum = nSum - 10;
                    nTakeOver = 1;
                    number[i] = (char)('0'+nSum);
                }
            }else {
                number[i] = (char)('0'+nSum);
                break;
            }
        }
        return isOverflow;
    }


    /**
     * 打印大数
     * 字符数组每个数字都初始化为 '0'，为了符合人们的习惯，我们只有在碰到第一个非 0 的数字时，才开始打印直至字符串结尾。
     *
     * @param number 字符串数组
     */
    private static void printNumber(char[] number) {
        boolean isBeginning0 = true;
        for (int i = 0; i < number.length; i++) {
            if (isBeginning0 && number[i]!='0'){
                isBeginning0 = false;
            }
            if (!isBeginning0){
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }

    /**
     * 用数字排列的方法表示：如果我们在数字前面补 0 的话，就会发现 n 位所有十进制数其实就是 n 个从 0 到 9 的全排列。
     * 也就是说，我们把数字的每一位都从 0 到 9 排列一遍，就得到了所有的十进制数。
     * 全排列用递归很容易实现，数字的每一位都可能是 0-9 中的一个数，然后设置好下一位。
     * 递归结束的条件是我们已经设置了数字的最后一位。
     * 当然打印的时候，我们应该将前面的 0 补位去掉。
     *
     * @param n n 位数
     */
    private static void printToMaxOfDigits_2(int n) {
        if (n<=0){
            System.out.println("输入的n没有意义");
            return ;
        }
        char[] number = new char[n];
        for (char ch :
                number) {
            ch = '0';
        }
        for (int i = 0; i < 10; i++) {
            number[0] = (char)('0'+i);
            printToMaxOfNDigitsRecursively(number,n,0);
        }
    }

    private static void printToMaxOfNDigitsRecursively(char[] number,int n,int index) {
        if (index==n-1){
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index+1] = (char)('0'+i);
            printToMaxOfNDigitsRecursively(number,n,index+1);
        }
    }

    public static void main(String[] args) {
        printToMaxOfDigits_2(3);
    }

}

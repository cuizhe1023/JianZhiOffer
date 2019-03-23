import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/23 11:26
 */
public class LastNumberInCircle {

    /**
     * 题目:
     * 0, 1, … , n-1 这 n 个数字排成一个圈圈，从数字 0 开始每次从圆圏里删除第 m 个数字。
     * 求出这个圈圈里剩下的最后一个数字。
     */

    /**
     * 思路：
     * 约瑟夫环问题
     * 经典解法，当你模拟了整个过程之后，就可以得到结果
     * 创建一个总共有 n 个结点的环形链表，然后每次在这个链表中删除第 m 个结点
     * 我们可以用 LinkedList 模拟环。
     * 但是 LinkedList 的实现是链表，怎么实现环呢？
     * 环：    0
     *      ↗     ↘
     *     5       1
     *     ↑       ↓
     *     4 ← 3 ← 2
     * 链表：
     * 0 → 1 → 2 → 3 → 4 → 5
     * 环的结点数为 6 = 链表的结点数
     *
     * 从 0 开始，当下标为 5 的时候，在 +1 就回到了 0。
     * index 表示下标。初始下标为 0
     * index = (index + m - 1) % size;
     * -1 是因为 list 是从 0 开始计的
     * index + m - 1 是计算 以当前的index为基础，再往前数（m-1）个数
     * 如果 index + m - 1 大于链表长度的话，会发生越界，因此用取余来形成环。
     *
     * 0 → 1 → 2 → 3 → 4 → 5
     * list:  size = 6 , m = 3 , index = 0
     * index = (index + m -1) % size
     *       = (0 + 3 - 1) % 6
     *       = 2 % 6 = 2;
     * 删除第 2 个元素，第 2 个元素为 2
     *
     * 0 → 1 → 3 → 4 → 5
     * list:  size = 5 , m = 3 , index = 2
     * index = (index + m -1) % 5
     *       = (2 + 3 - 1) % 5
     *       = 4 % 5 = 4;
     * 删除第 4 个元素，第 4 个元素是 5
     *
     * 0 → 1 → 3 → 4
     * list:  size = 4 , m = 3 , index = 4
     * index = (index + m -1) % 4
     *       = (4 + 3 - 1) % 4
     *       = 6 % 4 = 2;
     * 删除第 2 个元素，第 2 个元素是 3
     *
     * 0 → 1 → 4
     * list:  size = 3 , m = 3 , index = 2
     * index = (index + m -1) % 3
     *       = (2 + 3 - 1) % 3
     *       = 4 % 3 = 1;
     * 删除第 1 个元素，第 1 个元素是 1
     *
     * 0 → 4
     * list:  size = 2 , m = 3 , index = 1
     * index = (index + m -1) % 2
     *       = (1 + 3 - 1) % 2
     *       = 3 % 2 = 1;
     * 删除第 1 个元素，第 1 个元素是 4
     *
     * 此时只剩下了 0 ，即为所求
     *
     *
     * @param n n 个数字
     * @param m 第 m 个删除
     * @return 剩下的最后一个数字
     */
    public static int lastRemaining_1(int n, int m) {
        if (n<=0 || m <=0){
            return -1;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1){
            // 第一次删掉的位置是从 0 开始数 m - 1 个位置，以后每次从删掉的下一个节点开始取.
            // 所以每次要在的索引处加上 m-1，因为是环，所以加了以后对链表长度取余
            index = (index + m - 1)%list.size();
            System.out.println("size:"+list.size()+",index:"+index + ",remove:" + list.get(index));

            list.remove(index);
        }
        if (list.size() == 1){
            return list.get(0);
        }
        return -1;
    }


    /**
     * 思路：
     * 上面的思路虽然很容易理解，但是时间复杂度却很高，当 n，m 都比较大的时候，达到了 O(nm).
     * 我们可以用一点数学上的技巧，将最后结果推导出来。
     * 首先，我们定义一个关于 n 和 m 的方程 f(n,m),
     * 表示每次在 n 个数字 0,1,2,3,...,n - 1 中每次删除的第 m 个数字最后剩下的数字
     * 我们把这n个人的序号编号从 0 ~ n-1
     * 第一次出列：
     * 因为是从 0 开始计算的，所以应该是第 (m - 1) % n1(n1 为当前序列的总人数)个出列。
     * 在第一个人出列后，从他的下一个人又开始从 0 报数了，为了方便起见我们设为 k1 = (m - 1) % n1。
     * 那么，在第一个人出列后，k1 + 1 为下一次新的编号序列的首位元素，我们得到的新的编号序列为：
     * k1 + 1,k1 + 2,....,n - 2,n - 1,0,1,2,...,k1 - 2,k1 - 1  (k1 第一次已经出列了)。
     * 该序列最后剩下的数字也应该是关于 n 和 m 的函数。
     * 由于这个序列的规律和前面最初的序列不一样(最初的序列是从 0 开始的连续序列)
     * 因此不同于前面的函数 f(n,m)，记为 f'(n,m)
     * 最初序列最后剩下的数字 f(n,m) 一定是删除一个数字之后的序列剩下的数字，即 f(n,m) = f'(n,m)
     * 接下来，我们把剩下的这 n - 1 个数字的序列 k + 1,...,n - 1,0,1,...,k - 1 做一个映射
     * 映射的结果是形成一个从 0 到 n - 2 的序列：
     * k + 1  →  0
     * k + 2  →  1
     * ...
     * n - 1  →  n - k - 2
     * 0      →  n - k - 1
     * 1      →  n - k
     * ...
     * k - 1  →  n - 2
     * 我们把映射定义为 p，则 p(x) = (x - k -1) % n。该映射的逆映射是 p(逆) = (x + k + 1) % n
     * 由于映射之后的序列和最初的序列具有同样的形式，即都是从 0 开始的连续序列。
     * 因此仍然可以用函数 f 表示,记为 f(n - 1,m)。
     * 根据我们的映射规则，映射之前的序列中最后剩下的数字
     * f'(n - 1,m) = p[f(n - 1,m)] = [f(n - 1,m) + k + 1] % n
     * 把 k = (m - 1) % n 代入得到 f(n,m) = f'(n - 1,m) = [f(n - 1,m) + m] % n
     *
     * 终于，我们推到到了一个递推公式
     *          |---    0                 ,n = 1
     * f(n,m) = |
     *          |--- [f(n - 1,m) + m] & n ,n > 1
     *
     * @param n n 个数字
     * @param m 第 m 个删除
     * @return 剩下的最后一个数字
     */
    public static int lastRemaining_2(int n, int m) {
        if (n < 1 || m < 1){
            return -1;
        }
        int last = 0;
        for (int i = 2; i <=n; i++) {
            last = (last + m) %i;
        }
        return last;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining_2(6,3));
    }
}

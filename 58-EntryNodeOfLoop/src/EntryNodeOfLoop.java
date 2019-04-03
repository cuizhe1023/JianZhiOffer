import List.singlelinkdelist.ListNode;
import List.singlelinkdelist.MyList;

/**
 * @Author: cuizhe
 * @Date: 2019/4/3 9:13
 */
public class EntryNodeOfLoop {
    /**
     * 题目：
     * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
     */

    /**
     * 思路：
     * 1.首先判断链表是否为环
     *      可以通过快慢指针进行判断，快指针每次走两步，慢指针每次走一步。
     *      若链表中有环，则快慢指针肯定会在环内相遇。
     * 2.其次找出环的长度
     *      再从快慢指针相遇的结点往下遍历。由于这个结点是在环内，
     *      因此慢指针肯定会回到这个结点，记录步数算出环的长度
     * 3.找出环的入口
     *      知道了环的长度之后，再次利用快慢指针的思想。
     *      两个指针同时指向链表的头结点，快指针先走环的长度，然后快慢指针一起走。
     *      这样，因为快指针已经走了环的长度了，当两者相遇的时候肯定就在环的入口了。
     *      (为啥呢？举个例子
     *      下图中链表长 x = 8，环的长度 w = 6；
     *      m 为慢指针，n 为快指针
     *               ←  ←  ←  ←  ←  ←  ←
     *              ↓                   ↑
     *      1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
     *     ↑↑
     *     mn
     *
     *     快指针 n 先走 6 步:
     *              ←  ←  ←  ←  ←  ←  ←
     *             ↓                   ↑
     *     1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
     *     ↑                       ↑
     *     m                       n
     *
     *     之后快慢指针同时走一步，
     *              ←  ←  ←  ←  ←  ←  ←
     *             ↓                   ↑
     *     1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
     *         ↑                       ↑
     *         m                       n
     *
     *     之后快慢指针再同时走一步，
     *              ←  ←  ←  ←  ←  ←  ←
     *             ↓                   ↑
     *     1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
     *             ↑↑
     *             mn
     *     就同时到达了环的入口)
     *
     * @param pHead 环的头结点
     * @return 环的入口结点
     */
    public static ListNode entryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null){
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = isLoop(pHead);
        if (slow != null){
            int loopLength = loopLength(slow);
            while (loopLength > 0){
                fast = fast.next;
                loopLength--;
            }
            slow = pHead;
            while (slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }

    /**
     * 判断链表是否有环
     * @param pHead 链表的头结点
     * @return 返回快慢指针同时指向的结点
     */
    private static ListNode isLoop(ListNode pHead){
        //先判断是否是合法
        if (pHead == null || pHead.next == null){
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return slow;
            }
        }
        return null;
    }

    private static int loopLength(ListNode pHead){
        int length = 1;
        ListNode temp = pHead.next;
        while (temp != pHead){
            temp = temp.next;
            length++;
        }
        return length;
    }

    /**
     * 环为：
     *               ←  ←  ←  ←  ←  ←  ←
     *              ↓                   ↑
     *      1 → 2 → 3 → 4 → 5 → 6 → 7 → 8
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node3;
        MyList myList = new MyList();
        myList.setHead(node1);
        System.out.println("链表中环的入口为："+entryNodeOfLoop(myList.getHead()).val);
    }
}

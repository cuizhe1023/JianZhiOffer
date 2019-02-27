import List.ListNode;
import List.MyList;

/**
 * @Author: cuizhe
 * @Date: 2019/2/27 21:46
 */
public class FindKthToTail {
    /**
     * 题目：
     * 输入一个链表，输出该链表中倒数第k个结点。
     */

    /**
     * 思路：
     * 计算链表的长度 length，然后用 length-k+1，获取正向索引。
     * 再通过计数器获取结点，
     *
     * @param head 头结点
     * @param k 索引，倒数低k个
     * @return 结点
     */
    public static ListNode FindKthToTail_1(ListNode head,int k) {
        int listLength = length(head);
        int index = length(head)-k+1;//正向索引
        System.out.println("listLength:"+listLength+",index:"+index);
        if (index < 1 || index > listLength){
            return null;
        }
        if (head==null){
            return null;
        }
        if (index==1){//获取第一个
            return head;
        }
        ListNode curNode = head;
        int length = 1;
        while (curNode.next!=null){
            length++;
            if (index==length){
                return curNode.next;
            }
            curNode = curNode.next;
        }
        return null;
    }

    private static int length(ListNode head){
        int length = 0;
        ListNode curNode = head;
        while (curNode !=null){
            length++;
            curNode = curNode.next;
        }
        return length;
    }

    /**
     * 另一种想法就是，设置两个指针，第一个指向头结点，第二个指向第k个结点，之后返回第一个指针的结点
     *
     * @param head 头结点
     * @param k 索引，倒数低k个
     * @return 结点
     */
    public static ListNode FindKthToTail_2(ListNode head,int k) {
        if (head==null || k<=0){
            return null;
        }
        ListNode pre = head;
        ListNode end = head;
        //先让后面那个指针指向第 k 个位置
        for (int i = 1; i < k; i++) {
            end = end.next;
        }
        while (end.next!=null){
            pre = pre.next;
            end = end.next;
        }
        return pre;
    }

    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.insert(1);
        myList.insert(4);
        myList.insert(7);
        myList.insert(9);
        myList.insert(6);
        myList.insert(3);
        myList.printList();
        ListNode node = FindKthToTail_2(myList.getHead(),4);
        System.out.println(node.val);
    }

}

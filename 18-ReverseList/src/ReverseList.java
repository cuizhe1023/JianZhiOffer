import List.ListNode;
import List.MyList;

/**
 * @Author: cuizhe
 * @Date: 2019/2/28 16:13
 */
public class ReverseList {
    /**
     * 题目：
     * 输入一个链表，反转链表后，输出新链表的表头。
     */

    /**
     * 思路：
     * 非递归翻转链表，定义三个指针防止断链。
     *
     * @param head 头指针
     * @return 翻转后的头指针
     */
    public static ListNode reverseList(ListNode head) {
        ListNode preNode = null;//前驱结点
        ListNode curNode = head;//当前结点
        ListNode nextNode = null;//后继结点

        while (curNode!=null){
            nextNode = curNode.next;
            if (nextNode == null){
                head = curNode;
            }
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return head;
    }

    public static ListNode reverseListByRecursion(ListNode head){
        if (head==null || head.next==null){
            return head;
        }
        ListNode node = reverseListByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return node;
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
        ListNode head = reverseListByRecursion(myList.getHead());
        myList.setHead(head);
        myList.printList();
    }
}

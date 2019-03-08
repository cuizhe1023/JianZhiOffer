import List.singlelinkdelist.ListNode;
import List.singlelinkdelist.MyList;

/**
 * @Author: cuizhe
 * @Date: 2019/2/28 21:13
 */
public class MergeSortList {
    /**
     * 题目：
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     */

    /**
     * 思路：
     * 因为两个链表都是递增的，新建一个链表，设置两个指针同时比较大小，将小的添加给新链表。
     *  1->3->5->7->9
     * ↑
     * P1
     *  2->4->6->8->10
     * ↑
     * P2
     * 比较 p1 与 p2 的大小，将小的赋值给新链表
     *  3->5->7->9
     * ↑
     * P1
     *  2->4->6->8->10
     * ↑
     * P2
     *
     * mergeList: 1
     * 比较 p1 与 p2 的大小，将小的赋值给新链表
     *  3->5->7->9
     * ↑
     * P1
     *  4->6->8->10
     * ↑
     * P2
     * mergeList: 1->2
     * 以此类推，循环此操作，即可完成合并。

     * @param list1 链表1
     * @param list2 链表2
     * @return 合并后的链表的头结点
     */
    public static ListNode merge(ListNode list1, ListNode list2) {
        if (list1==null){
            return list2;
        }else if (list2==null){
            return list1;
        }
        ListNode mergeList = null;
        if (list1.val < list2.val){
            mergeList = list1;
            mergeList.next = merge(list1.next,list2);
        }else {
            mergeList = list2;
            mergeList.next = merge(list1,list2.next);
        }
        return mergeList;
    }

    public static void main(String[] args) {
        MyList list1 = new MyList();
        list1.insert(1);
        list1.insert(3);
        list1.insert(5);
        list1.insert(7);
        list1.insert(9);
        MyList list2 = new MyList();
        list2.insert(2);
        list2.insert(4);
        list2.insert(6);
        list2.insert(8);
        list2.insert(10);
        ListNode node = merge(list1.getHead(),list2.getHead());
        MyList list3 = new MyList();
        list3.setHead(node);
        list3.printList();
    }
}

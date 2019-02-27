import List.ListNode;
import List.MyList;

/**
 * @Author: cuizhe
 * @Date: 2019/2/27 20:47
 */
public class DeleteNodeFromList {
    /**
     * 题目：
     * 给定单项链表的头指针和一个结点指针，定义一个函数在 O(1) 的时间删除该结点。
     */

    /**
     * 思路：
     * 一般删除链表的结点，是需要通过遍历，找到该结点的前一个结点，再进行删除。这样的情况下时间复杂度就是 O(n)。
     * 既然规定时间复杂度得是 O(1)，那么我们可以做如下操作：
     * 1.将该结点的后一个结点的值赋给该结点.
     * 2.删除该结点的后一个结点。
     * 示例：     1->2->3->4->null  这里，我们需要删除 3 的结点，我们先将 3 后面的结点的值赋值给 3 ，
     * 则链表为： 1->2->4->4->null  接着删除后面的结点 1->2->4->null。
     * 还需要考虑的细节：
     * 1.链表只有一个结点：直接将该结点置为 null
     * 2.要删除的结点在最后一个位置：只能从头遍历。
     *
     * @param head 链表的头结点
     * @param node 要删除的结点
     */
    public static void DeleteNode(ListNode head,ListNode node){
        if (head==null || node==null){
            System.out.println("链表为空");
            return ;
        }

        //链表只有一个结点的情况
        if (head==node){
            head=null;
        }

        if (node.next!=null){
            //删除的结点不是尾结点的情况
            node.val = node.next.val;
            ListNode curNode = node.next;
            node.next = curNode.next;
            curNode.next = null;
        }else {
            //删除的结点是尾结点的情况
            ListNode curNode = head;
            while (curNode.next!=node){
                curNode = curNode.next;
            }
            curNode.next = null;
        }
    }

    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.insert(1);
        myList.insert(3);
        myList.insert(4);
        myList.insert(5);
        myList.insert(7);
        myList.insert(8);
        ListNode node4 = myList.getNodeByIndex(3);
        myList.printList();
        DeleteNode(myList.getHead(),node4);
        myList.printList();
        ListNode node8 = myList.getNodeByIndex(5);
        DeleteNode(myList.getHead(),node8);
        myList.printList();
    }
}

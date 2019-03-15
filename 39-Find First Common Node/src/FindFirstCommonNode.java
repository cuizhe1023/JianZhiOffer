import List.singlelinkdelist.ListNode;
import List.singlelinkdelist.MyList;

import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/3/15 9:42
 */
public class FindFirstCommonNode {
    /**
     * 题目：
     * 输入两个链表，找出它们的第一个公共结点。
     */

    /**
     * 思路：
     * 暴力解法，在第一个链表上顺序遍历，每遍历一个结点的时候，在第二个链表上顺序遍历每个结点。
     * 如果在第二个链表上有一个结点和第一个链表上的结点一样，说明两个链表重合。
     *
     * @param pHead1 链表 1 的头结点
     * @param pHead2 链表 2 的头结点
     * @return 公共结点
     */
    public static ListNode findFirstCommonNode_1(ListNode pHead1, ListNode pHead2) {
       if (pHead1==null || pHead2==null){
           return null;
       }
       ListNode list1Head = pHead1;
       while (list1Head.next!=null){
           ListNode list2Head = pHead2;
           while (list2Head.next != null){
               if (list1Head.val == list2Head.val){
                   return list2Head;
               }
               list2Head = list2Head.next;
           }
           list1Head = list1Head.next;
           System.out.println("pHead1:"+pHead1.val);
       }
       return null;
    }

    /**
     * 思路:
     * 如果两个链表有公共结点，那么他们应该是像 Y 型，而不是 X 型，他们会在公共点重合。
     * 我们可以从后往前比较，最后一个相同的结点就是我们所要找的公共点。
     * 因为单链表是单向的，我们只能从头遍历，但是最后到达的尾结点却需要先进行比较。
     * 所以是“后进先出”，我们可以通过辅助栈来进行比较。
     * 两个链表同时进栈，然后出栈时进行对比，最后一个就是公共结点。
     *
     * @param pHead1 链表 1 的头结点
     * @param pHead2 链表 2 的头结点
     * @return 公共结点
     */
    public static ListNode findFirstCommonNode_2(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null||pHead2==null){
            return null;
        }
        LinkedList<ListNode> stack1 = new LinkedList<>();
        LinkedList<ListNode> stack2 = new LinkedList<>();
        ListNode commonNode = null;

        while (pHead1!=null){
            stack1.push(pHead1);
            pHead1 = pHead1.next;
        }

        while (pHead2!=null){
            stack2.push(pHead2);
            pHead2 = pHead2.next;
        }

        while (!stack1.isEmpty() && !stack2.isEmpty()){
            if (stack1.peek() == stack2.peek()){
                commonNode = stack2.pop();
                stack1.pop();
            }else {
                break;
            }
        }
        return commonNode;
    }

    /**
     * 思路：
     * 除了用栈的方法以外，我们还可以同时遍历两个链表
     * 先比较两个链表的长短，让长的先走几步，然后同时进行遍历，节省了空间的开销。
     *
     * @param pHead1 链表 1 的头结点
     * @param pHead2 链表 2 的头结点
     * @return 公共结点
     */
    public static ListNode findFirstCommonNode_3(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null||pHead2==null){
            return null;
        }
        int list1Length = getLength(pHead1);
        int list2Length = getLength(pHead2);
        int lengthDiff = list1Length - list2Length;
        ListNode longList = pHead1;
        ListNode shortList = pHead2;
        if (lengthDiff<0){
            longList = pHead2;
            shortList = pHead1;
            lengthDiff = list2Length - list1Length;
        }
        for (int i = 0; i < lengthDiff; i++) {
            longList = longList.next;
        }
        while ( longList!=null && shortList!=null && longList!=shortList){
            longList = longList.next;
            shortList = shortList.next;
        }
        ListNode commonNode = longList;

        return commonNode;
    }

    private static int getLength(ListNode pHead) {
        int length = 0;
        ListNode node = pHead;
        while (node!=null){
            length++;
            node = node.next;
        }
        return length;
    }

    /**
         * 如果两个链表有公共结点，那么他们应该是像 Y 型，而不是 X 型。
         * list1: 1 → 2 → 3 ↘
         *                   6 → 7 → 8
         * list2:     4 → 5 ↗
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

        MyList list1 = new MyList();
        list1.insert(node1);
        list1.insert(node2);
        list1.insert(node3);
        list1.insert(node4);
        list1.insert(node7);
//        list1.insert(node8);
        list1.printList();
        MyList list2 = new MyList();
        list2.insert(node5);
        list2.insert(node6);
        list2.insert(node7);
        list2.printList();
        System.out.println(findFirstCommonNode_3(list1.getHead(),list2.getHead()).val);
    }
}

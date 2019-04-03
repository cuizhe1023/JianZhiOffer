import List.singlelinkdelist.ListNode;
import List.singlelinkdelist.MyList;

/**
 * @Author: cuizhe
 * @Date: 2019/4/3 15:27
 */
public class DeleteDuplicationNode {

    /**
     * 题目：
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     */

    /**
     * 思路：
     * 设置两个指针 preNode 和 curNode，preNode 指前一个结点，curNode 指当前结点。
     * 判断 curNode 和 curNode.next 的值是否相同：
     *      如果相同，curNode 往后移动，preNode 不动，直到遇到 curNode 和 curNode.next 的值不相同的，
     *      preNode就可以指向curNode.next了
     *      如果不相同，则 preNode 指向 curNode，curNode 指向下一个结点。
     *
     * @param pHead 链表的头结点
     * @return 删除重复结点后的头结点
     */
    public static ListNode deleteDuplication_1(ListNode pHead){
        if (pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode preNode = null;//链表开头可能就有重复的结点，所以默认 preNode = null
        ListNode curNode = pHead;
        while (curNode != null){
            if (curNode.next != null && curNode.next.val == curNode.val){
                int value = curNode.val;
                while (curNode.next != null && curNode.next.val == value){
                    curNode = curNode.next;
                }
                //在这里需要给 preNode 赋值的时候，如果 preNode == null 则说明链表开头就有重复的结点
                //则把 curNode.next 赋值给 pHead
                if (preNode == null){
                    pHead = curNode.next;
                }else {
                    preNode.next = curNode.next;
                }
            }else {
                preNode = curNode;
            }
            curNode = curNode.next;
        }
        return pHead;
    }

    /**
     * 思路：
     * 递归实现，删除目前指针向后的第一段连续节点，保证下一次递归的头结点与之后的不重复。
     *
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication_2(ListNode pHead){
        if (pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode curNode = pHead.next;
        //判断是否开始重复
        if (pHead.val == curNode.val){
            curNode = curNode.next;
            while (curNode != null && curNode.val == pHead.val){
                curNode = curNode.next;
            }
            return deleteDuplication_2(curNode);
        }else {
            pHead.next = deleteDuplication_2(curNode);
            return pHead;
        }
    }

    /**
     * 链表：
     * 1 → 2 → 3 → 3 → 4 → 4 → 5
     * @param args
     */
    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.insert(1);
        myList.insert(2);
        myList.insert(3);
        myList.insert(3);
        myList.insert(3);
        myList.insert(3);
        myList.insert(4);
        myList.insert(4);
        myList.insert(5);
        myList.printList();
        myList.setHead(deleteDuplication_2(myList.getHead()));
        myList.printList();
    }

}

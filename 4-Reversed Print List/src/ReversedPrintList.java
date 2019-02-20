import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: cuizhe
 * @Date: 2019/2/20 21:33
 */
public class ReversedPrintList {

    /*
    输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     */
    static ArrayList<Integer> arrayList = new ArrayList<>();
    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.insert(1);
        myList.insert(2);
        myList.insert(3);
        myList.insert(4);
        myList.insert(5);
        myList.insert(6);
        myList.printList();
        System.out.println(printListFromTailToHead1(myList.getHead()));
    }

    /**
     * 通过栈实现链表的逆序输出
     * @param listNode 目标链表
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode==null){
            return null;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        ListNode curNode = listNode;
        while (curNode!=null){
            stack.push(curNode.val);
            curNode = curNode.next;
        }
        while (!stack.isEmpty()){
            arrayList.add(stack.pop().intValue());
        }
        return arrayList;
    }

    /**
     * 通过递归实现链表的逆序输出
     * @param listNode 链表
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        if (listNode!=null){
            printListFromTailToHead1(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

}

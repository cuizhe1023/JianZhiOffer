import java.util.HashMap;

/**
 * @Author: cuizhe
 * @Date: 2019/3/5 21:30
 */
public class CloneComplexList {
    /**
     * 题目：
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）
     * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     */

    /**
     * 思路：
     * 先通过 next 指针，将整个链表构建出来，之后在去确定特殊指针。
     * (1).
     *     ↓ ← ← ← ↑
     *     ↓       ↑
     * 1 → 2 → 3 → 4 → 5
     * ↓   ↓   ↑       ↑
     * ↓ → ↓ → ↑       ↑
     *     ↓ → → → → → ↑
     *
     * (2).
     *          ↓ ← ← ← ← ← ← ← ← ↑
     *          ↓                 ↑
     * 1 → 1` → 2 → 2` → 3 → 3` → 4 → 4` → 5 → 5`
     * ↓        ↓        ↑                 ↑
     * ↓ → → → → → → → → ↑                 ↑
     *          ↓                          ↑
     *          ↓ → → → → → → → → → → → → →↑
     *
     * (3).
     *              ↓ ← ← ← ← ← ← ← ← ↑
     *          ↓ ← ↓ ← ← ← ← ← ← ↑   ↑
     *          ↓   ↓             ↑   ↑
     * 1 → 1` → 2 → 2` → 3 → 3` → 4 → 4` → 5 → 5`
     * ↓   ↓    ↓   ↓    ↑   ↑             ↑   ↑
     * ↓ → ↓ → →↓→ →↓→ → ↑   ↑             ↑   ↑
     *     ↓ → →↓→ →↓→ → → → ↑             ↑   ↑
     *          ↓   ↓ → → → → → → → → → → →↑ → ↑
     *          ↓ → → → → → → → → → → → → →↑
     *
     * @param pHead 复杂指针的头结点
     * @return 复制后的头结点
     */
    public static RandomListNode clone_1(RandomListNode pHead){
        if (pHead==null){
            return null;
        }
        RandomListNode curNode = pHead;
        //复制链表，如图（1）
        while (curNode!=null){
            RandomListNode cloneNode = new RandomListNode(curNode.label);
            cloneNode.next = curNode.next;
            curNode.next = cloneNode;
            curNode = curNode.next.next;
        }
        //复制特殊结点（2）
        curNode = pHead;
        RandomListNode cloneNode;
        while (curNode!=null){
            cloneNode = curNode.next;
            cloneNode.random = (curNode.random==null)?null:curNode.random.next;
            curNode = curNode.next.next;
        }
        //将链表拆开
        curNode = pHead;
        RandomListNode newHead = curNode.next;
        while (curNode!=null){
            cloneNode = curNode.next;
            curNode.next = curNode.next.next;
            curNode = curNode.next;
            cloneNode.next = (curNode==null)?null:curNode.next;
        }
        return newHead;
    }

    /**
     * 思路：
     * 通过 HashMap 存放结点信息。保存他的特殊指针的位置。
     *
     * @param pHead 复杂指针的头结点
     * @return 复制后的头结点
     */
    private static RandomListNode clone_2(RandomListNode pHead){
        if (pHead==null){
            return null;
        }
        HashMap<RandomListNode,RandomListNode> map = new HashMap<>();
        RandomListNode curNode = pHead;
        //将映射保存
        while (curNode!=null){
            RandomListNode cloneNode = new RandomListNode(curNode.label);
            map.put(curNode,cloneNode);
            curNode = curNode.next;
        }
        curNode = pHead;
        while (curNode!=null){
            RandomListNode cloneNode = map.get(curNode);
            cloneNode.next = (curNode.next==null)?null:map.get(curNode.next);
            cloneNode.random = (curNode.random==null)?null:map.get(curNode.random);
            curNode = curNode.next;
        }
        return map.get(pHead);
    }

    /**
     *     ↓ ← ← ← ↑
     *     ↓       ↑
     * 1 → 2 → 3 → 4 → 5
     * ↓   ↓   ↑       ↑
     * ↓ → ↓ → ↑       ↑
     *     ↓ → → → → → ↑
     *
     */
    public static void main(String[] args) {
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        RandomListNode node5 = new RandomListNode(5);
        MyRandomList list = new MyRandomList();
        list.insertNode(node1);
        list.insertNode(node2);
        list.insertNode(node3);
        list.insertNode(node4);
        list.insertNode(node5);
        list.insertRandomNode(node1,node3);
        list.insertRandomNode(node2,node5);
        list.insertRandomNode(node4,node2);
        list.print();

        MyRandomList cloneList = new MyRandomList();
        cloneList.setHead(clone_2(list.getHead()));
        cloneList.print();
    }
}

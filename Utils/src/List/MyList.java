package List;

/**
 * @Author: cuizhe
 * @Date: 2019/2/27 20:30
 */
public class MyList {
    private ListNode head;

    public ListNode getHead() {
        return head;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }

    public void insert(int data){
        ListNode newNode = new ListNode(data);
        ListNode curNode = head;
        if (head==null){
            head = newNode;
        }else{
            while (curNode.next!=null){
                curNode = curNode.next;
            }
            curNode.next = newNode;
        }
    }

    /**
     * 打印链表，遍历链表并打印结点数据。
     */
    public void printList() {
        if (head == null) {
            System.out.println("链表中没有数据");
        } else {
            ListNode curNode = head;
            System.out.print("[");
            while (curNode != null) {
                if (curNode.next != null) {
                    System.out.print(curNode.val + "->");
                } else {
                    System.out.println(curNode.val + "]");
                }
                curNode = curNode.next;
            }
        }
    }

}

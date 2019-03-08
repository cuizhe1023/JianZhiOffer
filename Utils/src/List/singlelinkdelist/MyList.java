package List.singlelinkdelist;

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
     * 获取指定位置的结点信息
     * 先判断指定位置合不合法，是否大于链表长度，是否小于1，以及判断链表是否为空；
     * 在判断特殊情况，是否获取的是第一个结点，是否获取的是第二个结点
     * 设置一个计数器，并遍历链表，在遍历每一个结点之前，计数器+1
     * （计数器代表新结点的位置，在遍历之前+1，是为了保证在插入前能获取上一个结点的信息）
     * 当计数器与传入的位置相同时，返回当前结点的后一个结点的信息
     *
     * @param index 指定的位置
     * @return 获取的结点信息
     */
    public ListNode getNodeByIndex(int index){
        if (index<1 || index > length()){
            System.out.println("输入错误,获取失败");
            return null;
        }
        if (head == null) {
            System.out.println("链表为空，获取失败");
            return null;
        }
        if (index == 1) {
            return head;
        }
        if (index == length()){
            return getEndNode();
        }
        int length = 1;
        ListNode curNode = head;
        while (curNode.next != null) {
            length++;
            if (index == length){
                return curNode.next;
            }
            curNode = curNode.next;
        }
        return null;
    }

    /**
     * 获取链表长度，设定一个计数器，每遍历一个点，计数器加一
     *
     * @return 链表长度，
     */
    public int length() {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 获取最后一个结点
     * @return 返回最后一个结点的结点信息
     */
    public ListNode getEndNode(){
        if (head == null) {
            System.out.println("链表为空，插入失败");
            return null;
        }
        ListNode curNode = head;
        while (curNode.next != null){
            curNode = curNode.next;
        }
        return curNode;
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

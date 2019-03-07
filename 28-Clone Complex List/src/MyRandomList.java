/**
 * @Author: cuizhe
 * @Date: 2019/3/6 12:49
 */
public class MyRandomList {

    private RandomListNode head;

    public RandomListNode getHead() {
        return head;
    }

    public void setHead(RandomListNode head) {
        this.head = head;
    }

    public void insertNode(RandomListNode node){
        if (head==null){
            head = node;
        }else {
            RandomListNode curNode = head;
            while (curNode.next!=null){
                curNode = curNode.next;
            }
            curNode.next = node;
        }
    }

    public void insertRandomNode(RandomListNode node1,RandomListNode node2){
        if (node1==null){
            return ;
        }
        node1.random = node2;
    }

    public void print(){
        if (head==null){
            return ;
        }else {
            RandomListNode curNode = head;
            System.out.print("[");
            while (curNode!=null){
                if (curNode.next!=null){
                    if (curNode.random==null)
                        System.out.print(curNode.label+"->");
                    else {
                        System.out.print(curNode.label+"("+curNode.random.label+")->");
                    }
                }else {
                    System.out.print(curNode.label+"]");
                }
                curNode = curNode.next;
            }
        }
        System.out.println();
    }
}

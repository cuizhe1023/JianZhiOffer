/**
 * @Author: cuizhe
 * @Date: 2019/3/5 21:31
 */
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;//特殊指针指向任意一个节点
    RandomListNode(int label) {
        this.label = label;
    }
}

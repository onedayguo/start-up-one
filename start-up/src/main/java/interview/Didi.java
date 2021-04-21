package interview;

/**
 * @Description: 滴滴面试
 * @Author: kami
 * @Date: 2021/4/21 21:35
 * @Version: 1.0.0
 */
public class Didi {
    // 单链表找到中间的节点
    class Node {
        int val;
        Node next;
    }

    private Node findMidNode(Node head) {
        Node quick = head;
        Node slow = head;
        while (quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }


}

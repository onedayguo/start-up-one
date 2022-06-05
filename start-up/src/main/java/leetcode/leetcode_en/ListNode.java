package leetcode.leetcode_en;

public class ListNode {
    int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode cur = this;
        while (cur != null){

            stringBuilder.append(cur.val);
            cur = cur.next;

        }
        return stringBuilder.toString();
    }
}

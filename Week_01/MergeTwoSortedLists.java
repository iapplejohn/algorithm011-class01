package Week_01;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 *  
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/6/27
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoListsOne(ListNode l1, ListNode l2) {
        // 审题：有序列表，合并成新的升序链表
        // 递归: 假设l1比l2小，问题变为将l1.next和l2合并，存在相似性
        // 时间复杂度: O(m + n)
        // 空间复杂度: O(m + n) 递归调用消耗栈空间
        // 边界条件
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoListsOne(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsOne(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoListsTwo(ListNode l1, ListNode l2) {
        // 迭代: 通过一个dummy节点，值为-1，next指向l1和l2中小的元素，直到其中一个为null
        // 时间复杂度: O(m + n)
        // 空间复杂度: O(1) 只需要常数的空间存放若干变量

        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 边界处理: 其中一个为null，另一个可能不为null
        prev.next = l1 == null ? l2 : l1;
        return preHead.next;
    }

}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }
}

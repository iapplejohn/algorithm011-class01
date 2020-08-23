package Week_09;

import java.util.LinkedList;

/**
 * 32. 最长有效括号
 *
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/23
 */
public class LongestValidParentheses_32 {

    public int longestValidParentheses(String s) {
        // Clarification: 只包括小括号，找出最长有效括号
        // 方案1: 动态规划 TODO
        return 0;
    }

    public int longestValidParenthesesTwo(String s) {
        // 方案2: 栈
        // 遍历每个字符，左括号入栈（位置值），右括号出栈，并计算当前位置和栈顶的差值（最大长度）
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        int max = 0;

        // 特殊处理：初始入栈 -1
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                // 特殊处理：当栈为空时（即右括号比左括号多1个，压入当前位置值，以新的位置计算）
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // i 为当前位置，栈顶为起始位置（未匹配），两者差即为当前最大有效长度
                    max = Math.max(max, i - stack.peek());
                }
            }
        }

        return max;
    }

    public int longestValidParenthesesThree(String s) {
        // 方案3: 统计法：不需要额外空间 TODO

        return 0;
    }
}

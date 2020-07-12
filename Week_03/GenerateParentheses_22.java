package Week_03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 22. 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/11
 */
public class GenerateParentheses_22 {

    private List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        // Clarification: 生成所有可能且有效的括号组合，n为括号的对数
        // 方案1：递归 + 最后判断有效性
        // 生成所有可能的括号组合，判断每个组合的合法性
        // 时间复杂度: O(2^n)
        // 空间复杂度: O(n) ?
        generate(0, 2 * n, "");
        return result;
    }

    private void generate(int level, int max, String s) {
        // terminator
        if (level == max) {
            if (valid(s)) {
                result.add(s);
            }
            return;
        }

        // process current

        // drill down
        generate(level + 1, max, s + '(');
        generate(level + 1, max, s + ')');

        // reverse current
    }

    private boolean valid(String s) {
        // 栈: 存储暂时未配对的括号
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // 三种可能: 当前元素入栈, 之前元素出栈, 不符合(FastFail)
            if (ch == '(') {
                stack.push(ch);
            } else {
                Character prevC = stack.peek();
                if (prevC != null && prevC == '(' && ch == ')') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

    public List<String> generateParenthesisTwo(int n) {
        // 方案2：递归 + 剪枝
        // 括号合法性:
        // 添加时机: 左括号可以随时加，只要不超标
        // 右括号个数小于左括号时，才能添加
        // 时间复杂度: O(2^n)
        // 空间复杂度: O(n) ?

        List<String> list = new ArrayList<>();

        // 该递归函数没有返回值，因为里面一分为二，结果是发散的，需要存储
        generate2(0, 0, n, "", list);
        return list;
    }

    private void generate2(int left, int right, int n, String s, List<String> list) {
        // terminator
        if (left == n && right == n) {
            list.add(s);
            return;
        }

        // process current

        // drill down
        if (left < n) {
            generate2(left + 1, right, n, s + '(', list);
        }

        // right < n 可以去掉，right既然小于left，left小于n(等于n就退出了, 见terminator）
        if (right < left && right < n) {
            generate2(left, right + 1, n, s + ')', list);
        }

        // reverse current
    }

    public List<String> generateParenthesisTwoTwo(int n) {
        // 方案2：递归 + 剪枝 + 减法
        // 括号合法性:
        // 添加时机: 左括号可以随时加，只要不超标
        // 右括号个数小于左括号时，才能添加
        // 时间复杂度: O(2^n)
        // 空间复杂度: O(n)

        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        generate22(n, n, "", res);
        return res;
    }

    /**
     * 生成有效括号对
     *
     * @param l 左括号还有几个
     * @param r 右括号还有几个
     * @param curStr 当前构建的字符串
     * @param res 符合条件的字符串列表
     */
    private void generate22(int l, int r, String curStr, List<String> res) {
        // terminator
        if (l == 0 && r == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (l > r) {
            return;
        }

        // process current

        // drill down
        if (l > 0) {
            generate22(l - 1, r, curStr + '(', res);
        }

        if (r > 0) {
            generate22(l, r - 1, curStr + ')', res);
        }

        // reverse current
    }

    public List<String> generateParenthesisThree(int n) {
        // 方案3：广度优先，使用队列 + 辅助结点

        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        Node first = new Node("", n, n);
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(first);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.left == 0 && node.right == 0) {
                res.add(node.curStr);
                continue;
            }

            if (node.left > 0) {
                queue.offer(new Node(node.curStr + '(', node.left - 1, node.right));
            }

            if (node.right > 0 && node.right > node.left) {
                queue.offer(new Node(node.curStr + ')', node.left, node.right - 1));
            }
        }

        return res;
    }

    class Node {

        /**
         * 当前得到的字符串
         */
        public String curStr;

        /**
         * 剩余左括号数量
         */
        public int left;

        /**
         * 剩余右括号数量
         */
        public int right;

        public Node(String curStr, int left, int right) {
            this.curStr = curStr;
            this.left = left;
            this.right = right;
        }
    }

    public List<String> generateParenthesisFour(int n) {
        // 方案4：动态规划
        // TODO
        return null;
    }

    public static void main(String[] args) {
        GenerateParentheses_22 instance = new GenerateParentheses_22();
        System.out.println(instance.generateParenthesis(3));
    }
}


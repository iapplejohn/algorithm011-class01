package Week_04;

import java.util.Arrays;

/**
 * 455. 分发饼干
 *
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 注意：
 *
 * 你可以假设胃口值为正。
 * 一个小朋友最多只能拥有一块饼干。
 *
 * 示例 1:
 *
 * 输入: [1,2,3], [1,1]
 *
 * 输出: 1
 *
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * 示例 2:
 *
 * 输入: [1,2], [1,2,3]
 *
 * 输出: 2
 *
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/assign-cookies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/14
 */
public class AssignCookies_455 {

    public int findContentChildren(int[] g, int[] s) {
        // Clarification:
        // 1. 给一个孩子的饼干应当尽量小并且又能满足该孩子，这样大饼干才能拿来给满足度比较大的孩子
        // 2. 因为满足度最小的孩子最容易得到满足，所以先满足满足度最小的孩子
        // 方案1：排序
        // 时间复杂度：O(nlogn)
        // 空间复杂度: O(1)

        int res = 0;
        if (g.length == 0 || s.length == 0) {
            return res;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                res++;
                i++;
            }
            j++;
        }

        return res;
    }

    public int findContentChildrenTwo(int[] g, int[] s) {
        // 方案2：双重循环，通过差值寻找恰巧满足孩子的饼干
        // 时间复杂度：O(m * n)
        // 空间复杂度: O(1)

        int res = 0;
        if (g.length == 0 || s.length == 0) {
            return res;
        }

        // 遍历每块饼干
        for (int i = 0; i < s.length; i++) {
            // 符合条件的小孩
            int childIndex = -1;
            int max = Integer.MAX_VALUE;

            // 遍历每个小孩
            for (int j = 0; j < g.length; j++) {
                // 该小孩还没分到饼干
                if (g[j] > 0) {
                    // 饼干大小 - 小孩的胃口
                    int value = s[i] - g[j];
                    // 恰好满足
                    if (value == 0) {
                        childIndex = j;
                        break;
                    } else if (value > 0 && value < max) {
                        childIndex = j;
                        max = value;
                    }
                }
            }

            // 找到符合的小孩
            if (childIndex > -1) {
                // 该小孩胃口已经满足了
                g[childIndex] = 0;
                res++;
            }
        }

        return res;
    }
}

package Week_04;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/14
 */
public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        // Clarification: 求最大利润，可以多次买卖一只股票，但不能同时参与多笔交易
        // 方案1：暴力，遍历每个组合 - 超出时间限制
        // 时间复杂度: O(n^n)
        // 空间复杂度: O(n) 递归的深度

        return recursiveCalc(prices, 0);
    }

    public int recursiveCalc(int[] prices, int start) {
        // terminator
        if (start >= prices.length) {
            return 0;
        }

        // process current
        int max = 0;
        for (int i = start; i < prices.length; i++) {
            int curMax = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] < prices[j]) {
                    // drill down
                    int curTotal = recursiveCalc(prices, j + 1) + prices[j] - prices[i];
                    if (curTotal > curMax) {
                        curMax = curTotal;
                    }
                }
            }

            if (curMax > max) {
                max = curMax;
            }
        }

        // reverse current

        return max;
    }

    public int maxProfitTwo(int[] prices) {
        // 方案2：峰谷法，考虑每一个紧跟的峰谷值，以最大化利润
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        int i = 0;
        int max = 0;
        int valley = prices[0];
        int peek = prices[0];

        // 大循环，寻找多对紧邻的峰谷
        // i 最大不能到prices.length - 1，否则 i++ 越界
        while (i < prices.length - 1) {
            // 寻找谷
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            // 寻找紧邻峰
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peek = prices[i];
            // 加上该对峰谷差对应的利润
            max += peek - valley;
        }

        return max;
    }

    public int maxProfitThree(int[] prices) {
        // 方案3：简单的一次遍历
        // 在方案2的基础上，因为谷和连续的峰的最后一个的差值，等于每个峰和之前谷的差值之和
        // 所以只要下一天比前一天高，就把利润算上，也就是前一天买，后一天卖，当天再以新的价格买入
        // 时间复杂度: O(n)
        // 空间复杂度: O(1)

        int maxProfit = 0;
        int i = 0;

        // 注意这里的右边界是 prices.length - 2，如果是 prices.length - 1，则 i + 1 越界
        while (i < prices.length - 1) {
            if (prices[i] < prices[i + 1]) {
                maxProfit += prices[i + 1] - prices[i];
            }
            i++;
        }

        return maxProfit;
    }
}

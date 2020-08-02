package Week_04;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/22
 */
public class CoinChange_322 {

    public int coinChange(int[] coins, int amount) {
        // Clarification: 零钱兑换
        // 方案1：搜索回溯 【超出时间限制】
        // 每个硬币的个数 x，不超过 amount / coins[i]，x 从 0 到 max，减去该硬币面额 * x 后，交给其他硬币进行同样的处理

        return recursive(0, coins, amount);
    }

    /**
     * 递归找零
     *
     * @param coinIndex 第几个硬币
     * @param coins 一组硬币
     * @param amount 找零金额
     * @return 需要的硬币个数
     */
    private int recursive(int coinIndex, int[] coins, int amount) {
        // terminator
        if (amount == 0) {
            return 0;
        }

        if (coinIndex < coins.length && amount > 0) {
            int curMax = amount / coins[coinIndex];
            int minCoins = Integer.MAX_VALUE;
            for (int i = 0; i <= curMax; i++) {
                int change = amount - coins[coinIndex] * i;
                if (change >= 0) {
                    int res = recursive(coinIndex + 1, coins, change);
                    if (res != -1) {
                        minCoins = Math.min(minCoins, i + res);
                    }
                }
            }
            return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        }

        return -1;
    }

    public int coinChangeTwo(int[] coins, int amount) {
        // 方案2：贪心算法 + 排序 TODO 解法有问题
        // 时间复杂度: O(nlogn)
        // 空间复杂度: O(1)

        Arrays.sort(coins);

        int count = 0;

        // 从大额硬币开始遍历，可能找不到合适的解
        for (int i = coins.length - 1; i >= 0; i--) {
            // 依次使用硬币，直到找零不够当前硬币面额
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
            }
        }

        return amount == 0 ? count : -1;
    }

    public int coinChangeThree(int[] coins, int amount) {
        // 方案2：动态规划 - 自顶向下，备忘录
        // 时间复杂度: O(Sn) S 是金额，n 是面额数
        // 空间复杂度: O(S)
        if (amount < 1) {
            return 0;
        }

        return dp(coins, amount, new int[amount]);
    }

    /**
     * 动态规划，获取最小硬币数
     *
     * @param coins 硬币列表
     * @param remain 剩余金额
     * @param cache 缓存：每种金额，兑换需要的最小硬币数
     * @return 兑换需要的最小硬币数
     */
    private int dp(int[] coins, int remain, int[] cache) {
        if (remain < 0) {
            return -1;
        }
        if (remain == 0) {
            return 0;
        }
        // 缓存中有
        if (cache[remain - 1] != 0) {
            return cache[remain - 1];
        }

        int min = Integer.MAX_VALUE;

        for (int coin : coins) {
            int res = dp(coins, remain - coin, cache);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }

        cache[remain - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return cache[remain - 1];
    }

    public int coinChangeFour(int[] coins, int amount) {
        // 动态规划-自底向上 TODO
        return 0;
    }
}

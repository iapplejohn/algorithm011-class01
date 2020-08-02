package Week_06;

/**
 * 213. 打家劫舍 II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/29
 */
public class HouseRobberII_213 {

    public int rob(int[] nums) {
        // 相邻的不能偷，第一个和最后一个不能偷
        // 方案1：递归 TODO
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        return 0;
    }

    public int robTwo(int[] nums) {
        // 方案2：动态规划，自顶向下
        return 0;
    }

    public int robThree(int[] nums) {
        // 方案3：动态规划，自底向上
        // 求两种情况的最大值：抢第一间房，不抢最后一间；不抢第一间，抢最后一间

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        int sum1 = robHandle(nums, 0, len - 2);
        int sum2 = robHandle(nums, 1, len - 1);
        return Math.max(sum1, sum2);
    }

    private int robHandle(int[] nums, int start, int end) {
        // dp_i: 0...i间房最多能抢多少钱
        // dp_i_1: 0...i-1间房能抢多少钱
        // dp_i_2: 0...i-2间房能抢多少钱
        int dp_i = 0, dp_i_1 = 0, dp_i_2 = 0;
        for (int i = start; i <= end; i++) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}

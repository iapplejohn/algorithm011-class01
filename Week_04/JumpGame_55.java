package Week_04;

/**
 * 55. 跳跃游戏
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4] 输出: true 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。 示例 2:
 *
 * 输入: [3,2,1,0,4] 输出: false 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/jump-game 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/28
 */
public class JumpGame_55 {

    public boolean canJump(int[] nums) {
        // Clarification: 按数组上的步数（可以小于）跳是否能跳到终点
        // 方案1 暴力 + 递归 超出时间限制
        // dfs 参数：数组, 当前位置，最后位置

        int len = nums.length;
        if (len == 0) {
            return true;
        }

        return dfs(nums, 0, len - 1);
    }

    private boolean dfs(int[] nums, int current, int end) {
        // terminator
        if (current == end) {
            return true;
        }

        // process current logic
        // 当前能跳的步数
        int steps = nums[current];
        // 可以跳1步，2步，... steps 步
        for (int i = 1; i <= steps; i++) {
            // drill down
            boolean result = dfs(nums, current + i, end);
            if (result) {
                return true;
            }
        }

        // reverse current state
        return false;
    }

    public boolean canJumpTwo(int[] nums) {
        // 方案2 贪心法，从后向前，结果导向，减少不必要的循环（走1步，2步，还是3步）

        int len = nums.length;
        if (len == 0) {
            return true;
        }

        int reach = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] + i >= reach) {
                reach = i;
            }
        }

        return reach == 0;
    }

    public static void main(String[] args) {
        JumpGame_55 instance = new JumpGame_55();
        long start = System.currentTimeMillis();
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(instance.canJump(nums));
        System.out.println(System.currentTimeMillis() - start);
    }

    // 方案3 动态规划
}

package Week_04;

/**
 * 860. 柠檬水找零
 *
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 *
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 *
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 *
 * 注意，一开始你手头没有任何零钱。
 *
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *
 * 示例 1：
 *
 * 输入：[5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 * 示例 2：
 *
 * 输入：[5,5,10]
 * 输出：true
 * 示例 3：
 *
 * 输入：[10,10]
 * 输出：false
 * 示例 4：
 *
 * 输入：[5,5,10,10,20]
 * 输出：false
 * 解释：
 * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
 * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
 * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
 * 由于不是每位顾客都得到了正确的找零，所以答案是 false。
 *  
 *
 * 提示：
 *
 * 0 <= bills.length <= 10000
 * bills[i] 不是 5 就是 10 或是 20 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lemonade-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/17
 */
public class LemonadeChange_860 {

    public boolean lemonadeChange(int[] bills) {
        // Clarification: 是否正确找零，有5，10和20美元的钞票
        // 贪心法
        // 时间复杂度: O(N)
        // 空间复杂度: O(1)

        if (bills.length == 0) {
            return true;
        }

        int[] rest = new int[3];

        // 遍历每个账单，如果没法找零，则返回false
        for (int bill : bills) {
            if (bill == 5) {
                // 收5元
                rest[0]++;
            } else if (bill == 10) {
                // 收10元
                rest[1]++;
                // 没有5元零钱
                if (rest[0] < 1) {
                    return false;
                }
                // 找零5元
                rest[0]--;
            } else {
                // 收20元
                rest[2]++;
                // 找1张10元和1张5元
                if (rest[0] > 0 && rest[1] > 0) {
                    rest[0]--;
                    rest[1]--;
                    // 找3张5元
                } else if (rest[0] > 2) {
                    rest[0] -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

}

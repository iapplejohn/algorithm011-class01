package Week_04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 874. 模拟行走机器人
 *
 * 机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
 *
 * -2：向左转 90 度
 * -1：向右转 90 度
 * 1 <= x <= 9：向前移动 x 个单位长度
 * 在网格上有一些格子被视为障碍物。
 *
 * 第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])
 *
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。
 *
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: commands = [4,-1,3], obstacles = []
 * 输出: 25
 * 解释: 机器人将会到达 (3, 4)
 * 示例 2：
 *
 * 输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出: 65
 * 解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
 *  
 *
 * 提示：
 *
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * 答案保证小于 2 ^ 31
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/walking-robot-simulation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/18
 */
public class WalkingRobotSimulation {

    public int robotSim(int[] commands, int[][] obstacles) {
        // Clarification: 直线行走 + 左/右转，遇到障碍物，停在前一格，返回最后位置横纵坐标的平方和
        // 方案1：模拟法，当前坐标，当前方向
        // 时间复杂度: O(n + k) n和k分别是commands 和 obstacles 的长度
        // 空间复杂度: O(k)

        int capacity = (int) (obstacles.length / 0.75);
        Set<String> obstacleSet = new HashSet<>(capacity);
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "-" + obstacle[1]);
        }

        int x = 0, y = 0, max = 0;
        ORI_ENUM ori = ORI_ENUM.NORTH;
        for (int command : commands) {
            switch (command) {
                case -1:
                    ori = ori.next();
                    break;
                case -2:
                    ori = ori.prev();
                    break;
                default:
                    for (int i = 0; i < command; i++) {
                        if (ori.xSeri != 0) {
                            x += ori.xSeri;
                        }
                        if (ori.ySeri != 0) {
                            y += ori.ySeri;
                        }
                        if (obstacleSet.contains(x + "-" + y)) {
                            x -= ori.xSeri;
                            y -= ori.ySeri;
                            break;
                        } else {
                            max = Math.max(max, x * x + y * y);
                        }
                    }

                    break;
            }
        }

        return max;
    }

    enum ORI_ENUM {
        NORTH(0, 0, 1), EAST(1, 1, 0), SOUTH(2, 0, -1), WEST(3, -1, 0);

        private int code;

        private int xSeri;

        private int ySeri;

        ORI_ENUM(int code, int xSeri, int ySeri) {
            this.code = code;
            this.xSeri = xSeri;
            this.ySeri = ySeri;
        }

        ORI_ENUM prev() {
            int prevCode = (code + 3) % 4;
            for (ORI_ENUM ori_enum : values()) {
                if (ori_enum.code == prevCode) {
                    return ori_enum;
                }
            }
            return ORI_ENUM.NORTH;
        }

        ORI_ENUM next() {
            int nextCode = (code + 1) % 4;
            for (ORI_ENUM ori_enum : values()) {
                if (ori_enum.code == nextCode) {
                    return ori_enum;
                }
            }
            return ORI_ENUM.NORTH;
        }
    }

    public int robotSimTwo(int[] commands, int[][] obstacles) {
        // 方案1：模拟法，使用集合来保存障碍物，使用数组来保存方位

        int capacity = (int) (obstacles.length / 0.75);
        Set<String> obstacleSet = new HashSet<>(capacity);
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "-" + obstacle[1]);
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int di = 0;
        int x = 0, y = 0, max = 0;

        for (int command : commands) {
            switch (command) {
                case -1:
                    di = (di + 1) % 4;
                    break;
                case -2:
                    di = (di + 3) % 4;
                    break;
                default:
                    for (int i = 0; i < command; i++) {
                        x += dx[di];
                        y += dy[di];
                        if (obstacleSet.contains(x + "-" + y)) {
                            x -= dx[di];
                            y -= dy[di];
                            break;
                        } else {
                            max = Math.max(max, x * x + y * y);
                        }
                    }

                    break;
            }
        }

        return max;
    }

    public int robotSimThree(int[] commands, int[][] obstacles) {
        // 方案1：模拟法，使用集合来保存障碍物，使用数组（x移位）来保存方位

        int capacity = (int) (obstacles.length / 0.75);
        Set<Long> obstacleSet = new HashSet<>(capacity);
        for (int[] obstacle : obstacles) {
            long ox = obstacle[0] + 30000;
            long oy = obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int di = 0;
        int x = 0, y = 0, max = 0;

        for (int command : commands) {
            switch (command) {
                case -1:
                    di = (di + 1) % 4;
                    break;
                case -2:
                    di = (di + 3) % 4;
                    break;
                default:
                    for (int i = 0; i < command; i++) {
                        x += dx[di];
                        y += dy[di];
                        long value = ((x + 30000) << 16) + (y + 30000);
                        if (obstacleSet.contains(value)) {
                            x -= dx[di];
                            y -= dy[di];
                            break;
                        } else {
                            max = Math.max(max, x * x + y * y);
                        }
                    }

                    break;
            }
        }

        return max;
    }
}

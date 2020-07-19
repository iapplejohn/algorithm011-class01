package Week_04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *  
 *
 * 示例 1:
 *
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/7/18
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        // Clarification: 岛屿为1，水为0，求岛屿数量
        // 方案1: 贪心法 + 递归，
        // 找到'1'后，递归：将相邻的所有格子'1'置为'0'，防止后面再统计到
        // 时间复杂度: O(r * c) r 和 c 分别为行数和列数
        // 空间复杂度: O(r * c) 最大递归深度

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length, nc = grid[0].length, count = 0;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    traverse(grid, r, c);
                }
            }
        }

        return count;
    }

    private void traverse(char[][] grid, int r, int c) {
        // terminator
        int nr = grid.length;
        int nc = grid[0].length;
        if (grid[r][c] == '0') {
            return;
        }

        // process current
        // 这里发现的陆地是和主函数中的陆地是一个岛，置为'0'，防止主函数后面的循环再统计到
        grid[r][c] = '0';

        // drill down
        if (r > 0 && grid[r - 1][c] == '1') {
            traverse(grid, r - 1, c);
        }
        if (r < nr - 1 && grid[r + 1][c] == '1') {
            traverse(grid, r + 1, c);
        }
        if (c > 0 && grid[r][c - 1] == '1') {
            traverse(grid, r, c - 1);
        }
        if (c < nc - 1 && grid[r][c + 1] == '1') {
            traverse(grid, r, c + 1);
        }

        // reverse current
    }

    public int numIslandsTwo(char[][] grid) {
        // 方案2: BFS + 队列辅助，
        // 找到'1'后，递归：将相邻的所有格子'1'置为'0'，防止后面再统计到
        // 时间复杂度: O(r * c) r 和 c 分别为行数和列数
        // 空间复杂度: O(r * c)

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length, nc = grid[0].length, count = 0;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    grid[r][c] = '0';

                    Queue<Integer> neighbours = new LinkedList<>();
                    neighbours.offer(r * nc + c);
                    while (!neighbours.isEmpty()) {
                        Integer pos = neighbours.poll();
                        int ri = pos / nc;
                        int ci = pos % nc;

                        if (ri > 0 && grid[ri - 1][ci] == '1') {
                            neighbours.offer((ri - 1) * nc + ci);
                            grid[ri - 1][ci] = '0';
                        }

                        if (ri < nr - 1 && grid[ri + 1][ci] == '1') {
                            neighbours.offer((ri + 1) * nc + ci);
                            grid[ri + 1][ci] = '0';
                        }

                        if (ci > 0 && grid[ri][ci - 1] == '1') {
                            neighbours.offer(ri * nc + ci - 1);
                            grid[ri][ci - 1] = '0';
                        }

                        if (ci < nc - 1 && grid[ri][ci + 1] == '1') {
                            neighbours.offer(ri * nc + ci + 1);
                            grid[ri][ci + 1] = '0';
                        }
                    }

                }
            }
        }

        return count;
    }

}

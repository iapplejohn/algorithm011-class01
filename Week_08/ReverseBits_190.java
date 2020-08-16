package Week_08;

import java.util.HashMap;
import java.util.Map;

/**
 * 190. 颠倒二进制位
 *
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * 示例 2：
 *
 * 输入：11111111111111111111111111111101
 * 输出：10111111111111111111111111111111
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/15
 */
public class ReverseBits_190 {

    public int reverseBits(int n) {
        // Clarification: 颠倒二进制位
        // 方案1: 按位处理，依次拿到每个位置上的数，然后移到高位上
        // 时间复杂度: O(1) 循环32次，常数次
        // 空间复杂度: O(1)

        // power 移动位数，初始值为31，将最后一位移到起始位置
        int res = 0, power = 31;
        while (power >= 0) {
            // n & 0x1: 拿到最后一位的值，<< power: 左移
            res += (n & 0x1) << power;
            // 高位的值依次往右移1位
            n = n >> 1;
            // 左移的位数依次减少
            power--;
        }

        return res;
    }

    private Map<Integer, Long> cache = new HashMap<>(8);

    public int reverseBitsTwo(int n) {
        // 方案2: 按字节处理

        int res = 0, power = 24;
        while (power >= 0) {
            res += reverseByte(n & 0xff) << power;
            n = n >> 8;
            power -= 8;
        }

        return res;
    }

    public Long reverseByte(int b) {
        Long val = cache.get(b);
        if (val == null) {
            val = (b * 0x0202020202L & 0x010884422010L) % 1023;
            cache.put(b, val);
        }
        return val;
    }

    public int reverseBitsThree(int n) {
        // 方案3: 分治，通过掩码将 32 位整数划分成具有较少位的块，对每个块进行反转，最后将结果合并
        // 注意：必须用 >>>，使用 >> 结果不对

        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);

        return n;
    }

}

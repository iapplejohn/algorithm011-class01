package Week_08;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 1122. 数组的相对排序
 *
 * 给你两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *  
 *
 * 示例：
 *
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *  
 *
 * 提示：
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/15
 */
public class RelativeSortArray_1122 {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Clarification: arr2 元素各不相同，都出现在 arr1 中，使 arr1 中的相对顺序和 arr2 一致，未出现过的排序放到末尾
        // 方案1: 计数排序，使用 Map 记录元素出现的次数，使用优先队列进行排序
        // 时间复杂度: O(nlogn)
        // 空间复杂度: O(n)

        // 使用 LinkedHashMap 记录 arr2 的元素，并保持相对顺序
        int capacity = (int) (arr2.length / 0.75);
        Map<Integer, Integer> map = new LinkedHashMap<>(capacity);
        for (int element2 : arr2) {
            map.put(element2, 0);
        }

        // 使用优先队列对未出现的元素进行排序
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // 统计 arr1 中，arr2 元素出现的次数
        for (int element : arr1) {
            Integer count = map.get(element);
            if (count != null) {
                map.put(element, count + 1);
            } else {
                queue.add(element);
            }
        }

        // 先输出出现的元素
        int[] res = new int[arr1.length];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            while (count-- > 0) {
                res[i++] = num;
            }
        }

        // 再输出未出现的元素
        while (!queue.isEmpty()) {
            res[i++] = queue.poll();
        }

        return res;
    }

    public int[] relativeSortArrayTwo(int[] arr1, int[] arr2) {
        // 方案2: 计数排序，使用数组(下标为元素值，值为出现个数），如果为arr2中元素，放到arr1中，然后输出其他元素（数组下标为元素值，已排序）
        // 时间复杂度: O(n)
        // 空间复杂度: O(n)

        int[] countArr = new int[1001];

        // 记录 arr1 中元素出现的个数
        for (int num1 : arr1) {
            countArr[num1]++;
        }

        // 输出 arr2 中出现的元素到 arr1 中
        int i = 0;
        for (int num2 : arr2) {
            while (countArr[num2] > 0) {
                --countArr[num2];
                arr1[i++] = num2;
            }
        }

        // 输出不在 arr2中的元素，有序
        for (int j = 0; j < countArr.length; j++) {
            while (countArr[j] > 0) {
                --countArr[j];
                arr1[i++] = j;
            }
        }

        return arr1;
    }

    public int[] relativeSortArrayThree(int[] arr1, int[] arr2) {
        // 方案3: 使用lambda 表达式，自定义函数
        // 时间复杂度: O(n)
        // 空间复杂度: O(nlogn)

        // 使用 Map 记录 arr2 的元素及其下标
        int capacity = (int) (arr2.length / 0.75);
        Map<Integer, Integer> map = new HashMap<>(capacity);
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        // arr1 转成 列表
        List<Integer> res = Arrays.stream(arr1).boxed().collect(Collectors.toList());

        Collections.sort(res, (num1, num2) -> {
            if (map.containsKey(num1) || map.containsKey(num2)) {
                return map.getOrDefault(num1, 1000) - map.getOrDefault(num2, 1000);
            } else {
                return num1 - num2;
            }
        });

        return res.stream().mapToInt(i -> i).toArray();
    }
}

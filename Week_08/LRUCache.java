package Week_08;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU缓存机制
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *  
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *  
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2 ); // 2 - 缓存容量
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhujiang.cheng
 * @since 2020/8/15
 */
public class LRUCache {

    private Map<Integer, Node> map;

    private DoubleList list;

    private int capaticy;

    public LRUCache(int capacity) {
        this.capaticy = capacity;
        this.map = new HashMap<>();
        this.list = new DoubleList();
    }

    public int get(int key) {
        // key 不存在
        if (!map.containsKey(key)) {
            return -1;
        }

        int value = map.get(key).value;
        // 通过 put 方法将该数据放到开头
        put(key, value);
        return value;
    }

    public void put(int key, int value) {
        // 构建新的节点
        Node node = new Node(key, value);

        // key 已存在，删除旧的数据，将新数据放到开头
        if (map.containsKey(key)) {
            Node old = map.get(key);
            list.remove(old);
            list.addFirst(node);
            map.put(key, node);
        } else {
            // 如果链表已满，删除尾部节点，删除map中对应的键
            if (capaticy == list.size()) {
                Node tail = list.removeLast();
                map.remove(tail.key);
            }
            // 将新的节点放到开头，map 中新建映射
            list.addFirst(node);
            map.put(key, node);
        }
    }
}

class DoubleList {

    private Node head, tail;

    private int size;

    public void addFirst(Node node) {
        if (head == null) {
            head = tail = node;
        } else {
            Node h = head;
            h.prev = node;
            node.next = h;
            head = node;
        }
        size++;
    }

    public void remove(Node node) {
        if (head == node && tail == node) {
            head = null;
            tail = null;
        } else if (tail == node) {
            node.prev.next = null;
            tail = node.prev;
        } else if (head == node) {
            node.next.prev = null;
            head = node.next;
        } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        size--;
    }

    public Node removeLast() {
        Node node = tail;
        remove(tail);
        return node;
    }

    public int size() {
        return size;
    }
}

class Node {

    public int key, value;

    public Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

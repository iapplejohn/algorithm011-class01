学习笔记

### 异位词
#### 异位词判断
1. 常规(排序，然后比较)
2. 哈希
  - 使用map存储第一个词字符出现次数
  - 和第二个词比对，对map的次数进行抵扣
3. 数组
  - 词是由26个字母组成，构建数组，位置为字母的顺序索引
  - 和方案2类似，对数组的次数进行抵扣

#### 异位词分组
1. 哈希 + 排序
2. 哈希 + 数组计数

### 求和
1. 常规(多重循环，遍历每个数)
2. 哈希
  - 两遍哈希
  - 一遍哈希

### 树
#### 前序遍历
1. 递归
2. 迭代 + 栈辅助
  - 先将根节点push到栈中，拿出得到节点值
  - 然后把右子节点，左子节点（注意顺序）push到栈中

#### 中序遍历
1. 递归
2. 迭代 + 栈辅助
  - 一直往栈中push，直至最左子节点，
  - 输出该节点的值，
  - 然后其根节点的值，
  - 接着把右节点push到栈中，输出其值

#### 后续遍历
1. 递归
2. 迭代 + 栈（逆向（根、右、左）），再逆序输出

#### 层序遍历
1. 递归 + 通过层数来确定位置
2. 数组 + 广度优先

### 丑数问题
1. 常规（遍历每个数字）：超出时间限制
2. 动态规划

### topK问题
1. 哈希表 统计每个元素出现个数
  - 使用PriorityQueue基于出现次数进行排序
  - 数组进行排序，下标为出现个数，数据为元素值

学习笔记

### 高级动态规划
#### 动态规划 Dynamic Programming
1. "Simplifying a complicated problem by breaking it 
down into simpler sub-problems"
  (in a recursive manner)

2. Divide & Conquer + Optimal substructure
  分治 + 最优子结构

3. 一般是顺推形式

#### 动态规划模板
```
function DP():
  dp = [][] # 二维情况
  
  for i = 0 ... M {
    for j = 0 ... N {
      dp[i][j] = _Function(dp[i'][j'] ...)
    }
  }

  return dp[M][N]
```
动态规划 和 递归或者分治 没有根本上的区别（关键看有无最优的子结构）
拥有共性：找到重复子问题

差异性：最优子结构、中途可以淘汰次优解

#### 复杂度来源
1. 状态拥有更多维度（二维、三维、或者更多，甚至需要压缩）
2. 状态方程更加复杂

#### 典型应用
1. 爬楼梯问题改进，对步伐进行限制
2. 编辑距离

### 字符串
1. 获取字符: 
```
char cur = s.charAt(i);
```

2. 获取字符数组:
```
char[] array = s.toCharArray();
```

3. 大小写相互转化
```
String upper = s.toUpperCase();
String lower = s.toLowerCase();
```

4. 大小写之间相距 32
```
char upper = (char)(lower - 32);
```
# 360公司2018校园招聘算法笔试题

<!-- TOC -->
* [第一题](#第一题)
* [第二题](#第二题)
* [第三题](#第三题)
<!-- TOC -->


## 第一题

### 题目描述
>有一个城市需要修建，给你N个民居的坐标X,Y，问把这么多民居全都包进城市的话，城市所需最小面积是多少（注意，城市为平行于坐标轴的正方形）

**输入描述:**
>第一行为N，表示民居数目（2≤N≤1000）
下面为N行，每行两个数字Xi，Yi，表示该居民的坐标（-1e9≤xi,yi≤1e9）


**输出描述:**
>城市所需最小面积

**样例：**
```
输入
2
0 0
2 2

输出
4

输入样例2
2
0 0
0 3
输出样例2
9
```

### 参考代码
```java
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] loc = new int[N][2];
        for(int i=0;i<N;i++){
            loc[i][0]=in.nextInt();
            loc[i][1]=in.nextInt();
        }
        long min = 0;
        long min_x = loc[0][0];
        long max_x = loc[0][0];
        long min_y = loc[0][1];
        long max_y = loc[0][1];
        for(int i = 1; i < N; ++i) {
            if(min_x > loc[i][0])
                min_x = loc[i][0];
            if(max_x < loc[i][0])
                max_x = loc[i][0];
            if(min_y > loc[i][1])
                min_y = loc[i][1];
            if(max_y < loc[i][1])
                max_y = loc[i][1];
        }
        min = (max_y-min_y)>(max_x-min_x)?(max_y-min_y)*(max_y-min_y):(max_x-min_x)*(max_x-min_x);
        System.out.println(min);
    }
}
```

## 第二题

### 题目描述
>小明有一个花园，花园里面一共有m朵花，对于每一朵花，都是不一样的，小明用1～m中的一个整数表示每一朵花。
他很喜欢去看这些花，有一天他看了n次，并将n次他看花的种类是什么按照时间顺序记录下来。
记录用a[i]表示，表示第i次他看了a[i]这朵花。
小红很好奇，她有Q个问题,问[l,r]的时间内，小明一共看了多少朵不同的花儿，小明因为在忙着欣赏他的花儿，所以想请你帮他回答这些问题。

**输入描述:**
>输入两个数n,m;(1<=n<=2000,1<=m<=100);分别表示n次看花，m表示一共有m朵花儿。
接下来输入n个数a[1]~a[n]，a[i]表示第i次，小明看的花的种类;
输入一个数Q(1<=Q<=1000000);表示小红的问题数量。
输入Q行 每行两个数 l,r(1<=l<=r<=n); 表示小红想知道在第l次到第r次，小明一共看了多少不同的花儿。


**输出描述:**
>一共Q行
每一行输出一个数 表示小明在[l,r]的时间内看了多少种花。

**样例：**
```
输入
5 3
1 2 3 2 2
3
1 4
2 4
1 5
输出
3
2
3
```

### 参考代码
```java
import java.util.HashSet;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] look = new int[n];
        for(int i=0;i<n;i++){
            look[i]=in.nextInt();
        }
        int k = in.nextInt();
        int[][] interval = new int[k][2];
        for(int i=0;i<k;i++){
            interval[i][0]=in.nextInt();
            interval[i][1]=in.nextInt();
        }
        for(int i=0;i<k;i++){
            HashSet<Integer> count = new HashSet<>();
            for(int j=interval[i][0]-1;j<=interval[i][1]-1;j++){
                count.add(look[j]);
            }
            System.out.println(count.size());
        }
    }
}

```

## 第三题

### 题目描述
>小红有两个长度为n的排列A和B。每个排列由[1,n]数组成，且里面的数字都是不同的。
现在要找到一个新的序列C，要求这个新序列中任意两个位置(i,j)满足:
如果在A数组中C[i]这个数在C[j]的后面，那么在B数组中需要C[i]这个数在C[j]的前面。
请问C序列的长度最长为多少呢？


**输入描述:**
>第一行一个整数，表示N。
第二行N个整数，表示A序列。
第三行N个整数，表示B序列。
满足:N<=50000


**输出描述:**
>输出最大的长度

**样例：**
```
输入
5
1 2 4 3 5
5 2 3 4 1
输入
2
```


### 参考代码

```java

```

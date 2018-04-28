<!-- TOC -->
* [第一题](#第一题)
* [第二题](#第二题)
* [第三题](#第三题)
* [第四题](#第四题)
* [第五题](#第五题)
<!-- TOC -->

## 第一题

### 题目描述：
>在n个元素的数组中，找到差值为k的数字对去重后的个数。

**输入描述:**
>第一行，n和k，n表示数字个数，k表示差值 
第二行，n个正整数

**输出描述：**
>差值为k的数字对去重后的个数

**样例:**
```
in:
5 2
1 5 3 4 2
out:
3
```

### 解题思路
- 剑指Offer66题之每日6题 - 第七天中第六题：和为S的两个数字中的头尾指针法，这里变形为差为K的两个数字。其实是一样的，这样一来，时间复杂度就为O(2n)，但是排序的时间复杂度还是O(nlogn)

### 参考代码
```java
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int k = in.nextInt();
            Set<Integer> s = new TreeSet<Integer>();
            for (int i = 0; i < n; i++) {
                s.add(in.nextInt());
            }
            Integer[] arr = new Integer[s.size()];
            Integer[] arrtemp = s.toArray(arr);
            int ans = 0;
            for (int head = 0, tail = 1; tail < arr.length; head++) {
                while (tail < arr.length && arr[tail] - arr[head] < Math.abs(k))
                    tail++;
                if (tail >= arr.length)
                    break;
                if (arr[tail] - arr[head] == Math.abs(k))
                    ans += 1;
            }
            System.out.println(ans);
        }
    }
}
```


## 第二题

### 题目描述：
>定义两个字符串变量：s和m，再定义两种操作， 
第一种操作：
```
m = s;
s = s + s;
```
第二种操作：
```
s = s + m;
```
假设s, m初始化如下：
```
s = "a";
m = s;
```
求最小的操作步骤数，可以将s拼接到长度等于n

**输入描述:**
>一个整数n，表明我们需要得到s字符长度，0<n<10000

**输出描述：**
>一个整数，表明总共操作次数

**样例:**
```
in:
6
out:
3
```

### 解题思路
- 质数x操作步数都是x-1;
- 合数x的操作步数都是由因数的操作步数+因数组合成合数的步数；

### 参考代码
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] dp = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                dp[i] = i - 1;//质数的操作步数
                //寻找最大因素
                for (int j = i / 2; j >= 1; j--) {
                    if (i % j == 0) {
                        //通过因数的组合过程也是一个质数的合成过程
                        dp[i] = Math.min(dp[i], dp[j] + i / j - 1);
                    }
                }
            }
            System.out.println(dp[n]);
        }
    }
}
```

## 第三题

### 题目描述：
>今日头条6周年周年庆就要开始啦。活动主办方请你帮忙制作一个小彩蛋。你的程序需要读取一个表达式，并输出用字符6拼出的计算结果。相邻数字使用两个英文句号"."间隔，如下是0123456789。
```
66666......6..66666..66666..6...6..66666..66666..66666..66666..66666
6...6......6......6......6..6...6..6......6..........6..6...6..6...6
6...6......6..66666..66666..66666..66666..66666......6..66666..66666
6...6......6..6..........6......6......6..6...6......6..6...6......6
66666......6..66666..66666......6..66666..66666......6..66666..66666
```

**输入描述：**
>第一行为一个整数n 
接下来n行，每一行为一个表达式 
对于30%的数据，表达式仅包含`6`, `+`, `-`三种字符 
对于100%的数据，表达式仅包含`6`, `+`, `-`, `*`四种字符。1≤n≤1001≤n≤100，表达式长度不超过100，其中`+`, `-`, `*`均为二元运算符，计算中间结果在$[-2^{63},2^{63}-1]$之内，最终结果在$[0,2^{63}?1]$之内

**输出描述：**
>对于每组数据输出用字符`6`拼出的计算结果。

**样例:**
```
in:
2
6+6
6*6
out:
....6..66666
....6......6
....6..66666
....6..6....
....6..66666
66666..66666
....6..6....
66666..66666
....6..6...6
66666..66666
```

### 参考代码

```java
import java.util.Scanner;

public class Main {
    private static String G[][] = {
            { "66666", "....6", "66666", "66666", "6...6", "66666", "66666",
                    "66666", "66666", "66666" },
            { "6...6", "....6", "....6", "....6", "6...6", "6....", "6....",
                    "....6", "6...6", "6...6" },
            { "6...6", "....6", "66666", "66666", "66666", "66666", "66666",
                    "....6", "66666", "66666" },
            { "6...6", "....6", "6....", "....6", "....6", "....6", "6...6",
                    "....6", "6...6", "....6" },
            { "66666", "....6", "66666", "66666", "....6", "66666", "66666",
                    "....6", "66666", "66666" } };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int n = Integer.parseInt(in.nextLine());
            for (int i = 0; i < n; i++) {
                String s = in.nextLine();
                long ans = cal(s);
                String ansString = "" + ans;
                int[] digit = new int[ansString.length()];
                for (int k = 0; k < ansString.length(); k++) {
                    digit[k] = ansString.charAt(k) - '0';
                }
                for (int j = 0; j < 5; j++) {
                    String temp = G[j][digit[0]];
                    for (int k = 1; k < digit.length; k++) {
                        temp = temp + ".." + G[j][digit[k]];
                    }
                    System.out.println(temp);
                }

            }
        }
    }

    private static long cal(String s) {
        int n = s.length();
        long sum = 0, cur = 0, prd = 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0')
                cur = cur * 10 + s.charAt(i) - '0';
            else if (s.charAt(i) == '-') {
                sum += prd * cur;
                cur = 0;
                prd = -1;
            } else if (s.charAt(i) == '+') {
                sum += prd * cur;
                cur = 0;
                prd = 1;
            } else {
                prd *= cur;
                cur = 0;
            }
        }
        return sum + prd * cur;
    }
}
```

## 第四题

### 题目描述：
>给一个包含`n`个整数元素的集合`a`，一个包含`m`个整数元素的集合`b`。定义magic操作为，从一个集合中取出一个元素，放到另一个集合里，切操作过后每个集合的平均值都大于操作前。
注意一下两点：
- 不可以把一个集合的元素取空，这样就没有平均值了
- 值为x的元素从集合b取出放入集合a，但集合a中已经有值为x的元素，则a的平均值不变(因为集合元素不会重复)，b的平均值可能会改变(因为x被取出了)
问最多可以进行多少次magic操作？


**输入描述：**
>第一行为两个整数n，m 
第二行n个整数，表示集合a中的元素 
第三行m个整数，表示集合b中的元素 
对于100%的数据，`1<n,m<100000` `0<a[i],b[i]<100000000`，集合a中元素互不相同，集合b中的元素互不相同。

**输出描述：**
>输出一个整数，表示最多可以进行的操作次数。

**样例:**
```
in:
3 5
1 2 5
2 3 4 5 6
out:
2
```

### 参考代码

```java
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n,m;
        int cnt;
        n=scan.nextInt();
        m=scan.nextInt();
        double []a=new double[n];
        double []b=new double[m];
        for(int i=0;i<n;++i) {
            a[i]=scan.nextDouble();
        }
        for(int i=0;i<m;++i) {
            b[i]=scan.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        double va=avd(a);
        double vb=avd(b);
        if(vb>va) {
            cnt=Maicg(a,b,va,vb);
        }
        else {
            cnt=Maicg(b,a,vb,va);
        }
        System.out.println(cnt);
    }
    static double avd(double []a) {
        double sum=0;
        for(int i=0;i<a.length;++i) {
            sum+=a[i];
        }
        return sum/a.length;
    }
    static int Maicg(double[]a,double[]b,double va,double vb) {
        int cnt=0;
        Set se=new TreeSet();
        for(int i=0;i<a.length;++i) {
            se.add(a[i]);
        }
        int n,m;
        n=a.length;
        m=b.length;
        for(int i=0;i<b.length-1&&b[i]<vb;i++) {
            if(b[i]>va) {
                int j=se.size();
                se.add(b[i]);
                if(j!=se.size()) {
                    va=(va*n+b[i])/(n+1);
                    vb=(vb*m-b[i])/(m-1);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
```

## 第五题

### 题目描述
>小T最近迷上了一款跳板小游戏。已知在空中有N个高度互不相同的跳板，小T刚开始在高度为0的地方，每次跳跃可以选择与自己当前高度绝对值差小于等于H的跳板，跳跃过后到达以跳板为轴的镜像位置，问小T在最多跳K次的情况下最高能跳多高？（任意时刻，高度不能为负）

**输入描述：**
>第一行三个整数N, K, H
以下N行，每行一个整数Ti，表示第i个跳板的离地高度

**输出描述：**
>一个整数，表示最高能跳到的高度

**样例：**
```
输入：
3 3 2
1
3
6
输出：
8
```

### 参考代码
```java
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static int max = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt();
            int K = in.nextInt();
            int H = in.nextInt();
            int[] block = new int[N];
            for (int i = 0; i < N; i++) {
                block[i] = in.nextInt();
            }

            HighestLevel(block, 0, K, H, new Stack<Integer>());
            System.out.println(max);
        }
    }

    private static void HighestLevel(int[] block, int cur, int k, int h,
            Stack<Integer> s) {
        // TODO Auto-generated method stub
        if (cur > max)
            max = cur;
        for (int i = 0; i < block.length; i++) {
            if (block[i] - cur > 0 && block[i] - cur <= h) {
                s.push(block[i]);
                cur = cur + (block[i] - cur) * 2;
                HighestLevel(block, cur, k - 1, h, s);
                cur = cur - (cur - block[i]) * 2;
                s.pop();
            }
        }
        return;
    }
}
```

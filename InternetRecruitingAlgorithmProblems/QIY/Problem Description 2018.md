# 爱奇艺公司2018校园招聘算法笔试题

<!-- TOC -->
* [第一题](#第一题)
* [第二题](#第二题)
* [第三题](#第三题)
<!-- TOC -->


## 第一题 拼凑正方形

### 题目描述
>牛牛有4根木棍,长度分别为a,b,c,d。羊羊家提供改变木棍长度的服务,如果牛牛支付一个硬币就可以让一根木棍的长度加一或者减一。
牛牛需要用这四根木棍拼凑一个正方形出来,牛牛最少需要支付多少硬币才能让这四根木棍拼凑出正方形。

**输入描述:**
>输入包括一行,四个整数a,b,c,d(1 ≤ a,b,c,d ≤ 10^6), 以空格分割


**输出描述:**
>输出一个整数,表示牛牛最少需要支付的硬币

**样例：**
```
输入
4 1 5 4

输出
4
```

### 参考代码
```java
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] arr = new int[4];
        for(int i=0;i<4;i++){
            arr[i]=in.nextInt();
        }
        Arrays.sort(arr);
        int edge = arr[1];
        int result = 0;
        for(int i=0;i<arr.length;i++){
            result+=Math.abs(edge-arr[i]);
        }
        System.out.println(result);
    }
}
```

## 第二题 区间表达

### 题目描述
>牛牛的老师给出了一个区间的定义:对于x ≤ y,[x, y]表示x到y之间(包括x和y)的所有连续整数集合。例如[3,3] = {3}, [4,7] = {4,5,6,7}.牛牛现在有一个长度为n的递增序列,牛牛想知道需要多少个区间并起来等于这个序列。
例如:
{1,2,3,4,5,6,7,8,9,10}最少只需要[1,10]这一个区间
{1,3,5,6,7}最少只需要[1,1],[3,3],[5,7]这三个区间 

**输入描述:**
>输入包括两行,第一行一个整数n(1 ≤ n ≤ 50),
第二行n个整数a[i](1 ≤ a[i] ≤ 50),表示牛牛的序列,保证序列是递增的。


**输出描述:**
>输出一个整数,表示最少区间个数。

**样例：**
```
输入
5
1 3 5 6 7

输出
3
```

### 参考代码
```java
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=in.nextInt();
        }
        int result = 1;
        for(int i=1;i<n;i++){
            if(arr[i]-arr[i-1]>1){
                result++;
            }
        }
        System.out.println(result);
    }
}
```

## 第三题 缺失的括号

### 题目描述
>一个完整的括号字符串定义规则如下:
1、空字符串是完整的。
2、如果s是完整的字符串，那么(s)也是完整的。
3、如果s和t是完整的字符串，将它们连接起来形成的st也是完整的。
例如，"(()())", ""和"(())()"是完整的括号字符串，"())(", "()(" 和 ")"是不完整的括号字符串。
牛牛有一个括号字符串s,现在需要在其中任意位置尽量少地添加括号,将其转化为一个完整的括号字符串。请问牛牛至少需要添加多少个括号。 

**输入描述:**
>输入包括一行,一个括号序列s,序列长度length(1 ≤ length ≤ 50).
s中每个字符都是左括号或者右括号,即'('或者')'.


**输出描述:**
>输出一个整数,表示最少需要添加的括号数

**样例：**
```
输入
(()(()

输出
2
```

### 参考代码
```java
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            if(stack.empty()){
                stack.push(s.charAt(i));
            }else{
                if(s.charAt(i)==')'){
                    if(stack.peek()=='('){
                        stack.pop();
                    }
                    else{
                        stack.push(')');
                    }
                }else{
                    stack.push('(');
                }
            }
        }
        System.out.println(stack.size());
    }
}
```

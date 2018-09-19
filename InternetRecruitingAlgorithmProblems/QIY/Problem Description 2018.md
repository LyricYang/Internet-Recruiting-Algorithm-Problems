# 爱奇艺公司2018校园招聘算法笔试题

<!-- TOC -->
* [第一题:拼凑正方形](#第一题-拼凑正方形)
* [第二题:区间表达](#第二题-区间表达)
* [第三题:缺失的括号](#第三题-缺失的括号)
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


## 第四题 回文素数

### 题目描述
>如果一个整数只能被1和自己整除,就称这个数是素数。
如果一个数正着反着都是一样,就称为这个数是回文数。例如:6, 66, 606, 6666
如果一个数字既是素数也是回文数,就称这个数是回文素数
牛牛现在给定一个区间[L, R],希望你能求出在这个区间内有多少个回文素数。  

**输入描述:**
>输入包括一行,一行中有两个整数(1 ≤ L ≤ R ≤ 1000)


**输出描述:**
>输出一个整数,表示区间内回文素数个数。

**样例：**
```
输入
100 150

输出
2
```

### 参考代码
```java
import java.util.*;
 
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int low = in.nextInt();
        int high = in.nextInt();
        int result = 0;
        for(int i=low;i<=high;i++){
            if(isPrime(i)&&isPlalindrome(i)){
                result++;
            }
        }
        System.out.println(low==1?result-1:result);
    }
     
    public static boolean isPrime(int num){
        for(int i=2;i<=(int)Math.sqrt(num);i++){
            if(num%i==0)
                return false;
        }
        return true;
    }
     
    public static boolean isPlalindrome(int num){
        String s = ""+num;
        int left = 0;
        int right = s.length()-1;
        while(left<right){
            if(s.charAt(left++)!=s.charAt(right--)){
                return false;
            }
        }
        return true;
    }
}
```

## 第五题 排序

### 题目描述
>牛牛有一个长度为n的整数序列,牛牛想对这个序列进行重排为一个非严格升序序列。牛牛比较懒惰,他想移动尽量少的数就完成重排,请你帮他计算一下他最少需要移动多少个序列中的元素。(当一个元素不在它原来所在的位置,这个元素就是被移动了的) 

**输入描述:**
>输入包括两行,第一行一个整数n(1 ≤ n ≤ 50),即序列的长度
第二行n个整数x[i](1 ≤ x[i] ≤ 100),即序列中的每个数

**输出描述:**
>输出一个整数,即最少需要移动的元素个数

**样例：**
```
输入
3
3 2 1

输出
2
```

### 参考代码
```java
import java.util.*;
 
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr_origine = new int[n];
        int[] arr_sorted = new int[n];
        for(int i=0;i<n;i++){
            arr_origine[i]=in.nextInt();
            arr_sorted[i]=arr_origine[i];
        }
        Arrays.sort(arr_sorted);
        int result = 0;
        for(int i=0;i<n;i++){
            if(arr_origine[i]!=arr_sorted[i]){
                result++;
            }
        }
        System.out.println(result);
    }
}
```


## 第六题 青草游戏

### 题目描述
>牛牛和羊羊都很喜欢青草。今天他们决定玩青草游戏。
最初有一个装有n份青草的箱子,牛牛和羊羊依次进行,牛牛先开始。在每个回合中,每个玩家必须吃一些箱子中的青草,所吃的青草份数必须是4的x次幂,比如1,4,16,64等等。不能在箱子中吃到有效份数青草的玩家落败。假定牛牛和羊羊都是按照最佳方法进行游戏,请输出胜利者的名字。 

**输入描述:**
>输入包括t+1行。
第一行包括一个整数t(1 ≤ t ≤ 100),表示情况数.
接下来t行每行一个n(1 ≤ n ≤ 10^9),表示青草份数

**输出描述:**
>对于每一个n,如果牛牛胜利输出"niu",如果羊羊胜利输出"yang"。

**样例：**
```
输入
3
1
2
3

输出
niu
yang
niu
```

### 参考代码
```java
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            int num = in.nextInt();
            if(num%5==0||num%5==2){
                System.out.println("yang");
            }else{
                System.out.println("niu");
            }
        }
    }
}
```
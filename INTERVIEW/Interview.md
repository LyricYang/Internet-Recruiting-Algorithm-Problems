# 面试手写代码

<!-- TOC -->
* [第一题](#第一题-查找目录)
* [第二题](#第二题-快速排序)
* [第三题](#第三题-TOPK)
* [第四题](#第四题-幸运数)
<!-- TOC -->

## 第一题 查找目录

### 题目描述
>获取一个目录下的所有文件夹和文件，包括子文件夹和子文件

```java
import java.io.File;
/*
 * 获取一个目录下的所有文件夹和文件，包括子文件夹和子文件 。
 * 并将文件夹和文件名称打印在控制台上面。并且要显示文件目录的层级
 * 注：运用了递归的算法。
 */
public class FilePath {
 
  public static void main(String[] args) {
    File dir=new File("/home/coding/workspace/python/");
    //File dir=new File("F:\\");
    //如果使用上述的盘符的根目录，会出现java.lang.NullPointerException
    //为什么？
    getAllFiles(dir,0);//0表示最顶层
  }
  //获取层级的方法
  public static String getLevel(int level)
  {
    //A mutable sequence of characters.
    StringBuilder sb=new StringBuilder();
    for(int l=0;l<level;l++)
    {
      sb.append("|--");
    }
    return sb.toString();
  }
  public static void getAllFiles(File dir,int level)
  {
    System.out.println(getLevel(level)+dir.getName());
    level++;
    File[] files=dir.listFiles();
    for(int i=0;i<files.length;i++)
    {
      if(files[i].isDirectory())
      {
        //这里面用了递归的算法
        getAllFiles(files[i],level);
      }
      else {
        System.out.println(getLevel(level)+files[i]);
      }	
    }		 
  }
}
```

## 第二题 快速排序

### 题目描述
>快速排序

```java
public class QuickSort {
    public static void main(String[] args){
        int[] array = {1,1,8,8,11,34,55,23,65,24,67,4,5};
        for (int i:array) {
            System.out.print(i+" ");
        }
        System.out.println();
        quickSort(array,0,array.length-1);
        for (int i:array) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    private static void quickSort(int[] arr,int lo,int hi){
        if(arr==null||arr.length==0||lo>=hi) return;
        int loc=paritition(arr,lo,hi);
        quickSort(arr,lo,loc-1);
        quickSort(arr,loc+1,hi);
    }

    private static int paritition(int[] arr,int lo,int hi){
        int x=arr[lo];
        int i=lo;
        int j=hi;
        while(i<=j){
            while(i<=j&&arr[i]<=x) i++;
            while(j>=i&&arr[j]>x) j--;
            if(i<j){
                swap(arr,i,j);
                i++;
                j--;
            }
        }
        swap(arr,lo,j);
        return j;
    }

    private static void swap(int[] arr,int loc1,int loc2){
        if(loc1==loc2) return;
        int temp = arr[loc1];
        arr[loc1] = arr[loc2];
        arr[loc2] = temp;
    }
}
```

## 第三题 TOPK

### 题目描述
>TopK

- 最大的K个元素最小堆，最小的K个元素最大堆

```java
public class TopK {
    public static void main(String[] args){
        int[] arr = { 1, 17, 3, 4, 5, 6, 7, 16, 9, 10};
        int K=4;
        int[] b=topK(arr,K);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]+",");
        }
    }
    private static int[] topK(int[] arr,int K){
        int[] top = new int[K];
        for (int i = 0; i < K; i++) {
            top[i]=arr[i];
        }
        buildHeap(top);
        for (int i = K ; i <arr.length ; i++) {
            if(arr[i]<top[0]){
                setTop(top,arr[i]);
            }
        }
        return top;
    }
    private static void buildHeap(int[] arr){
        int length = arr.length;
        for(int i=length/2-1;i>=0;i--){
            heapify(arr,i,length);
        }
    }
    private static void setTop(int[] arr,int val){
        arr[0]=val;
        heapify(arr,0,arr.length);
    }

    private static void heapify(int[] arr,int index,int length) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;
        if (left < length && arr[left] > arr[largest])
            largest = left;
        if (right < length && arr[right] > arr[largest])
            largest = right;
        if (index != largest) {
            swap(arr, index, largest);
            heapify(arr, largest, length);
        }
    }
    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
```

- 快排思想

```java
public class TopKSort {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] array = { 9, 3, 1, 10, 5, 7, 6, 2, 8, 0 };
        TopK(array, 4);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
    }

    public static void TopK(int[] arr,int K){
        if(arr!=null&&arr.length>0){
            int low = 0;
            int high = arr.length-1;
            int index = partition(arr,low,high);
            while(index!=K-1){
                if(index>K-1){
                    high = index-1;
                    index=partition(arr,low,high);
                }
                if(index<K-1){
                    low=index+1;
                    index=partition(arr,low,high);
                }
            }
        }
    }

    private static int partition(int[] arr,int lo,int high){
        int x=arr[0];
        int i=lo+1;
        int j=high;
        while(i<=j){
            while(i<=j&&arr[i]<=x) i++;
            while(i<=j&&arr[j]>=x) j--;
            if(i<j){
                swap(arr,i,j);
                i++;
                j--;
            }
        }
        swap(arr,0,j);
        return j;
    }

    private static void swap(int[] arr,int loc1,int loc2){
        if(loc1==loc2) return;
        int temp = arr[loc1];
        arr[loc1] = arr[loc2];
        arr[loc2] = temp;
    }
}
```
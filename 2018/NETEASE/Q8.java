import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int w = in.nextInt();
            int[] v = new int[n];
            for(int i=0;i<n;i++){
                v[i]=in.nextInt();
            }
            long res = putSolution(v,w,n-1);
            System.out.println(res);
        }
    }
    public static long putSolution(int[] v,int w,int n){
        if(w<0)
            return 0;
        if(n==0&&w>=v[0])
            return 2;
        if(n==0&&w<v[0])
            return 1;
        return putSolution(v,w,n-1)+putSolution(v,w-v[n],n-1);
    }
}
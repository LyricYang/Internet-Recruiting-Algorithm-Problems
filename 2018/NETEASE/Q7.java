import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int N = in.nextInt();
            int[] alarm=new int[N];
            for(int i=0;i<N;i++){
                alarm[i]=in.nextInt()*60+in.nextInt();
            }
            int X = in.nextInt();
            int school = in.nextInt()*60+in.nextInt();
            int wakeup = school-X;
            Arrays.sort(alarm);
            int index=Arrays.binarySearch(alarm,wakeup);
            int res=0;
            if(index<0){
                res=-index-2;
            }
            else{
                res = index;
            }
            System.out.println(alarm[res]/60+" "+alarm[res]%60);
        }
    }
}
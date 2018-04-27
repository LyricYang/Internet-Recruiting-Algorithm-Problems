import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int left = in.nextInt();
            int right = in.nextInt();
            int result = 0;
            for(int i=left;i<=right;i++){
                if(i%3==0||i%3==2){
                    result++;
                }
            }
            System.out.println(result);
        }
    }
}
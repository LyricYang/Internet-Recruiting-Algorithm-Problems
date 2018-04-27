import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            int t = Integer.parseInt(in.nextLine());
            for(int i=0;i<t;i++){
                int n = Integer.parseInt(in.nextLine());
                String s = in.nextLine();
                int res = 0;
                int index=0;
                while(index<s.length()){
                    if(index+2>=s.length()){
                        break;
                    }
                    if(s.charAt(index)=='X')
                        index++;
                    else{
                        index+=3;
                        res++;
                    }
                }
                if(index+1<=s.length())
                    if(s.charAt(index)=='.')
                        res++;
                    else if(index+2==s.length()){
                        if(s.charAt(index+1)=='.')
                            res++;
                    }
                System.out.println(res);
            }
        }
    }
}
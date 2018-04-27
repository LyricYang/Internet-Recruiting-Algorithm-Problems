import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            int N = Integer.parseInt(in.nextLine());
            String s = in.nextLine();
            String res = "N";
            for(int i=0;i<s.length();i++){
                if(res.equals("N")){
                    if(s.charAt(i)=='L')
                        res="W";
                    else
                        res="E";
                }
                else if(res.equals("S")){
                    if(s.charAt(i)=='L')
                        res="E";
                    else
                        res="W";
                }
                else if(res.equals("E")){
                    if(s.charAt(i)=='L')
                        res="N";
                    else
                        res="S";
                }
                else if(res.equals("W")){
                    if(s.charAt(i)=='L')
                        res="S";
                    else
                        res="N";
                }
            }
            System.out.println(res);
        }
    }
}
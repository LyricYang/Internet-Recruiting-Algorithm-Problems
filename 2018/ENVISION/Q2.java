import java.util.*;
public class Hanoi {
    private String[] pos = {"left", "mid", "right"};
    public ArrayList<String> getSolution(int n) {
        return getSolution(n, 0, 2);
    }
    public ArrayList<String> getSolution(int n, int from, int to) {
        if(n==0) return new ArrayList<String>();
        ArrayList<String> list = getSolution(n-1, from, 3-from-to);
        list.add("move from "+pos[from]+" to "+pos[to]);
        list.addAll(getSolution(n-1, 3-from-to, to));
        return list;
    }
}
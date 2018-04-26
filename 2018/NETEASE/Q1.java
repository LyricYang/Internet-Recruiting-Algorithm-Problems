import java.util.Scanner;
public class Main {
    public static int solve(int[] x1, int[] x2, int[] y1, int[] y2, int k,
            int xa, int ya, int xb, int yb) {
        if (k == x1.length)
            return 0;
        else {
            if (x1[k] >= xb || y1[k] >= yb || xa >= x2[k] || ya >= y2[k])// 排除相离的情况。
                return solve(x1, x2, y1, y2, k + 1, xa, ya, xb, yb);
            else {
                int xa1 = Math.max(xa, x1[k]);
                int ya1 = Math.max(ya, y1[k]);
                int xb1 = Math.min(xb, x2[k]);
                int yb1 = Math.min(yb, y2[k]);// 如果当前矩形计算在内，保留公共区域。
                return Math.max(solve(x1, x2, y1, y2, k + 1, xa, ya, xb, yb),
                        solve(x1, x2, y1, y2, k + 1, xa1, ya1, xb1, yb1) + 1);// 当前矩形算不算
            }
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] x1 = new int[n];
            int[] y1 = new int[n];
            int[] x2 = new int[n];
            int[] y2 = new int[n];
            for (int i = 0; i < n; i++) {
                x1[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                y1[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                x2[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                y2[i] = sc.nextInt();
            }
            int ans = 1;
            for (int i = 0; i < x1.length; i++)// 依次以不同矩形为基础进行计算，同时以相应的坐标点为基准值。求最大值。
            {
                int num = solve(x1, x2, y1, y2, 0, x1[i], y1[i], x2[i], y2[i]);
                ans = Math.max(num, ans);
            }
            System.out.println(ans);
        }
    }
}
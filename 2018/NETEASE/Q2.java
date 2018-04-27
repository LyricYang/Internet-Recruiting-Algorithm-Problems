import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int k = in.nextInt();
            if (k == 0)
                System.out.println(1L * n * n);
            else {
                long ans = 0L;
                for (int i = k + 1; i <= n; i++) {
                    ans += (n / i) * (i - k);
                    if (n % i >= k)
                        ans += n % i - k + 1;
                }
                System.out.println(ans);
            }
        }
    }
}
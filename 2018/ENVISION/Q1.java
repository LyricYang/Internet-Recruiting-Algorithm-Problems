import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int x = in.nextInt();
            int[] source = new int[n];
            for (int i = 0; i < n; i++) {
                source[i] = in.nextInt();
            }
            int result = finder(source, n, x);
            System.out.println(result);
        }
    }
    public static int finder(int[] arr, int n, int x) {
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] < x) {
                if (arr[left] > arr[mid] && x > arr[right])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (arr[mid] > arr[right] && x < arr[left])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}
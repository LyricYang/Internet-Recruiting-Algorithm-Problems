import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N = in.nextInt();
            int M = in.nextInt();
            int[][] mission = new int[N + 1][2];
            int[][] person = new int[M + 1][2];
            for (int i = 1; i <= N; i++) {
                mission[i][0] = in.nextInt();
                mission[i][1] = in.nextInt();
            }
            for (int i = 1; i <= M; i++) {
                person[i][0] = in.nextInt();
                person[i][1] = i;
            }

            Arrays.sort(mission, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    int[] one = (int[]) o1;
                    int[] two = (int[]) o2;
                    if (one[0] > two[0])
                        return 1;
                    else if (one[0] < two[0])
                        return -1;
                    else
                        return 0;
                }
            });
            Arrays.sort(person, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    int[] one = (int[]) o1;
                    int[] two = (int[]) o2;
                    if (one[0] > two[0])
                        return 1;
                    else if (one[0] < two[0])
                        return -1;
                    else
                        return 0;
                }
            });

            int[] DP = new int[N + M + 1];
            int[] location = new int[M + 1];
            int index = 1;
            int left = 1;
            int right = 1;
            while (left <= N && right <= M) {
                if (mission[left][0] < person[right][0]) {
                    DP[index++] = mission[left++][1];
                } else if (mission[left][0] == person[right][0]) {
                    DP[index] = mission[left++][1];
                    location[person[right][1]] = index;
                    right++;
                    index++;
                } else {
                    location[person[right][1]] = index;
                    index++;
                    right++;
                }
            }
            while (right <= M) {
                location[person[right][1]] = index;
                index++;
                right++;
            }
            for (int i = 1; i <= N + M; i++) {
                DP[i] = Math.max(DP[i - 1], DP[i]);
            }
            for (int i = 1; i <= M; i++) {
                System.out.println(DP[location[i]]);
            }
        }
    }
}
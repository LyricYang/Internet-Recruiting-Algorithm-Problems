import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        boolean[] broken = new boolean[500]; // 用来记录键盘是否坏了
        for (int i = 0; i < 500; i++) {
            broken[i] = false;
        }

        for (int i = 0; i < a.length(); i++) { // 更新坏的键盘的布尔值
            broken[a.charAt(i)] = true;
            if (a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') {
                broken[a.charAt(i) + 32] = true;
            }
        }
        // 遍历输出
        for (int i = 0; i < b.length(); i++) {
            if (broken['+'] || broken['\''] || broken['-'] || broken['.']) {
                if (!broken[b.charAt(i)]
                        && (b.charAt(i) < 'A' || b.charAt(i) > 'Z')) {
                    System.out.print(b.charAt(i));
                }
            } else {
                if (!broken[b.charAt(i)]) {
                    System.out.print(b.charAt(i));
                }
            }
        }
        System.out.println();
    }
}
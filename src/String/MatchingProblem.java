package String;

import java.util.Scanner;

public class MatchingProblem {
    public static void main(String[] args) {
        int T, n, m, sl, flag = 0;
        String s, t;
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            n = in.nextInt();
            m = in.nextInt();
            sl = n - 1;
            s = in.nextLine();
            t = in.nextLine();
            for (int j = 1; j < sl; j++) {
                if (s.charAt(j) != t.charAt(j)) {
                    flag = 0;
                    break;
                }
                if (s.charAt(j) == '*') {
                    flag = 1;
                    break;
                }
            }
            for (int j = sl; j > 0; j--) {
                if (s.charAt(j) != t.charAt(j)) {
                    flag = 0;
                    break;
                }
                if (s.charAt(j) == '*') {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1 && sl > m) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}

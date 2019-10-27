package String;

import java.util.Scanner;

public class MatchingProblem {
    static boolean isEqual(String s1, String s2, int j) {
        for (int i = 0; i < j; i++) {
            if (s1.charAt(i) != s2.charAt(i))
                return false;
        }
        return true;
    }
    
    static boolean isEqualBack(String s1, String s2, int len) {
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        while (len > 0) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return false;
            }
            i--;
            j--;
            len--;
        }
        return true;
    }
    
    public static void main(String[] args) {
        int T, n, m;
        String s, t;
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int flag = 0;
            n = in.nextInt();
            m = in.nextInt();
            in.nextLine();
            s = in.nextLine();
            t = in.nextLine();
            
            int j;
            
            if (n <= m + 1) {
                for (j = 0; j < n; j++) {
                    if (s.charAt(j) == '*') {
                        break;
                    }
                }
                if (j < n) {
                    if (isEqual(s, t, j) && isEqualBack(s, t, n - 1 - j))
                        flag = 1;
                } else if (n == m) {
                    if (isEqual(s, t, j))
                        flag = 1;
                }
            }
            if (flag == 1) {
                System.out.println("YES");
            } else
                System.out.println("NO");
        }
    }
}

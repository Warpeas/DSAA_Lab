package String;

import java.util.Scanner;

public class Necklace {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine();
        for (int i = 0; i < t; i++) {
            String s = in.nextLine();
            int m = s.length();
            int[] next = new int[m];
            next[0] = 0;
            int k = 0;
//            int c;
//            int half = m / 2;
//            if (half * 2 < m) {
//                half++;
//            }
            
            for (int j = 1; j < m; j++) {
                while (k > 0 && s.charAt(k) != s.charAt(j)) {
                    k = next[k - 1];
                }
                if (s.charAt(k) == s.charAt(j)) {
                    k++;
                }
                next[j] = k;
            }
            
            if (next[s.length() - 1] > m / 2) {
                int a = m % (m - next[s.length() - 1]);
                if (a != 0)
                    System.out.println(m - next[s.length() - 1] - a);
                else
                    System.out.println(0);
            } else {
                System.out.println(s.length() - 2 * next[s.length() - 1]);
            }
        }
    }
}

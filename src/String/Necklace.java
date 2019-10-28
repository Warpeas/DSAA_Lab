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
            
            for (int j = 1; j < m; j++) {
                while (k > 0 && s.charAt(k) != s.charAt(j)) {
                    k = next[k];
                }
                if (s.charAt(k) == s.charAt(j)) {
                    k++;
                }
                next[j] = k;
            }
            System.out.println(m - next[s.length() - 1] - 1);
        }
    }
}

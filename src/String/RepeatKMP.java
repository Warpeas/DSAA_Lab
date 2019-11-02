package String;

import java.util.Scanner;

public class RepeatKMP {
    private static int[] next(String p) {
        int m = p.length();
        int[] next = new int[m];
        next[0] = 0;
        int k = 0;
        
        for (int i = 1; i < m; i++) {
            while (k > 0 && p.charAt(k) != p.charAt(i)) {
                k = next[k - 1];
            }
            if (p.charAt(k) == p.charAt(i)) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }
    
    private static int KMP(String t, String p) {
        int n = t.length();
        int m = p.length();
        int[] nextP = next(p);
        int cnt = 0;
        int q = 0;
        for (int i = 0; i < n; i++) {
            while (q > 0 && p.charAt(q) != t.charAt(i)) {
                q = nextP[q - 1];
            }
            if (p.charAt(q) == t.charAt(i)) {
                q++;
            }
            if (q == m) {
                cnt++;
                q = 0;
            }
        }
        return cnt;
    }
    
    public static void main(String[] args) {
        int t;
        int cnt = 0;
        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String s1 = in.next();
            String s2 = in.next();
            int l = s1.length() / 3;
            if (l * 3 < s1.length()) {
                l++;
            }
            String p = s1.substring(0, l);
            if (KMP(s2, p) > 0) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

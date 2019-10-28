package String;

import java.util.Scanner;

public class RepeatRK {
    private static long b = 131;
    private static int q = 131;
    
    private static long pow(long n) {
        long a = 1;
        for (int i = 0; i < n; i++) {
            a *= b;
//            a %= q;
        }
        return a;
    }
    
    private static long hash(String str) {
        long h = 0;
        if (str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                h = b * h + str.charAt(i);
//                h %= q;
            }
        }
        return h;
    }
    
    private static boolean ifEqual(long[] sh1, long[] sh2) {
        for (int i = 0; i < sh1.length; i++) {
            if (binarySearch(sh1[i], sh2, 0, sh2.length - 1))
                return true;
        }
        return false;
    }
    
    private static long[] hashString(String str, int level, long h) {
        long[] s = new long[str.length() - level + 1];
        s[0] = hash(str.substring(0, level));
//        int h = pow(level - 1);
        for (int i = 0; i + level < str.length(); i++) {
//            s[i + 1] = (b * (s[i] - str.charAt(i) * h % q + q) % q + str.charAt(i + level) % q) % q;
            s[i + 1] = (b * (s[i] - str.charAt(i) * h) + str.charAt(i + level));
            
        }
        return s;
    }
    
    private static boolean binarySearch(long key, long[] sh, int l, int r) {
        if (l == r) {
            return key == sh[l];
        }
        if (key < sh[0] || key > sh[r]) {
            return false;
        }
        
        if (r == l + 1) {
            return sh[l] == key || sh[r] == key;
        }
        
        int mid = (l + r) / 2;
        
        if (key == sh[mid]) {
            return true;
        } else if (key > sh[mid]) {
            l = mid;
        } else if (key < sh[mid]) {
            r = mid;
        }
        return binarySearch(key, sh, l, r);
    }
    
    private static long[] merge(long[] L, int nl, long[] R, int nr) {
        int n = nl + nr;
        long[] A = new long[n];
        int i = 0, j = 0;
        for (int k = 0; k < n; k++) {
            if (i < nl && (j == nr || L[i] <= R[j])) {
                A[k] = L[i++];
            } else {
                A[k] = R[j++];
            }
        }
        return A;
    }
    
    private static long[] mergeSort(long[] A, int n) {
        if (n > 1) {
            int p = n / 2;
            long[] B;
            B = new long[p];
            for (int i = 0; i < p; i++) {
                B[i] = A[i];
            }
            long[] C;
            int j = 0;
            C = new long[n - p];
            for (int i = p; i < n; i++) {
                C[j++] = A[i];
            }
            B = mergeSort(B, p);
            C = mergeSort(C, n - p);
            return merge(B, p, C, n - p);
        }
        return A;
    }
    
    public static void main(String[] args) {
        int t;
        int cnt = 0;
        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String s1 = in.next();
            String s2 = in.next();
            int n = s1.length();
            int l = n / 3;
            if (l * 3 < n) {
                l++;
            }
            if (s2.length()>=l) {
                String p = s1.substring(0, l);
                long h = pow(l - 1);
                long[] sh1;
                long[] sh2;
                sh1 = hashString(p, l, h);
                sh2 = hashString(s2, l, h);
                sh2 = mergeSort(sh2, sh2.length);
                if (ifEqual(sh1, sh2)) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
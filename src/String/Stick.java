package String;

import java.util.Scanner;

public class Stick {
    /*
    Stick(s1,s2)
     if (s1.length < s2.length)
     level <- s1.length
     sh1[]
     sh1 = hashString(s1, level)
     sh2[]
     sh2 = hashString(s2, level)
     sort(sh2)
     while(level > 0)
        if(ifEqual(sh1, sh2)
            break;
        level <- level - 1
        hashSub(s1, sh1, level)
        hashSub(s2, sh2, level)
        sort(sh2)
     print(level)
     */
    private static long b = 3;
    private static int q = 1000000007;
    
    private static long pow(int n) {
        long a = 1;
        for (int i = 0; i < n; i++) {
            a *= b;
            a %= q;
        }
        return a;
    }
    
    private static long hash(String str) {
        long h = 0;
        if (str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                h = b * h + str.charAt(i);
                h %= q;
            }
        }
        return h;
    }
    
    private static long[] hashString(String str, int level, long h) {
        long[] s = new long[str.length() - level + 1];
        s[0] = hash(str.substring(0, level));
//        int h = pow(level - 1);
        for (int i = 0; i + level < str.length(); i++) {
            s[i + 1] = ((b * (s[i] % q - str.charAt(i) * h % q + q) % q) % q + str.charAt(i + level) % q) % q;
//            s[i + 1] = (b * (s[i] - str.charAt(i) * h) + str.charAt(i + level));
            
        }
        return s;
    }
//
//    private static int[] hashSub(String str, int[] s, int level) {
//        int[] sub = new int[s.length + 1];
//        for (int i = 0; i < s.length; i++) {
////            sub[i] = s[i] - str.charAt(i + level - 1) % q;
//            sub[i] = s[i] - str.charAt(i + level - 1);
//        }
////        sub[s.length] = (s[s.length - 1] - str.charAt(s.length - 1) * pow(level - 1) % q + q) % q;
//        sub[s.length] = (s[s.length - 1] - str.charAt(s.length - 1) * pow(level - 1));
//        return sub;
//    }
    
    private static boolean ifEqual(long[] sh1, long[] sh2) {
        for (int i = 0; i < sh1.length; i++) {
            if (binarySearch(sh1[i], sh2, 0, sh2.length - 1))
                return true;
        }
        return false;
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
        int level = 0;
        int d = 0, u;
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        if (s1.length() > s2.length()) {
            String s;
            s = s1;
            s1 = s2;
            s2 = s;
        }
        u = s1.length();
        long h;
        long[] sh1;
        long[] sh2;
        
        while (u >= d) {
            level = (d + u) / 2;
            h = pow(level - 1);
            sh1 = hashString(s1, level, h);
            sh2 = hashString(s2, level, h);
            sh2 = mergeSort(sh2, sh2.length);
            if (u != d + 1) {
                if (ifEqual(sh1, sh2)) {
                    d = level;
                } else {
                    u = level;
                }
            } else {
                h *= b;
                sh1 = hashString(s1, u, h);
                sh2 = hashString(s2, u, h);
                sh2 = mergeSort(sh2, sh2.length);
                if (ifEqual(sh1, sh2)) {
                    level = u;
                }
                break;
            }
        }
        System.out.println(level);
    }
}

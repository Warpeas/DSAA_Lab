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
    static int b = 31;
    static int q = 131;
    
    private static int pow(int n) {
        int a = 1;
        for (int i = 0; i < n; i++) {
            a *= b;
            a %= q;
        }
        return a;
    }
    
    private static int hash(String str) {
        int h = 0;
        if (str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                h = b * h + str.charAt(i);
                h %= q;
            }
        }
        return h;
    }
    
    private static int[] hashString(String str, int level) {
        int[] s = new int[str.length() - level + 1];
        s[0] = hash(str.substring(0, level));
        for (int i = 0; i + level < str.length(); i++) {
            s[i + 1] = (b * (s[i] - str.charAt(i) * pow(level - 1)) + str.charAt(i + level)) % q;
        }
        return s;
    }
    
    private static int[] hashSub(String str, int[] s, int level) {
        int[] sub = new int[s.length + 1];
        for (int i = 0; i < s.length; i++) {
            sub[i] = s[i] - str.charAt(i + level - 1);
        }
        sub[s.length] = (s[s.length - 1] - str.charAt(s.length - 1) * pow(level - 1)) % q;
        return sub;
    }
    
    private static boolean ifEqual(int[] sh1, int[] sh2) {
        for (int i = 0; i < sh1.length; i++) {
            if (binarySearch(sh1[i], sh2, 0, sh2.length - 1))
                return true;
        }
        return false;
    }
    
    private static boolean binarySearch(int key, int[] sh, int l, int r) {
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
    
    private static int[] merge(int[] L, int nl, int[] R, int nr) {
        int n = nl + nr;
        int[] A = new int[n];
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
    
    private static int[] mergeSort(int[] A, int n) {
        if (n > 1) {
            int p = n / 2;
            int[] B;
            B = new int[p];
            for (int i = 0; i < p; i++) {
                B[i] = A[i];
            }
            int[] C;
            int j = 0;
            C = new int[n - p];
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
        int level;
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        if (s1.length() > s2.length()) {
            String s;
            s = s1;
            s1 = s2;
            s2 = s;
        }
        level = s1.length();
        int[] sh1;
        sh1 = hashString(s1, level);
//        int[] rsh1 = sh1;
        int[] sh2;
        sh2 = hashString(s2, level);
        sh2 = mergeSort(sh2, sh2.length);
//        int[] rsh2 = sh2;
        while (level > 0) {
            if (ifEqual(sh1, sh2))
                break;
            level--;
            if (level == 0) {
                break;
            }
//            sh1 = hashSub(s1, sh1, level);
            sh1 = hashString(s1, level);
//            sh2 = hashSub(s2, sh2, level);
            sh2 = hashString(s2, level);
            sh2 = mergeSort(sh2, sh2.length);
//            sh2 = mergeSort(sh2, sh2.length);
        }
        
        System.out.println(level);
    }
}

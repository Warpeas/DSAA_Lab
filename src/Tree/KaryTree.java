package Tree;

import java.util.Scanner;

public class KaryTree {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            long n = in.nextInt();
            long k = in.nextInt();
            long sum = 1;
            long b = 1;
            for (; sum <= n; sum = sum * k + 1) {
                b *= k;
            }
            long a = (b - 1) / (k - 1);
            System.out.println((b - n + a) / k + n - a);
        }
    }
}

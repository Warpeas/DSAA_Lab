package String;

import java.util.Scanner;

public class HowManySubstrings {
    /*
    int t
    while (t-->0)
    string str <- inline
    str.size C 2 + 1
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine();
        while (t-- > 0) {
            String str = in.nextLine();
            System.out.println((str.length() + 1) * str.length() / 2);
        }
    }
}

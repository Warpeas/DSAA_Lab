import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(8);
        while (!(n > 0))
            n = r.nextInt(8);
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            long b = r.nextInt(2000000000);
            System.out.println(r.nextInt(2) + " " + b);
        }
    }
}
import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random r = new Random();
        int t = r.nextInt(10);

        System.out.println(t);
        for (int i = 0; i < t; i++) {
            int n = r.nextInt(10000);
            System.out.println(n);
            for (int j = 0; j < n; j++) {
                System.out.print(r.nextInt(10000)+" ");
            }
            System.out.println();
        }
    }
}
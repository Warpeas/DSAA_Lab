import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(15);
        System.out.println(1);
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.print(r.nextInt(10)+" ");
        }
    }
}
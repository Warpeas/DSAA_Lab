import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random r = new Random();
        int t = 500;

        System.out.println(t);
        for (int i = 0; i < t; i++) {
            int n = r.nextInt(10);
            while (!(n>0)){
                n = r.nextInt(10);
            }
            System.out.println(n);
            for (int j = 0; j < n; j++) {
                int tmp = r.nextInt(100);
                while (!(tmp>0)){
                    tmp = r.nextInt(10);
                }
                System.out.print(tmp+" ");
            }
            System.out.println();
        }
    }
}
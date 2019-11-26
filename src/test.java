import java.util.Random;

public class test {
    public static void main(String[] args) {
        Random r = new Random();
        int m = r.nextInt(100000);
        while (!(m>0))
            m = r.nextInt(100000);
        int k = r.nextInt(m);
        while (!(k>0)){
            k = r.nextInt(m);
        }
        System.out.println(m+" "+k);
        for (int i = 0; i < m; i++) {
            int n = r.nextInt(10000);
            while (!(n>0)){
                n = r.nextInt(10000);
            }
            System.out.println(n);
            for (int j = 0; j < m-k; j++) {
                int tmp = r.nextInt(k);
                while (!(tmp>0)){
                    tmp = r.nextInt(k);
                }
                System.out.print(tmp+" ");
            }
            System.out.println();
        }
    }
}
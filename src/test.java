public class test {
    public static void main(String[] args) {
        int a = 1;
        int fast = a;
        for (int i = 0; i < 100000; i++) {
            System.out.printf("%d, ", a);
            a *= 31;
            a %= 131;
            fast = a * 31;
            fast %= 131;
            if (a==fast)
                break;
        }
    }
}

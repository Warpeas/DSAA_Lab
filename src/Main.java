import java.util.Scanner;

public class Main {
    private static class arrayStack {
        String[] data;
        int max_size;
        int top;
        
        public arrayStack() {
            max_size = 10;
            initArrayStack();
        }
        
        public arrayStack(int max) {
            max_size = max;
            initArrayStack();
        }
        
        void initArrayStack() {
            data = new String[max_size];
            top = -1;
        }
        
        
        boolean isEmpty() {
            return top == -1;
        }
        
        boolean isFull() {
            return top >= max_size - 1;
        }
        
        int push(String item) {
            if (isFull()) {
                return 0;
            } else
                data[++top] = item;
            return 1;
        }
        
        int pop() {
            if (isEmpty()) {
                return 0;
            } else
                top--;
            return 1;
        }
        
        String peek() {
            if (isEmpty()) {
                return null;
            }
            return data[top];
        }
        
        void clear() {
            top = -1;
        }
        
        int size() {
            return top + 1;
        }
    }
    
    public static void main(String[] args) {
        int t, n;
        String str;
        Scanner in = new Scanner(System.in);
        t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            in.nextLine();
//            if (n == 0) {
//                continue;
//            }
            str = in.nextLine();
            if (n % 2 != 0) {
                System.out.println("NO");
                continue;
            }
            arrayStack s = new arrayStack(n);
            for (int j = 0; j < n; j++) {
                if (!s.isEmpty()) {
                    if ((s.peek().equals("(") && str.substring(j, j + 1).equals(")"))
                            || (s.peek().equals("[") && str.substring(j, j + 1).equals("]"))
                            || (s.peek().equals("{") && str.substring(j, j + 1).equals("}"))) {
                        s.pop();
                    } else {
                        s.push(str.substring(j, j + 1));
                    }
                } else {
                    s.push(str.substring(j, j + 1));
                }
            }
            if (s.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

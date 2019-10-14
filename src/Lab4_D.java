import java.io.*;
import java.util.StringTokenizer;

public class Lab4_D {
    static class arrayQueue {
        int[] data;
        int max_size;
        int front;
        int rear;
        int cnt;
        
        void initArrayQueue() {
            data = new int[max_size];
            front = 0;
            rear = 0;
            cnt = 0;
        }
        
        arrayQueue() {
            max_size = 0;
            initArrayQueue();
        }
        
        arrayQueue(int max) {
            max_size = max;
            initArrayQueue();
        }
        
        boolean isFull() {
            return cnt == max_size;
        }
        
        boolean isEmpty() {
            return cnt == 0;
        }
        
        void enQueue(int item) {
            if (!isFull()) {
                data[rear] = item;
                rear = (rear + 1) % max_size;
                cnt++;
            }
        }
        
        void deQueue() {
            if (!isEmpty()) {
                front = (front + 1) % max_size;
                cnt--;
            }
        }
        
        void deQueueTail() {
            if (!isEmpty()) {
                rear = (rear + max_size - 1) % max_size;
                cnt--;
            }
        }
        
        int getFront() {
            return data[front];
        }
        
        int getRear() {
            return data[(rear + max_size - 1) % max_size];
        }
        
        void clear() {
            front = 0;
            rear = 0;
        }
        
        void copy(arrayQueue queue) {
            this.data = queue.data;
            this.cnt = queue.cnt;
            this.max_size = queue.max_size;
            this.rear = queue.rear;
            this.front = queue.front;
        }
        
        int size() {
            return cnt;
        }
    }
    
    private static class arrayStack {
        int[] data;
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
            data = new int[max_size];
            top = -1;
        }
        
        
        boolean isEmpty() {
            return top == -1;
        }
        
        boolean isFull() {
            return top >= max_size - 1;
        }
        
        int push(int item) {
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
        
        int peek() {
            if (isEmpty()) {
                return 0;
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
    
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
        
        public char[] nextCharArray() {
            return next().toCharArray();
        }
    }
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t, n;
        int[] c;
        arrayQueue q;
        arrayStack s;
        t = in.nextInt();
        for (int i = 0; i < t; i++) {
            n = in.nextInt();
            c = new int[n];
            q = new arrayQueue(n);
            s = new arrayStack(n);
            for (int j = 0; j < n; j++) {
                c[j] = in.nextInt();
                if (q.isEmpty()) {
                    q.enQueue(j);
                } else {
                    if (c[q.getFront()] <= c[j]) {
                        while (c[q.getRear()] > c[j]) {
                            q.deQueueTail();
                        }
                        q.enQueue(j);
                    } else if (c[q.getFront()] > c[j]) {
                        q.clear();
                        q.enQueue(j);
                    }
                }
            }
            for (int j = 0; j < n; j++) {
//                if (s.isEmpty()) {
////                    if (c[j] <= c[q.getFront()]) {
////                        out.print(c[j] + " ");
////                    } else {
////                        s.push(c[j]);
////                    }
////                } else {
////                    if (s.peek() > c[q.getFront()] && c[j] > c[q.getFront()]) {
////                        s.push(c[j]);
////                    } else if (c[j] < c[q.getFront()] && c[j] < s.peek()) {
////                        out.print(c[j] + " ");
////                    } else if (c[j] > s.peek() && s.peek() <= c[q.getFront()]) {
////                        while (!s.isEmpty() && s.peek() <= c[q.getFront()]) {
////                            out.print(s.peek() + " ");
////                            s.pop();
////                        }
////                        s.push(c[j]);
////                    } else if (c[j] <= s.peek() && s.peek() <= c[q.getFront()]) {
////                        out.print(c[j] + " ");
////                        while (!s.isEmpty() && s.peek() <= c[q.getFront()]) {
////                            out.print(s.peek() + " ");
////                            s.pop();
////                        }
////                    } else {
////                        out.print(c[j] + " ");
////                        q.deQueue();
////                    }
////                }
                if (j!=q.getFront()){
                    s.push(c[j]);
                }else {
                    out.print(c[j] + " ");
                    q.deQueue();
                    while (!s.isEmpty() && s.peek()<c[q.getFront()]){
                        out.print(s.peek()+" ");
                        s.pop();
                    }
                }
            }
            while (!s.isEmpty()) {
                out.print(s.peek() + " ");
                s.pop();
            }
            out.println();
        }
        out.close();
    }
}

import java.io.*;
import java.util.StringTokenizer;

public class Lab4_C {
    /*pseudo code:
    int m, i, result
    input -> m
    queue[m]
    input -> a
    while(a!=-1)
        if(queue.isNotEmpty)
            if(queue.isFull)
                front++
            if(queue[front] >= a)
                while(queue[rear] < a)
                    rear--
                queue[rear] = a
            else if(queue[front] < a)
                clear queue
                queue[front] = a
        if(i == m)
            result = queue[front]
        if(i > m)
            result ^= queue[front]
        i++;
        input -> a
     */
    static class data{
        int value;
        int index;
    }
    static class arrayQueue {
        data[] data;
        int max_size;
        int cnt;
        int front;
        int rear;
        
        void initArrayQueue() {
            data = new data[max_size];
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
        
//        void enQueue(int item) {
//            if (!isFull()) {
//                data[rear].value = item;
//                rear = (rear + 1) % max_size;
//                cnt++;
//            }
//        }
    
        void enQueue(int item, int index) {
            if (!isFull()) {
                data[rear] = new data();
                data[rear].value = item;
                data[rear].index = index;
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
            return data[front].value;
        }
        
        data peekFront() {return  data[front]; }
        
        int getRear() {
            return data[(rear + max_size - 1) % max_size].value;
        }
        
        void clear() {
            front = 0;
            rear = 0;
            cnt = 0;
        }
        
        int size() {
            return cnt;
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
        int m, a, i, result = 0;
        i = 0;
        m = in.nextInt();
        a = in.nextInt();
        arrayQueue queue = new arrayQueue(m);
        while (a != -1) {
            if (!queue.isEmpty()) {
                if (i - queue.peekFront().index == m)
                    queue.deQueue();
                if (queue.getFront() >= a) {
                    while (queue.getRear() < a) {
                        queue.deQueueTail();
                    }
                    queue.enQueue(a, i);
                } else if (queue.getFront() < a) {
                    queue.clear();
                    queue.enQueue(a, i);
                }
            } else
                queue.enQueue(a, i);
            if (i == m - 1)
                result = queue.getFront();
            if (i > m - 1)
                result ^= queue.getFront();
            i++;
            a = in.nextInt();
        }
        out.println(result);
        out.close();
    }
}

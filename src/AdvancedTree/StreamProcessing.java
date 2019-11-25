package AdvancedTree;

import java.io.*;
import java.util.StringTokenizer;

public class StreamProcessing {
    static InputStream inputStream = System.in;
    static InputReader in = new InputReader(inputStream);
    static OutputStream outputStream = System.out;
    static PrintWriter out = new PrintWriter(outputStream);
    static long[] heap;
    static int cnt, t, k;
    static long time, s;
    
    public static void main(String[] args) {
        buildHeap();
        out.close();
    }
    
    public static void buildHeap() {
        t = in.nextInt();
        k = in.nextInt();
        s = in.nextInt();
        heap = new long[k + 1];
        for (time = 1; time <= t; time++) {
            insert();
            if (time % 100 == 0) {
                out.print(heap[1] + " ");
                s = heap[1];
            }
        }
    }
    
    public static void insert() {
        long n = a(time + s);
//        s = n;
        if (cnt < k) {
            heap[++cnt] = n;
            int a = cnt;
            while (heap[a] < heap[a / 2]) {
                swap(a, a / 2);
                a = a / 2;
                if (a == 1) {
                    break;
                }
            }
        } else {
            if (n > heap[1]) {
                heap[1] = n;
            }
            maintain(1);
        }
    }
    
    public static int getMax() {
        int max = 1;
        for (int i = 2; i <= k; i++) {
            if (heap[i] > heap[max]) {
                max = i;
            }
        }
        return max;
    }
    
    public static void maintain(int i) {
        if (2 * i + 1 <= cnt) {
            if (heap[i] > heap[2 * i] || heap[i] > heap[2 * i + 1]) {
                if (heap[2 * i] < heap[2 * i + 1]) {
                    swap(i, 2 * i);
                } else {
                    swap(i, 2 * i + 1);
                }
                maintain(2 * i);
                maintain(2 * i + 1);
            }
        } else if (2 * i <= cnt) {
            if (heap[i] > heap[2 * i]) {
                swap(i, 2 * i);
            }
        }
    }
    
    public static void swap(int i, int j) {
        long tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    
    public static long a(long n) {
        long a = n;
        while (a != 0) {
            n += a % 10;
            a /= 10;
        }
        return n;
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
}

package OJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BeggingforGames {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    
    static long[] prices;
    static long[] stop;
    static long[] stopMoney;
    static int m, cover = 0;
    static long n, total = 0, i, k;
    static long day = 1;
    static int time = 0;
    static int low = 0, high, up;
    
    static void day() {
        time = 0;
        while (cover < m && day >= stop[cover]) {
            cover++;
        }
        day++;
        n += (m - cover) * k;
    }
    
    static void night() {
        time = 1;
        n += i;
    }
    
    private static int binarySearch() {
        if (low < up) {
            int mid = (low + up) / 2;
            
            if (total == stopMoney[mid]) {
                return mid;
            } else if (total > stopMoney[mid]) {
                low = mid + 1;
            } else if (total < stopMoney[mid]) {
                up = mid - 1;
            }
            return binarySearch();
        }
        return 0;
    }
    
    public static void main(String[] args) {
        n = in.nextInt();
        i = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        prices = new long[m];
        for (int j = 0; j < m; j++) {
            prices[j] = in.nextInt();
            total += prices[j];
        }
        high = (int) ((total - n) / i);
        if (k > 0) {
            Arrays.sort(prices);
            stop = new long[m];
            stopMoney = new long[m];
            up = m;
            for (int j = 0; j < m; j++) {
                stop[j] = prices[j] / k;
                if (j == 0) {
                    stopMoney[j] = n + (stop[j] - 1) * k * (m - j) + stop[j] * i;
                } else
                    stopMoney[j] = stopMoney[j - 1] + (stop[j] - stop[j - 1]) * (k * (m - j) + i);
            }
            int a = binarySearch();
            if (low != 0)
                n = stopMoney[low - 1];
            cover = low;
            if (a > 0) {
                day = a + 1;
                time = 1;
            } else {
                day = low;
                time = 1;
                for (int j = low; j < high; j++) {
                    if (n >= total) {
                        break;
                    }
                    day();
                    if (n >= total) {
                        break;
                    }
                    night();
                }
            }
        } else {
            if (high != 0) {
                day = high;
                time = 1;
            }
        }
        out.print(day + " ");
        if (time == 0) {
            out.println("morning");
        } else {
            out.println("evening");
        }
        out.close();
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

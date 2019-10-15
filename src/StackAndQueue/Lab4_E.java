package StackAndQueue;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Lab4_E {
    /*pseudo code:
    Student:
    int height
    int left
    int right
    
    Student A[n]
    Stack s[n]
    for(i = 0;i < n;i++;)
     if (s.isNotEmpty)
      s.push(i)
     else{
      while (Student on the stack top is lower than the next student)
       s.pop()
      else
       i -> A[s.peek()].right
       s.push(i)
     while (s.isNotEmpty)
      s.peek() -> A[s.deepPeek].right
      s.pop
     
     for(i = n-1;i > 0;i--)
     if (s.isNotEmpty)
      s.push(i)
     else{
      while (Student on the stack top is lower than the next student)
       s.pop()
      else
       i -> A[s.peek()].left
       s.push(i)
     while (s.isNotEmpty)
      s.peek() -> A[s.deepPeek].right
      s.pop
     */
    private static class Student {
        int height;
        int left;
        int right;
        
        public Student(int height) {
            this.height = height;
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
        
        boolean largerThanTwo() {
            return top >= 1;
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
        
        int deeperPeek() {
            if (top < 1)
                return 0;
            return data[top - 1];
        }
        
        void clear() {
            top = -1;
        }
        
        int size() {
            return top + 1;
        }
    }
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t, n;
        t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            n = in.nextInt();
            Student[] A = new Student[n];
            arrayStack s = new arrayStack(n);
            for (int j = 0; j < n; j++) {
                A[j] = new Student(in.nextInt());
            }
            for (int j = 0; j < n; j++)
                if (s.isEmpty())
                    s.push(j);
                else {
                    while (A[s.peek()].height < A[j].height) {
                        s.pop();
                        if (s.isEmpty())
                            break;
                    }if (!s.isEmpty()) {
                        A[s.peek()].right = j + 1;
                    }
                    s.push(j);
                }
            while (s.largerThanTwo()) {
                A[s.deeperPeek()].right = s.peek() + 1;
                s.pop();
            }
            s.clear();
            
            for (int j = n - 1; j >= 0; j--)
                if (s.isEmpty())
                    s.push(j);
                else {
                    while (A[s.peek()].height < A[j].height) {
                        s.pop();
                        if (s.isEmpty())
                            break;
                    }
                    if (!s.isEmpty()) {
                        A[s.peek()].left = j + 1;
                    }
                    s.push(j);
                }
            while (s.largerThanTwo()) {
                A[s.deeperPeek()].left = s.peek() + 1;
                s.pop();
            }
            s.clear();
            
            out.println("Case " + i + ":");
            for (int j = 0; j < n; j++) {
                out.println(A[j].left + " " + A[j].right);
            }
        }
        out.close();
    }
}

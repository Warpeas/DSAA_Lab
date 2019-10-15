package StackAndQueue;

import java.util.Scanner;

public class Lab4_A {
    static class arrayQueue {
        int[] data;
        int max_size;
        int front;
        int rear;
        
        void initArrayQueue() {
            data = new int[max_size];
            front = 0;
            rear = 0;
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
            return (rear + 1) % max_size == front;
        }
        
        boolean isEmpty() {
            return rear - front == 0;
        }
        
        void enQueue(int item) {
            if (!isFull()) {
                data[rear] = item;
                rear = (rear + 1) % max_size;
            }
        }
        
        void deQueue() {
            if (!isEmpty()) {
                front = (front + 1) % max_size;
            }
        }
        
        int getFront() {
            return data[front];
        }
        
        void clear() {
            front = 0;
            rear = 0;
        }
        
        int size() {
            return (rear - front + max_size) % max_size;
        }
    }
    
    public static void main(String[] args) {
        int n;
        char o;
        int x;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        arrayQueue q = new arrayQueue(n+1);
        for (int i = 0; i < n; i++) {
            o = in.next().charAt(0);
            if (o == 'E') {
                x = in.nextInt();
                q.enQueue(x);
            } else if (o == 'D') {
                q.deQueue();
            } else if (o == 'A') {
                if (!q.isEmpty()) {
                    System.out.println(q.getFront());
                }
            }
        }
        while (!q.isEmpty()){
            System.out.print(q.getFront() + " ");
            q.deQueue();
        }
        System.out.println();
    }
}

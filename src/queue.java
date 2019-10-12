public class queue {
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
}

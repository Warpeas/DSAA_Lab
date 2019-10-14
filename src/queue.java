public class queue {
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
            return data[rear];
        }
        
        void clear() {
            front = 0;
            rear = 0;
        }
        
        int size() {
            return cnt;
        }
    }
}

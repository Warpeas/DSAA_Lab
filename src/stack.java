public class stack {
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
    
    static class ListNode {
        int data;
        ListNode next;
        ListNode pre;
    }
    
    static class linkedStack {
        int max_size;
        int t;
        ListNode head;
        ListNode top;
        
        void initLinkedStack() {
            head = new ListNode();
            top = head;
            t = -1;
        }
        
        linkedStack() {
            max_size = -1;
            initLinkedStack();
        }
        
        linkedStack(int max) {
            max_size = max;
            initLinkedStack();
        }
        
        boolean isEmpty() {
            return top == head;
        }
        
        boolean isFull() {
            return t == max_size - 1;
        }
        
        int push(int item) {
            if (isFull()) {
                return 0;
            }
            top.next = new ListNode();
            top.next.pre = top;
            top = top.next;
            t++;
            return 1;
        }
        
        int pop() {
            if (isEmpty()) {
                return 0;
            }
            top = top.pre;
            top.next = null;
            t--;
            return 1;
        }
        
        ListNode peek() {
            if (isEmpty()) {
                return null;
            }
            return top;
        }
        
        void clear() {
            while (top != head) {
                top = top.pre;
                top.next = null;
            }
            t = -1;
        }
        
        int size() {
            return t;
        }
    }
}

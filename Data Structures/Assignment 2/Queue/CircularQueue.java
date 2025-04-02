public class CircularQueue<T> implements QueueInterface<T> {
    private T[] elements;
    private int capacity;
    private int front;
    private int rear;

    public CircularQueue(){
        capacity = 10;
        front = -1;
        rear = -1;
        elements = (T[]) new Object[capacity];
    }

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.front = -1;
        this.rear = -1;
        this.elements = (T[]) new Object[capacity];
    }

    @Override
    public boolean enqueue(T value) {
        if (isFull()) {
            increaseCapacity();
        }
        if (front == -1) {
            front = 0;
        }
        rear = (rear + 1) % capacity;
        elements[rear] = value;
        return true;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new AssertionError("Queue is Empty");
        }
        T value = elements[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new AssertionError("Queue is Empty");
        }
        return elements[front];
    }

    @Override
    public void increaseCapacity() {
        capacity *= 2;
        T[] newElements = (T[]) new Object[capacity];
        for (int i = front; i <= rear; i++) {
            newElements[i - front] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public void clear(){
        rear = -1;
        front = -1;
        elements = (T[]) new Object[capacity];
    }
    
    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        if (rear >= front) {
            return rear - front + 1;
        } else {
            return capacity - front + rear + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return front == -1;
    }

    @Override
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public T[] getElements() {
        return elements;
    }

    public void setElements(T[] elements) {
        this.elements = elements;
    }

    public T getElementAt(int index) {
        return elements[index];
    }
}
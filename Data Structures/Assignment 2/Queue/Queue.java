public class Queue<T> implements QueueInterface<T>{
    private T[] elements;
    private int capacity;
    private int front;
    private int rear;
    
    public Queue(){
        capacity = 10;
        front = -1;
        rear = -1;
        elements = (T[]) new Object[capacity];
    }

    public Queue(int capacity){
        front = -1;
        rear = -1;
        this.capacity = capacity;
        elements = (T[]) new Object[capacity];
    }

    @Override
    public boolean enqueue(T value){
        if(front == -1){
            front += 1;
        }
        if(!isFull()){
            elements[++rear] = value;
            return true;
        }else{
            throw new AssertionError("Queue is Full");
        }
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
            front++;
        }
        
        return value;
    }

    @Override
    public T peek(){
        if(front != -1){
            return elements[front];
        }else{
            throw new AssertionError("Queue is Empty");
        }
    }

    @Override
    public void increaseCapacity() {
        capacity *= 2;
        T[] newElements = (T[]) new Object[capacity];
        for (int i = front; i <= rear; i++) {
            newElements[i - front] = elements[i];
        }
        rear -= front;
        front = 0;
        elements = newElements;
    }

    @Override
    public int size() {
        return isEmpty() ? 0 : (rear - front + 1);
    }

    @Override
    public void clear(){
        rear = -1;
        front = -1;
        elements = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty(){
        return rear == -1;
    }

    @Override
    public boolean isFull(){
        return rear + 1 == capacity;
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
}
public class Stack<T> implements StackInterface<T>{
    private T[] elements;
    private int capacity;
    private int top;

    @SuppressWarnings("unchecked")
    public Stack(){
        top = -1;
        capacity = 10;
        elements = (T[])new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        top = -1;
        this.capacity = capacity;
        elements = (T[])new Object[capacity];
    }

    @Override
    public final boolean push(T value){
        if(!isFull()){
            elements[++top] = value;
            return true;
        }else{
            throw new AssertionError("Stack is Full");
        }
    }

    @Override
    public T pop(){
        if(!isEmpty()){
            top--;
            return elements[top + 1];
        }else{
            throw new AssertionError("Stack is Empty");
        }
    }

    @Override
    public T peek(){
        if(!isEmpty()){
            return elements[top];
        }else{
            throw new AssertionError("Stack is Empty");
        }
    }

    @Override
    public boolean isEmpty(){
        return top == -1;
    }

    @Override
    public boolean isFull(){
        return top == capacity - 1;
    }

    @Override
    public int size(){
        return top + 1;
    }

    @Override
    public void increaseCapacity(int newCapacity){
        if(this.capacity > newCapacity){
            throw new AssertionError("New Capacity is lower than before");
        }else{
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(this.elements, 0, newElements, 0, this.capacity);
            this.elements = newElements;
            this.capacity = newCapacity;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
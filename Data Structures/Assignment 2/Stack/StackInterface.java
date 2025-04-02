package Stack;

public interface StackInterface<T> {
    public boolean push(T value);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public int size();
    public boolean isFull();
    public void increaseCapacity(int newCapacity);
    public void clear();
}

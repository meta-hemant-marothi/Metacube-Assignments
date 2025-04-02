public interface QueueInterface<T> {
    public boolean enqueue(T value);
    public T dequeue();
    public T peek();
    public int size();
    public boolean isEmpty();
    public boolean isFull();
    public void clear();
    public void increaseCapacity();
}

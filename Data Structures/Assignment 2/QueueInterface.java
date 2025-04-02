public interface QueueInterface<T> {
    public boolean add(T value);
    public T remove();
    public T peek();
    public int size();
    public boolean isEmpty();
    public boolean isFull();
}

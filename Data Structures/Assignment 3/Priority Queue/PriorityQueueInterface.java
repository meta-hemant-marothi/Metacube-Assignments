public interface PriorityQueueInterface<T> {
    public boolean insert(T value);
    public T poll();
    public T peek();
    public boolean isEmpty();
    public boolean isFull();
    public void heapify();
}

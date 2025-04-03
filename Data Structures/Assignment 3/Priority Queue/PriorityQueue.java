import java.util.Comparator;

public class PriorityQueue<T extends Comparable<T>> implements PriorityQueueInterface<T> {
    private final int capacity;
    private int currentSize;
    private final T[] elements;
    private final Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public PriorityQueue(Comparator<T> comparator, int capacity) {
        this.currentSize = -1;
        this.capacity = capacity;
        this.comparator = comparator;
        elements = (T[]) new Comparable[this.capacity];
    }

    @Override
    public boolean insert(T value) {
        if (!isFull()) {
            int index = ++currentSize;
            elements[currentSize] = value;

            while (index > 0 && comparator.compare(elements[getParentIndex(index)], elements[index]) < 0) {
                swap(index, getParentIndex(index));
                index = getParentIndex(index);
            }
            return true;
        } else {
            throw new AssertionError("Priority Queue is full");
        }
    }

    @Override
    public T poll() {
        if (!isEmpty()) {
            T root = elements[0];
            elements[0] = elements[currentSize];
            elements[currentSize] = null;
            currentSize--;

            int index = 0;
            while (true) {
                int leftIndex = 2 * index + 1;
                int rightIndex = 2 * index + 2;
                int largestIndex = index;

                if (leftIndex <= currentSize && comparator.compare(elements[leftIndex], elements[largestIndex]) > 0) {
                    largestIndex = leftIndex;
                }
                if (rightIndex <= currentSize && comparator.compare(elements[rightIndex], elements[largestIndex]) > 0) {
                    largestIndex = rightIndex;
                }
                if (largestIndex == index) {
                    break;
                }

                swap(index, largestIndex);
                index = largestIndex;
            }
            return root;
        } else {
            throw new AssertionError("Priority Queue is empty");
        }
    }

    @Override
    public T peek() {
        if (!isEmpty()) {
            return elements[0];
        } else {
            throw new AssertionError("Priority Queue is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return currentSize == -1;
    }

    @Override
    public boolean isFull() {
        return currentSize + 1 == capacity;
    }

    @Override
    public void heapify() {
        for (int i = currentSize / 2; i >= 0; i--) {
            int index = i;
            while (true) {
                int leftIndex = 2 * index + 1;
                int rightIndex = 2 * index + 2;
                int largestIndex = index;

                if (leftIndex <= currentSize && comparator.compare(elements[leftIndex], elements[largestIndex]) > 0) {
                    largestIndex = leftIndex;
                }
                if (rightIndex <= currentSize && comparator.compare(elements[rightIndex], elements[largestIndex]) > 0) {
                    largestIndex = rightIndex;
                }
                if (largestIndex == index) {
                    break;
                }

                swap(index, largestIndex);
                index = largestIndex;
            }
        }
    }

    private void swap(int index1, int index2) {
        T temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public void display() {
        System.out.print("Priority Queue: ");
        for (int i = 0; i <= currentSize; i++) {
            System.out.print(elements[i] + " (" + i + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Max-Heap Comparator
        Comparator<Integer> maxHeapComparator = (o1, o2) -> o1 - o2;

        // Min-Heap Comparator
        Comparator<Integer> minHeapComparator = (o1, o2) -> o2 - o1;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(maxHeapComparator, 20);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(minHeapComparator, 20);

        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(5);
        maxHeap.insert(15);

        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(5);
        minHeap.insert(15);

        System.out.println("Max-Heap:");
        maxHeap.display();

        System.out.println("Min-Heap:");
        minHeap.display();
    }
}
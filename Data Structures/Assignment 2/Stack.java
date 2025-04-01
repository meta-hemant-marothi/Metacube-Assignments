import java.util.LinkedList;

public class Stack<T>{
    private LinkedList<T> elements;

    public Stack(){
        elements = new LinkedList<>();
    }

    public Stack(int size, T defaultValue) {
        elements = new LinkedList<>();
        for(int i = 0; i < size; i++){
            push(defaultValue);
        }
    }

    public boolean push(T value){
        if(elements.add(value)){
            return true;
        }
        return false;
    }

    public T pop(){
        return elements.removeLast();
    }

    public T peek(){
        return elements.getLast();
    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }

    public int size(){
        return elements.size();
    }
}
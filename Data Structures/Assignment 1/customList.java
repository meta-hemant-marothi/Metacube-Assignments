public class customList {
    private int value;
    private customList next;

    public customList(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public customList getNext() {
        return next;
    }

    public void setNext(customList next) {
        this.next = next;
    }
}

package Dictionary;
public class DictionaryPair<K,V> {
    private K key;
    private V value;
    private DictionaryPair<K,V> leftChild;
    private DictionaryPair<K,V> rightChild;

    public DictionaryPair(K key, V value) {
        this.key = key;
        this.value = value;
        leftChild = null;
        rightChild = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public DictionaryPair<K, V> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(DictionaryPair<K, V> leftChild) {
        this.leftChild = leftChild;
    }

    public DictionaryPair<K, V> getRightChild() {
        return rightChild;
    }

    public void setRightChild(DictionaryPair<K, V> rightChild) {
        this.rightChild = rightChild;
    }
    
}

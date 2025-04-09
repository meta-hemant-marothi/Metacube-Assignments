package Dictionary;

import java.util.List;
import java.util.Map;

public class DictionaryBST<K extends Comparable<K>, V> {

    private DictionaryPair<K, V> root;

    public DictionaryBST() {
        root = null;
    }

    public DictionaryBST(List<Map.Entry<K, V>> initialEntries) {
        root = null;
        for (Map.Entry<K, V> entry : initialEntries) {
            add(entry.getKey(), entry.getValue());
        }
    }

    public void add(K key, V value) {
        DictionaryPair<K, V> currentNode = root;
        DictionaryPair<K, V> newPair = new DictionaryPair<>(key, value);
        if (root == null) {
            root = newPair;
            return;
        }
        while (currentNode != null) {
            int comparision = currentNode.getKey().compareTo(key);
            if (comparision < 0) {
                if (currentNode.getRightChild() != null) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode.setRightChild(newPair);
                    break;
                }
            } else {
                if (currentNode.getLeftChild() != null) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode.setLeftChild(newPair);
                    break;
                }
            }
        }
    }

    public V get(K key) {
        DictionaryPair<K, V> currentNode = root;
        if (root == null) {
            throw new AssertionError("Key-Value not found");
        }
        while (currentNode != null) {
            int comparision = currentNode.getKey().compareTo(key);
            if (comparision == 0) {
                return currentNode.getValue();
            } else if (comparision < 0) {
                currentNode = currentNode.getRightChild();
            } else {
                currentNode = currentNode.getLeftChild();
            }
        }
        throw new AssertionError("Key-Value not found");
    }

    public boolean delete(K key) {
        DictionaryPair<K, V> currentNode = root;
        DictionaryPair<K, V> parentNode = null;

        while (currentNode != null && currentNode.getKey().compareTo(key) != 0) {
            parentNode = currentNode;
            int comparison = currentNode.getKey().compareTo(key);
            if (comparison < 0) {
                currentNode = currentNode.getRightChild();
            } else {
                currentNode = currentNode.getLeftChild();
            }
        }
        if (currentNode == null) {
            return false;
        }
        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            if (parentNode == null) {
                root = null; // Deleting the root node
            } else if (parentNode.getLeftChild() == currentNode) {
                parentNode.setLeftChild(null);
            } else {
                parentNode.setRightChild(null);
            }
        } else if (currentNode.getLeftChild() == null || currentNode.getRightChild() == null) {
            DictionaryPair<K, V> childNode = (currentNode.getLeftChild() == null)
                    ? currentNode.getRightChild()
                    : currentNode.getLeftChild();

            if (parentNode == null) {
                root = childNode; // Deleting the root node
            } else if (parentNode.getLeftChild() == currentNode) {
                parentNode.setLeftChild(childNode);
            } else {
                parentNode.setRightChild(childNode);
            }
        } else {
            DictionaryPair<K, V> successor = currentNode.getRightChild();
            DictionaryPair<K, V> successorParent = currentNode;

            while (successor.getLeftChild() != null) {
                successorParent = successor;
                successor = successor.getLeftChild();
            }

            currentNode.setKey(successor.getKey());
            currentNode.setValue(successor.getValue());

            if (successorParent.getLeftChild() == successor) {
                successorParent.setLeftChild(successor.getRightChild());
            } else {
                successorParent.setRightChild(successor.getRightChild());
            }
        }
        return true;
    }

    public void display() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(DictionaryPair<K, V> currentNode) {
        if (currentNode != null) {
            inOrderTraversal(currentNode.getLeftChild());

            System.out.println(currentNode.getKey() + " -> " + currentNode.getValue());

            inOrderTraversal(currentNode.getRightChild());
        }
    }

}

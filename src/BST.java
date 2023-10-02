import java.util.NoSuchElementException;
import java.util.Stack;
public class BST<E extends Comparable<? super E>> implements Iterator<E> {
    private Node<E> root; //root of BST
    private int nodecount; //number of nodes in BST

    // Implement the constructor
    BST() {
        root = null;
        nodecount = 0;
    }

    // Implement the clear method
    public void clear() {
        root = null;
        nodecount = 0;
    }

    // Implement the size method
    public int size() {
        return nodecount;
    }

    // Implement the insert method
    public void insert(E e) {
        root = inserthelp(root, e);
        nodecount++;

    }

    // Implement the remove method
    public E remove(E key) {
        E temp = findhelp(root, key);
        if (temp != null) {
            root = removehelp(root, key);
            nodecount--;
        }
        return temp;
    }

    // Implement the search method
    private E findhelp(Node<E> rt, E key) {
        if (rt == null) return null;
        if (rt.value().compareTo(key) > 0)
            return findhelp(rt.left(), key);
        else if (rt.value().compareTo(key) == 0)
            return rt.value();
        else return findhelp(rt.right(), key);
    }

    public E find(E key) {
        return findhelp(root, key);
    }


    //insert help method
    private Node<E> inserthelp(Node<E> rt, E key) {
        if (rt == null) return new Node<E>(key);
        if (rt.value().compareTo(key) > 0) {
            rt.setLeft(inserthelp(rt.left(), key));
        } else {
            rt.setRight(inserthelp(rt.right(), key));
        }
        return rt;
    }

    // remove help method
    private Node<E> removehelp(Node<E> rt, E key) {
        if (rt == null) return null;
        if (rt.value().compareTo(key) > 0) {
            rt.setLeft(removehelp(rt.left(), key));
        } else if (rt.value().compareTo(key) < 0) {
            rt.setRight(removehelp(rt.right(), key));
        } else {
            if (rt.left() == null) {
                return rt.right();
            } else if (rt.right() == null) {
                return rt.left();
            } else {
                Node<E> temp = getMax(rt.left());
                rt.setValue(temp.value());
                rt.setLeft(deleteMax(rt.left()));
            }
        }
        return rt;
    }

    private Node<E> getMax(Node<E> rt) {
        if (rt.right() == null) {
            return rt;
        } else return getMax(rt.right());
    }

    private Node<E> deleteMax(Node<E> rt) {
        if (rt.right() == null) {
            return rt.right();
        } else {
            rt.setRight(deleteMax(rt.right()));
            return rt;
        }
    }

    // Implement the BSTIterator class -in order tree transversal
    private class BSTIterator implements Iterator<E> {
        private Node currentNode;
        private Stack<Node> stack;

        public BSTIterator() {
            currentNode = root;
            stack = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || currentNode != null;
        }

        @Override
        public E next() {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left();
            }

            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the BST");
            }

            Node<E> node = stack.pop();
            currentNode = node.right();

            return node.value();
        }

        public Iterator<E> iterator() {
            return new BSTIterator();
        }
    }
}





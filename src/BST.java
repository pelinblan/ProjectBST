import java.util.NoSuchElementException;
import java.util.Stack;
public class BST<E extends Comparable<? super E>> {
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
        if (rt.getElement().compareTo(key) > 0)
            return findhelp(rt.getLeft(), key);
        else if (rt.getElement().compareTo(key) == 0)
            return rt.getElement();
        else return findhelp(rt.getRight(), key);
    }

    public E find(E key) {
        return findhelp(root, key);
    }


    //insert help method
    private Node<E> inserthelp(Node<E> rt, E key) {
        if (rt == null) return new Node<E>(key);
        if (rt.getElement().compareTo(key) > 0) {
            rt.setLeft(inserthelp(rt.getLeft(), key));
        } else {
            rt.setRight(inserthelp(rt.getRight(), key));
        }
        return rt;
    }

    // remove help method
    private Node<E> removehelp(Node<E> rt, E key) {
        if (rt == null) return null;
        if (rt.getElement().compareTo(key) > 0) {
            rt.setLeft(removehelp(rt.getLeft(), key));
        } else if (rt.getElement().compareTo(key) < 0) {
            rt.setRight(removehelp(rt.getRight(), key));
        } else {
            if (rt.getLeft() == null) {
                return rt.getRight();
            } else if (rt.getRight() == null) {
                return rt.getLeft();
            } else {
                Node<E> temp = getMax(rt.getLeft());
                rt.setValue(temp.getElement());
                rt.setLeft(deleteMax(rt.getLeft()));
            }
        }
        return rt;
    }

    private Node<E> getMax(Node<E> rt) {
        if (rt.getRight() == null) {
            return rt;
        } else return getMax(rt.getRight());
    }

    private Node<E> deleteMax(Node<E> rt) {
        if (rt.getRight() == null) {
            return rt.getRight();
        } else {
            rt.setRight(deleteMax(rt.getRight()));
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
                currentNode = currentNode.getLeft();
            }

            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the BST");
            }

            Node<E> node = stack.pop();
            currentNode = node.getRight();

            return node.getElement();
        }

        public Iterator<E> iterator() {
            return new BSTIterator();
        }
    }
}





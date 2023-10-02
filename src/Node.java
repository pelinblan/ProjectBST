public class Node<E extends Comparable<? super E>>  {
        private Node<E> rt;

        private Node<E> key;
        private E element;
        private Node<E> left;
        private Node<E> right;

        // Constructors
        Node(){
            left = right = null;
        }
        Node(E val){ left = right = null; element = val; }

        Node(E val, Node<E> l, Node<E> r){ left = l; right = r; element = val; }
        public E value(){
            return element;
        }
        public void setValue(E v){
            element = v;
        }
        public Node<E> left(){ return left; }
        public Node<E> right(){ return right; }
        public void setLeft(Node<E> p){ left = p; }
        public void setRight(Node<E> n){ right = n;}

        public boolean isLeaf(){return ((left == null) && (right == null));}
    }


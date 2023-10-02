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
        public E getElement(){
            return element;
        }
        public void setValue(E v){
            element = v;
        }
        public Node<E> getLeft(){ return left; }
        public Node<E> getRight(){ return right; }
        public void setLeft(Node<E> left){ left = this.left; }
        public void setRight(Node<E> right){ right = this.right;}

        public boolean isLeaf(){return ((left == null) && (right == null));}
    }


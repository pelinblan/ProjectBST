
public interface Iterator<E> {
    // Check if the stack has more elements to iterate over
    boolean hasNext();

    // Get the next element from the stack
    E next();

    // Push an element onto the stack
    void push(E element);

    // Pop and return the top element from the stack
    E pop();

    // Peek and return the top element from the stack without removing it
    E peek();

    // Check if the stack is empty
    boolean isEmpty();

    // Return the size of the stack
    int size();
}

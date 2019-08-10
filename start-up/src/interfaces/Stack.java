package interfaces;

public interface Stack<E> {
    int gerSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();}

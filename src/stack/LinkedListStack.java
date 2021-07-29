package stack;

import list.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> listStack;

    public LinkedListStack() {
        listStack = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return listStack.getSize();
    }

    @Override
    public boolean isEmpty() {
        return listStack.isEmpty();
    }

    @Override
    public void push(E e) {
        listStack.addFirst(e);
    }

    @Override
    public E pop() {
        return listStack.removeFirst();
    }

    @Override
    public E peek() {
        return listStack.getFirst();
    }

    @Override
    public String toString() {
        return "LinkedListStack{" +
                "listStack=" + listStack +
                '}';
    }
}

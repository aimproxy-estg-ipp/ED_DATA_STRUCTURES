package List;

import List.AbstractDoubleLinkedList;
import List.UnorderedListADT;

import java.util.NoSuchElementException;

public class DoubleLinkedUnorderedList<T extends Comparable<T>> extends AbstractDoubleLinkedList<T> implements UnorderedListADT<T> {

    public DoubleLinkedUnorderedList() {
        super();
    }

    @Override
    public void addToFront(T element) {
        Node<T> node = new Node<>(element);

        if (isEmpty()) {
            super.front = super.rear = node;
            currentSize++;
            return;
        }

        super.front.previous = node;
        node.next = super.front;
        super.front = node;
        super.currentSize++;
        super.modCount++;
    }

    @Override
    public void addToRear(T element) {
        Node<T> node = new Node<>(element);

        if (isEmpty()) {
            super.front = super.rear = node;
            currentSize++;
            return;
        }

        node.previous = super.rear;
        super.rear.next = node;
        super.rear = node;
        super.currentSize++;
        super.modCount++;
    }

    @Override
    public void addAfter(T element, T target) throws NoSuchElementException {
        Node<T> node = new Node<>(element);

        // Add at the end of the list
        if (target.compareTo(super.rear.element) == 0) {
            super.rear.next = node;
            node.previous = super.rear;
            super.rear = node;
            super.currentSize++;
            super.modCount++;
            return;
        }

        // Add in the middle
        Node<T> tmp = super.front;
        while (tmp != null) {

            if (target.compareTo(tmp.element) == 0) {
                tmp.next.previous = node;
                node.next = tmp.next;
                node.previous = tmp;
                tmp.next = node;
                super.currentSize++;
                super.modCount++;
                return;
            }

            tmp = tmp.next;
        }

        throw new NoSuchElementException();
    }
}

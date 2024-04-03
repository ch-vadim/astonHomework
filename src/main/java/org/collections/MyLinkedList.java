package org.collections;

import java.util.*;

public class MyLinkedList<E> implements Deque<E> {

    private Node<E> first = null;
    private Node<E> last = null;

    private int size;

    public MyLinkedList() {
        size =0;
    }

    public MyLinkedList(Collection<? extends E> c) {
        size=0;
        for (E el: c) addLast(el);
    }

    @Override
    public void addFirst(E e) {
        Node<E> node = new Node<>(e, null, first);
        if (first == null) {
            last = node;
        } else {
            first.prev = node;
        }
        first = node;
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> node = new Node<>(e, last, null);
        if (last == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
    }

    /**
    * This implementation does not support limitations on the number of elements
    * */
    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    /**
     * This implementation does not support limitations on the number of elements
     * */
    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        Node<E> f = first;
        if (f == null) return null;
        first = f.next;
        if (first == null)  {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return f.value;

    }

    @Override
    public E removeLast() {
        Node<E> l = last;
        if (l == null) return null;
        last = l.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return l.value;
    }

    @Override
    public E pollFirst() {
        return removeFirst();
    }

    @Override
    public E pollLast() {
        return removeLast();
    }

    @Override
    public E getFirst() {
        return first.value;
    }

    @Override
    public E getLast() {
        return last.value;
    }

    @Override
    public E peekFirst() {
        return getFirst();
    }

    @Override
    public E peekLast() {
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (first == null) return false;
        Node<E> node= first;
        while (node!= null) {
            if (remove(o, node)) return true;
            node = node.next;
        }
        return false;

    }

    private boolean remove(Object o, Node<E> node) {
        if (node.value.equals(o)) {
            if (node.prev == null) {
                first = node.next;
            } else {
                node.prev.next = node.next;
            }
            if (node.next == null) {
                last = node.prev;
            } else {
                node.next.prev = node.prev;
            }
            size--;
            node.value = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (last == null) return false;
        Node<E> node= last;
        while (node!= null) {
            if (remove(o, node)) return true;
            node = node.prev;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    /**
     * This implementation does not support limitations on the number of elements
     * */
    @Override
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E el: c) addLast(el);
        return !c.isEmpty();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean contains(Object o) {
        Node<E> node = first;
        while (node != null) {
            if (node.value.equals(o)) return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<>() {
            Node<E> node = last;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E value = node.value;
                node = node.prev;
                return value;
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<E> node = first;
        while (node!=null) {
            array[index] = node.value;
            index++;
            node = node.next;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) toArray();
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object el: c) {
            if(!contains(el)) return false;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean wasRemoved = false;
        for(Object el: c) {
            wasRemoved = wasRemoved || remove(el);
        }
        return wasRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("¯\\_(ツ)_/¯");
    }

    @Override
    public void clear() {
        while (first!= null) {
            Node<E> f = first;
            first = f.next;
            f.next = null;
            f.prev = null;
            f.value = null;
        }
        last = null;
        size = 0;
    }

    public void sort(Comparator<? super E> comparator) {
        boolean flag = true;
        E[] elements = (E[]) this.toArray();
        while (flag) {
            flag = false;
            for (int i = 0; i< size-1; i++) {
                if (comparator.compare(elements[i], elements[i+1]) > 0) {
                    E temp = elements[i];
                    elements[i] = elements[i+1];
                    elements[i+1] = temp;
                    flag = true;
                }
            }
        }
    }

    public static <E extends Comparable<? super E>> void sort(MyLinkedList<E> list) {
        list.sort(Comparator.naturalOrder());
    }

    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}

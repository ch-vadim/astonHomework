package org.collections;

import java.lang.reflect.Array;
import java.util.*;

public class MyArrayList<E> implements Collection<E>{
    private Object[] elements;
    private int size;
    private final static int DEFAULT_INITIAL_CAPACITY = 10;

    public MyArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        elements = new Object[initialCapacity];
        size = 0;
    }

    public MyArrayList(Collection<? extends E> collection) {
        this();
        this.addAll(collection);
        size = collection.size();
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o != null) {
            for (int i = 0; i<this.size; i++) {
                if (o.equals(elements[i])) return true;
            }
        } else {
            for (int i = 0; i<this.size; i++) {
                if (elements[i] == null) return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int curIndex = 0;
            @Override
            public boolean hasNext() {
                return curIndex <size;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (E) elements[curIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }


    @Override
    public boolean add(E e) {
        if (size+1> elements.length) allocDoubleMemory();
        elements[size] = e;
        size++;
        return true;
    }

    private void allocDoubleMemory() {
        Object[] newArray = new Object[2*elements.length+1];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
    @Override
    public boolean remove(Object o) {
        int indexOfElement = -1;
        for (int i=0; i<size; i++) {
            if (o.equals(elements[i])) {
                indexOfElement =i;
                break;
            }
        }
        if (indexOfElement == -1) return false;
        for (int i = indexOfElement; i<size-1; i++) {
            elements[i] = elements[i+1];
        }
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o: c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E el: c) {
            this.add(el);
        }
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean wasRemoved = false;
        for (Object el: c) {
            wasRemoved = wasRemoved || remove(el);
        }
        return wasRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean wasRemoved = false;
        for (int i=0; i<size; i++) {
            if (!c.contains(elements[i])) {
                remove(elements[i]);
                i--;
                wasRemoved = true;
            }
        }
        return wasRemoved;
    }

    @Override
    public void clear() {
        for (int i =0; i<size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public E get(int index) {
        if (index<0 || index>=size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds size " + size);
        return (E) elements[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i= 0; i<size; i++) {
            sb.append(elements[i].toString()).append(", ");
        }
        sb.replace(sb.length()-2, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }

    public void sort(Comparator<? super E> comparator) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i< size-1; i++) {
                if (comparator.compare((E) elements[i], (E) elements[i+1]) > 0) {
                    Object temp = elements[i];
                    elements[i] = elements[i+1];
                    elements[i+1] = temp;
                    flag = true;
                }
            }
        }
    }

    public static <E extends Comparable<? super E>> void sort(MyArrayList<E> list) {
        list.sort(Comparator.naturalOrder());
    }
}

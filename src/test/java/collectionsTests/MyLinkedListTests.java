package collectionsTests;

import org.collections.MyLinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedListTests {

    @Test
    public void constructorFromCollectionTest() {
        List<String> src = new ArrayList<>();
        src.add("1");
        src.add("2");
        src.add("3");
        MyLinkedList<String> list = new MyLinkedList<>(src);
        int index = 0;
        while (index<src.size()) {
            Assert.assertEquals(src.get(index), list.pop());
            index++;
        }
        Assert.assertNull(list.pop());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void oneSizeTest() {
        MyLinkedList<String> list = new MyLinkedList<>();
        String s = "test";
        list.add(s);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(s, list.peekFirst());
        Assert.assertEquals(s, list.peekLast());
    }

    @Test
    public void removeTest() {
        MyLinkedList<String> list = new MyLinkedList<>();
        String a = "a";
        String b = "b";
        String c = "c";
        list.add(a);
        list.add(b);
        list.add(c);
        Assert.assertEquals(3, list.size());
        list.remove(b);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(c, list.pollLast());
        Assert.assertEquals(a, list.pollLast());

    }
}

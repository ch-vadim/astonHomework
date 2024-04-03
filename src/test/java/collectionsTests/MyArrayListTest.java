package collectionsTests;

import org.collections.MyArrayList;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyArrayListTest {

    @Test
    public void assertIsTrueEmpty() {
        MyArrayList<String> list = new MyArrayList<>();

        Assert.assertTrue(list.isEmpty());
    }
    @Test
    public void assertIsFalseEmpty() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("test");

        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void assertZeroSize() {
        MyArrayList<String> list = new MyArrayList<>();

        Assert.assertEquals(0, list.size());
    }

    @Test
    public void assertNonZeroSize() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("first");
        list.add("second");

        Assert.assertEquals(2, list.size());
    }

    @Test
    public void testGetMethod() {
        MyArrayList<String> list = new MyArrayList<>();
        String testString  = "test";

        list.add(testString);

        Assert.assertEquals(testString, list.get(0));
    }

    @Test
    public void assertTrueContains() {
        MyArrayList<String> list = new MyArrayList<>();
        String testString  = "test";

        list.add(testString);

        Assert.assertTrue(list.contains(testString));
    }
    @Test
    public void assertFalseContains() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("test");

        Assert.assertFalse(list.contains("test2"));
    }

    @Test
    public void testAddAllMethod() {
        MyArrayList<String> list = new MyArrayList<>();
        List<String> testCollection = new ArrayList<>();
        testCollection.add("first");
        testCollection.add("second");
        testCollection.add("third");

        list.addAll(testCollection);

        Assert.assertEquals(testCollection.size(), list.size());
        for (int i = 0; i < testCollection.size(); i++) {
            Assert.assertEquals(testCollection.get(i), list.get(i));
        }
    }

    @Test
    public void testConstructorByCollection() {
        List<String> testCollection = new ArrayList<>();
        testCollection.add("first");
        testCollection.add("second");
        testCollection.add("third");

        MyArrayList<String> list = new MyArrayList<>(testCollection);

        Assert.assertEquals(testCollection.size(), list.size());
        for (int i = 0; i < testCollection.size(); i++) {
            Assert.assertEquals(testCollection.get(i), list.get(i));
        }
    }

    @Test
    public void assertUncorrectedGetIndex() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("test");

        Assert.assertThrows(IndexOutOfBoundsException.class, () ->list.get(-1));
        Assert.assertThrows(IndexOutOfBoundsException.class, () ->list.get(1));
    }

    @Test
    public void testRemoveMethod() {
        MyArrayList<String> list = new MyArrayList<>();
        String first = "first";
        String second = "second";
        list.add(first);
        list.add(second);

        Assert.assertTrue(list.remove(first));
        Assert.assertEquals(1, list.size());
        Assert.assertFalse(list.contains(first));
        Assert.assertTrue(list.contains(second));
    }

    @Test
    public void testFalseRemove() {
        MyArrayList<String> list = new MyArrayList<>();
        String first = "first";
        String second = "second";
        list.add(second);

        Assert.assertFalse(list.remove(first));
        Assert.assertEquals(1, list.size());

    }

    @Test
    public void testClearMethod() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("test");

        list.clear();

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void assertTrueContainsAllMethod() {
        List<String> testCollection = new ArrayList<>();
        MyArrayList<String> list = new MyArrayList<>();
        String first = "first";
        String second = "second";
        list.add(first);
        testCollection.add(first);
        list.add(second);
        testCollection.add(second);

        Assert.assertTrue(list.containsAll(testCollection));

    }

    @Test
    public void assertFalseContainsAllMethod() {
        List<String> testCollection = new ArrayList<>();
        MyArrayList<String> list = new MyArrayList<>();
        String first = "first";
        String second = "second";
        list.add(first);
        testCollection.add(first);
        testCollection.add(second);

        Assert.assertFalse(list.containsAll(testCollection));
    }

    @Test
    public void testRetainAllMethod() {
        List<String> testCollection = new ArrayList<>();
        MyArrayList<String> list = new MyArrayList<>();
        String first = "first";
        String second = "second";
        list.add(first);
        list.add(second);
        testCollection.add(first);

        list.retainAll(testCollection);

        Assert.assertTrue(testCollection.containsAll(list));
    }

    @Test
    public void testAllocateNewMemory() {
        MyArrayList<String> list = new MyArrayList<>(1);

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");


        Assert.assertEquals(11, list.size());
        Assert.assertEquals("10", list.get(10));

    }

    @Test
    public void testSortMethod() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        // test comparator by super class
        list.sort(Comparator.comparing((Number o) -> BigDecimal.valueOf(o.doubleValue())));
        boolean sorted = true;
        for (int i = 0; i<list.size()-1; i++) {
            if (BigDecimal.valueOf(list.get(i))
                    .compareTo(BigDecimal.valueOf(list.get(i+1))) > 0) {
                sorted = false;
                break;
            }
        }
        Assert.assertTrue(sorted);
    }

    @Test
    public void testStaticSortMethod() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        MyArrayList.sort(list);
        boolean sorted = true;
        for (int i = 0; i<list.size()-1; i++) {
            if (list.get(i) > list.get(i+1)) {
                sorted = false;
                break;
            }
        }
        Assert.assertTrue(sorted);
    }


}

import org.collections.MyArrayList;
import org.collections.MyLinkedList;


public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Homework ");
        list.add("is ");
        list.add("not");
        String first = list.get(2);
        list.remove(first);

        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("!");
        linkedList.addFirst("complete");

        for (String s : list) System.out.print(s);
        for (String s: linkedList) System.out.print(s);
    }
}

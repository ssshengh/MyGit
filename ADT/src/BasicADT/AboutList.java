package BasicADT;

import org.w3c.dom.ls.LSOutput;
import sortAbout.Sort;

import java.util.*;

/**
 * java中的容器是Collection接口类，为线性表、集合、向量、栈、队列、优先队列定义了共同的操作
 * java中容器主要分两种：一种是元素合集collection，一种是键值对印射表map。
 * set：用于储存一组不重复的元素
 * List：用于储存一组有序元素合集
 * Stack：主要是利用其先进后出的处理方式
 * Queue：后进后出
 * Priority Queue：存储按照优先级处理的对象
 *
 * 全部在java.util中
 * */

public class AboutList {
    public static void main(String[] args) {
        ArrayList<String> collection1 = new ArrayList<>();
        collection1.add("SISI");
        collection1.add("da");
        collection1.add("bendan");
        System.out.println("My honey:");
        System.out.println(collection1);
        //addAll U     removeAll -       retainAll  &

        /**
         * Iterator用于在不暴露数据是如何保存细节的前提下，遍历一个数据结构
         * Collection接口继承自Iterable接口。Iterable接口中定义了iterator方法，该方法会返回一个迭代器
         * Iterator方法为遍历各种类型的合集中的（注意没有map）元素提供了统一的方法
         * */
        Iterator<String> iterator = collection1.iterator();
        while (iterator.hasNext()){
            if (iterator.next() == "da")
                iterator.remove();
            //System.out.println(iterator.next());
        }
        System.out.println(collection1);

        //List
        System.out.println("\nLets see some list");
        List<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(1);
        array.add(4);
        System.out.println(array);
        array.add(0,10);
        array.add(3,30);
        System.out.println("When use new insert method:" + array);

        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(0, "red");
        linkedList.add("sisi love yellow");
        System.out.println(linkedList);
        linkedList.removeLast();
        linkedList.addFirst("green");
        System.out.println(linkedList);
        System.out.println("Now use the list iterator:");
        ListIterator<Object> Li = linkedList.listIterator();
        while (Li.hasNext()){
            System.out.print(Li.next()+ " ");
        }
        System.out.println();
        System.out.println("backward:");
        Li = linkedList.listIterator(linkedList.size());
        while (Li.hasPrevious()){
            System.out.print(Li.previous()+ " ");


        }


        System.out.println("\n线性表和合集的静态方法:");
        List<String> list = Arrays.asList("red", "green", "blue", "black");
        System.out.println(list);
        Collections.sort(list);// up sort
        System.out.println(list);

        System.out.println("Another List:");
        List<String> list1 = Arrays.asList("yellow", "red", "Blue", "Green");
        Collections.sort(list1, Collections.reverseOrder());// down sort
        System.out.println(list1);
        System.out.println("(1) index is :"+Collections.binarySearch(list, "red")); //must be up sorted

        Collections.reverse(list1);
        System.out.println("After reverse :" + list1);
        Collections.shuffle(list1);
        System.out.println("After shuffle :" + list1);
        System.out.println("Use random to shuffle:");
        Random ran = new Random(20);
        System.out.println("Random is :" + ran);
        Collections.shuffle(list1, ran);
        System.out.println(list1);

        //copy将源线性表中的所有元素以同样的下标复制到目标线性表中，二者必须等长，若源线性表大于目标线性表，那么目标线性表中剩余的元素不会受到影响
        System.out.println("\nAbout copy and ncopy : ");
        List<String> st1 = Arrays.asList("yellow", "red", "green", "blue");
        List<String> st2 = Arrays.asList("white", "black");
        Collections.copy(st1, st2);
        System.out.println(st2);
        //copy是浅复制，只是将源线性表的引用复制了过来，nCopies是创建一个包含指定对象的n个副本的不可变线性表
        System.out.println("nCopies:");
        List<GregorianCalendar> list2 = Collections.nCopies(5, new GregorianCalendar(2005, 0 ,1));
        System.out.println(list2);// no add 、delete、update element

        System.out.println("Lets see fill and disjoint:" +
                " ");
        System.out.println("list1 is :" + list1);
        Collections.fill(list1, "sisi");
        System.out.println("list1 is Now : " + list1);
        System.out.println("disjoint list1 & list2 :" + Collections.disjoint(list1, list2)); // true means there are no same elements
    }

}

package BasicADT;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class AboutSet {
    public static void main(String[] args) {
        //set都不能包含重复元素，hashSet可以储存互不相同的元素
        Set<String> s_Hash = new HashSet<>();
        s_Hash.add("Bei Jing");
        s_Hash.add("Shang Hai");
        s_Hash.add("Wu han");
        s_Hash.add("Hang Zhou");
        s_Hash.add("Kun Ming");

        System.out.println("s_Hash is : " + s_Hash);
        System.out.println("usr for-each");
        for (String s : s_Hash)
            System.out.print(s.toUpperCase() + " ");

        s_Hash.remove("Wu han");
        System.out.println("\nWhen we remove Wu Han s_Hash is :" + s_Hash);

        Set<String> set = new HashSet<>();
        set.add("Wuhan");
        set.add("Zhengzhou");
        set.add("Chang Sha");
        System.out.println("set is "+ set);
        s_Hash.addAll(set);
        System.out.println("set+s_Hash is : " + s_Hash);
        set.retainAll(s_Hash);
        System.out.println("keep the same elements" + set);

        //LinkedHashSet 使用链表来扩展hash表，HsahSet里的元素是没有被排序的，但是L可以使得元素按照他们插入集合的顺序提取
        Set<String> S_linked = new LinkedHashSet<>();
        S_linked.add("London");
        S_linked.add("Paris");
        S_linked.addAll(s_Hash);
        System.out.println("S_linked is : " + S_linked);

        //TreeSet确保集合中的元素有序
        Set<String> s_Tree  = new TreeSet<>();
        s_Tree.addAll(S_linked);
        System.out.println("s_Tree is : " + s_Tree);
        System.out.println("first element: " + s_Tree.stream().findFirst());
        System.out.println(s_Tree.hashCode());

        //在无重复元素进行排序方面，集合比线性表更加高效，线性表通过索引来访问元素方面很有用


    }

}

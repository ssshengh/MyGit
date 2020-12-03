package binaryHeap;

import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        int a = 1;
        System.out.println(++a);
        int b = ++a;
        System.out.println(b);

        int [] arr= {1,2,3,4,5,6,7,8,9,10};
        //b=arr[-1];
        System.out.println(b);

        List<Integer> la = new LinkedList<>();
        la.add(10);
        la.add(20);
        la.add(30);

        System.out.println(la.get(2));
    }
}

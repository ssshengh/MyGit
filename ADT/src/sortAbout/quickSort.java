package sortAbout;

import java.util.ArrayList;
import java.util.List;

public class quickSort {

    //基本快速排序法思路
    public static void sort(List<Integer> items){
        if (items.size() > 1){
            List<Integer> smaller = new ArrayList<>();
            List<Integer> larger = new ArrayList<>();
            List<Integer> same = new ArrayList<>();

            Integer chosenItem = items.get(items.size()/2);
            for (Integer i :items){
                if (i>chosenItem)
                    larger.add(i);
                else if(i<chosenItem)
                    smaller.add(i);
                else
                    same.add(i);

            }

            sort(smaller);
            sort(larger);

            items.clear();
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);

        }

    }

    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static <AnyType> void swapReferences( AnyType [ ] a, int index1, int index2 )
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    //输出pivot
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3 ( AnyType[] a, int left, int right){
        int center = (left+right)/2;
        if (a[center].compareTo(a[left])<0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left])<0)
            swapReferences(a, right, left);
        if (a[center].compareTo(a[right])<0)
            swapReferences(a, center, right);

        swapReferences(a, center, right-1);
        return a[right-1];
    }

    static final int CUTOFF = 3;

    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a, int left, int right){
        if (left+CUTOFF <= right)
        {
            AnyType pivot = median3(a, left, right);

            int i = left, j = right-1;
            for (; ;){
                while (a[++i].compareTo(pivot)<0){}
                while (a[--j].compareTo(pivot)>0){}
                if (i<j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right-1);

            quicksort(a, left, i-1);
            quicksort(a, i+1, right);
        }
        else
            Sort.insertingSort(a, left, right);
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a){
        quicksort(a, 0, a.length-1);
    }



}

package sortAbout;

import java.util.Comparator;

public class mergeSort {

    /**
     * 归并排序的核心，将一个数组分为排序完毕的两段进行合并
     * 注意，这里的泛型数组作为引用传递，在整个过程中，这段内存始终存在
     * */
    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] a , AnyType[] tmpArray , int leftPos , int rightPos, int rightEnd){
        int temPos = leftPos;
        int leftEnd = rightPos - 1;
        int numSize = rightEnd - leftPos + 1;

        while (leftPos<=leftEnd && rightPos<=rightEnd)
            if (a[leftPos].compareTo(a[rightPos])<=0)
                tmpArray[temPos++] = a[leftPos++];
            else
                tmpArray[temPos++] = a[rightPos++];

        while (leftPos<=leftEnd)
            tmpArray[temPos++] = a[leftPos++];
        while (rightPos<=rightEnd)
            tmpArray[temPos++] = a[rightPos++];

        for (int i = 0 ; i<numSize ; i++, rightEnd--)
            a[rightEnd] = tmpArray[rightEnd];
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void MergeSort(AnyType [] a, AnyType[] tmpArry, int left, int right)
    {
        if (left < right){
            int center = (right+left)/2;
            MergeSort(a, tmpArry, left, center);
            MergeSort(a, tmpArry, center+1, right);
            merge(a, tmpArry, left, center+1, right);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void MergeSort(AnyType[] a){
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        MergeSort(a, tmpArray, 0, a.length-1);
    }
}

package sortAbout;

public final class HeapSort {
    //数据从下标0开始存储，所以其左儿子的下标是*2+1
    private static int leftChild(int i ){
        return i*2+1;
    }

    /**
     *下滤，这一次是把大的换上来，空穴不断下移，从一颗三个节点的子树开始到下一棵三个节点的子树
     * 注意空穴的用法
     * @param a 处理的二叉堆
     * @param i 开始的位置
     * @param n 数组里的元素数量
     * */
    private static <AnyType extends Comparable<? super AnyType>>
    void percDown(AnyType [] a , int i , int n){
        int child;
        AnyType tmp;

        for (tmp = a[i] ; leftChild(i)<n ; i=child){//注意这里的停止条件不能是i<n,得是每一个用以下滤的节点拥有子节点
            child = leftChild(i);
            if (child != n-1 && a[child].compareTo(a[child+1])<0)//这里的判断条件不能是i<n-1，因为明显和这一步没有关系，应该是子节点有着兄弟节点
                child++;
            if (tmp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;//
        }
        //a[child] = tmp; wrong!!!!
        a[i] = tmp;

    }

    /**
     * 堆排序，取出每一次的第一个节点作为最大的放在数组的最后一个位置，数组长度-1，刚好就空出来了
     * */
    public static <AnyType extends Comparable<? super AnyType>>
    void heapSort( AnyType [] a ){
        for (int i = a.length/2-1 ; i>=0 ; i--)/* buildHeap */
            percDown(a,i,a.length);
        for( int i = a.length - 1; i > 0; i-- )
        {
            swapReferences( a, 0, i );                /* deleteMax */
            percDown( a, 0, i );
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
}

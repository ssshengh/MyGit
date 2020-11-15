package binaryHeap;
import java.io.*;
/*
*结质:
*堆其实就是一颗被完全填满的二叉树，底层上的元素被从左向右填入，这样的树称为完全二叉树
*完全二叉树，高为h，那么就有2^h - 2^h-1个节点，意味着其高为logN
* 堆序性质：
* 让操作快速执行，由于我们想要快速找出最小元，那最小元应该在根上
* */


import org.jetbrains.annotations.NotNull;

public class BinaryHeap <AnyType extends Comparable<? super AnyType>>{

    private static final int DEFAULT_CAPACITY = 10; //Default capacity of the heap array
    private int currentSize ; //Number of elements in heap
    private AnyType [] array ; //The heap of array

    public BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity){
        currentSize = 0;
        array = (AnyType[]) new Comparable[capacity+1];//！！！：Notice   泛型数组的创建
    }


    public BinaryHeap(AnyType @NotNull [] items){
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10 ];

        int i = 1 ;
        for (AnyType item : items)
            array[i++] = item;
        buildHeap();
    }

    /*
     *insert : 上滤：基本思路是利用堆的性质，将数据插入，如果其小于了父节点，那么就向上冒一次
     * @param x the item to insert
     *  */
    public void insert(AnyType x){
        // Check the size of Array. It will be enlarge twice size when the array is filled.
        if (currentSize == array.length - 1)
            enlargeArray( array.length * 2 + 1  );

        //hole is used to record the location
        //hole指向数组的最后的一个空的位置，和数组的第一个位置一样，用以记录并用来操作数组
        //这可能是堆最重要的一个性质，当数组里存储了整个树时，留出第一个位置作为调整位置的话，那么，用元素数加一除以2，得到的刚好是父节点下标
        //percolate up 上滤
        int hole = ++currentSize;
        for ( array[0] = x ; x.compareTo( array[hole / 2] ) < 0 ; hole /=2 )
            array[hole] = array [hole / 2 ];
        array[ hole ] = x;
    }

    /**
     * 删除最小元
     * 找到最小元是简单的，因为总是在根节点
     * 但是删除困难，因为需要将其他元素冒上去，而且需要把最下面的右节点提出来在合适的地方插入（完全二叉树）
     * 步骤：先变成一个空穴，然后把较小子节点移上去，重复.实际操作是，将最后一个元素放到根，然后一直往下冒下去
     * 如果堆中存在偶数个元素，那么将遇到一个结点只有一个儿子的情况，巧妙的办法是，始终保证算法把每一个节点都看为两个儿子
     * @param hole : the index at which the percolate begins
     * */
    private void percolateDown(int hole){
        int child;
        AnyType tmp = array[hole];

        for (; hole * 2 <= currentSize ; hole = child)
        {
            child = hole*2;
            if (child != currentSize && array[child+1] .compareTo(array[child]) < 0)
                child++;
            //每一次都只考虑三个节点的子树
            if (array[child] . compareTo(tmp) < 0)
                array[hole] = array[child];//
            else
                break;
        }
        array[hole] = tmp;
    }
    public  AnyType deleteMin(){
        if(isEmpty())
            throw new NoClassDefFoundError();
        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public void makeEmpty( )
    {
        currentSize = 0;
    }


    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new NoClassDefFoundError();
        return array[ 1 ];
    }


    private void buildHeap(){
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }
    private void enlargeArray( int newSize){
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }
    private boolean isEmpty(){
        return currentSize==0;
    }

}

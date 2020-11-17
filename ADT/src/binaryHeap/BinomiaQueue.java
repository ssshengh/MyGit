package binaryHeap;

import java.nio.BufferUnderflowException;

public class BinomiaQueue <AnyType extends Comparable<? super AnyType>>{
    //使用链表完成二项树以及二项队列，只需要存储数据、每一个节点的第一个儿子（最右边）和右兄弟即可
    private static class BinNode<AnyType>
    {
        // Constructors
        BinNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinNode( AnyType theElement, BinNode<AnyType> lt, BinNode<AnyType> nt )
        {
            element     = theElement;
            leftChild   = lt;
            nextSibling = nt;
        }

        AnyType          element;     // The data in the node
        BinNode<AnyType> leftChild;   // Left child 第一个儿子
        BinNode<AnyType> nextSibling; // Right child 右兄弟
    }

    private  static final int DEFAULT_TREES = 1;
    private int currentSize ; //二项队列中的元素数量
    private BinNode<AnyType> [] TheTrees;//An array of tree roots!!

    public BinomiaQueue( AnyType item ){
        currentSize = 1;
        TheTrees = new BinNode[1];
        TheTrees[0] = new BinNode<>(item , null , null  );
    }

    public BinomiaQueue(){
        TheTrees = new BinNode[DEFAULT_TREES];
        makeEmpty();
    }

    //根据树的数量来扩大，而不是容量
    private void expandTheTrees( int newNumTrees){
        BinNode<AnyType> [] old = TheTrees;
        int oldNumTrees = TheTrees.length;

        TheTrees = new BinNode[newNumTrees];//数组一样不是自适应扩大的
        for (int i = 0 ; i <Math.min(oldNumTrees , newNumTrees ) ; i++)
            TheTrees[i] = old[i];
        for (int i = oldNumTrees ; i < newNumTrees ; i++)
            TheTrees[i] = null;

    }

    public void merge(BinomiaQueue<AnyType> rhs){
        if(this == rhs )
            return;

        currentSize  += rhs.currentSize;

        if (currentSize > capacity()){
            int newNumTrees = Math.max(TheTrees.length , rhs.TheTrees.length);
            expandTheTrees(newNumTrees);
        }

        BinNode<AnyType> carry = null;
        for (int i = 0 , j = 1 ; j <= currentSize ; i++ , j*=2)
        {
            //i记录树节点
            BinNode<AnyType> t1 = TheTrees[i];
            BinNode<AnyType> t2 = i<rhs.TheTrees.length? rhs.TheTrees[i] : null ;

            int whichCase = t1 == null? 0:1;
            whichCase += t2 == null? 0:2;
            whichCase += carry == null? 0:2;

            switch (whichCase)
            {
                case 0:/* No Trees */
                case 1:/* Only This */
                    break;
                case 2: /* Only rhs */
                    TheTrees[ i ] = t2;
                    rhs.TheTrees[ i ] = null;
                    break;
                case 4: /* Only carry */
                    TheTrees[ i ] = carry;
                    carry = null;
                    break;
                case 3: /* this and rhs */
                    carry = combineTrees( t1, t2 );
                    TheTrees[ i ] = rhs.TheTrees[ i ] = null;
                    break;
                case 5: /* this and carry */
                    carry = combineTrees( t1, carry );
                    TheTrees[ i ] = null;
                    break;
                case 6: /* rhs and carry */
                    carry = combineTrees( t2, carry );
                    rhs.TheTrees[ i ] = null;
                    break;
                case 7: /* All three */
                    TheTrees[ i ] = carry;
                    carry = combineTrees( t1, t2 );
                    rhs.TheTrees[ i ] = null;
                    break;
            }


        }
        for( int k = 0; k < rhs.TheTrees.length; k++ )
            rhs.TheTrees[ k ] = null;
        rhs.currentSize = 0;

    }

    public void insert (AnyType x){
        merge(new BinomiaQueue<>(x));
    }

    public AnyType findMin(){
        if(isEmpty())
            throw new BufferUnderflowException();
        return TheTrees[ findMinIndex( ) ].element;
    }


    /**
     * Find index of tree containing the smallest item in the priority queue.
     * The priority queue must not be empty.
     * @return the index of tree containing the smallest item.
     */
    private int findMinIndex(){
        int i;
        int minIndex;

        for (i = 0 ; TheTrees[i] == null ; i++)
            ;//从不为空的地方开始算起

        for (minIndex = i ; minIndex<TheTrees.length ; i++)
            if( TheTrees[ i ] != null &&
                    TheTrees[ i ].element.compareTo( TheTrees[ minIndex ].element ) < 0 )
                minIndex = i;
        return minIndex;
    }


    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new BufferUnderflowException();

        int minIndex = findMinIndex( );
        AnyType minItem = TheTrees[ minIndex ].element;

        BinNode<AnyType> deletedTree = TheTrees[ minIndex ].leftChild;

        // Construct H''
        BinomiaQueue<AnyType> deletedQueue = new BinomiaQueue<>( );
        deletedQueue.expandTheTrees( minIndex );

        deletedQueue.currentSize = ( 1 << minIndex ) - 1;
        for( int j = minIndex - 1; j >= 0; j-- )
        {
            deletedQueue.TheTrees[ j ] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.TheTrees[ j ].nextSibling = null;
        }

        // Construct H'
        TheTrees[ minIndex ] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge( deletedQueue );

        return minItem;
    }

    /**
     * Return the result of merging equal-sized t1 and t2.
     */
    private BinNode<AnyType> combineTrees( BinNode<AnyType> t1, BinNode<AnyType> t2 )
    {
        if( t1.element.compareTo( t2.element ) > 0 )
            return combineTrees( t2, t1 );
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }


    /**
     * Return the capacity.满的时候的二项队列的节点数，可以通过同一高度只能有一棵二项树得到
     */
    private int capacity( )
    {
        return ( 1 << TheTrees.length ) - 1;
        //<<表示左移移，不分正负数，低位补0；
        //>>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
        //>>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
        //左移的实际上是2的倍数，左移一位等于2，两位等于4
    }




    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
        for( int i = 0; i < TheTrees.length; i++ )
            TheTrees[ i ] = null;
    }
}

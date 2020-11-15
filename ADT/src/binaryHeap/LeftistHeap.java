package binaryHeap;

import java.nio.BufferUnderflowException;

public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {
    //内部类，记录每个节点的三个信息：左右儿子、元素以及零路径长
    private static class LeftistNode<AnyType>{
        LeftistNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        LeftistNode( AnyType theElement, LeftistNode<AnyType> lt, LeftistNode<AnyType> rt )
        {
            element = theElement;
            left    = lt;
            right   = rt;
            npl     = 0;
        }

        AnyType              element;      // The data in the node
        LeftistNode<AnyType> left;         // Left child
        LeftistNode<AnyType> right;        // Right child
        int                  npl;          // null path length
    }

    private LeftistNode<AnyType> root; //root

    public LeftistHeap(){
        root = null;
    }

    private LeftistNode<AnyType> merge(LeftistNode<AnyType> h1 , LeftistNode<AnyType> h2){
        if(h1 == null)
            return h2;
        if (h2 == null)       return h1;
        if (h1.element.compareTo(h2.element)<0)
            return merge1(h1,h2);
        else
            return merge1(h2 , h1);
    }

    private LeftistNode<AnyType> merge1(LeftistNode<AnyType> h1 , LeftistNode<AnyType> h2){
        if (h1.left == null)
            h1.left = h2;
        else
        {
            h1.right = merge(h1.right , h2);
            if (h1.left.npl < h1.right.npl)
                swapChildren(h1);
            h1.npl = h1.right.npl+1;
        }
        return h1;
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = merge( new LeftistNode<>( x ), root );
    }

    /*
    * 只要左右儿子的零路程距离不为左大于等于右,只需要调换左右子树即可
    * */
    public static <AnyType> void swapChildren( LeftistNode<AnyType> t){
        LeftistNode<AnyType> tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new BufferUnderflowException();
        return root.element;
    }
    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new BufferUnderflowException();

        AnyType minItem = root.element;
        root = merge( root.left, root.right );

        return minItem;
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }
    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

}

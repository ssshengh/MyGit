public class Recursion {
    //斐波拉切数列
    static int fieb (int n){
        if (n <=2 )
            return n;
        return fieb(n-1)+fieb(n-2);
    }

    //小青蛙跳台阶
    static int littleFrog(int n ){
        //注意结束条件不能是n==1因为，f(2) = f(1)+f(0), f(0) = f(-1)+f(-2),陷入死循环
        if (n<=2)
            return n;
        else
            return littleFrog(n-1)+littleFrog(n-2);
    }

    //反转单链表元素很关键，揭示了递归可以拆解到下一步，而后面的已经全部完成
    static class Node {
        int data;
        Node next;
    }
    static Node reverses(Node head){
        if (head==null || head.next==null)
            return head;
        else{
            Node NewList = reverses(head.next);

            head.next.next = head.next;
            head.next = null;
            return NewList;
        }
    }


}

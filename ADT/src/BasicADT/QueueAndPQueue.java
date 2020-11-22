package BasicADT;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class QueueAndPQueue {
    public static void main(String[] args) {
        //双端队列，支持在两端插入和删除元素
        Deque<String> deque = new LinkedList<>();
        deque.offerFirst("sisi");
        deque.offer("da");
        deque.offerLast("bendan");
        deque.offerFirst("hengheng love:");
        System.out.println("deque:" + deque);
        System.out.println("deque size is: " + deque.size() + ". get me :" + deque.remove() + ". get my honey: " + deque.getFirst());

        System.out.println("Now take priority queue: ");
        PriorityQueue<String> queue1 = new PriorityQueue<>();
        queue1.offer("Beijing");
        queue1.offer("Shanghai");
        queue1.offer("Wuhan");
        queue1.offer("Hangzhou");
        System.out.println("Priority queue using Comparable:");
        while (queue1.size()>0)
            System.out.println(queue1.remove() + " ");

    }
}

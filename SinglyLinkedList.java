import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (size <= 1) {
        return;
    }

    ArrayList<Node<E>> originalList = new ArrayList<>();
    ArrayList<Node<E>> sortedList = new ArrayList<>();

    Node<E> current = head;

    while (current != null) {
        originalList.add(current);
        sortedList.add(current);
        current = current.getNext();
    }

    sortedList.sort((a, b) ->
        a.getElement().compareTo(b.getElement())
    );

    HashMap<Node<E>, Node<E>> nodePairingMap = new HashMap<>();

    for (int i = 0; i < sortedList.size(); i++) {
        nodePairingMap.put(sortedList.get(i), sortedList.get(sortedList.size() - 1 - i));
    }

    // Build the new sequence based on the original positions
    ArrayList<Node<E>> result = new ArrayList<>();

    for (Node<E> node : originalList) {
        result.add(nodePairingMap.get(node));
    }

    for (int i = 0; i < result.size() - 1; i++) {
        result.get(i).setNext(result.get(i + 1));
    }

    result.get(result.size() - 1).setNext(null);

    head = result.get(0);
    tail = result.get(result.size() - 1);

    }
   
}


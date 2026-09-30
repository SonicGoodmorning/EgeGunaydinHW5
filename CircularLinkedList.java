import java.util.*;
// we importing it all.

public class CircularLinkedList<E> {
    private static class Node<E>{
        E data;
        Node<E> next;

        public Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }

    }

    private Node<E> tail = null;
    private int size = 0;

    public void addLast(E item){
        Node<E> newNode = new Node<>(item, null);
        if(tail == null){
            tail = newNode;
            newNode.next = newNode;
        } else {
        newNode.next = tail.next;
        tail.next = newNode;
        tail = newNode;
        }
        size++;
    }

    public E getFirst(){
        if(tail == null){throw new NoSuchElementException("Empty list");}
        return tail.next.data;
    }

    public E removeFirst(){
        if(tail == null){throw new NoSuchElementException("Empty list");}
        Node<E> head = tail.next;
        E removed = head.data;

        if(size == 1){tail = null;}
        else{tail.next = head.next;}
        size--;
        return removed;
    }

    public void rotate(){
        if(tail != null){tail = tail.next;}
    }

    public boolean remove(E item){
        // case 1
        if (tail == null){return false;}
        Node<E> previous = tail;
        Node<E> current = tail.next;
        // case 2
        for(int i = 0; i < size; i++){
            if(Objects.equals(current.data, item)){
                if(size == 1){tail = null;}
                else {
                    previous.next = current.next;
                    if(current == tail){tail = previous;}
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int size(){return size;}

    public boolean isEmpty(){
        if(size == 0){return true;}
        return false;
    }

    public boolean validateStructure(){
        if(tail == null){return size == 0;}
        if(size <= 0){return false;}

        Node<E> last = null;
        Node<E> head = tail.next;
        Node<E> current = head; // easier to just get a head from this

        int c = 0;

        while(current != null && c < size){
            last = current;
            current = current.next;
            c++;

            if(current == head && c < size){return false;}
        }
        return c == size && current == head && last == tail;
    }

    public String toString(){
        if(tail == null){return "[]";}
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> current = tail.next;

        for(int i = 0; i < size; i++){
            sb.append(current.data);
            if(i < size - 1){sb.append(", ");}
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }


}

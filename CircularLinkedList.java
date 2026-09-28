import java.util.NoSuchElementException;
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
        tail.next = tail.next.next;
        return tail.next.data;
    }
    
    public void rotate(){tail = tail.next;}

    public boolean remove(E item){
        // case 1
        if (tail == null){return false;}
        Node<E> current = tail;
        // case 2
        if(tail.data.equals(item)){

            if(tail.next == tail){
                tail = null;
                return false;
            }
            current = tail.next;
            while(current.next != tail){
                current = current.next;
            }
            current.next = tail.next;
            tail = current;
            return true;
        }

        current = tail;

        while(current.next != tail){
            if(current.next.data.equals(item)){
                current.next = current.next.next;
                return true;
            }
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
        
    }

    public String toString(){
        Node<E> current = tail;
        do{
        System.out.print(current.data + " ");
        current = current.next;
        } while (current != tail.next);
    }

    
}

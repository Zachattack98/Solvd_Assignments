package computerrepairservice.linkedlist;

class Node<T> {
    protected T value; //store value of node
    protected Node next; //store address of next node
 
    protected Node(T value) {
        this.value = value;
        this.next = null;
    }
}

public class LinkedListCustom<T> {
 
    private Node<T> head;
    private int length = 0; //length of list
    
    public LinkedListCustom() {
        this.head = null;
    }
    
    //method that allows a node to be added at end of list
    public void add(T element) {
        
        Node<T> node = new Node<>(element);
        
        if (this.head == null) {
            this.head = node;
        } else {
            Node<T> tempnode = head; //temporary node for traversal
            
            //iterate until we recach end of list
            while (tempnode.next != null) {
                tempnode = tempnode.next;
            }
            tempnode.next = node;
        }
    }
 
    //method that allows a node to be added at any given position
    void add(int position, T data) {
        //check if position is valid
        if (position > length + 1) {
            System.out.println("Position Unavailable in LinkedList");
            return;
        }
 
        //if new position is head then replace head node
        if (position == 1) {
            //temporary node that stores previous head value
            Node<T> tempnode1 = head;
 
            //new valued node stored in head
            head = new Node<>(data);
 
            // New head node pointing to old head node
            head.next = tempnode1;
 
            return;
        }
 
        //new temporary node for traversal
        Node<T> tempnode2 = head;
 
        //dummy node with null value that stores previous node
        Node<T> prevnode = new Node<>(null);
        
        //iterating to the given position
        while (position - 1 > 0) {
            prevnode = tempnode2; //assign previous node
            tempnode2 = tempnode2.next; //increment next node
            position--; //decrease position counter
        }
        
        prevnode.next = new Node<>(data); //previous node now points to new value
        prevnode.next.next = tempnode2; //new value now points to former current node
    }
    
    //remove a node from list
    void remove(T key) {
        //dummy node with null value
        Node<T> prevnode = new Node<>(null);
 
        //dummy node pointing to head node
        prevnode.next = head;
 
        //next node that points ahead of current node
        Node<T> nextnode = head.next;
 
        //temporary node for traversal
        Node<T> tempnode = head;
 
        //used to check whether value exists or not before it can be removed
        //assign as false to assume it does not exist
        boolean exists = false;
 
        //if head node needs to be deleted
        if (head.value == key) {
            head = head.next;
 
            //node to be deleted exists
            exists = true;
        }
 
        //iterating over LinkedList
        while (tempnode.next != null) {
            //compare value of key and current node
            if (String.valueOf(tempnode.value).equals(String.valueOf(key))) {
                //if node to be deleted is found previously,
                //node now points to next node skipping the current node
                prevnode.next = nextnode;
                exists = true;
 
                //exit loop now that the node to be deleted has been found
                break;
            }
 
            prevnode = tempnode; //previous node now points to current node 
            tempnode = tempnode.next; //current node now points to next node
            nextnode = tempnode.next; //next node points the node ahead of current node
        }
 
        //compare the last node with the given key value
        if (exists == false && String.valueOf(tempnode.value).equals(String.valueOf(key))) {
            //if node to be deleted is found previously,
            //last node is skipped over
            prevnode.next = null;
            exists = true;
        }
 
        //condition statements based on whether the node to be deleted exists
        if (exists) {
            length--; //reduce length of list
        }
        else {
            System.out.println("Value is not present in linked list!");
            System.out.println();
        }
    }
 
    /*
    //clear the entire list
    void clear() {
        head = null; //head now points to null
        length = 0; //assign length as zero
    }
 
    //verifies if list is empty or not
    boolean empty() {
        //check if head node points to null
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }
    */
    
    //return the length of list
    int length() { 
        return this.length; 
    }
    
    public void printList() {
        Node iterator = head;
        //iterate until no node remains
        while (iterator != null) {
            System.out.println(iterator.value + " ");
            iterator = iterator.next;
        }
    }
}
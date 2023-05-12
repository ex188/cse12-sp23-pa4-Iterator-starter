/*
 * Name: Anthony Chu
 * Email: abc004@ucsd.edu
 * PID: A17496679
 * Did not use any outside source
 * A gather of method for class MyLinkedList that 
 * includes constructor, inner class and public methods. 
 * The purpose is to let user access the MyLinkedList 
 * that was created based on the Node and AbstractList class
 */


 import java.util.AbstractList;
 import java.util.Iterator;
 import java.util.NoSuchElementException;
 import java.util.ListIterator;
/** 
 * MyLinkedList class hold to all the method that 
 * should be modify from the extends class AbstractList. 
 * Important instance variables are: size(the size of the LinkedList),
 * head(The starter dummy node)
 * tail(The ended dummy node)
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     * Important instance variables are: data(the actual values stored),
     * next(the next node of this node)
     * prev(the previous node of this node)
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    protected class MyListIterator implements ListIterator<E> {

        // class variables here
        Node left,right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        public MyListIterator(){
            left=head;
            right=head.getNext();
            idx=0;
            forward=true;
            canRemoveOrSet=false;
        }
        // MyListIterator methods
        public boolean hasNext() {
            return idx<size();
        }
        // more methods, etc.

        public E next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            right=right.getNext();
            left=left.getNext();
            idx++;
            canRemoveOrSet=true;
            forward=true;
            return (E) left.getElement();
        }

        public boolean hasPrevious(){
            return idx>0;
        }

        public E previous(){
            if (!hasPrevious()){
                throw new NoSuchElementException();
            }
            right=right.getPrev();
            left=left.getPrev();
            idx--;
            canRemoveOrSet=true;
            forward=false;
            return (E) right.getElement();
        }

        public int nextIndex() {
            if (!hasNext()) {
                return idx;
            }
            return idx++;
        }

        public int previousIndex() {
            if (!hasPrevious()) {
                return -1;
            }
            return --idx;
        }

        public void add(E element){
            if (element==null){
                throw new NullPointerException();
            }
            Node added=new Node(element);
            added.setNext(left.getNext());
            added.setPrev(right.getPrev());
            left.setNext(added);
            right.setPrev(added);
            left=left.getNext();
            canRemoveOrSet=false;
            idx++;
        }

        public void set(E element){
            if (element==null){
                throw new NullPointerException();
            }
            if (!canRemoveOrSet){
                throw new IllegalStateException();
            }
            if (!forward){
                right.setElement(element);;
            }
            else left.setElement(element);;
        }

        public void remove(){
            if (!canRemoveOrSet){
                throw new IllegalStateException();
            }
            if(forward){
                left=left.getPrev();
                right.setPrev(left);
                left.setNext(right);
                canRemoveOrSet=false;
                idx-=1;
            }
            else{
                right=right.getNext();
                left.setNext(right);
                right.setPrev(left);
                canRemoveOrSet=false;            
            }
        }
    }

    //  Implementation of the MyLinkedList Class

    /**
     * A default constructor to initailize a new class object
     * It will set the size to 0
     * create a dummy head and tail
     * make the head.next and tail.prev point to each other
     */
    public MyLinkedList() {
        /* Add your implementation here */
        // TODO
        size=0;
        head=new Node(null);
        tail=new Node(null);
        head.next=tail;
        tail.prev=head;
    }

    /**
     * To get the size of the object
     * 
     * @return the size of the class object
     */
    @Override
    public int size() {
        // need to implement the size method
        return size; // TODO
    }

    /**
     * To get the element in the index in the object
     * 
     * @param index the index that the element should access
     * 
     * @return the element in the index in the object class object
     */
    @Override
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            return (E) getNth(index).data;
        }  // TODO
    }

    /**
     * To add the element at the require index of the LinkedList
     * 
     * @param index the index that the element should place
     * @param data the element that will be insert
     */
    @Override
    public void add(int index, E data) {
        /* Add your implementation here */
        // TODO
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        // inital the node element that will be insert
        Node element = new Node(data);
        // setup current node that will be next and prev of the add element
        Node currNodeNext= head;
        Node currNodePrev= tail;
        // two for loop to get to target nodes
        for (int i=0; i<index; i++){
            currNodeNext=currNodeNext.getNext();
        }
        for (int j=index; j<size();j++){
            currNodePrev=currNodePrev.getPrev();
        }
        currNodeNext.setNext(element);
        element.setPrev(currNodeNext);
        currNodePrev.setPrev(element);
        element.setNext(currNodePrev);
        size+=1;
    }

    /**
     * To add the element at the end of the LinkedList
     * 
     * @param data the element that will be insert
     * 
     * @return return true because it is a boolean function 
     * due to the method definition in AbstractList
     */
    @Override
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        // inital the node element that will be insert
        Node element = new Node(data);
        // setup current node that will be prev of the add element
        Node currNodeNext= head;
        for (int i=0; i<size; i++){
            currNodeNext=currNodeNext.getNext();
        }
        currNodeNext.setNext(element);
        element.setPrev(currNodeNext);
        tail.setPrev(element);
        element.setNext(tail);
        size+=1;
        return true;
    }

    /**
     * To set the element at the require index in the LinkedList
     * 
     * @param index the index that the element should be modify
     * @param data the element that will be insert
     * 
     * @return return the original element that was modified
     */
    @Override
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // setup node that will be modify
        Node prve = getNth(index);
        // inital the node element that will be insert
        Node element = new Node(data);
        element.setNext(prve.getNext());
        element.setPrev(prve.getPrev());
        prve.getNext().setPrev(element);
        prve.getPrev().setNext(element);
        return (E) prve.getElement();
    }

    /**
     * To remove the element at the require index in the LinkedList
     * 
     * @param index the index that the element should be remove
     * 
     * @return return the removed element
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // setup the node that will be remove
        Node output = getNth(index);
        output.getPrev().setNext(output.getNext());
        output.getNext().setPrev(output.getPrev());
        size-=1;
        return (E) output.getElement();
    }

    /**
     * To clear the entire LinkedList
     */
    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * To check the LinkedList is empty
     * 
     * @return return true is the LinkedList if it 
     * is empty, else return false
     */
    @Override
    public boolean isEmpty() {
        if (size==0){
            return true;
        }
        return false;  // TODO
    }

    /**
     * To get the element at the require index in the LinkedList
     * by looping the whole LinkedList
     * 
     * @param index the index that the element should be get
     * 
     * @return return the element that's in the index
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node output=head.getNext();
        // a for loop that will go through the whole LinkedList
        for (int i = 0; i < index; i++) {
            output = output.getNext();
        }
        return (Node) output;
    }
}

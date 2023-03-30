package com.cqueltech.linkedlist;

/*
 * Class definition to define each element for a linked list. Consists of a single
 * class variable 'prev' (stores reference/pointer to the previous node in the list).
 * Extends super class 'Node' to allow DNode to act as elements for a doubly linked
 * list.
 * 
 * @param   prev : of type 'Node', a reference pointer to previous node in the list
 */
public class DNode<T> extends SNode<T> {
  
  private DNode<T> prev;

  DNode(T object) {
    super(object); // Constructor for superclass
    this.prev = null;
  }

  DNode<T> getPrev() {
    return prev;
  }

  void setPrev(DNode<T> prev) {
    this.prev = prev;
  }
}

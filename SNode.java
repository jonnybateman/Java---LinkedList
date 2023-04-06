package com.cqueltech.linkedlist;

/*
 * Class definition to define each element for a linked list. Consists of two class
 * variables, 'data' (data to be stored in the element) and 'next' (stores reference/
 * pointer to the next node in the list).
 * 
 * @param   object : of type 'Object', item to be stored within the node.
 * @param   next : of type 'Node', a reference/pointer to the next node in the list
 * 
 * @author  Jonathan Bateman
 * @version 1.0.1
 */
class SNode<T> {
  private T object;
  private SNode<T> next;

  SNode(T object) {
    this.object = object;
    this.next = null;
  }

  T getObject() {
    return object;
  }

  void setData(T object) {
    this.object = object;
  }

  SNode<T> getNext() {
    return next;
  }

  void setNext(SNode<T> next) {
    this.next = next;
  }
}

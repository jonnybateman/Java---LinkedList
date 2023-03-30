package com.cqueltech.linkedlist;

/*
 * Interface describing the implementations for an Iterator object. Used when
 * iterating through a Linked List.
 * 
 * @author  Jonathan Bateman
 * @version 1.0
 */
public interface IIterator<T> {

  public enum IteratorDirection {
    FORWARD,
    BACKWARD
  }

  /*
   * Getters and setters for Class level variables.
   */
  public SNode<T> getPrev();
  public SNode<T> getCurr();
  public SNode<T> getNext();
  public void setPrev(SNode<T> node);
  public void setCurr(SNode<T> node);
  public void setNext(SNode<T> node);

  /*
   * Returns true when there is another node after the current node or when no node has yet been
   * processed but there is at least one in the list. Otherwise returns false.
   */
  default public boolean hasNext() {
    return getNext() == null ? false : true;
  }

  /*
   * Target the next node in the list. The next node then becomes the current node and is ready
   * to be processed.
   */
  default public void next() {
    if (getCurr() != null) {
      // If curr node was null from previous iteration then curr was removed so
      // prev node will remain the same in this next iteration i.e. will not change.
      setPrev(getCurr());
    }
    setCurr(getNext());
    setNext(getNext().getNext());
  }

  /*
   * Returns true when there is another node before the current node or when no node has yet been
   * processed but there is at least one in the list. Otherwise returns false. Should only be used
   * with an iterator for a doubly linked list.
   */
  public boolean hasPrev();

  /*
   * Target the next node in the list. The next node then becomes the current node and is ready
   * to be processed. Should only be used with an iterator for a doubly linked list.
   */
  public void prev();

  /*
   * Remove the current node from the list.
   */
  public void remove();

  /*
   * Retrieve the object from the current node.
   */
  default public T get() {
    return getCurr() == null ? null : getCurr().getObject();
  }
  /*
   * Set the data property of the current node. Checks that the current node has not been removed
   * prior to setting the data property.
   * 
   * @param   data : object to replace the existing object in the current node.
   */
  default public void set(T data) {
    if (getCurr() != null) {
      // Current node has not been removed so we can set data property.
      getCurr().setData(data);
    }
  }

  /*
   * Add a new node to the list before the current node.
   * 
   * @param   data : object to be stored in the new node.
   */
  public void addBefore(T data);

  /*
   * Add a new node to the list after the current node.
   * 
   * @param   data : object to be stored in the new node.
   */
  public void addAfter(T data);
}
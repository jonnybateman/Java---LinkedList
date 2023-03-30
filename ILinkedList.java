package com.cqueltech.linkedlist;

/*
 * Interface describing the implementations for custom linked list class CqueltechLinkedList
 * 
 * @author  Jonathan Bateman
 * @version 1.0
 */
public interface ILinkedList<T> {

  /*
   * Insert a new node at the end of the list.
   */
  void add(T data);

  /*
   * Insert a new node in the list at the specified index.
   */
  void add(T data, int index);

  /*
   * Retrieves and removes the head (first element) of this list. Returns
   * null if list is empty.
   */
  public T poll();

  /*
   * Getters for Class level variables.
   */
  public SNode<T> getHead();
  public SNode<T> getTail();
  public int getSize();

  /*
   * Return boolean, true if list is empty, false if not.
   */
  default boolean isEmpty() {
    return getHead() == null ? true : false;
  }

  /*
   * Return the number of elements in the list.
   */
  default public int size() {
    return getHead() == null ? 0 : getSize();
  }

  /*
   * Retrieves, but does not remove, the head (first element) of this list.
   * Returns null if list is empty
   */
  default T peek() {
    return getHead() == null ? null : getHead().getObject();
  }

  /*
   * Retrieves, but does not remove, the tail (last element) of this list.
   * Returns null if list is empty
   */
  default T peekTail() {
    return getTail() == null ? null : getTail().getObject();
  }

  /*
   * Return the index of node that contains specified data item.
   * 
   * @param   object : item that is being searched for.
   */
  default public int indexOf(T object) {
    int idx = 0;
    if (object == null) {
      for (SNode<T> n=getHead(); n!=null; n=n.getNext()) {
        if (n.getObject() == null) {
          return idx;
        }
        idx++;
      }
    } else {
      for (SNode<T> n=getHead(); n!=null; n=n.getNext()) {
        if (n.getObject().equals(object)) {
          return idx;
        }
        idx++;
      }
    }
    return -1;
  }

  /*
   * Determine if list contains the specified object. Returns true if the object
   * is present.
   * 
   * @param   data : item being searched for.
   */
  default public boolean contains(T object) {
    return indexOf(object) >= 0;
  }

  /*
   * Construct an IndexOutOfBounds exception message.
   * 
   * @param   idx : positional identifier that is out of bounds.
   */
  default public String outOfBoundsMsg(int idx) {
    return "Index: " + idx + "out of bounds. Collection size: " + getSize();
  }

  /*
   * Check that a valid index has been provided for an existing node. If out of
   * bounds throw exception.
   * 
   * @param   idx : positional identifier for existing node.
   */
  default public void checkNodeIndexValid(int idx) {
    if (idx < 0 || idx >= getSize()) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(idx));
    }
  }

  /*
   * Checks if the argument passed is a valid position for example an add operation.
   * If not an out of bounds exception is thrown.
   * 
   * @param   idx : positional identifier checked for valid position.
   */
  default public void checkPositionIndexValid(int idx) {
    if (idx < 0 || idx > getSize()) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(idx));
    }
  }
}

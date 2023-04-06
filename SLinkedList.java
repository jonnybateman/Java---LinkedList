package com.cqueltech.linkedlist;

/*
 * A custom linked list class. This class denotes a singly linked list. Uses the Node class
 * to generate elements for the list.
 * 
 * Class variables: head - Stores the first node in the list.
 *                  tail - Stores the last node in the list.
 *                  listSize - Stores the current size of the list.
 * 
 * Public methods:
 *    add(Object object) : Add an element to the end of the list.
 *    add(Object object, int idx) : Insert element into list at specified index.
 *    remove(int indx) : Remove an element from the list at the specified index.
 *    poll() : Retrieves and removes the head (first element) of this list.
 *    iterator() : Return a ListIterator object to alow us to iterate through a list and
 *                 manipulate the nodes it contains.
 *
 * @author  Jonathan Bateman
 * @version 1.0.1
 */
public class SLinkedList<T> implements ILinkedList<T> {

  private SNode<T> head;
  private SNode<T> tail;
  private int listSize;

  public SLinkedList() {
    this.head = null;
    this.tail = null;
    this.listSize = 0;
  };

  public SNode<T> getHead() {
    return head;
  }

  public SNode<T> getTail() {
    return tail;
  }

  public int getSize() {
    return listSize;
  }

  /*
   * @Overloaded method
   * Add an element to the end of the list.
   * 
   * @param object : Parameter of type 'object' which will be stored in the new node
   * instance.
   * 
   */
  public void add(T object) {

    // Create a new node and assign the data object passed to it.
    SNode<T> newNode = new SNode<>(object);

    // Check if any nodes have already been added to the list.
    if (listSize == 0) {
      // No nodes currently exist in the list, set the head and tail nodes to the new
      // node.
      // In essence what we are doing is assigning the reference/pointer of the new
      // node to head and tail.
      head = newNode;
      tail = newNode;
    } else {
      // List has at least one node add new node to the end of the list.
      // For the current tail node set the next node reference to the new node. The
      // new node will be the new tail.
      tail.setNext(newNode);
      // We have a new tail node so set the tail node reference to the reference of
      // the new node.
      tail = newNode;
    }

    // Increase the size of the list by 1.
    listSize++;
  }

  /*
   * @Overloaded method
   * Add an element at a specific location within the list.
   * Set index to 0 to add at beginning of list.
   * Set index equal to list size to add to end of list.
   * 
   * @param object : Parameter of type 'object' which will be stored in the new node
   * instance.
   * 
   * @param idx : Parameter of type 'int'. Position within the list that the new
   * node is to be inserted.
   * 
   */
  public void add(T object, int idx) {

    if ((idx == 0 && listSize == 0) || idx == listSize) {
      // Adding first node in list or adding node to end of list.
      add(object);
    } else {
      checkPositionIndexValid(idx);
      // Adding at the beginning of an initialised list or adding before and after
      // existing nodes.
      SNode<T> newNode = new SNode<>(object);

      if (idx == 0) {
        newNode.setNext(head);
        head = newNode;
      } else {
        SNode<T> currNode = head.getNext();
        SNode<T> prevNode = head;
        for (int i = 1; i <= idx; i++) {
          if (i == idx) {
            prevNode.setNext(newNode);
            newNode.setNext(currNode);
          }
          prevNode = currNode;
          currNode = currNode.getNext();
        }
      }
      listSize++;
    }
  }

  /*
   * Remove an element from the list at the specified index.
   * 
   * When removing an item we need to set the 'next' reference of the previous
   * node to refer to the node that
   * comes after the node that we are deleting. This will leave no reference to
   * the node being deleted so will
   * be destroyed by the garbage collector.
   * 
   * @param idx : Parameter of type 'int', acts as indentifier for element to be
   * removed from list
   * 
   */
  public void remove(int idx) {

    SNode<T> currNode = head;
    SNode<T> prevNode = null;

    checkNodeIndexValid(idx);
    for (int i = 0; i <= idx; i++) {
      if (idx == 0) {
        // We are trying to remove the head of the list so set the new head of the list
        // to the current head's 'next' node reference.
        head = currNode.getNext();
        currNode.setNext(null);
      } else if (i == idx) {
        // Reached the node we want to remove. Set the 'next' reference of the previous
        // node to the 'next' reference of the current node.
        prevNode.setNext(currNode.getNext());

        if (currNode.getNext() == null) {
          // The 'next' reference of the current node is null as it is the tail node, set
          // the previous
          // node to now act as the list's tail node.
          tail = prevNode;
        } else {
          currNode.setNext(null);
        }
      }
      prevNode = currNode;
      currNode = currNode.getNext();
    }
    listSize--;
  }

  /*
   * Retrieves and removes the head (first element) of this list. Returns null if list
   * is empty
   */
  public T poll() {
    T object = head == null ? null : head.getObject();
    if (head != null) {
      SNode<T> newHead = head.getNext();
      head.setNext(null);
      head = newHead;
      listSize--;
    }
    return object;
  }
  
  /*
   * Returns an object of ListIterator, used to iterate through
   * a singly linked list
   */
  public IIterator<T> iterator() {
    return new ListIterator();
  }

  /*
   * Iterator class to allow list to be travesersed unidirectionally.
   * 
   * @param next : holds the reference/pointer to the next node to be processed.
   * 
   * @public methods:
   *    hasNext() - Returns true if there is still at least one more node in the list.
   * 
   *    next() - Returns the next node in the list to be processed.
   * 
   *    remove() - Removes the current node from the list. Will point the previous
   *               node to the node that comes after the node being removed. next()
   *               must be called in the current iteration prior to calling remove().
   * 
   *    set() - Set the data object of the current node to the object passed into the
   *            method.
   * 
   *    addBefore() - Adds a new node to the list, placed before the current node in the
   *                  iteration. Iterator.next() must be called in the current iteration
   *                  prior to calling addBefore().
   * 
   *    addAfter() - Adds a new node to the list, placed after the current node in the
   *                 iteration. Iterator.next() must be called in the current iteration
   *                 prior to calling addAfter().
   * 
   *    Note: Only one instance of remove(), addBefore() or addAfter() should be invoked
   *          per iteration.
   */
  private class ListIterator implements IIterator<T> {

    private SNode<T> prev;
    private SNode<T> curr;
    private SNode<T> next;

    private ListIterator() {
      this.prev = null;
      this.curr = null;
      this.next = head;
    }

    public SNode<T> getPrev() {
      return prev;
    }

    public SNode<T> getCurr() {
      return curr;
    }

    public SNode<T> getNext() {
      return next;
    }

    public void setPrev(SNode<T> node) {
      prev = node;
    }

    public void setCurr(SNode<T> node) {
      curr = node;
    }

    public void setNext(SNode<T> node) {
      next = node;
    }

    /*
     * Interface implementation of method. Has no function for a singly linked list. Return false.
     */
    public boolean hasPrev() {
      return false;
    }

    /*
     * Interface implementation of method. Has no function for a singly linked list. Do nothing.
     */
    public void prev() {
    }

    /*
     * Removes the current node from the list. This method should not be called unless next() has been
     * called prior in the same iteration. The previous node to the one being removed will be changed
     * so that it points to the node immediately after the one being removed 
     */
    public void remove() {
      if (curr != head) {
        prev.setNext(next);
        if (next == null) {
          tail = prev;
        }
      } else {
        head = next;
      }
      curr.setNext(null);
      curr = null;
      listSize--;
    }

    public void addBefore(T object) {
      SNode<T> newNode = new SNode<>(object);
      if (prev == null) {
        head = newNode;
      } else {
        prev.setNext(newNode);
      }
      newNode.setNext(curr);
      listSize++;
    }

    public void addAfter(T object) {
      SNode<T> newNode = new SNode<>(object);
      newNode.setNext(curr.getNext());
      if (newNode.getNext() == null) {
        tail = newNode;
      }
      curr.setNext(newNode);
      listSize++;
    }
  }
}
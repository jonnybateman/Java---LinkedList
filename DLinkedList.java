package com.cqueltech.linkedlist;

/*
 * A custom linked list class. This class denotes a doubly linked list. Uses the Node class
 * to generate elements for the list.
 * 
 * Class variables: head - Stores the first node in the list.
 *                  tail - Stores the last node in the list.
 *                  listSize - Stores the current size of the list.
 * 
 * Public methods:
 *    add(Object object) : Add an element to the end of the list.
 *    add(Object object, int index) : Insert element into list at specified index.
 *    remove(int idx) : Remove an element from the list at the specified index.
 *    poll() : Retrieves and removes the head (first element) of this list.
 *    pollTail() : Retrieves and removes the tail (last element) of this list.
 *    iterator() : Return a ListIterator object to alow us to iterate through a list and
 *                 manipulate the nodes it contains.
 *
 * @author  Jonathan Bateman
 * @version 1.0.1
 */
public class DLinkedList<T> implements ILinkedList<T> {

  private DNode<T> head;
  private DNode<T> tail;
  private int listSize;

  public DLinkedList() {
    this.head = null;
    this.tail = null;
    listSize = 0;
  };

  public DNode<T> getHead() {
    return head;
  }

  public DNode<T> getTail() {
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
    DNode<T> newNode = new DNode<>(object);

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
      // Set the prev reference of the new node to that of the old tail node.
      newNode.setPrev(tail);
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
   * @param data : Parameter of type 'object' which will be stored in the new node
   * instance.
   * 
   * @param idx : Parameter of type 'int'. Position within the list that the new
   * node is to be added.
   * 
   */
  public void add(T object, int idx) {

    if ((idx == 0 && listSize == 0) || idx == listSize) {
      // Adding first node in list or adding node to end of list.
      add(object);
    } else {
      // Adding at the beginning of an initialised list or inserting at specified
      // position.
      DNode<T> newNode = new DNode<>(object);

      if (idx == 0) {
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
      } else {
        checkPositionIndexValid(idx);
        DNode<T> currNode = (DNode<T>) head.getNext();
        DNode<T> prevNode = head;

        for (int i = 1; i <= idx; i++) {
          if (i == idx) {
            prevNode.setNext(newNode);
            newNode.setNext(currNode);
            newNode.setPrev(prevNode);
            currNode.setPrev(newNode);
          }
          prevNode = currNode;
          currNode = (DNode<T>) currNode.getNext();
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
    DNode<T> currNode = head;
    DNode<T> prevNode = null;

    checkNodeIndexValid(idx);
    for (int i = 0; i <= idx; i++) {
      if (idx == 0) {
        // We are trying to remove the head of the list so set the new head of the list
        // to the current head's 'next' node reference.
        head = (DNode<T>) currNode.getNext();
        head.setPrev(null);
        currNode.setNext(null);
      } else if (i == idx) {
        // Reached the node we want to remove. Set the 'next' reference of the previous
        // node to the 'next' reference of the current node and
        prevNode.setNext(currNode.getNext());

        if (currNode.getNext() == null) {
          // The 'next' reference of the current node is null as it is the tail node, set
          // the previous node to now act as the list's tail node.
          tail = prevNode;
        } else {
          // Set the 'prev' reference of the next node to the reference of the previous
          // Node.
          ((DNode<T>) currNode.getNext()).setPrev(prevNode);
          // Clear the current node's next reference.
          currNode.setNext(null);
        }
        // Clear the current node's prev reference.
        currNode.setPrev(null);
        prevNode = currNode;
        currNode = (DNode<T>) currNode.getNext();
      }
    }
    listSize--;
  }

  /*
   * Retrieves and removes the head (first element) of this list. Returns null if list
   * is empty
   */
  public T poll() {
    T data = head == null ? null : head.getObject();
    if (head != null) {
      DNode<T> oldHead = head;
      head = (DNode<T>) head.getNext();
      head.setPrev(null);
      oldHead.setNext(null);
      listSize--;
    }
    return data;
  }

  /*
   * Retrieves and removes the tail (last element) of this list. Returns
   * null if list is empty.
   */
  public T pollTail() {
    T data = tail == null ? null : tail.getObject();
    if (tail != null) {
      DNode<T> oldTail = tail;
      tail = tail.getPrev();
      tail.setNext(null);
      oldTail.setPrev(null);
      listSize--;
    }
    return data;
  }

  /*
   * Returns an object of interface Iterator, used to iterate through
   * a singly linked list
   */
  public IIterator<T> iterator(IIterator.IteratorDirection direction) {
    return new ListIterator(direction);
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
   *    get() - Retrieve the data object stored in the current node.
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

    private DNode<T> prev;
    private DNode<T> curr;
    private DNode<T> next;

    private ListIterator(IteratorDirection direction) {

      if (direction == IteratorDirection.FORWARD) {
        this.prev = null;
        this.next = head;
      } else {
        this.prev = tail;
        this.next = null;
      }
      this.curr = null;
    }

    public DNode<T> getPrev() {
      return prev;
    }

    public DNode<T> getCurr() {
      return curr;
    }

    public DNode<T> getNext() {
      return next;
    }

    public void setPrev(SNode<T> node) {
      prev = (DNode<T>) node;
    }

    public void setCurr(SNode<T> node) {
      curr = (DNode<T>) node;
    }

    public void setNext(SNode<T> node) {
      next = (DNode<T>) node;
    }

    /*
     * Returns true when there is another node before the current node or when no node has yet been
     * processed but there is at least one in the list. Otherwise returns false.
     */
    public boolean hasPrev() {
      return prev == null ? false : true;
    }

    /*
     * Target the previous node in the list. The previous node then becomes the current node and is ready
     * to be processed.
     */
    public void prev() {
      if (curr != null) {
        // If curr node was null from previous iteration then curr was removed so
        // next node will remain the same in this next iteration i.e. will not change.
        next = curr;
      }
      curr = prev;
      prev = curr.getPrev();
    }

    /*
     * Removes the current node from the list. This method should not be called unless next() has been
     * called prior in the same iteration. The previous and next nodes to the one being removed will
     * have their 'next'/'prev' pointer variables set to point to one another.
     */
    public void remove() {
      if (curr != head) {
        prev.setNext(next);
        if (next == null) {
          tail = prev;
        } else {
          next.setPrev(prev);
        }
      } else {
        head = next;
        next.setPrev(null);
      }
      curr.setNext(null);
      curr.setPrev(null);
      curr = null;
      listSize--;
    }

    /*
     * Add a new node before the current node. The previous node in the current iteration will
     * point to the new node and the current node will point back to the new node.
     * 
     * @param   data : the object to be stored in the new node.
     */
    public void addBefore(T data) {
      DNode<T> newNode = new DNode<>(data);
      if (prev == null) {
        head = newNode;
      } else {
        prev.setNext(newNode);
        newNode.setPrev(prev);
      }
      newNode.setNext(curr);
      curr.setPrev(newNode);
      listSize++;
    }

    /*
     * Add a new node after the current node. The current node in the current iteration will
     * be pointed to the new node and the next node will point back to the new node.
     * 
     * @param   data : the object to be stored in the new node.
     */
    public void addAfter(T data) {
      DNode<T> newNode = new DNode<>(data);
      newNode.setNext(curr.getNext());
      newNode.setPrev(curr);
      if (next == null) {
        tail = newNode;
      }
      curr.setNext(newNode);
      listSize++;
    }
  }
}

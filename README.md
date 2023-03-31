# Java--CustomLinkedList

This repository contains a java package for a linked list, a linear data structure used to store the elements (nodes) in contiguous locations. The package contains classes and interfaces for singly and doubly linked lists. A linked list has pointers that are used to link the elements, and each element in the linked list consists of two parts, namely the data part and the address part. The data part is the value or object to be stored in an element, and the address part consists of a pointer to the next element in the list. In the case of a doubly linked list each element has an additional part, a pointer to link an element to the previous element in the list. Each element in the list is called a node.

## Syntax
The syntax to define an instance of this custom linked list is:

&emsp;*SLinkedList<data_type> list_name = new SlinkedList<>();   // for a single linked list*
   
&emsp;*DLinkedList<data_type> list_name = new DlinkedList<>();   // for a double linked list*
   
The syntax to define an instance of Iterator for a list is as follows:

&emsp;*IIterator<data_type> iterator_name = list_name.iterator();   // for a single linked list*
  
&emsp;*IIterator<data_type> iterator_name = list_name.iterator(IteratorDirection.direction);   // for a double linked list*
  
&emsp;&emsp;A Doubly Linked list can be iterated either forwards or backwards. Direction needs to be specified when initiating the iterator:

&emsp;&emsp;- IteratorDirection.FORWARD  
&emsp;&emsp;- IteratorDirection.BACKWARDS
   
## Methods (Linked List)

|Method|Description|SLinkedList|DLinkedList|
|------|-----------|:---------:|:---------:|
|isEmpty()|Returns a boolean, true if list is empty, false if not.|Yes|Yes|
|size()|Return number of elements/nodes in list.|Yes|Yes|
|add(Object o)|Add node to end of list and assign object to it.|Yes|Yes|
|add(Object o, int index)|Add node to list at specified index. Assign object to it.|Yes|Yes|
|remove(int index)|Remove node from list at specified index|Yes|Yes|
|peek()|Returns the object stored in the first node of the list.|Yes|Yes|
|peekTail()|Returns the object stored in the last node of the list.|Yes|Yes|
|poll()|Returns the object stored in the first node of list and removes the node from list.|Yes|Yes|
|pollTail()|Returns the object stored in the last node of the list and removes the node from the list|Yes|Yes|
|indexOf(Object o)|Return the index of element/node that contains the specified object.|Yes|Yes|
|contains(Object o)|Returns true if list contains the specified object.|Yes|Yes|
|iterator()|Return a ListIterator object for a particular list. Used to iterate through that list|Yes|Yes|

## Methods (Iterator)

|Method|Description|SlinkedList|DLinkedList|
|------|-----------|-----------|-----------|
|hasNext()|

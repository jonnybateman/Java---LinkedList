# Java--CustomLinkedList

This repository contains a java package for a linked list, a linear data structure used to store the elements (nodes) in contiguous locations. The package contains classes and interfaces for singly and doubly linked lists. A linked list has pointers that are used to link the elements, and each element in the linked list consists of two parts, namely the data part and the address part. The data part is the value or object to be stored in an element, and the address part consists of a pointer to the next element in the list. In the case of a doubly linked list each element has an additional part, a pointer to link an element to the previous element in the list. Each element in the list is called a node.

## Syntax

The syntax to define an instance of this custom linked list is:

   *SLinkedList<data_type> list_name = new SlinkedList<>();   // for a single linked list*
   *DLinkedList<data_type> list_name = new DlinkedList<>();   // for a double linked list*
   
## Methods

|Method|Description|SLinkedList|DLinkedList|
|------|-----------|-----------|-----------|
|get() |Retrieve the value or object for a particular node|:Yes:|:Yes:|
   

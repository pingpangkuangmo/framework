package com.demo.node;

public class Node {

	public Node next;
	public int value;
	
	public Node(Node next, int value) {
		super();
		this.next = next;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node(" + value +")";
	}
	
	
	
}

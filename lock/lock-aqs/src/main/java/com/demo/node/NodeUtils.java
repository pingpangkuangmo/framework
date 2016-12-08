package com.demo.node;

public class NodeUtils {
	
	public static Node getNode(){
		Node node8 = new Node(null, 8);
		Node node7 = new Node(node8, 7);
		Node node6 = new Node(node7, 6);
		Node node5 = new Node(node6, 5);
		Node node4 = new Node(node5, 4);
		Node node3 = new Node(node4, 3);
		Node node2 = new Node(node3, 2);
		Node node1 = new Node(node2, 1);
		return node1;
	}
	
	public static Node getNode1(){
		Node node8 = new Node(null, 18);
		Node node6 = new Node(node8, 10);
		Node node4 = new Node(node6, 7);
		Node node3 = new Node(node4, 5);
		Node node1 = new Node(node3, 3);
		return node1;
	}
	
	public static Node getNode2(){
		Node node8 = new Node(null, 8);
		Node node6 = new Node(node8, 6);
		Node node4 = new Node(node6, 4);
		Node node3 = new Node(node4, 3);
		Node node1 = new Node(node3, 1);
		return node1;
	}
	
	public static void print(Node node){
		System.out.println();
		Node tmp = node;
		while(tmp != null){
			System.out.print(tmp.value + " ");
			tmp = tmp.next;
		}
	}
}

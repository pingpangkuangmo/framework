package com.demo.node;

public class SortNode {
	
	public static void main(String[] args){
		Node list1 = getNode();
		print(list1);
		Node list2 = reverse(list1);
		print(list2);
	}

	public static Node reverse(Node list){
		if(list == null){
			return null;
		}
		Node x = list;
		Node y = list.next;
		x.next = null;
		while(y != null){
			Node tmp = y.next;
			y.next = x;
			x = y;
			y = tmp;
		}
		return x;
	}
	
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
	
	public static void print(Node node){
		System.out.println();
		Node tmp = node;
		while(tmp != null){
			System.out.print(tmp.value + " ");
			tmp = tmp.next;
		}
	}
}

package com.demo.node;

public class SortNode {
	
	public static void main(String[] args){
		Node list1 = NodeUtils.getNode();
		NodeUtils.print(list1);
		Node list2 = reverse(list1);
		NodeUtils.print(list2);
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
	
}

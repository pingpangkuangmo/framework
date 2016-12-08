package com.demo.node;

public class MergeNode {
	
	public static void main(String[] args){
		merge();
	}
	
	public static void merge(){
		Node node1 = NodeUtils.getNode1();
		Node node2 = NodeUtils.getNode2();
		boolean isNode1 = node1.value < node2.value;
		Node node3 = null;
		Node x = null;
		Node y = null;
		Node z = null;
		if(isNode1){
			x = node1.next;
			node3 = node1;
			node3.next = null;
			y = node2;
		}else{
			y = node2.next;
			node3 = node2;
			node3.next = null;
			x = node1;
		}
		z = node3;
		while(y != null || x !=null){
			if(x == null){
				z.next = y;
				break;
			}else if(y == null){
				z.next = x;
				break;
			}else{
				boolean isX = x.value < y.value;
				if(isX){
					z.next = x;
					x = x.next;
					z = z.next;
					z.next = null;
				}else{
					z.next = y;
					y = y.next;
					z = z.next;
					z.next = null;
				}
			}
		}
		NodeUtils.print(node3);
	}
}

package edu.school.binarytree;

public class BinaryTreeTest {

	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(13);
		//bt.print();
		bt.add(10);
		//bt.print();
		bt.add(15);
		//bt.print();
		bt.add(16);
		bt.add(17);
		bt.add(18);
		//bt.print();
		//bt.add(11);
		//bt.print();
		//bt.add(5);
		//bt.print();
		//bt.add(6);
		//bt.print();
		//bt.add(4);
		//bt.print();
		//bt.add(7);
		
		//bt.print();
		System.out.println();
		/*
		 * 					73
		 * 		48		
		 * 	35			61
		 * 			53		69
		 */
		//bt.remove(73);
		//bt.remove(61);
		//bt.remove(48);
		//bt.remove(35);
		bt.print();
	}

}

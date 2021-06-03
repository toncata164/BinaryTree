package edu.school.binarytree;

public class BinaryTreeTest {

	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(73);
		bt.add(48);
		bt.add(61);
		bt.add(69);
		bt.add(53);
		bt.add(35);
		bt.print();
		System.out.println();
		/*
		 * 					73
		 * 		48		
		 * 	35			61
		 * 			53		69
		 */
		//bt.remove(73);
		//bt.remove(61);
		bt.remove(48);
		//bt.remove(35);
		bt.print();
	}

}

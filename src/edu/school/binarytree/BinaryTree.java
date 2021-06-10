package edu.school.binarytree;

class Node<T extends Comparable<T>>
{
	private T data;
	private Node<T> left;
	private Node<T> right;
	private int balance;
	
	public void setData(T data) {
		this.data = data;
	}
	public void setLeft(Node<T> left)
	{
		this.left = left;
	}
	public void setRight(Node<T> right)
	{
		this.right = right;
	}
	
	public T getData()
	{
		return data;
	}
	public Node<T> getLeft()
	{
		return left;
	}
	public Node<T> getRight()
	{
		return right;
	}
	
	
	/*
	private int getLeftSubtreeDepth()
	{
		if(left == null)
			return 0;
		int l = left.getLeftSubtreeDepth();
		int r = left.getRightSubtreeDepth();
		
		if(l > r) return l+1;
		return r+1;
	}
	
	private int getRightSubtreeDepth()
	{
		if(right == null)
			return 0;
		int l = right.getLeftSubtreeDepth();
		int r = right.getRightSubtreeDepth();
		
		if(l > r) return l+1;
		return r+1;
	}*/
	
	/*Node(T data, Node<T> left, Node<T> right)
	{
		this.data = data;
		if(left == nu2ll && right == null)
		{
			this.left = left;
			this.right = right;
		}
		if()
	}*/
	
	Node(T data)
	{
		this.data = data;
		this.left = null;
		this.right = null;
		this.balance = 0;
	}
	
	boolean isLeaf()
	{
		return left == null && right == null;
	}
}

public class BinaryTree <T extends Comparable<T>> {
	
	private Node<T> root;
	
	public BinaryTree()
	{
		root = null;
	}
	
	private int getDepth(Node<T> current)
	{
		if(current == null)
			return 0;
		int leftDepth = getDepth(current.getLeft());
		int rightDepth = getDepth(current.getRight());
		if(leftDepth > rightDepth)
			return leftDepth+1;
		return rightDepth+1;
	}
	
	private int getBalance(Node<T> current)
	{
		if(current == null)
			return 0;
		int leftDepth = getDepth(current.getLeft());
		int rightDepth = getDepth(current.getRight());
		return rightDepth - leftDepth;
	}
	
	private void rotateRight(Node<T> disbalanced)
	{
		Node<T> parent = getParentOf(disbalanced.getData());
		Node<T> left = disbalanced.getLeft();
		Node<T> leftRight = left.getRight();
		//kogato e koren
		if(parent == null)
		{
			root = left;
			root.setRight(disbalanced);
			disbalanced.setLeft(leftRight);
		}
		//kogato ne e koren
		else
		{
			
			if(parent.getLeft() == disbalanced)
			{
				parent.setLeft(left);
				left.setRight(disbalanced);
			}
			if(parent.getRight() == disbalanced) 
			{
				parent.setRight(left);
				parent.getRight().setRight(disbalanced);
			}
			disbalanced.setLeft(leftRight);
		}
	}
	
	private void rotateLeft(Node<T> disbalanced)
	{
		Node<T> parent = getParentOf(disbalanced.getData());
		//kogato e koren
		Node<T> right = disbalanced.getRight();
		Node<T> rightLeft = right.getLeft();
		if(parent == null)
		{
			root = right;
			root.setLeft(disbalanced);
			disbalanced.setRight(rightLeft);
		}
		//kogato ne e koren
		else
		{
			
			if(parent.getRight() == disbalanced)
			{
				parent.setRight(right);
				right.setLeft(disbalanced);
				disbalanced.setRight(rightLeft);
			}
			if(parent.getLeft() == disbalanced)
			{
				parent.setLeft(right);
				parent.getLeft().setLeft(disbalanced);
				disbalanced.setRight(rightLeft);
			}
		}
	}
	
	public boolean isEmpty()
	{
		return root == null;
	}
	
	public void add(T data)
	{
		if(isEmpty())
			root = new Node<>(data);
		else
			add(data, root);
	}
	
	private void add(T data,  Node<T> current)
	{
		if(data.compareTo(current.getData()) < 0)
		{
			if(current.getLeft() == null)
			{
				//tuk trqbva da smetnem balance utre
				current.setLeft(new Node<>(data));
			}
			else
			{
				add(data, current.getLeft());
				int balance = getBalance(current);
				if(balance < -1 || balance > 1)
				{
					System.out.println();
				}
				if(balance == -2)
				{
					if(getBalance(current.getLeft()) == -1)
					{
						rotateRight(current);
					}
					if(getBalance(current.getLeft()) == 1)
					{
						rotateLeft(current.getLeft());
						rotateRight(current);
					}
				}
				if(balance == 2)
				{
					if(getBalance(current.getRight()) == 1)
					{
						rotateLeft(current);
					}
					if(getBalance(current.getRight())==-1)
					{
						rotateRight(current.getRight());
						rotateLeft(current);
					}
				}
			}
		}
		else if(data.compareTo(current.getData()) > 0)
		{
			if(current.getRight() == null)
			{
				current.setRight(new Node<>(data));
			}
			else
			{
				add(data, current.getRight());
				int balance = getBalance(current);
				if(balance < -1 || balance > 1)
				{
					System.out.println();
				}
				if(balance == -2)
				{
					if(getBalance(current.getLeft()) == -1)
					{
						rotateRight(current);
					}
					if(getBalance(current.getLeft()) == 1)
					{
						rotateLeft(current.getLeft());
						rotateRight(current);
					}
				}
				if(balance == 2)
				{
					if(getBalance(current.getRight()) == 1)
					{
						rotateLeft(current);
					}
					if(getBalance(current.getRight())==-1)
					{
						rotateRight(current.getRight());
						rotateLeft(current);
					}
				}
			}
		}
	}
	
	public void remove(T data)
	{
		if(isEmpty())
			return;
		if(data.compareTo(root.getData()) == 0)//ako e korena na durvoto
		{
			if(root.isLeaf())//ako korena e listo
			{
				root = null;
				return;
			}
			//ako korenut ima edin naslednik
			else if(root.getLeft() == null && root.getRight() != null)
			{
				root = root.getRight();
				return;
			}
			//pak
			else if(root.getLeft() != null && root.getRight() == null)
			{
				root = root.getLeft();
				return;
			}
			//ako korena ima dvama naslednici
			else
			{
				Node<T> max = getMaxOf(root.getLeft());
				Node<T> maxParent = getParentOf(max.getData());
				if(maxParent == root)
					maxParent.setLeft(null);
				else if(max.isLeaf())
					maxParent.setRight(null);
				else
					maxParent.setRight(max.getLeft());
				root.setData(max.getData());
			}
		}
		else //ako ne e korena
		{
			Node<T> parent = getParentOf(data);
			if(parent != null)
			{
				//namirame node koyto she mahame
				Node<T> toRemove = parent.getLeft();
				if(data.compareTo(parent.getData())>0)
					toRemove = parent.getRight();
				//ako node e listo
				if(toRemove.isLeaf())
				{
					if(toRemove == parent.getLeft())
						parent.setLeft(null);
					if(toRemove == parent.getRight())
						parent.setRight(null);
				}
				else //ako ne e listo
				{
					//ako ne e listo i ima edin naslednik
					if(toRemove.getLeft() == null && toRemove.getRight() != null)
					{
						if(parent.getLeft() == toRemove)
							parent.setLeft(toRemove.getRight());
						else if(parent.getRight() == toRemove)
							parent.setRight(toRemove.getRight());
					}
					//pak
					else if(toRemove.getLeft() != null && toRemove.getRight() == null)
					{
						if(parent.getLeft() == toRemove)
							parent.setLeft(toRemove.getLeft());
						else if(parent.getRight() == toRemove)
							parent.setRight(toRemove.getLeft());
					}
					//ako ima 2ma naslednici
					else
					{
						Node<T> max = getMaxOf(toRemove.getLeft());
						Node<T> maxParent = getParentOf(max.getData());
						if(maxParent == toRemove)
							maxParent.setLeft(null);
						else if(max.isLeaf())
							maxParent.setRight(null);
						else
							maxParent.setRight(max.getLeft());
						toRemove.setData(max.getData());
					}
				}
			}
		}
	}
	
	private Node<T> getMaxOf(Node<T> current)
	{
		while(current.getRight() != null)
		{
			current = current.getRight();
		}
		return current;
	}
	
	private Node<T> getParentOf(T data)
	{
		Node<T> current = root;
		if(current.getData().compareTo(data) == 0)
		{
			return null;
		}
		while(current != null && current.getData().compareTo(data) != 0)
		{
			if(current.getData().compareTo(data) > 0)
			{
				if(current.getLeft() != null && current.getLeft().getData().compareTo(data) == 0)
				{
					return current;
				}
				else
				{
					current = current.getLeft();
				}
			}
			else
			{
				if(current.getRight() != null && current.getRight().getData().compareTo(data) == 0)
				{
					return current;
				}
				else
				{
					current = current.getRight();
				}
			}
		}
		return null;
	}
	
	public void print()
	{
		print(root);
	}
	
	private void print(Node<T> current)
	{
		if(current == null)
			return;
		print(current.getLeft());
		System.out.print(current.getData()+" "+getBalance(current)+"\n");
		print(current.getRight());
	}
}

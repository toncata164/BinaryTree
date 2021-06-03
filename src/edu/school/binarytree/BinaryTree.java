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
	
	/*Node(T data, Node<T> left, Node<T> right)
	{
		this.data = data;
		if(left == null && right == null)
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
	
	private void rotateRight(Node<T> disbalanced)
	{
		Node<T> parent = getParentOf(disbalanced.getData());
		//kogato e koren
		if(parent == null)
		{
			
		}
		//kogato ne e koren
		else
		{
			Node<T> left = disbalanced.getLeft();
			Node<T> leftRight = left.getRight();
			if(parent.getLeft() == disbalanced)
			{
				parent.setLeft(left);
				left.setRight(disbalanced);
				disbalanced.setLeft(leftRight);
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
		System.out.print(current.getData()+" ");
		print(current.getRight());
	}
}

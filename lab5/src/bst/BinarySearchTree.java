package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E extends Comparable<E>> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comp;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root=null;
		size=0;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comp) {
		this.comp=comp;
		root=null;
		size=0;		
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return addNode(x, root);
	}
	
	private boolean addNode(E x, BinaryNode<E> currentNode) {
		if(root==null) {
			root=new BinaryNode<E>(x);
			size++;		
			return true;
		}
		
		if(x.compareTo(currentNode.element)<0) {
			if(currentNode.left!=null) {
				addNode(x, currentNode.left);
			}
			else {
				currentNode.left=new BinaryNode<E>(x);
				size++;
				return true;
			}
		}
		else if(x.compareTo(currentNode.element)>0) {
			if(currentNode.right!=null) {
				addNode(x, currentNode.right);
			}
			else {
				currentNode.right=new BinaryNode<E>(x);
				size++;
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		if(getHeight(root)==-1) {
			return 0;
		}
		return getHeight(root);
	}
	private int getHeight(BinaryNode<E> node) {
		if(node==null) {
			return -1;
		}
		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);
		
		return Math.max(leftHeight, rightHeight)+1;
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root=null;
		size=0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		inOrder(root);
	}
	private void inOrder(BinaryNode<E> node) {
		if(node==null) {
			return;  //vad händer här? fråga övnl
		}
		
		inOrder(node.left);
		System.out.println(node.element);
		inOrder(node.right);
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<E>();
		toArray(root, sorted);
		root=buildTree(sorted, 0, sorted.size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> node, ArrayList<E> sorted) {
		if(node==null) {
			return;
		}
		toArray(node.left, sorted);
		sorted.add(node.element);
		toArray(node.right, sorted);
		}
	
	
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first>last) {
			return null;
		}
		
		int mid= (first+last)/2;
		BinaryNode<E> tempRoot = new BinaryNode<E>(sorted.get(mid));
		
		tempRoot.left=buildTree(sorted, first, mid-1);
		tempRoot.right=buildTree(sorted, mid+1, last);
		
		return tempRoot;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}

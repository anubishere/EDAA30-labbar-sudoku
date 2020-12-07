package bst;

public class bstTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree<Integer>tree= new BinarySearchTree<Integer>();
		int[] intList = {
				 3, 5, 7, 9, 11, 13
		};
		for(int a: intList) {
			tree.add(a);
		}
		
		BSTVisualizer bst = new BSTVisualizer("BST", 500, 500);
		
		//tree.rebuild();
		bst.drawTree(tree);
	}

}

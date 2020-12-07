package testBST;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class TestBST {

	BinarySearchTree<String> myStringTree;
	BinarySearchTree<Integer> myIntTree;
	String[] stringList = {
		"hej", "kalle", "ost", "pelle", "juice", "katt", "hej"	
	};
	int[] intList = {
			10, 15, 20, 5, 8, 4, 8
	};
	
	
	@BeforeEach
	void setUp() throws Exception {
		myStringTree = new BinarySearchTree<String>();
		myIntTree = new BinarySearchTree<Integer>();

	}

	@AfterEach
	void tearDown() throws Exception {
		myStringTree.clear();
		myIntTree.clear();
		
	}

	@Test
	void testHeight() {
		for(int a: intList) {
			myIntTree.add(a);
		}
		assertEquals(2, myIntTree.height(), "returns the wrong height");
	}
	
	@Test
	void testSize() {
		for (String a: stringList) {
			myStringTree.add(a);
		}
		
		assertEquals(6, myStringTree.size(), "returns wrong size");
		}
	
	@Test
	void testClear() {
		for(int a: intList) {
			myIntTree.add(a);
		}
		myIntTree.clear();
		
		assertEquals(0, myIntTree.size(), "clear not working");
	}
	
	@Test
	void testAdd() {
		for(String a: stringList) {
			myStringTree.add(a);
		}
		assertEquals(false, myStringTree.add("hej"), "add doesnt return false" );
	}
	
	@Test
	void testSizeEmpty() {
		assertEquals(0, myIntTree.size(), "size not 0");
	}
	
	@Test
	void testHeightEmpty() {
		assertEquals(0, myStringTree.height(), "height not 0");
	}
	
	@Test
	void testLambdaExpression() {
		
	}

}

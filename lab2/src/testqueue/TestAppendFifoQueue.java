package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	
	private FifoQueue<Integer> myIntQueue;
	private Queue<String> myStringQueue;
	
	@BeforeEach
	void setUp() throws Exception {
		myIntQueue = new FifoQueue<Integer>();
		myStringQueue = new FifoQueue<String>();
	}

	@AfterEach
	void tearDown() throws Exception {
		myIntQueue = null;
		myStringQueue = null;
	}

	@Test
	void testEmptyQueue() {		
		FifoQueue<Integer> queue = new FifoQueue<Integer>();
		assertThrows(IllegalArgumentException.class, () -> queue.append(myIntQueue));
	}
	@Test
	void testAppendEmptyToNonEmpty() {
		FifoQueue <Integer> queue = new FifoQueue<Integer>();
		
		for(int i =0; i<5; i++) {
			myIntQueue.offer(i);
		}
		myIntQueue.append(queue);
		for(int i =0; i<5; i++) {
			int k=i;
			assertEquals(k, myIntQueue.poll(), "poll returns wrong number");
		}
		
	}
	@Test
	void testAppendNonEmptyToEmpty(){
	FifoQueue<Integer> queue = new FifoQueue<Integer>();
	
	for(int i=0; i<5; i++) {
		queue.offer(i);
	}
	myIntQueue.append(queue);
	for(int i =0; i<5; i++) {
		int k =i;
		assertEquals(k, myIntQueue.poll(), "poll returns wrong value");
		}
	}

	@Test
	void testAppendQueues() {
		FifoQueue<Integer> queue = new FifoQueue<Integer>();
		
		for(int i=0; i<5; i++) {
			myIntQueue.offer(i);
			}	
		for(int i=5; i<10; i++) {
			queue.offer(i);
			}
		myIntQueue.append(queue);
		for(int i =0; i<10; i++) {
			int k=i;
			assertEquals(k, myIntQueue.poll(), "wrong value from poll");
			}		
		}
	
	@Test
	void testAppendSelf() {
		for(int i =0; i<5; i++) {
			myIntQueue.offer(i);
		}
		assertThrows(IllegalArgumentException.class, () -> myIntQueue.append(myIntQueue));
		
	}
	
	
	
	
}

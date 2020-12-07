package map;

import java.util.Random;

public class testHashMapMain {

	public static void main(String[] args) {
		SimpleHashMap map = new SimpleHashMap(10);
		
		Random rand = new Random();
		
		for(int i =0; i<40; i++) {
			map.put(rand.nextInt(100), rand.nextInt(100));
		}
		
		System.out.print(map.show());
	}

}

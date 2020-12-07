package textproc;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
public class MultiWordCounter implements TextProcessor{

	Map<String, Integer> map;
	
	public MultiWordCounter (String[] landskap) {
		map = new TreeMap<String, Integer>();
		for(int i=0; i<landskap.length; i++) {
			map.put(landskap[i], 0);
		}
	}
	@Override
	public void process(String w) {
		// TODO Auto-generated method stub
		/**for (String key : map.keySet()) {
			if (key.equals(w)) {
				int temp = map.get(w);
				map.put(w, temp+1);
			}
		}**/
		
		if(map.containsKey(w)) {
			int temp = map.get(w);
			map.put(w, temp+1);
		}
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		for ( String key : map.keySet()) {
			System.out.println(key+": " + map.get(key));
		}
	}
}

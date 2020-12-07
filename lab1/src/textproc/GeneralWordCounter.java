package textproc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class GeneralWordCounter implements TextProcessor {

		Map <String, Integer> map;
		Set<String> ord;
	public GeneralWordCounter(Set<String> ord) {
		this.ord=ord;
		map = new TreeMap<String, Integer>();
	}
	
	@Override
	public void process(String w) {
		// TODO Auto-generated method stub
		if (ord.contains(w)) {
			
		} else if (map.containsKey(w)) {
			int temp = map.get(w);
			map.put(w, temp+1);
		}
		else {
			map.put(w, 1);
		}
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
		Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());

		
		for (int i=0; i<20; i++) {
			System.out.println(wordList.get(i).getKey() +":"+wordList.get(i).getValue());
		}
		/**for ( String key : map.keySet()) {
			if (map.get(key)>=200) {
				System.out.println(key+": " + map.get(key));
			}
		}**/
	}
	
}

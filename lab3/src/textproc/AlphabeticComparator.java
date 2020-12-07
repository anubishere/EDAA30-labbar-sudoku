package textproc;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AlphabeticComparator implements Comparator<Map.Entry<String, Integer>>{

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if(o1.getKey().compareTo(o2.getKey())>0) {
			return 1;
		}
		else if (o1.getKey().compareTo(o2.getKey())<0) {
			return -1;
		}
		else {
			if (o1.getValue()<o2.getValue()) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	

}

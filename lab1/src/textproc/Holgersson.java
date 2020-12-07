package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		ArrayList<TextProcessor> list = new ArrayList();

;
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> StopWords= new HashSet<String>();
		while (scan.hasNext()) {
			String noWord = scan.next().toLowerCase();
			StopWords.add(noWord);
		}
		
		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));
		list.add(new MultiWordCounter(REGIONS));
		list.add(new GeneralWordCounter(StopWords));
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(int i=0; i<list.size(); i++){
				list.get(i).process(word);
			}
		}

		s.close();
		for(int i=0; i<list.size(); i++){
			list.get(i).report();
		}
		long t1=System.nanoTime();
	}
}

	

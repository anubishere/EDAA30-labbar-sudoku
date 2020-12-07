package textproc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;


public class BookReaderController {
	

	
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
		}
	
	private void createWindow(GeneralWordCounter counter, String title,int width, int height) {
		JFrame frame = new JFrame(title);
		
		SortedListModel<Map.Entry<String,Integer>> sortedList = new SortedListModel<>(counter.getWordList());
		JList<Map.Entry<String,Integer>> list = new JList<>(sortedList);

		
		//delar upp fönstret i tre "panels", gör en borderlayout i mainpanel, sätter btnpanel till att lägga in objekt 
		JPanel mainPanel = new JPanel(new BorderLayout());													//från vänster
		JPanel toppanel = new JPanel();
		JPanel btnpanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		//skapar två knappar, lägger till dom i btnpanel
		JButton button1 = new JButton("Alphabetic");
		JButton button2 = new JButton("Frequency");
		btnpanel.add(button1);
		btnpanel.add(button2);
		WordCountComparator comp= new WordCountComparator();
		AlphabeticComparator alphabetic = new AlphabeticComparator();
		
		//skapar eventlisteners till knapparna
		button1.addActionListener( e-> sortedList.sort(alphabetic));
		button2.addActionListener(e -> sortedList.sort(comp));
		
		
		//?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
	
		
		//skapar scrollern med listan, sätter size på listan
		JScrollPane scroll = new JScrollPane(list);		
		scroll.setPreferredSize(new Dimension(500,300));
		
		//skapar textfield, en sökknapp
		JTextField text = new JTextField();
		JButton searchbtn = new JButton("Search");
		text.setPreferredSize(new Dimension (200,20));
		btnpanel.add(text);
		btnpanel.add(searchbtn);
		frame.getRootPane().setDefaultButton(searchbtn);
		
		//söker igenom listan 
		searchbtn.addActionListener(e -> {	
			boolean wordExists=false;
			for(int i=0; i < sortedList.getSize();i++) {
				
				Object obj = sortedList.getElementAt(i);
				Map.Entry<String, Integer> entry = ((Map.Entry<String, Integer>) obj);
				String str = entry.getKey();
				if(str.equalsIgnoreCase(text.getText().replaceAll("\\s+",""))) {
					list.ensureIndexIsVisible(i);
					list.setSelectedIndex(i);
					wordExists=true;
				}
				
			}
			 if(!wordExists) {
				 JOptionPane.showMessageDialog(frame, "The word doesn't exist in the file");
				
			 }
			
			
		});
		
		
		//lägger till scroll i toppanel, lägger till toppanel/btnpanel i panel, lägger till panel i pane
		toppanel.add(scroll);
		mainPanel.add(toppanel, BorderLayout.NORTH);
		mainPanel.add(btnpanel, BorderLayout.SOUTH);
		pane.add(mainPanel);
	
		
	// pane är en behållarkomponent till vilken de övriga komponenterna(listvy, knappar etc.) 
	//ska läggas till.
	frame.pack();
	frame.setVisible(true);
	}
}

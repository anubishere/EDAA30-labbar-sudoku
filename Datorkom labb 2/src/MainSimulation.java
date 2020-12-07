import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

    	//Signallistan startas och actSignal deklareras. actSignal är den senast utplockade signalen i huvudloopen nedan.

    	Signal actSignal;
    	new SignalList();

    	//Här nedan skapas de processinstanser som behövs och parametrar i dem ges värden.
    	ArrayList <QS> queue = new ArrayList();
    	QS Q1 = new QS();
    	QS Q2 = new QS();
    	QS Q3 = new QS();
    	QS Q4= new QS();
    	QS Q5 = new QS();
    	
    	queue.add(Q1);
    	queue.add(Q2);
    	queue.add(Q3);
    	queue.add(Q4);
    	queue.add(Q5);
    
    	Q1.sendTo = null;
    	Q2.sendTo = null;
    	Q3.sendTo = null;
    	Q4.sendTo = null;
    	Q5.sendTo = null;
    	
    	Disp disp = new Disp();
    	
    	Gen Generator = new Gen();
    	Generator.lambda = 45; //Generator ska generera nio kunder per sekund
    	Generator.sendTo = disp; //De genererade kunderna ska skickas till kösystemet QS
    	
    	Random rand = new Random();
    	
    	
    	//Här nedan skickas de första signalerna för att simuleringen ska komma igång.

    	SignalList.SendSignal(READY, Generator, time);
    	SignalList.SendSignal(MEASURE, Q1, time);
    	SignalList.SendSignal(MEASURE, Q2, time);
    	SignalList.SendSignal(MEASURE, Q3, time);
    	SignalList.SendSignal(MEASURE, Q4, time);
    	SignalList.SendSignal(MEASURE, Q5, time);

    	int i =1;
    	// Detta är simuleringsloopen:

    	while (time < 100000){
    		actSignal = SignalList.FetchSignal();
    		
    		if(actSignal.signalType==ARRIVAL) {
    			/**int rndnbr=rand.nextInt(5)+1;
    		
    			if (rndnbr==1) {
    			disp.sendTo=Q1;
    			}
    			else if (rndnbr==2) {
    				disp.sendTo=Q2;
    			}
    			else if (rndnbr==3) {
    				disp.sendTo=Q3;
    			}
    			else if (rndnbr==4) {
    				disp.sendTo=Q4;
    			}
    			else if (rndnbr==5) {
    				disp.sendTo=Q5;
    			}
    		}*/
    			/**	if (i==1) {
        			disp.sendTo=Q1;
        			}
        			else if (i==2) {
        				disp.sendTo=Q2;
        			}
        			else if (i==3) {
        				disp.sendTo=Q3;
        			}
        			else if (i==4) {
        				disp.sendTo=Q4;
        			}
        			else if (i==5) {
        				disp.sendTo=Q5;
        			}
    		i++;
    		
    		if(i==6) {
    			i=1;
    		}
    		}*/
    			int min=10000000;
    			QS temp = new QS();
    			for(QS s: queue) {
    				if (s.numberInQueue<min) {
    					min=s.numberInQueue;
    					temp=s;
    				}
    			}
    			
    			disp.sendTo=temp;
    		
    		}
    		
    		
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}

    	//Slutligen skrivs resultatet av simuleringen ut nedan:

    	System.out.println("Medelantal kunder i kösystem 1: " + 1.0*Q1.accumulated/Q1.noMeasurements);
    	System.out.println("Medelantal kunder i kösystem 2: " + 1.0*Q2.accumulated/Q2.noMeasurements);
    	System.out.println("Medelantal kunder i kösystem 3: " + 1.0*Q3.accumulated/Q3.noMeasurements);
    	System.out.println("Medelantal kunder i kösystem 4: " + 1.0*Q4.accumulated/Q4.noMeasurements);
    	System.out.println("Medelantal kunder i kösystem 5: " + 1.0*Q5.accumulated/Q5.noMeasurements);
    	
    }
}
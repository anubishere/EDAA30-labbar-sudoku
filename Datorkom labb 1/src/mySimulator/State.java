package mySimulator;
import java.util.*;

class State extends GlobalSimulation{
	
	public int numberAInQueue = 0, accumulated = 0, noMeasurements = 0, numberBInQueue=0;
	
	private EventList myEventList;

	Random slump = new Random();
	
	State(EventList x){
		myEventList = x;
	}
	
	private void InsertEvent(int event, double timeOfEvent){
		myEventList.InsertEvent(event, timeOfEvent);
	}
	
	
	public void TreatEvent(Event x){
		switch (x.eventType){
			case ARRIVALA:
				arrival();
				break;
			case ARRIVALB:
				arrivalB();
				break;
			case READYA:
				ready();
				break;
			case READYB:
				readyB();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	private double generateMean(double mean){
		return 2*mean*slump.nextDouble();
	}
	
	private void arrival(){
		if (numberAInQueue == 0 && numberBInQueue==0)
			InsertEvent(READYA, time + 0.002);
		numberAInQueue++;
		InsertEvent(ARRIVALA, time + generateMean(0.006667));
	}
	private void arrivalB() {
		if (numberBInQueue==0 && numberAInQueue==0)
			InsertEvent(READYB, time+0.004);
		numberBInQueue++;
	}
	
	private void ready(){
		numberAInQueue--;
		InsertEvent(ARRIVALB, time+ 1);
		if (numberBInQueue>0)
			InsertEvent(READYB, time + 0.004);
		else if (numberAInQueue>0)
			InsertEvent(READYA, time+0.002);
	}
	private void readyB() {
		numberBInQueue--;
		if (numberBInQueue>0)
			InsertEvent(READYB, time + 0.004);
		else if (numberAInQueue>0)
			InsertEvent(READYA, time+0.002);
	}
	private void measure(){
		accumulated = accumulated + numberAInQueue+numberBInQueue;
		noMeasurements++;
		InsertEvent(MEASURE, time + 0.1);
	}
}
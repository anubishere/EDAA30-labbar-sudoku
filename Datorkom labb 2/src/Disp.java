import java.util.Random;

class Disp extends Proc {


    //Slumptalsgeneratorn startas:
    Random slump = new Random();

    //Generatorn har två parametrar:
    public Proc sendTo;    //Anger till vilken process de genererade kunderna ska skickas

    @Override
    public void TreatSignal(Signal x) {
        switch (x.signalType){
        case ARRIVAL:{

            SignalList.SendSignal(ARRIVAL, sendTo, time);
            SignalList.SendSignal(READY, this, time);}
            break;
    }

    }

}
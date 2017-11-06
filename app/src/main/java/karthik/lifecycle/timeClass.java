package karthik.lifecycle;

/**
 * Created by Karthik Admin on 2/26/2017.
 */

public class timeClass {

    String savedTimes = "";
    String current = "00:00:00";
    int count = 1;

    public void setCurrent(String currTime){
        this.current = currTime;
    }
    public String getCurrent(){
        return this.current;
    }
    public void clearList(){
        savedTimes = "";
    }

    public void pushTime(String newLap){

        savedTimes += newLap;
        savedTimes += '\n';
    }
    public String writeTime(){
        return savedTimes;
    }

}

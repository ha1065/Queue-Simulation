
import java.util.Calendar;

public class customer {
	public int iD;
	public Calendar arrival_time;
	private int queueLength;
	private int waitingTime;
	public customer next;


	public customer(int iD, Calendar arrival_time){
		this.iD=iD;
		this.arrival_time = arrival_time;
	
}
	public int getwaitingtime(){
		return this.waitingTime;
	}
	public int getqueueLength(){
		return this.queueLength;
	}
	
	public void setwaitingtime(int x){
		this.waitingTime = x; 
	}
	public void setqueueLength(int x){
		this.queueLength = x; 
	}

	public customer() {
		// TODO Auto-generated constructor stub
	}

	public void displayLink(){
		System.out.print(iD+"  "+arrival_time + " ");
	}

	
}


import java.util.Calendar;

public class queue {
	private customer first; 
	private customer last;
	
	public queue(){
		first= null; 
		last = null; 
	}
	
	public boolean isEmpty(){
		if(first ==null){
			return true;}
		else{
			return false;
		}
	}
	
	public boolean isFull(){
		return false; 
	}
	
	//Add customer in last position
	public void addLast(int i, Calendar j){
		customer new_customer = new customer(i,j);
		new_customer.arrival_time=j;
		new_customer.iD=i;
		if(last ==null){
			first = new_customer;
		}
		else{
			last.next = new_customer;
			
		}
		last = new_customer;
	}
	
	public void addLast(customer i){
		customer new_customer = new customer();
		new_customer.arrival_time =i.arrival_time;
		new_customer.iD =i.iD;
		new_customer.setwaitingtime(i.getwaitingtime());
		if(last ==null){
			first = new_customer;
		}
		else{
			last.next = new_customer;
			
		};
		last = new_customer;
	}
		
	//Remove front customer from this queue 
	public customer removeFirst(customer i){
		
		customer temp ;
		temp = first;
		temp.iD=first.iD;
		temp.arrival_time=first.arrival_time;
		first = first.next;
		if(first==null){
			last =null;
		}
		
		return temp;
	}
	
	public int getwaittime(int id){
		//Calculate waiting time in line 
		customer temp;
		int x=0;
		temp = first;
		while(temp!=null){
			if(temp.iD ==id){
				 x= temp.getwaitingtime();
			}
			temp=temp.next;
			
		}
		return x;
		
	}

	
		
	
}

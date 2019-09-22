

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.PrintWriter;




public class program {

	public static int secondsCalculation(String x){
	        String[] tokens = x.split(":");
	        int hours = Integer.parseInt(tokens[0]);
	        int minutes = Integer.parseInt(tokens[1]);
	        int seconds = Integer.parseInt(tokens[2]);
	        int duration = 3600 * hours + 60 * minutes + seconds;
	        return duration ; 
	        }
	  
	  public static String compareTime(Calendar x , Calendar y) throws ParseException{
		  SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		  int t1_second;
		  int t2_second; 
		  String x1 =df.format(x.getTime());
		  String y1 =df.format(y.getTime());
		  String openTime = "06:00:00";
		  java.util.Date d1 =   df.parse(openTime); 
	      Calendar cal1 = Calendar.getInstance();
	      cal1.setTime(d1);
	      String openTime1= df.format(cal1.getTime());
	  
		  
		  if(x.before(cal1)){
			  x.add(Calendar.HOUR, 12);
			  x1 = df.format(x.getTime());
			
             
		  }
		  
		  if(y.before(cal1)){
			  y.add(Calendar.HOUR, 12);
			  y1 = df.format(y.getTime());
			 
              
		  }
		  
		  t1_second = secondsCalculation(x1);
		  t2_second = secondsCalculation(y1);
		  
		  if(t1_second>t2_second){
			  return "GREATER";
		  }
		  
		  if (t1_second<t2_second){
		  return "LESS";  }
		  
		  else{
			  return "EQUAL";
		  }
		
	  }
	  public static int timeDifference(Calendar x, Calendar y) throws ParseException{
		  SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		  int t1_second;
		  int t2_second; 
		  String x1 =df.format(x.getTime());
		  String y1 =df.format(y.getTime());
		  String openTime = "06:00:00";
		  java.util.Date d1 =   df.parse(openTime); 
	      Calendar cal1 = Calendar.getInstance();
	      cal1.setTime(d1);
	      String openTime1= df.format(cal1.getTime());
	  
		  
		  if(x.before(cal1)){
			  x.add(Calendar.HOUR, 12);
			  x1 = df.format(x.getTime());
			
             
		  }
		  
		  if(y.before(cal1)){
			  y.add(Calendar.HOUR, 12);
			  y1 = df.format(y.getTime());
			 
              
		  }
		  
		  t1_second = secondsCalculation(x1);
		  t2_second = secondsCalculation(y1);
		  
		  
			 return (t1_second - t2_second);
		  
	  }
	  
	  public static Calendar addTime (Calendar x, int seconds) throws ParseException{
		 
		  SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		  String x1 =df.format(x.getTime());
		  int temp =0;
		  String openTime = "06:00:00";
		  java.util.Date d1 =   df.parse(openTime); 
	      Calendar cal1 = Calendar.getInstance();
	      cal1.setTime(d1);
	      String openTime1= df.format(cal1.getTime());
	      
		  	if(x.before(cal1)){
			  x.add(Calendar.HOUR, 12);
			  x1 = df.format(x.getTime());
             
		  }
		 
		  	
		  temp = secondsCalculation(x1)+seconds;
		  Date d = new Date(temp * 1000L);
		  String Time = df.format(d);
		  Date d2 =   df.parse(Time); 
		  Calendar new_time = Calendar.getInstance();
		  return new_time;
		  
	  }

	public static void main(String[] args) throws ParseException, IOException{
		customer temp = null;
		queue list = new queue();
		queue finalized_list = new queue();
		int  T =300;
		int  customers_served =0;
		int  breakTime=0;
		int  total_break_time=0;
		int  max_break_time =0 ; 
		int  longestBreak =0;
		int  temp_longest_break = 0; //place holder for longest break 
		int  total_idle_time =0;
		int  max_num_of_people_in_queue =0;
		int  serving_time = 0;
		int  after_hour_service = 1; // token as the extreme case when employee works over time 
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String openTime="09:00:00";
        String closeTime = "05:00:00";
        String currentTime = "09:00:00";// Current Time
		java.util.Date d1 =   df.parse(openTime); 
		java.util.Date d2 =   df.parse(closeTime); 
		java.util.Date d3 =   df.parse(currentTime); 
	    Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        cal3.setTime(d3);
        String openTime1= df.format(cal1.getTime());
        String closeTime1= df.format(cal2.getTime());
        String closeTime3= df.format(cal3.getTime());

    
        
		File customerFile = null;
		File queriesFile = null;
		if (0 < args.length) {
		   customerFile = new File(args[0]);//read first file from command line : customersfile.txt
		   queriesFile = new File(args[1]);//read second file from command line: queriesfile.txt
		} 
		else {
		   System.err.println("Invalid arguments count:" + args.length);
		   System.exit(0);
		}
		BufferedReader br = null;//first file reader 
		BufferedReader cr = null;//2nd file reader 
		
		  try {

	            String sCurrentLine;
	            String cCurrentLine;

	            br = new BufferedReader(new FileReader(customerFile));
	            
	            String l1= br.readLine();
	            serving_time =Integer.parseInt(l1);
	            T = serving_time;
	           // System.out.println("This is the serving time: "+serving_time);
	            br.readLine();
	            while ((sCurrentLine = br.readLine()) != null) {
	            	String[] line = sCurrentLine.split("  ");
	            	String id = line[1];
	            	int id_int = Integer.parseInt(id);
	            	//System.out.println(id_int);
	            	sCurrentLine = br.readLine();
	            	String[] line2 = sCurrentLine.split(" ");
	            	String arr_time = line2[1];
	            	
	                //System.out.println(sCurrentLine);
	                df = new SimpleDateFormat("HH:mm:ss");
	                java.util.Date d =   df.parse(arr_time); 
	                Calendar cal = Calendar.getInstance();
	                cal.setTime(d);
	               
	                String newTime1 = df.format(cal.getTime());
	               // System.out.println(newTime1);
	                customer temp1 = new customer(id_int,cal);
	                list.addLast(temp1);
	                br.readLine();
	            }
	            
	            cr = new  BufferedReader(new FileReader(queriesFile));
	            
	            //cCurrentLine = cr.readLine();
	           
	            
	            

	        } 

	        catch (IOException e) {
	            e.printStackTrace();
	        } 

			catch (NumberFormatException e) {
				
				e.printStackTrace();
			}
		  	int counter =1;
		  	//readfile(list, temp);
		  	while((compareTime(cal3,cal2)=="LESS")&& list.isEmpty()!=true){
		  		
		  		//Current time < closing time 
		  		customer temp2 = list.removeFirst(temp);
		  		
		  	//	System.out.println("Stores open  Customer ID:"+ counter);
		  		counter++;
		  		
		  		if(compareTime(temp2.arrival_time,cal1)=="LESS"){ // Come in before 9 scenario 
		  			temp2.setwaitingtime(timeDifference(cal3,temp2.arrival_time));
		  			//System.out.println("1st customer in line waiting time:"+ temp2.setwaitingtime(timeDifference(cal3,temp2.arrival_time));
		  			if (compareTime(cal3,cal1)=="EQUAL"){//people come in line before 9 
		  				temp2.setqueueLength(0);
		  			}
		  			else{//if there is already people come in before 9, and in the queue, the queue number increment
		  				temp2.setqueueLength(timeDifference(cal3,cal1)/T);
		  				//System.out.println("number of peopel in queue :"+ temp2.getqueueLength());
		  				if(temp2.getqueueLength()>max_num_of_people_in_queue){
		  					max_num_of_people_in_queue  = temp2.getqueueLength();
		  				}
		  			}
		  			cal3.add(Calendar.SECOND, T);// Current add 300seconds serving time 
		  			customers_served++;
		  			finalized_list.addLast(temp2);

		  		}
		  		
		  		else if (compareTime(cal3,temp2.arrival_time)=="GREATER"){
		  			temp2.setwaitingtime(timeDifference(cal3,temp2.arrival_time));
		  			
		  			temp2.setqueueLength(temp2.getwaitingtime()/T+1);
		  			if(temp2.getqueueLength()>max_num_of_people_in_queue){
	  					max_num_of_people_in_queue  = temp2.getqueueLength();
	  				}
		  			cal3.add(Calendar.SECOND, T);
		  			customers_served++;
		  			finalized_list.addLast(temp2);
		  			
		  			
		  		}
		  		
		  		else if (compareTime(cal3,temp2.arrival_time)=="LESS"){
		  			breakTime = timeDifference(temp2.arrival_time,cal3);
		  			//System.out.println("Break time is :" +breakTime);
		  			if ( breakTime>max_break_time ){
		  					max_break_time = breakTime;
		  			}
		  			cal3.add(Calendar.SECOND, breakTime);
		  			total_break_time +=breakTime;
		  			
		  			temp2.setwaitingtime(0);
		  			temp2.setqueueLength(0);
		  			cal3.add(Calendar.SECOND, T);
		  			
		  	
		  			customers_served ++;
		  			finalized_list.addLast(temp2);
		  			
		  		}
		  		
		  		if((compareTime(cal3,cal2)=="LESS") && list.isEmpty()){
		  			//The case when no one is in line until closing time 
		  			breakTime=timeDifference(cal2,cal3);
		  			if ( breakTime>max_break_time ){
		  					max_break_time = breakTime;
		  			}
		  			total_break_time +=breakTime;
		  			
		  		}
		  	}
		  	
		  	while(list.isEmpty()!=true){
		  		customer temp2 =list.removeFirst(temp);
		  		if (compareTime(temp2.arrival_time,cal2)=="LESS"){
		  			temp2.setwaitingtime(timeDifference(cal2,cal3));
		  			
		  		}
		  		else if (compareTime(temp2.arrival_time,cal2)=="GREATER"){
		  			temp2.setwaitingtime(0);
		  		}
		  		else{
		  			temp2.setwaitingtime(0);
		  		
		  		}
		  		finalized_list.addLast(temp2);
		  	}
		  	 
		  	cr = new  BufferedReader(new FileReader(queriesFile));
		  
		  	 read_queries(finalized_list,cr, total_break_time, max_break_time,customers_served, max_num_of_people_in_queue) ;
		  	 
		     
		  	
	}
    
	public static void read_queries(queue ll,BufferedReader cr,int total_break_time,int max_break_time,int customers_served, int max_num_of_people_in_queue) throws IOException{
		

		int id;
		int waittime; 
		String cCurrentLine;
		PrintWriter writer = new PrintWriter("output.txt");
        
		while((cCurrentLine = cr.readLine()) != null) {
			
	
			    
			    if(cCurrentLine.equals("NUMBER-OF-CUSTOMERS-SERVED")){
			    	System.out.println("NUMBER-OF-CUSTOMERS-SERVED:"+customers_served);
			    	writer.println("NUMBER-OF-CUSTOMERS-SERVED:"+customers_served);
			    	}
			    if(cCurrentLine.equals("LONGEST-BREAK-LENGTH")){
			    	System.out.println("LONGEST-BREAK-LENGTH:"+max_break_time);
			    	writer.println("LONGEST-BREAK-LENGTH:"+max_break_time);
			    }
			    if(cCurrentLine.equals("TOTAL-IDLE-TIME")){
			    	System.out.println("TOTAL-IDLE-TIME:"+total_break_time);
			    	writer.println("TOTAL-IDLE-TIME:"+total_break_time);
			    }
			    if(cCurrentLine.equals("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME")){ 
			    	System.out.println("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME:"+max_num_of_people_in_queue);
			    	writer.println("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME:"+max_num_of_people_in_queue);
			    }
			    
			    String[] line = cCurrentLine.split(" ");
			   // System.out.println(line[0]);
			    if(line[0].equals("WAITING-TIME-OF")){
			    	int id_temp = Integer.parseInt(line[1]);
			    	//System.out.println(id_temp);
			    	 System.out.println(line[0]+" " + line[1]+":"+ ll.getwaittime(id_temp));
			    	 writer.println(line[0]+" " + line[1]+":"+ ll.getwaittime(id_temp));

			    	
			    }

			   
			   
			}

			 writer.close();
       
	}
	  
	        
}

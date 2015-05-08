package Model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class ItemLogs implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int itemid;
	public ArrayList<LogEntry> logs;
	
	
	public ItemLogs(){
		itemid = -1;
		logs = new ArrayList<LogEntry>();
	}
	
	public void addEntry(String d){
		//add timestamp
		logs.add(new LogEntry(LocalTime.now().toString(), d));
	}
	
	public LogEntry getEntry(int i){
		if(i < logs.size()){
			return logs.get(i);
		}
		return null;
	}
	
	
}

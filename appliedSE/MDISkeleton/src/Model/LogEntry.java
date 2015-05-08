package Model;

import java.io.Serializable;

public class LogEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3157447320258205795L;
	public String date;
	public String desc;
	
	public LogEntry(String da, String de){
		this.date = da;
		this.desc = de;
	}
}

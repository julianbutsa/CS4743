package log;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;

import javax.ejb.Singleton;



import com.sun.javafx.collections.MappingChange.Map;

import logremote.LogGatewayRemote;
import logremote.LogObserver;

@Singleton(name="LogGateway")
public class LogGateway implements LogGatewayRemote{

	private ArrayList<LogObserver> observers;
	
	public LogGateway(){
		observers = new ArrayList<LogObserver>();
	}
	
	
	
	@Override
	public void registerObserver(LogObserver r) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateObservers(int id){
		for(int i = 0; i < observers.size(); i++){
			try {
				observers.get(i).updateObserver(id);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Override
	public void log( int id) {
		updateObservers(id);
	}


}

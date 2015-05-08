package Window;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.rmi.PortableRemoteObject;

import logremote.LogGatewayRemote;
import logremote.LogObserver;

public class LogObLocal implements LogObserver, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8336383962826925548L;
	public LogObLocal(LogGatewayRemote lgr){
		//System.out.println("Starting observer local");
		try {
			PortableRemoteObject.exportObject(this);
		} catch (RemoteException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		lgr.registerObserver(this);
	}
	@Override
	public void updateObserver(int id) {
		// TODO Auto-generated method stub
		System.out.printf("Log Changed:%d\n", id);
	}

}

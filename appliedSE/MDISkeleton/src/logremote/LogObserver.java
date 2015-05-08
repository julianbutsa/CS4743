package logremote;

import java.rmi.Remote;

public interface LogObserver extends Remote{
	public void updateObserver(int id) throws java.rmi.RemoteException;
}

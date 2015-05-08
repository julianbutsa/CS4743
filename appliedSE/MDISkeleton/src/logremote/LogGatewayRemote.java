package logremote;

import javax.ejb.Remote;

@Remote
public interface LogGatewayRemote {
	public void registerObserver(LogObserver r);
	public void log(int id);
}

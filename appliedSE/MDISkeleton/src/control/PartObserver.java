package control;

import Model.PartModel;

public interface PartObserver {

	public void updateObserver(PartModel m, int action);
}

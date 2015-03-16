package control;

import Model.ProductPartModel;

public interface ProductPartObserver {

	public void updateObserver(ProductPartModel m, int action);
}

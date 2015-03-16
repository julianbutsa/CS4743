package control;

import Model.ProductModel;

public interface ProductObserver {

	public void updateObserver(ProductModel m, int action);
}

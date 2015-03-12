package control;

import Model.ItemModel;

public interface InventoryObserver {

	public void updateObserver(ItemModel i, int action);

}

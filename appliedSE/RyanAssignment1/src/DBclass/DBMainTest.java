package DBclass;

import java.util.ArrayList;

import assignment1.ItemModel;
import assignment1.PartModel;

public class DBMainTest {
	public static void main(String[] args) {
		PartModel p = new PartModel("testpart3", "1234", "Guy maker", "0001");
		ItemModel i = new ItemModel(p, "test place 3", 3 );
		DBQuery q = new DBQuery();
		q.getParts();
		//q.addLocation("the place");
		
		
		//System.out.println(q.getLocationId("test Location 2"));
		//q.addPart(p);
		//q.addToInventory(i);
		ArrayList<ItemModel> ilist = q.getInventory();
		System.out.println("List:\n" + ilist.toString());
		
		
		/* Test getting whole parts list
		ArrayList<PartModel> plist = q.getParts();
		System.out.println("List of parts:\n" + plist.toString());
		*/
		q.destroy();
	}
}

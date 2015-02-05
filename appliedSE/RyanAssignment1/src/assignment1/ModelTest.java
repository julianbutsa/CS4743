package assignment1;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ModelTest {

		/*
		 * TestPart: Tests the Edit model functionality.
		 * 
		 * TestInvetory: Tests the add part and delete part functionalities.
		 */
		@BeforeClass
		public static void setUpBeforeClass() throws Exception{
			
		}
		
		@Test
		public void testPart(){
			InventoryModel m = new InventoryModel();
			PartModel model = new PartModel("Name", "1", "Vendor", 5);
			m.addPart(model);
			model.editModel("Eman", "2", "Distributor", 6, m);
			model.editModel("Ewoman", "3", "B", -1, m);
			assertTrue(model.getPname() == "Eman" && model.getPnum() == "2" && model.getVendor() == "Distributor" && model.getQuantity() == 6);
		}
		
		@Test
		public void testInventory(){
			int a, b, c, d, e = 0;
			PartModel t = new PartModel("Eman", "2", "Distrubutor", 6);
			InventoryModel model = new InventoryModel();
			a = model.addPart("Name", "1", "Vendor", 5);
			b = model.addPart(t);
			c = model.addPart("Test1", "Test1", "Test1", 0);
			d = model.addPart("Test2", "ExtremelyLongTestCase", "Test2", 4);
			e = model.addPart("Name", "2", "", 3);
			model.deletePart(t);
			assertTrue(a == 0 && b == 0 && c == -1 && d == -1 && e == -1);
			
		}
}

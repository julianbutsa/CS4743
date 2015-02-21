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
			PartModel model = new PartModel("Name", "1", "Vendor",null);
			m.addPart(model);
			model.editModel("Eman", "2", "Distributor",  m, "Unknown");
			model.editModel("Ewoman", "3", "B", m, "Unknown");
			//assertTrue(model.getPname() == "Eman" && model.getPnum() == "2" && model.getVendor() == "Distributor" && model.getQuantity() == 6);
		}
		
		@Test
		public void testInventory(){
			int a, b, c, d, e = 0;
			PartModel t = new PartModel("Eman", "2", "Distrubutor",null);
			InventoryModel model = new InventoryModel();
			a = model.addPart("Name", "1", "Vendor",null, 5);
			b = model.addPart(t);
			c = model.addPart("Test1", "Test1", "Test1",null, 0);
			d = model.addPart("Test2", "ExtremelyLongTestCase", "Test2",null, 4);
			e = model.addPart("Name", "2", "",null, 3);
			model.deletePart(t);
			assertTrue(a == 0 && b == 0 && c == -1 && d == -1 && e == -1);
			
		}
}

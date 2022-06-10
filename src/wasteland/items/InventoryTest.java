package wasteland.items;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class InventoryTest {
    
    @Test
    public void testFood(){

        Inventory i1 = new Inventory(50, 40, 30);

        assertEquals(50, i1.getMaxFood());

        assertEquals(0, i1.getFood());
        i1.addFood(20);
        assertEquals(20, i1.getFood());
        i1.removeFood(10);
        assertEquals(10, i1.getFood());

    }

    @Test
    public void testWater(){

        Inventory i1 = new Inventory(50, 40, 30);

        assertEquals(40, i1.getMaxWater());

        assertEquals(0, i1.getWater());
        i1.addWater(30);
        assertEquals(30, i1.getWater());
        i1.removeWater(5);
        assertEquals(25, i1.getWater());

    }

    @Test
    public void testItems(){

        Inventory i1 = new Inventory(50, 40, 30);
        Item key = new Key(0,0,null);
        Item[] test = new Item[30];

        assertEquals(30, i1.getItems().length);

        assertArrayEquals(test, i1.getItems());
        i1.addItem(key);
        test[0] = key;
        assertArrayEquals(test, i1.getItems());
        assertEquals(key, i1.getItemByName("🔑 Key"));
        i1.removeItem(key);
        test[0] = null;
        assertArrayEquals(test, i1.getItems());


    }


}

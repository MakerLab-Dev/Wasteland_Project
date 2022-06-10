package wasteland.world;

import org.junit.Test;

import wasteland.entity.Entity;
import wasteland.items.Item;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class WorldTest {
    
    @Test
    public void getWorldWidth(){

        int [][] w1Tiles = {{0,1},{1,0}};
        Entity[] entities = new Entity[0];
        Item[] items = new Item[0];

        World w1 = new World(w1Tiles, entities,items);

        assertEquals(2, w1.getWorldWidth());
    }

    @Test
    public void getWorldHeight(){

        int [][] w1Tiles = {{0,1},{1,0}};
        Entity[] entities = new Entity[0];
        Item[] items = new Item[0];

        World w1 = new World(w1Tiles, entities,items);

        assertEquals(2, w1.getWorldHeight());
    }

    @Test
    public void getTile(){

        int [][] w1Tiles = {{0,1},{1,0}};
        Entity[] entities = new Entity[0];
        Item[] items = new Item[0];

        World w1 = new World(w1Tiles, entities,items);

        assertEquals(0, w1.getTile(1,1));
    }

    @Test
    public void setTile(){

        int [][] w1Tiles = {{0,1},{1,0}};
        Entity[] entities = new Entity[0];
        Item[] items = new Item[0];

        World w1 = new World(w1Tiles, entities,items);

        assertEquals(0, w1.getTile(1,1));
        w1.setTile(1, 1, 1);
        assertEquals(1, w1.getTile(1,1));
    }



}
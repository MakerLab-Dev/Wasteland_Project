package wasteland.entity;

import org.junit.Test;

import wasteland.entity.Enemies.Zombie;
import wasteland.levels.*;
import wasteland.world.World;

import static org.junit.Assert.assertEquals;

public class EntityTest {
    
    @Test
    public void testDamage(){
        
        Entity x= new Zombie("", 0, 0, 150, 100, true, null, 1, 1,1);
        assertEquals(150, x.getHealth());  
        x.damage(100);
        assertEquals(50, x.getHealth());

        x.heal(100);
        assertEquals(150, x.getHealth());  

    }

    @Test
    public void testMovement(){
        Level level = new Level01();
        World world = new World(level.getWorld(), level.getEntities(), level.getItems());
        Entity x= new Zombie("", 0, 0, 150, 100, true, world, 1, 1,1);

        assertEquals(0, x.getX());
        assertEquals(0, x.getY());  

        x.down();
        x.right();
  
        assertEquals(1, x.getX());
        assertEquals(1, x.getY()); 
        
        x.left();
        x.up();
        
        assertEquals(0, x.getX());
        assertEquals(0, x.getY());  


        

    }
    
}

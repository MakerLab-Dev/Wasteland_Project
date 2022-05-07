package wasteland.world;
import wasteland.entity.*;

public class World {
    private int worldWidth;
    private int worldHeigth;
    private int tiles[][];
    private Entity entities[]=new Entity[100];

    public World(int[][] worldTiles){
        this.worldHeigth= worldTiles.length;
        this.worldWidth= worldTiles[0].length;
        tiles= worldTiles;
        
    }

    int getWorldWidth(){
        return worldWidth;
    }

    int getWorldHeigth(){
        return worldHeigth;
    }
    void removeEntity(Entity entity){

    }
    void addEntity(Entity entity){
       for(int i=0; i<entities.length; i++){
           if(entities[i]==null){
               entities[i]=entity;
               return;
            }
        }
    }
    
    void setTile(int x,int y){
        this.tile= tiles[x][y];
    }

    int getTile(){
        return this.tile;
    }

    

    Entity getEntity(){
        return entity;
    }


}

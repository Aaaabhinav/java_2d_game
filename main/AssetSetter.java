
package main;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp =gp;
    }
    public void setObject(){
        gp.obj[0]=new OBJ_Key();
        gp.obj[0].worldX=23*gp.tileSize;
        gp.obj[0].worldY=7*gp.tileSize;




        gp.obj[1]=new OBJ_chest();
        gp.obj[1].worldX=10*gp.tileSize;
        gp.obj[1].worldY=8*gp.tileSize;


        gp.obj[2]=new OBJ_door();
        gp.obj[2].worldX=10*gp.tileSize;
        gp.obj[2].worldY=11*gp.tileSize;


        gp.obj[3]=new OBJ_boots();
        gp.obj[3].worldX=38*gp.tileSize;
        gp.obj[3].worldY=42*gp.tileSize;


        
        gp.obj[4]=new OBJ_door();
        gp.obj[4].worldX=12*gp.tileSize;
        gp.obj[4].worldY=22*gp.tileSize;
        
        gp.obj[5]=new OBJ_Key();
        gp.obj[5].worldX=40*gp.tileSize;
        gp.obj[5].worldY=8*gp.tileSize;
        
        
    }
}

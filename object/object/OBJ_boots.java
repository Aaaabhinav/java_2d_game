package object;

import java.io.IOException;

import javax.imageio.ImageIO;  
public class OBJ_boots extends SuperObject {


    public OBJ_boots(){
        name= "boots";
        try{
        image = ImageIO.read(getClass().getResourceAsStream("/res/objects/boots.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
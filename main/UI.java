package main;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Key;

public class UI {
    GamePanel gp;
    Font arial_40;
    Font arial_41;
    BufferedImage keyImage;
    int messageCounter=0;
    public boolean messageOn=false;
    public String message ="";
    public boolean gameFinished=false;
    double playtime; 




    public UI (GamePanel gp){
        this.gp=gp;
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_41=new Font("Arial",Font.BOLD,80 );
       
        
        OBJ_Key key =new OBJ_Key();
        keyImage=key.image;
    }
    
    public void showMessage(String text){
        message=text;
        messageOn=true;

    }
    public  double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    public void draw(Graphics2D g2){

        if(gameFinished){
            g2.setFont(arial_41);
            g2.setColor(Color.yellow);
            g2.drawString("Game over", (gp.screenWidth/2)-4*gp.tileSize, gp.screenHeight/2);

            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawString("Your time was : "+roundToTwoDecimalPlaces(playtime), 4*gp.tileSize+gp.tileSize/2, 2*gp.tileSize);

        }else{
            playtime+=(double)1/60;
            g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("X "+gp.player.hasKey,74,65);
        
        playtime+=(double)1/60;
        g2.drawString("Time : "+roundToTwoDecimalPlaces(playtime), 11*gp.tileSize, 1*gp.tileSize);


        //message
        if(messageOn==true){
            g2.setFont(g2.getFont().deriveFont(30F));

            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
            messageCounter++;
            if(messageCounter>120){
                messageCounter=0;
                messageOn=false;
            }
        }

        }
        
    }
}

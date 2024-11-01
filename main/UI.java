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
    public int commandNum=0;




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

    public void drawTitleScreen(Graphics2D g2){

        //setting background color of the titile screen
        g2.setColor(new Color(101,230,245));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

     

        //setting title text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,46F));
        int x=4*gp.tileSize+gp.tileSize/2;
        int y=2*gp.tileSize;
        String text="Blue boys dream";

        //adding shadow
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);

       

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //adding the boys image 
        x=(gp.screenWidth/2)-30;
        y=3*gp.tileSize;

        g2.drawImage(gp.player.down1, x, y,2*gp.tileSize,2*gp.tileSize,null);
        
        //displaying menu

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));



        text="Play";
        x=7*gp.tileSize+30;
        y=7*gp.tileSize;

        if(commandNum== 0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+3, y+3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

       


        text="Quit";
        x=7*gp.tileSize+30;
        y=8*gp.tileSize;
        if(commandNum== 1){
            g2.drawString(">",x-gp.tileSize,y);
        }

        g2.setColor(Color.BLACK);
        g2.drawString(text, x+3, y+3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        



    }
    public void draw(Graphics2D g2){


        //condition for title state

        if(gp.gameState==0){
        
          drawTitleScreen(g2);


        }else{
            
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
}

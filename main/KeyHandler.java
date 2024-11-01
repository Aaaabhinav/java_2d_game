package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;;

public class KeyHandler implements KeyListener{
    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        //Title State{}
        if(gp.gameState==0){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                if(gp.ui.commandNum>0){
                    
                gp.ui.commandNum--;

                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                if(gp.ui.commandNum<1){
                    gp.ui.commandNum++;

                }
                
            }

            if(code==KeyEvent.VK_ENTER){
                if(gp.ui.commandNum==0){
                    gp.gameState=1;
                }
            }

            if(code==KeyEvent.VK_ENTER){
                if(gp.ui.commandNum==1){
                  System.exit(0);
                }
            }

        }

        if(gp.gameState==2){
            
            if(code==KeyEvent.VK_A){
                if(gp.ui.quitState>0){
                    gp.ui.quitState--;
                }
                
            }

            if(code==KeyEvent.VK_D){
                if(gp.ui.quitState<1){
                    gp.ui.quitState++;

                }
              
            }

            //code inorder to replay the game 

            if(code==KeyEvent.VK_ENTER){
                if(gp.ui.quitState==0){
                    gp.gameState=1;
                    gp.ui.gameFinished=false;
                    gp.ui.playtime=0;
                    gp.player.worldX = gp.tileSize * 23;
                     gp.player.worldY = gp.tileSize * 21;
                     gp.player.speed = 4;
                     gp.player.direction = "down";
                    gp.setupGame();
                   
              
                }
            }

            if(code==KeyEvent.VK_ENTER){
                if(gp.ui.quitState==1){
               
                    
                    System.exit(0);
                }
                
            }



        }


        if(gp.gameState==1){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }

        }
        

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();



        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }
    
}
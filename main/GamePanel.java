package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; //768 pixels
    public final int screenHeight = maxScreenRow * tileSize; //576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxScreenCol * maxWorldCol; //50 tiles per screen
    public final int worldHeight = maxScreenRow * maxWorldRow; //50 tiles per screen


    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter =new AssetSetter(this);
    public UI ui = new UI(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] =new SuperObject[10];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        // double nextDrawTime = System.nanoTime() + drawInterval;

        // while (gameThread != null) {

        //     update();

        //     repaint();

            
        //     try {
        //         double remainingTime = nextDrawTime - System.nanoTime();
        //         remainingTime = remainingTime / 1000000;

        //         if (remainingTime < 0) {
        //             remainingTime = 0;
        //         }

        //         Thread.sleep((long) remainingTime);

        //         nextDrawTime += drawInterval;

        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int frames = 0;

        while (gameThread != null) {
            
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                frames++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        
        player.update();

    }

    //This is different, it is public
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        for(int i=0;i<obj.length;i++){
            if(obj[i]!=null){
                obj[i].draw(g2, this);
            }
        }
        
        player.draw(g2);
        
        //ui
        ui.draw(g2);

        g2.dispose();
    }
}
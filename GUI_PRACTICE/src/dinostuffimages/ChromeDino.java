package dinostuffimages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ChromeDino extends JPanel implements ActionListener, KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        if (collision==false){

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY=-41;
        }}
        else{
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                velocityY=0;
                score=0;
                obstacles.clear();
                collision=false;
                dino.objectimg=dinoimg;
                dinotimer.start();
                cactustimer.start();
            }
        }



    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        score++;
        move();
        if (collision){
            dinotimer.stop();
            cactustimer.stop();
            dino.objectimg=dinodeadimg;
        }
        repaint();

    }

    String dinorun;
    String dinodead;
    String dinorun1;
    String dinorun2;
    String cactus1FILE;
    String cactus2File;
    String cactus3File;

    final int screenHeight=250;
    final int screenWidth=800;
    Image dinoimg;
    Image dinodeadimg;
    Image cactus1img;
    Image cactus2img;
    Image cactus3img;
    Image gameoverimg;

    Block cactus1;
    Block cactus2;
    Block cactus3;
    Block dino;
    boolean collision=false;
    ArrayList<Block> obstacles=new ArrayList<Block>();
    Timer dinotimer;
    Timer cactustimer;
    int score=0;
    int velocityX=-19;//moving
    int velocityY=0;//jumping
    int gravity=7;





    public ChromeDino(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);
        this.addKeyListener(this);

        dinorun="dino-run.gif";
        dinorun1="dino.png";
        dinodead="dino-dead.png";
        cactus1FILE="cactus1.png";
        cactus2File ="cactus2.png";
        cactus3File="cactus3.png";
        dinoimg=new ImageIcon(getClass().getResource(dinorun)).getImage();
        dinodeadimg=new ImageIcon(getClass().getResource(dinodead)).getImage();
        gameoverimg=new ImageIcon(getClass().getResource("game-over.png")).getImage();
        cactus1img=new ImageIcon(getClass().getResource(cactus1FILE)).getImage();
        cactus2img=new ImageIcon(getClass().getResource(cactus2File)).getImage();
        cactus3img=new ImageIcon(getClass().getResource(cactus3File)).getImage();
        dino=new Block(50,dinoimg.getWidth(null),dinoimg.getHeight(null),dinoimg);
        cactus1=new Block(screenWidth-cactus1img.getWidth(null),cactus1img.getWidth(null),cactus1img.getHeight(null),cactus1img);
        cactus2=new Block(screenWidth-cactus2img.getWidth(null),cactus2img.getWidth(null),cactus2img.getHeight(null),cactus2img);
        cactus3=new Block(screenWidth-cactus3img.getWidth(null),cactus3img.getWidth(null),cactus3img.getHeight(null),cactus3img);
        dinotimer = new Timer(1000/60,this);
        cactustimer = new Timer(1500,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double chance=Math.random();
                if(chance>0.9){
                    obstacles.add(new Block(screenWidth-cactus3img.getWidth(null),cactus3img.getWidth(null),cactus3img.getHeight(null),cactus3img));
                }
                else if(chance>0.8){
                    obstacles.add(new Block(screenWidth-cactus2img.getWidth(null),cactus2img.getWidth(null),cactus2img.getHeight(null),cactus2img));
                }
                else if(chance>0.5){obstacles.add(new Block(screenWidth-cactus1img.getWidth(null),cactus1img.getWidth(null),cactus1img.getHeight(null),cactus1img));}
                if(obstacles.size()>10){
                    obstacles.remove(0);
                }
            }
        });
        dinotimer.start();
        cactustimer.start();


    }

    class Block{
        Image objectimg;
        int x;
        int y;
        int w;
        int h;
        public Block(int px,int w,int h,Image pobjectimg){
            this.w=w;
            this.h=h;
            this.x=px;
            this.y=screenHeight-h;
            objectimg =pobjectimg;
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setFont(new Font("Arial",Font.BOLD,30));
        if (collision==false) {

        g.drawString("Score: "+String.valueOf(score), 50, 50);
        }
        else{
            g.drawImage(gameoverimg,250,30,gameoverimg.getWidth(null),gameoverimg.getHeight(null),this);
            g.drawString("TOTAL SCORE: "+String.valueOf(score), 300, 100);
        }
        //dino
        g.drawImage(dino.objectimg,50,dino.y,dino.w,dino.h,this);

        //obstacles
        for(int i=0;i<obstacles.size();i++){
            Block b=obstacles.get(i);
            g.drawImage(b.objectimg,b.x,b.y,b.w,b.h,this);
        }

    }

    public void move(){
        //dino
        dino.y+=velocityY;
        velocityY=velocityY+gravity;
        if(dino.y>screenHeight-dino.h){
            velocityY=0;
            dino.y=screenHeight-dino.h;
        }



        //obstacles
        for(int i=0;i<obstacles.size();i++){
            Block temp=obstacles.get(i);
            temp.x=temp.x+velocityX;
            if(dino.y<temp.y+temp.h && dino.y+dino.h>temp.y && dino.x<temp.x+temp.w && dino.x+dino.w>temp.x){
                collision=true;
            }


        }
    }

}

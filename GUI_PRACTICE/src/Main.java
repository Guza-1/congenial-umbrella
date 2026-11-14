import dinostuffimages.ChromeDino;

import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args){
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GoogleChrome Dino");
        frame.setResizable(false);
         ChromeDino dino=new ChromeDino();
        frame.add(dino);
        dino.requestFocus();
        frame.pack();
        frame.setVisible(true);
    }
}

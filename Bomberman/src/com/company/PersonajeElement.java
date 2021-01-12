package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;


public class PersonajeElement {

    private BufferStrategy bs;
    private Graphics g;

    public static BufferedImage personaje;

    private int PosX,PosY;

    public PersonajeElement(int PosX, int PosY){
        this.PosX=PosX;
        this.PosY=PosY;

    }

    public PersonajeElement(){
        personaje = loadImage("Img/pacmanDer.png");
    }

    public void tick(){

    }

    public static void render(Graphics g,int PosX, int PosY){
        g.drawImage(PersonajeElement.personaje,PosX,PosY,null);
    }

    public static BufferedImage loadImage(String path){
        File file = new File(path);
        try {
            return ImageIO.read(file);
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}

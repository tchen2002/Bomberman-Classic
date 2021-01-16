package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;

public class PersonajeElement {

    private static boolean estado_bombda;
    private BufferStrategy bs;
    private Graphics g;
    private static  Animacion animacion_bomba,animacion_heroe,animacion_globo_rebotando;

    public static BufferedImage personaje,villano1;

    private BufferedImage[] bomba_explosion, heroe_caminando,globo_rebotando;

    private int PosX,PosY;

    public PersonajeElement(int PosX, int PosY){
        this.PosX=PosX;
        this.PosY=PosY;

    }

    public PersonajeElement(){
        personaje = loadImage("Img/pacmanDer.png");
        villano1 = loadImage("Img/globo1.png");

        bomba_explosion = new BufferedImage[2];
        bomba_explosion[0] =loadImage("Img/expo1.png");
        bomba_explosion[1] =loadImage("Img/expo2.png");
        //bomba_explosion[2] =loadImage("Img/expo3.png");
        //bomba_explosion[3] =loadImage("Img/expo4.png");
        animacion_bomba = new Animacion(500,bomba_explosion);

        heroe_caminando = new BufferedImage[2];
        heroe_caminando[0] = loadImage("Img/heroe1.png");
        heroe_caminando[1] = loadImage("Img/heroe2.png");
        animacion_heroe = new Animacion(500,heroe_caminando);

        globo_rebotando  = new BufferedImage[2];
        globo_rebotando[0] = loadImage("Img/globo1.png");
        globo_rebotando[1] = loadImage("Img/globo2.png");
        animacion_globo_rebotando = new Animacion(500,globo_rebotando);
    }

    public static void heroe_anima(){
        animacion_heroe.tick();
    }


    public static void rebotar(Graphics g,int id,int x,int y){
        
        animacion_globo_rebotando.tick();
        g.drawImage(animacion_globo_rebotando.getCurrentFrame(),x,y,null);
        Nivel.list_villano.get(id).setCamino(true);
    }

    public static void explosion(){
        animacion_bomba.tick();
    }

    /*
    public static void render(Graphics g,int PosX, int PosY){

        //rebotar();
        //g.drawImage(animacion_globo_rebotando.getCurrentFrame(),120,120,null);

        heroe_anima();
        g.drawImage(animacion_heroe.getCurrentFrame(),PosX,PosY,null);
        if(estado_bombda){
            explosion();
            g.drawImage(animacion_bomba.getCurrentFrame(),60,60,null);
        }

    }
    */

    public static void dibujar_heroe(Graphics g,int PosX, int PosY){
        heroe_anima();
        g.drawImage(animacion_heroe.getCurrentFrame(),PosX,PosY,null);
    }

    public static void dibujarExplosion(int x,int y){
        System.out.println("hello cochinada");
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                estado_bombda=true;
            }
        } ,2000);
    }

    public static void dibujar_globo(Graphics g,int id, int x,int y){
        if(Nivel.list_villano.get(id).getCamino()){
            g.drawImage(villano1,x,y,null);
        }else{
            rebotar(g,id,x,y);
        }

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

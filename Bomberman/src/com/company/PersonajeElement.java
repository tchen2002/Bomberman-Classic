package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PersonajeElement {

    private static ArrayList<Integer> list_explosion = new ArrayList<Integer>();
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

    public static void render(Graphics g,int PosX, int PosY){
        if(estado_bombda){
            explosion();
            //PONGO 2 PARA PRUEBAS
            //ArrayList<Bomba> lista = Tablero.explosion(list_explosion.get(0),list_explosion.get(1),);
            ArrayList<Bomba> lista = Tablero.explosion(list_explosion.get(0),list_explosion.get(1),Heroe.getCantCupon()+1);
           /* for (int c=0;c<lista.size();c++) {
                System.out.println("POSX"+lista.get(c).getPosX()+"POSY"+lista.get(c).getPosY());
            }*/
            for (int c=0;c<lista.size();c++) {
                g.drawImage(animacion_bomba.getCurrentFrame(),lista.get(c).getPosY()*30,lista.get(c).getPosX()*30,null);
            }


        }
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                estado_bombda=false;

            }
        } ,2000);

    }

    public static void dibujar_heroe(Graphics g,int PosX, int PosY){
        heroe_anima();
        g.drawImage(animacion_heroe.getCurrentFrame(),PosY*30,PosX*30,null);
        render(g,60,60);
    }

    public static void dibujarExplosion(int x,int y){
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                estado_bombda=true;
                list_explosion.clear();
                list_explosion.add((Juego.list_bomba.get(0).getPosX()));
                list_explosion.add((Juego.list_bomba.get(0).getPosY()));
                Juego.list_bomba.remove(0);

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

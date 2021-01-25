package com.company;

import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PersonajeElement {

    private static Sonido sonidoBomba;

    private static ArrayList<Integer> list_muertes_villanos = new ArrayList<Integer>();
    private static boolean estado_muertos = false;
    private static final ArrayList<Integer> list_explosion = new ArrayList<Integer>();
    private static final ArrayList<Animacion> list_enemigos_animacion = new ArrayList<Animacion>();
    private static boolean estado_bomba;
    private BufferStrategy bs;
    private Graphics g;
    private static  Animacion animacion_bomba,animacion_heroe, animacion_globo_rebotando;
    static BufferedImage[] list_img_villanos = new BufferedImage[8];

    private static Animacion animacion_muerte_villano;

    private static Animacion animacion_cel_rebotando,animacion_haki_rebotando;
    private static Animacion animacion_espon_rebotando,animacion_fant_rebotando,animacion_mon_rebotando,animacion_mong_rebotando;

    private BufferedImage[] muerte_villano;

    private BufferedImage[] bomba_explosion, heroe_caminando,globo_rebotando;

    private BufferedImage[] cel_rebotando,haki_rebotando,espon_rebotando,fant_rebotando,mon_rebotando,mong_rebotando;

    private int PosX,PosY;

    public PersonajeElement(int PosX, int PosY){
        this.PosX=PosX;
        this.PosY=PosY;
    }

    public PersonajeElement(){
        CargarImagenesHeroe();
        CargarImagenesEnemigos();
        CargarImagenesBomba();
        CargarImagenesMuerteVillano();
    }

    public void CargarImagenesMuerteVillano(){
        muerte_villano = new BufferedImage[2];
        muerte_villano[0] = loadImage("Img/muerte1.png");
        muerte_villano[1] = loadImage("Img/muerte2.png");
        animacion_muerte_villano = new Animacion(500,muerte_villano);
    }

    public void CargarImagenesHeroe(){
        heroe_caminando = new BufferedImage[2];
        heroe_caminando[0] = loadImage("Img/heroe1.png");
        heroe_caminando[1] = loadImage("Img/heroe2.png");
        animacion_heroe = new Animacion(500,heroe_caminando);
    }

    public void CargarImagenesBomba(){
        bomba_explosion = new BufferedImage[2];
        bomba_explosion[0] =loadImage("Img/expo1.png");
        bomba_explosion[1] =loadImage("Img/expo2.png");
        //bomba_explosion[2] =loadImage("Img/expo3.png");
        //bomba_explosion[3] =loadImage("Img/expo4.png");
        animacion_bomba = new Animacion(500,bomba_explosion);
    }

    public void CargarImagenesEnemigos(){
        list_img_villanos[0] = loadImage("Img/globo1.png");
        list_img_villanos[1] = loadImage("Img/cel1.png");
        list_img_villanos[2] = loadImage("Img/haki1.png");
        list_img_villanos[3] = loadImage("Img/espon1.png");
        list_img_villanos[4] = loadImage("Img/fant1.png");
        list_img_villanos[5] = loadImage("Img/mon1.png");
        list_img_villanos[6] = loadImage("Img/monG1.png");

        //ENEMIGOS
        globo_rebotando  = new BufferedImage[2];
        globo_rebotando[0] = loadImage("Img/globo1.png");
        globo_rebotando[1] = loadImage("Img/globo2.png");
        animacion_globo_rebotando = new Animacion(500,globo_rebotando);

        cel_rebotando  = new BufferedImage[2];
        cel_rebotando[0] = loadImage("Img/cel1.png");
        cel_rebotando[1] = loadImage("Img/cel2.png");
        animacion_cel_rebotando = new Animacion(500,cel_rebotando);

        haki_rebotando  = new BufferedImage[2];
        haki_rebotando[0] = loadImage("Img/haki1.png");
        haki_rebotando[1] = loadImage("Img/haki2.png");
        animacion_haki_rebotando = new Animacion(500,haki_rebotando);

        espon_rebotando  = new BufferedImage[2];
        espon_rebotando[0] = loadImage("Img/espon1.png");
        espon_rebotando[1] = loadImage("Img/espon2.png");
        animacion_espon_rebotando = new Animacion(500,espon_rebotando);

        fant_rebotando  = new BufferedImage[2];
        fant_rebotando[0] = loadImage("Img/fant1.png");
        fant_rebotando[1] = loadImage("Img/fant2.png");
        animacion_fant_rebotando = new Animacion(500,fant_rebotando);

        mon_rebotando  = new BufferedImage[2];
        mon_rebotando[0] = loadImage("Img/mon1.png");
        mon_rebotando[1] = loadImage("Img/mon2.png");
        animacion_mon_rebotando = new Animacion(500,mon_rebotando);

        mong_rebotando  = new BufferedImage[3];
        mong_rebotando[0] = loadImage("Img/monG1.png");
        mong_rebotando[1] = loadImage("Img/monG2.png");
        mong_rebotando[2] = loadImage("Img/monG3.png");
        animacion_mong_rebotando = new Animacion(500,mong_rebotando);

        list_enemigos_animacion.add(animacion_globo_rebotando);
        list_enemigos_animacion.add(animacion_cel_rebotando);
        list_enemigos_animacion.add(animacion_haki_rebotando);
        list_enemigos_animacion.add(animacion_espon_rebotando);
        list_enemigos_animacion.add(animacion_fant_rebotando);
        list_enemigos_animacion.add(animacion_mon_rebotando);
        list_enemigos_animacion.add(animacion_mong_rebotando);
    }

    public static void heroe_anima(){
        animacion_heroe.tick();
    }


    public static void rebotar(Graphics g,int id,int x,int y,int tipo){
        list_enemigos_animacion.get(tipo).tick();
        g.drawImage(list_enemigos_animacion.get(tipo).getCurrentFrame(),x,y,null);
        Nivel.list_villano.get(id).setCamino(true);
    }

    public static void explosion(){
        animacion_bomba.tick();
    }

    public static void render(Graphics g){
                if(estado_bomba){
                    explosion();
                    ArrayList<Bomba> lista = Tablero.explosion(list_explosion.get(0),list_explosion.get(1),Heroe.getCantCupon()+1);
                    Observer.VerificarExplosion(lista);
                    Observer.VerificarExplosionPuerta(lista);
                    Observer.VerificarExplosionCupon(lista);
                    for (int c=0;c<lista.size();c++) {
                        g.drawImage(animacion_bomba.getCurrentFrame(),lista.get(c).getPosY()*30,lista.get(c).getPosX()*30,null);
                    }

                }
                Timer time = new Timer();
                time.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        estado_bomba=false;
                    }
                } ,2000);



        if(estado_muertos){
            for(int i=0;i<list_muertes_villanos.size();i+=2){
                animacion_muerte_villano.tick();
                g.drawImage(animacion_muerte_villano.getCurrentFrame(),list_muertes_villanos.get(i+1)*30,list_muertes_villanos.get(i)*30,null);
            }
        }

        Timer time1 = new Timer();
        time1.schedule(new TimerTask() {
            @Override
            public void run() {
                estado_muertos=false;

            }
        } ,3000);

    }

    public static void dibujar_heroe(Graphics g,int PosX, int PosY){
        heroe_anima();
        g.drawImage(animacion_heroe.getCurrentFrame(),PosY*30,PosX*30,null);
        render(g);
    }

    public static boolean dibujar_villanos_muertos(ArrayList<Integer> lista){
        list_muertes_villanos.clear();
        if(!lista.isEmpty()){
            estado_muertos=true;
            list_muertes_villanos = lista;
            return true;
        }
        return false;
    }

    public static void dibujarExplosion(){
        if(!Juego.list_bomba.isEmpty() && Juego.list_bomba.get(0).getEstado()){
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    estado_bomba=true;
                    list_explosion.clear();
                    list_explosion.add((Juego.list_bomba.get(0).getPosX()));
                    list_explosion.add((Juego.list_bomba.get(0).getPosY()));
                    Juego.list_bomba.remove(0);
                    Heroe.setCantBomba(Heroe.getCantBomba()+1);
                    Sonido sonidoExplosion = new Sonido();
                    sonidoExplosion.play("Img/boom.mp3");
                }
            } ,2000);
   }
                }

    public static void dibujar_enemigo(Graphics g,int id, int tipo,int x,int y){
        if(Nivel.list_villano.get(id).getCamino()){
            g.drawImage(list_img_villanos[tipo],x,y,null);
        }else{
            rebotar(g,id,x,y,tipo);
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

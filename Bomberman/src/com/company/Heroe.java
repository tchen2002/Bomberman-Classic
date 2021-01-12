package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.security.Key;


public class Heroe extends Personaje {

    private static int PosX,PosY;
    private BufferStrategy bs;
    private Graphics g;
    private int vida,CantBombda,CantCupón;
    private Juego juego;
    private PersonajeElement personajeElement;
    private Personaje personaje;
    public static int dir;

    public Heroe(int PosX, int PosY, int Velocidad, int Dirección, boolean Estado) {
        super(PosX, PosY, Velocidad, Dirección, Estado);
    }

    public static void mover(int dir){
        if(dir==0){
            PosY-=30;
        }

        if(dir==1){
            PosY+=30;
        }

        if(dir==2){
            PosX-=30;
        }

        if(dir==3) {
            PosX+=30;
        }
    }
    @Override
    public void tick(int dir) {
    }

    @Override
    public void render(Graphics g) {
        System.out.println(PosX + " " + PosY);
        PersonajeElement.render(g,PosX,PosY);
    }
}

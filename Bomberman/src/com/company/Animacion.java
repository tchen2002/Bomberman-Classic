package com.company;

import java.awt.image.BufferedImage;

public class Animacion {

    private final int speed;
    private int index;
    private long lastTime, timer;
    private final BufferedImage[] frames;

    /* Función: Animacion
   Dominio: Recibe la velocidad como entero y un buffer de la imagen
   Codominio: Es el constructor que permite utilizar las variables*/
    public Animacion(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /* Función: tick
   Dominio: No recibe ningún parámetro
   Codominio: Reproducir las imágenes según el índice*/
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length)
                index = 0;
        }
    }

    /* Función: GetCurrentFrame
    Dominio: No recibe ningún parámetro
    Codominio: Devuelve el frame actual según la posición del índice*/
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

}
package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Villano extends Personaje{

    private BufferStrategy bs;
    private Graphics g;
    private int colision;
    private int id,PosX,PosY,Velocidad,Direccion;
    private boolean Estado,Camino;

    public Villano(int id,int PosX, int PosY, int Velocidad, int Direccion, boolean Estado,boolean Camino) {
        this.id=id;
        this.PosX=PosX;
        this.PosY=PosY;
        this.Velocidad=Velocidad;
        this.Direccion=Direccion;
        this.Estado=Estado;
        this.Camino=Camino;
    }

    @Override
    public void tick(int dir) {

    }

    @Override
    public void render(Graphics g) {

    }
    public static void DibujarVillanos(Graphics g){
        for(int i=0;i<10;i++){
            PersonajeElement.dibujar_globo(g,Nivel.list_villano.get(i).getId(),Nivel.list_villano.get(i).getPosY()*30 , Nivel.list_villano.get(i).getPosX()*30);
            //g.drawImage(PersonajeElement.villano1,Nivel.list_villano.get(i).getPosY()*30 , Nivel.list_villano.get(i).getPosX()*30,null);
            InteligenciaArtificial.MoverRnd(Nivel.list_villano.get(i).getId());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosX() {
        return PosX;
    }

    public void setPosX(int posX) {
        PosX = posX;
    }

    public int getPosY() {
        return PosY;
    }

    public void setPosY(int posY) {
        PosY = posY;
    }

    public int getVelocidad() {
        return Velocidad;
    }

    public void setVelocidad(int velocidad) {
        Velocidad = velocidad;
    }

    public int getDireccion() {
        return Direccion;
    }

    public void setDireccion(int direccion) {
        Direccion = direccion;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public boolean getCamino() {
        return Camino;
    }

    public void setCamino(boolean camino) {
        Camino = camino;
    }
}

package com.company;

import java.awt.*;

public abstract class Personaje {

    private int id,PosX,PosY,Velocidad,Direccion;
    private boolean Estado;

    public Personaje(int id,int PosX, int PosY, int Velocidad, int Direccion, boolean Estado) {
        this.id=id;
        this.PosX = PosX;
        this.PosY = PosY;
        this.Velocidad = Velocidad;
        this.Direccion = Direccion;
        this.Estado = Estado;
    }

    public Personaje() {

    }

    public abstract void tick(int dir);
    public abstract void render(Graphics g);

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

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }
}
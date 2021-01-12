package com.company;

import java.awt.*;

public abstract class Personaje {

    public static int PosX,PosY,Velocidad,Dirección;
    private boolean Estado;

    public Personaje(int PosX, int PosY, int Velocidad, int Dirección, boolean Estado) {
        this.PosX = PosX;
        this.PosY = PosY;
        this.Velocidad = Velocidad;
        this.Dirección = Dirección;
        this.Estado = Estado;
    }

    public abstract void tick(int dir);
    public abstract void render(Graphics g);

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

    public int getDirección() {
        return Dirección;
    }

    public void setDirección(int dirección) {
        Dirección = dirección;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }
}

package com.company;

import java.awt.*;

public abstract class Personaje {

    private int id,PosX,PosY,Direccion;
    private boolean Estado;
    private double Velocidad;

    /* Función: Personaje
   Dominio: Id,Posiciones x,y, direccion de tipo entero,velocidad de tipo flotante  y el estado de tipo booleano
   Codominio: Constructor que llama a las variables de la clase que se vaya a utilizar
*/
    public Personaje(int id,int PosX, int PosY, double Velocidad, int Direccion, boolean Estado) {
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

    /* --------------------------------------------------
                  Getters y Setters
   --------------------------------------------------
*/
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

    public double getVelocidad() {
        return Velocidad;
    }

    public void setVelocidad(double velocidad) {
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

package com.company;

public class Puerta {

    private int PosX,PosY;
    private boolean Estado;

    public Puerta(int posX, int posY, boolean Estado) {
        this.PosX = posX;
        this.PosY = posY;
        this.Estado = Estado;
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

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }
}

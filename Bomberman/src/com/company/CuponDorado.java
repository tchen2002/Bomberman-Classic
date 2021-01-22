package com.company;

public class CuponDorado {
    private int Cupon, Nivel,PosX,PosY;
    private boolean AfterLife, Estado,Activo;

    public CuponDorado(int cupon, int nivel, boolean afterLife, boolean activo) {
        Cupon = cupon;
        Nivel = nivel;
        AfterLife = afterLife;
        Activo = activo;
    }

    public CuponDorado(int cupon, boolean Estado, int posX, int posY) {
        Cupon = cupon;
        Estado = Estado;
        PosX = posX;
        PosY = posY;
    }

    public void MasAlcanceBombas(){
        Juego.heroe.setCantCupon((Juego.heroe.getCantCupon())+2);
    }

    public void MasColocarBomba(){
        Juego.heroe.setCantBomba((Juego.heroe.getCantBomba())+1);
    }

    public int getCupon() {
        return Cupon;
    }

    public void setCupon(int cupon) {
        Cupon = cupon;
    }

    public int getNivel() {
        return Nivel;
    }

    public void setNivel(int nivel) {
        Nivel = nivel;
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

    public void setPosY(int posy) {
        PosY = posy;
    }

    public boolean getAfterLife() {
        return AfterLife;
    }

    public void setAfterLife(boolean afterLife) {
        AfterLife = afterLife;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public boolean getActivo() {
        return Activo;
    }

    public void setActivo(boolean activo) {
        Activo = activo;
    }
}

package com.company;

public class Bomba{
    private int PosX,PosY,LargoBomba,CantBomba;
    private boolean Estado;

    public Bomba(int posX, int posY, boolean Estado) {
        this.PosX = posX;
        this.PosY = posY;
        this.Estado = Estado;
    }

    public Bomba(int posX, int posY) {
        this.PosX = posX;
        this.PosY = posY;
    }

    public static void render(){
        PersonajeElement.dibujarExplosion(30,30);
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

    public int getLargoBomba() {
        return LargoBomba;
    }

    public void setLargoBomba(int largoBomba) {
        LargoBomba = largoBomba;
    }

    public int getCantBomba() {
        return CantBomba;
    }

    public void setCantBomba(int cantBomba) {
        CantBomba = cantBomba;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }
}

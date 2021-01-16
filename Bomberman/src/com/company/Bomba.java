package com.company;

import javafx.geometry.Pos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Bomba{
    private int PosX,PosY,LargoBomba,CantBomba;

    public Bomba(int posX, int posY) {
        PosX = posX;
        PosY = posY;
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

}

package com.company;

import java.awt.*;

public class Heroe extends Personaje {

    private static int PosX,PosY;
    public static int Vida,CantBomba,CantCupon;
    private static int colision;


    public Heroe(int id,int PosX, int PosY, int Velocidad, int Direccion, boolean Estado,int Vida,int CantBoomba,int CantCupon) {
        super(id,PosX, PosY, Velocidad, Direccion, Estado);
        Vida = Vida;
        CantBomba = CantBomba;
        CantCupon = CantCupon;
    }

    public static void ColocarBomba(){
        int X= PosX;
        int Y= PosY;
        Bomba bomba = new Bomba(X,Y,true);
        Juego.list_bomba.add(bomba);
        Bomba.render();

    }

    public static void mover(int dir){
        colision = Tablero.colision(PosX,PosY,dir);
        if (colision == 0) {
            if (dir == 0) {
                PosX -= 1;
            }

            if (dir == 1) {
                PosX += 1;
            }

            if (dir == 2) {
                PosY -= 1;
            }

            if (dir == 3) {
                PosY += 1;
            }
        }
    }

    @Override
    public void tick(int dir) {
    }

    @Override
    public void render(Graphics g) {
        if (PosX == 0 && PosY == 0){
            PosX+=1;
            PosY+=1;
        }
        PersonajeElement.dibujar_heroe(g,PosX,PosY);
    }

    public int getVida() {
        return Vida;
    }

    public void setVida(int vida) {
        Vida = vida;
    }

    public int getCantBomba() {
        return CantBomba;
    }

    public void setCantBomba(int cantBomba) {
        CantBomba = cantBomba;
    }

    public static int getCantCupon() {
        return CantCupon;
    }

    public void setCantCupon(int cantCupon) {
        CantCupon = cantCupon;
    }
}

package com.company;

import java.awt.*;

public class Heroe extends Personaje {

    private static int PosX,PosY;
    private int vida,CantBombda,CantCupón;
    private static int colision;
    private int Vida,CantBomba,CantCupon;

    public Heroe(int id,int PosX, int PosY, int Velocidad, int Direccion, boolean Estado,int Vida,int CantBoomba,int CantCupon) {
        super(id,PosX, PosY, Velocidad, Direccion, Estado);
        this.Vida = Vida;
        this.CantBomba = CantBombda;
        this.CantCupon = CantCupón;
    }

    public static void ColocarBomba(){
        int X= PosX;
        int Y= PosY;
        Bomba bomba = new Bomba(Y,X);
        Juego.list_bomba.add(bomba);
        Juego.list_bomba_explosion.add(bomba);
        for(int i=0;i<Juego.list_bomba_explosion.size();i++){
            System.out.println("BOMBA"+i+Juego.list_bomba_explosion.get(i).getPosX()+Juego.list_bomba_explosion.get(i).getPosY());
        }
        Bomba.render();
    }

    public static void mover(int dir){
        colision = Tablero.colision(PosX/30,PosY/30,dir);
        if (colision == 0) {
            if (dir == 0) {
                PosX -= 30;
            }

            if (dir == 1) {
                PosX += 30;
            }

            if (dir == 2) {
                PosY -= 30;
            }

            if (dir == 3) {
                PosY += 30;
            }
        }
    }

    @Override
    public void tick(int dir) {
    }

    @Override
    public void render(Graphics g) {
        if (PosX == 0 && PosY == 0){
            PosX+=30;
            PosY+=30;
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

    public int getCantCupon() {
        return CantCupon;
    }

    public void setCantCupon(int cantCupon) {
        CantCupon = cantCupon;
    }
}

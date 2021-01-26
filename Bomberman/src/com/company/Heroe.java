package com.company;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Heroe extends Personaje {

    private static int PosX,PosY;
    public static int Vida,CantBomba,CantCupon;
    private static int colision;

    /* Función: Heroe
       Dominio: Un id,posicion x,y, velocidad,direccion,estado,vida, cantidad de bombas y
                cantidad de cupones de tipo entero o flotante
       Codominio: Constructor que inicializa las variables de la clase Heroe
    */
    public Heroe(int id,int PosX, int PosY, double Velocidad, int Direccion, boolean Estado,int Vida,int cantBomba,int CantCupon) {
        Heroe.Vida = Vida;
        CantBomba = cantBomba;
        Heroe.CantCupon = CantCupon;
    }

    /* Función: Colocar Bomba
       Dominio: No recibe ningún parámetro
       Codominio: Según la posición x, y del héroe si en el nivel se encuentra el cupón activo crea la bomba con esas características
                  del cupón,  de lo contrario solo crea la bomba
    */
    public static void ColocarBomba(){
        int X= PosX;
        int Y= PosY;
        if(Nivel.list_cupon.get(2).getActivo()){
            Bomba bomba = new Bomba(X,Y,false);
            Juego.list_bomba.add(bomba);
        }else{
            Bomba bomba = new Bomba(X,Y,true);
            Juego.list_bomba.add(bomba);
        }
        Bomba.render();
    }

    /* Función: Mover
        Dominio: Una dirección de tipo entero
        Codominio: Verifica si esa próxima posición es un muro o ladrillo
                   Si no lo es actualiza la nueva posición del héroe según
                   la dirección que entró como parámetro
     */
    public static void mover(int dir){
        colision = Tablero.colision(PosX,PosY,dir);
        if (colision == 0) {
            if (dir == 0) PosX -= 1;
            if (dir == 1) PosX += 1;
            if (dir == 2) PosY -= 1;
            if (dir == 3) PosY += 1;
        }
    }

    /* Función: Encontrar Cupon
       Dominio: No recibe ningún parámetro
       Codominio: Cuando el héroe encuentra el cupón, se actualizará el estado del cupón y activará el poder
       y suena el sonido de campana
*/
    public static void EncontrarCupon(){
        int x = Nivel.cupon.getPosX();
        int y = Nivel.cupon.getPosY();
        if(Tablero.Mapa[x][y] == '-' && Heroe.GetPosX()==x && Heroe.GetPosY()==y && !Nivel.cupon.getEstado()){
            if(!Nivel.cupon.getEstado()){
                Juego.heroe.setCantCupon((getCantCupon())+1);
                Nivel.cupon.setEstado(true);
                Nivel.list_cupon.get(Nivel.cupon.getCupon()).setActivo(true);
                CuponDorado.ActivarPoder(Nivel.cupon.getCupon());

                Timer time = new Timer();
                time.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        Sonido sonidoCupon = new Sonido();
                        sonidoCupon.play("Img/cupondorado.mp3");
                    }
                } ,2000);

            }
        }
    }

    /* Función: Encontrar Puerta
       Dominio: No recibe ningún parámetro
       Codominio: Verificar si el héroe ya encontró la puerta
    */
    public static boolean EncontrarPuerta(){
        int x = Nivel.puerta.getPosX();
        int y = Nivel.puerta.getPosY();
        return Tablero.Mapa[x][y] == '-' && Heroe.GetPosX() == x && Heroe.GetPosY() == y;
    }

    @Override
    public void tick(int dir) {
    }

    /* Función: Render
       Dominio:
       Codominio: Si las coordenadas están en 0,0 de cambian a 1,1
                  para evitar problemas de colisiones
    */
    @Override
    public void render(Graphics g) {
        if (PosX == 0 && PosY == 0){
            PosX+=1;
            PosY+=1;
        }
        PersonajeElement.dibujar_heroe(g,PosX,PosY);
    }


    /* --------------------------------------------------
                      Getters y Setters
       --------------------------------------------------
    */
    public static int getVida() {
        return Vida;
    }

    public static void setVida(int vida) {
        Vida = vida;
    }

    public static int getCantBomba() {
        return CantBomba;
    }

    public static void setCantBomba(int cantBomba) {
        CantBomba = cantBomba;
    }

    public static int getCantCupon() {
        return CantCupon;
    }

    public void setCantCupon(int cantCupon) {
        CantCupon = cantCupon;
    }

    public static int GetPosX() {
        return PosX;
    }

    public static void  SetPosX(int posX) {
        PosX = posX;
    }

    public static int GetPosY() {
        return PosY;
    }

    public static void SetPosY(int posY) {
        PosY = posY;
    }
}

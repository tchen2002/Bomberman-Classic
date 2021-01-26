package com.company;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CuponDorado {

    private int Cupon, Nivel_,PosX,PosY;
    private boolean AfterLife, Estado,Activo;

    public static boolean flag_60s;

    /* Función: CuponDorado
       Dominio: Recibe un cupon,nivel de tipo entero, si funciona despues de morir, y una variable activo de tipo booleano
       Codominio: Constructor que inicializa las variables de la clase CuponDorado
    */
    public CuponDorado(int cupon, int nivel, boolean afterLife, boolean activo) {
        Cupon = cupon;
        Nivel_ = nivel;
        AfterLife = afterLife;
        Activo = activo;
    }

    /* Función: CuponDorado
       Dominio: Recibe un cupón de tipo entero, si funciona después de morir, y una variable activo de tipo booleano
       Codominio: Constructor que inicializa las variables de la clase CuponDorado sin incluir el nivel
    */
    public CuponDorado(int cupon, boolean Estado, int posX, int posY) {
        Cupon = cupon;
        Estado = Estado;
        PosX = posX;
        PosY = posY;
    }

    /* Función:Activar poder
     Dominio: Recibe cuál es el tipo cupón
     Codominio: Según  el número de 0 a 6 va a realizar la acción de cada cupón
  */
    public static void ActivarPoder(int c){
        if(c==0){
            MasAlcanceBombas();
        }else if(c==1){
            MasColocarBomba();
        }else if(c==3){
            Aumentarvelocidad();
        }else if(c==6){
            flag_60s=true;
            Pregunta();
        }
    }

    /* Función: MasAlcanceBombas
       Dominio: No recibe ningún parámetro
       Codominio: Se le agrega dos cupones, la distancia de la explosión se le suma 2 cuadros
    */
    public static void MasAlcanceBombas(){
        Juego.heroe.setCantCupon((Heroe.getCantCupon())+2);
    }

    /* Función: MasColocarBomba
       Dominio: No recibe ningún parámetro
       Codominio: Se le agrega una bomba más
    */
    public static void MasColocarBomba(){
        Heroe.setCantBomba((Heroe.getCantBomba())+1);
    }

    /* Función: Detonador
       Dominio: No recibe ningún parámetro
       Codominio: Cambiar el estado de bomba si presiona Spacebar o Right-click y llamar a la función de explosión
    */
    public static void Detonador(){
        Juego.list_bomba.get(0).setEstado(true);
        Bomba.render();
    }

    /* Función: AtraviesaBomba
       Dominio: No recibe ningún parámetro
       Codominio: la bomba ya no será un obstáculo para el héroe y no puede atrevesarla
    */
    public static boolean AtraviesaBomba(){
        return Nivel.list_cupon.get(Nivel.cupon.getCupon()).getNivel() == 11 && Nivel.list_cupon.get(Nivel.cupon.getCupon()).getActivo();
    }

    /* Función: AtraviesaMuro
       Dominio: No recibe ningún parámetro
       Codominio: el héroe podrá atravesar muro
    */
    public static boolean AtraviesaMuro(){
        return Nivel.list_cupon.get(Nivel.cupon.getCupon()).getNivel() == 14 && Nivel.list_cupon.get(Nivel.cupon.getCupon()).getActivo();
    }


    /* Función: AtraviesaMuro
       Dominio: No recibe ningún parámetro
       Codominio: el héroe será invulnerable al fuego
    */
    public static boolean HombreLlama(){
        return Nivel.list_cupon.get(Nivel.cupon.getCupon()).getNivel() == 23 && Nivel.list_cupon.get(Nivel.cupon.getCupon()).getActivo();
    }

    /* Función: Pregunta
       Dominio: No recibe ningún parámetro
       Codominio:el héroe será invulnerable al fuego por 60 segundos
    */
    public static void Pregunta(){
        if(Nivel.list_cupon.get(Nivel.cupon.getCupon()).getNivel()==19 && Nivel.list_cupon.get(Nivel.cupon.getCupon()).getActivo()){
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    flag_60s=false;
                }
            } ,60000);
        }
    }

    /* Función: Aumentarvelocidad
   Dominio: No recibe ningún parámetro
   Codominio: Aumentará la velocidad del héroe
    */
    public static void Aumentarvelocidad(){
        Juego.heroe.setVelocidad((Juego.heroe.getVelocidad())*1.5);
    }


    /* --------------------------------------------------
                      Getters y Setters
       --------------------------------------------------
    */
    public int getCupon() {
        return Cupon;
    }

    public void setCupon(int cupon) {
        Cupon = cupon;
    }

    public int getNivel() {
        return Nivel_;
    }

    public void setNivel(int nivel) {
        Nivel_ = nivel;
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

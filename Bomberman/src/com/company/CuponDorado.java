package com.company;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CuponDorado {
    private int Cupon, Nivel_,PosX,PosY;
    private boolean AfterLife, Estado,Activo;

    public static boolean flag_60s;

    public CuponDorado(int cupon, int nivel, boolean afterLife, boolean activo) {
        Cupon = cupon;
        Nivel_ = nivel;
        AfterLife = afterLife;
        Activo = activo;
    }

    public CuponDorado(int cupon, boolean Estado, int posX, int posY) {
        Cupon = cupon;
        Estado = Estado;
        PosX = posX;
        PosY = posY;
    }

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

    public static void MasAlcanceBombas(){
        Juego.heroe.setCantCupon((Juego.heroe.getCantCupon())+2);
    }

    public static void MasColocarBomba(){
        Juego.heroe.setCantBomba((Juego.heroe.getCantBomba())+1);
    }

    public static void Detonador(){
        Juego.list_bomba.get(0).setEstado(true);
        Bomba.render();
    }

    public static boolean AtraviesaBomba(){
        if(Nivel.list_cupon.get(Nivel.cupon.getCupon()).getNivel()==11 && Nivel.list_cupon.get(Nivel.cupon.getCupon()).getActivo()){
            return true;
        }
        return false;
    }

    public static boolean AtraviesaMuro(){
        if(Nivel.list_cupon.get(Nivel.cupon.getCupon()).getNivel()==14 && Nivel.list_cupon.get(Nivel.cupon.getCupon()).getActivo()){
            return true;
        }
        return false;
    }

    public static boolean HombreLlama(){
        if(Nivel.list_cupon.get(Nivel.cupon.getCupon()).getNivel()==23 && Nivel.list_cupon.get(Nivel.cupon.getCupon()).getActivo()){
            return true;
        }
        return false;
    }

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

    public static void Aumentarvelocidad(){
        Juego.heroe.setVelocidad((Juego.heroe.getVelocidad())*1.5);
    }

    public void AumentarVelocidad(){
        for(int i=0;i<Nivel.list_villano.size();i++){
            Nivel.list_villano.get(i).setVelocidad(Nivel.list_villano.get(i).getVelocidad() * 1.5);
        }
    }

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

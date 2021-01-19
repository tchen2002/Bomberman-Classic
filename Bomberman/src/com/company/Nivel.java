package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Nivel {

    public static ArrayList<Villano> list_villano = new ArrayList<Villano>();
    private int nivel,cantVillano;
    private Villano villano;

    public Nivel(int nivel,int cantVillano){
        this.nivel = nivel;
        this.cantVillano=cantVillano;
        Poisson();
    }

    public void Poisson(){
        int x,y;
        for(int i=0;i<getCantVillano();i++){
            int Dir=generarRandom();
            llenarArreglo(i,Dir);
        }
    }

    public int generarRandom(){
        Random ram = new Random();
        return ram.nextInt(4);
    }

    public void llenarArreglo(int cont, int dire){
        int x,y;
        int i=cont;
        int Dir=dire;
        Boolean est = true;
        Random ram = new Random();
        x=ram.nextInt(Juego.Largo-1);
        y=ram.nextInt(Juego.Ancho-1);
        while((Tablero.tipoCuadrado(x,y)!=true)){

            x=ram.nextInt(Juego.Largo-1);
            y=ram.nextInt(Juego.Ancho-1);
            if ((x == 1 && y==1) || (x == 1 && y==2) || (x == 2 && y==1)) {
                x=ram.nextInt(Juego.Largo-1);
                y=ram.nextInt(Juego.Ancho-1);
            }
        }
        list_villano.add(new Villano(i,x,y,0,Dir,est,true));
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCantVillano() {
        return cantVillano;
    }

    public void setCantVillano(int cantVillano) {
        this.cantVillano = cantVillano;
    }
}

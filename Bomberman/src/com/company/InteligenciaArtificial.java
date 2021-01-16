package com.company;


import java.util.Random;

public class InteligenciaArtificial {
    private int TipoInteligencia;
    //private static int colision;

    public InteligenciaArtificial(int tipoInteligencia) {
        TipoInteligencia = tipoInteligencia;
    }

    public static void mover(int id){
        Nivel.list_villano.get(id).setPosX((Nivel.list_villano.get(id).getPosX())-1);
        Nivel.list_villano.get(id).setPosY((Nivel.list_villano.get(id).getPosY())-1);
    }

    public static void moverRandom(int id){
        System.out.println("ANTES");
        System.out.println("POSY"+(Nivel.list_villano.get(id).getPosX())+"POSX"+(Nivel.list_villano.get(id).getPosY()));
        Nivel.list_villano.get(id).setPosX((Nivel.list_villano.get(id).getPosX())-1);
        Nivel.list_villano.get(id).setPosY((Nivel.list_villano.get(id).getPosY())-1);
        System.out.println("DESPUES");
        System.out.println("POSY"+(Nivel.list_villano.get(id).getPosX())+"POSX"+(Nivel.list_villano.get(id).getPosY()));
    }

    public static void MoverRnd(int id){
        Random ram = new Random();
        int a,b,c,colision,D;
        a=Nivel.list_villano.get(id).getPosX();
        b=Nivel.list_villano.get(id).getPosY();
        c=Nivel.list_villano.get(id).getDireccion();
        System.out.println(a+" "+b+" "+c);
        colision = Tablero.colision(a,b,c);
        System.out.println(colision);
        if(c==0){
            if(Tablero.tipoCuadrado(a-1,b)){
                Nivel.list_villano.get(id).setPosX(a-1);
            }else{
                D= ram.nextInt(4);
                Nivel.list_villano.get(id).setDireccion(D);
            }
        }

        if(c==1){
            if(Tablero.tipoCuadrado(a+1,b)){
                Nivel.list_villano.get(id).setPosX(a+1);
            }else{
                D= ram.nextInt(4);
                Nivel.list_villano.get(id).setDireccion(D);
            }
        }

        if(c==2){
            if(Tablero.tipoCuadrado(a,b-1)){
                Nivel.list_villano.get(id).setPosY(b-1);
            }else{
                D= ram.nextInt(4);
                Nivel.list_villano.get(id).setDireccion(D);
            }
        }

        if(c==3){
            if(Tablero.tipoCuadrado(a,b+1)){
                Nivel.list_villano.get(id).setPosY(b+1);
            }else{
                D= ram.nextInt(4);
                Nivel.list_villano.get(id).setDireccion(D);
            }
        }

    }
}

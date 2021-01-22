package com.company;


import java.util.Random;

public class InteligenciaArtificial {
    private int TipoInteligencia;

    public InteligenciaArtificial(int tipoInteligencia) {
        TipoInteligencia = tipoInteligencia;
    }

    public static void InteliArtificial(int id){
        MoverRnd(id);
        /*
        if(Nivel.list_villano.get(id).getTipo()==0 || Nivel.list_villano.get(id).getTipo()==1){
            MoverRnd(id);
        }*/
    }


    public static void MoverRnd(int id){
        Random ram = new Random();
        int a,b,c,D;
        a=Nivel.list_villano.get(id).getPosX();
        b=Nivel.list_villano.get(id).getPosY();
        c=Nivel.list_villano.get(id).getDireccion();
        if(c==0){
            if(Tablero.tipoCuadrado(a-1,b)){
                Nivel.list_villano.get(id).setPosX(a-1);
            }else{
                D= ram.nextInt(4);
                Nivel.list_villano.get(id).setDireccion(D);
                Nivel.list_villano.get(id).setCamino(false);
            }
        }

        if(c==1){
            if(Tablero.tipoCuadrado(a+1,b)){
                Nivel.list_villano.get(id).setPosX(a+1);
            }else{
                D= ram.nextInt(4);
                Nivel.list_villano.get(id).setDireccion(D);
                Nivel.list_villano.get(id).setCamino(false);
            }
        }

        if(c==2){
            if(Tablero.tipoCuadrado(a,b-1)){
                Nivel.list_villano.get(id).setPosY(b-1);
            }else{
                D= ram.nextInt(4);
                Nivel.list_villano.get(id).setDireccion(D);
                Nivel.list_villano.get(id).setCamino(false);
            }
        }

        if(c==3){
            if(Tablero.tipoCuadrado(a,b+1)){
                Nivel.list_villano.get(id).setPosY(b+1);
            }else{
                D= ram.nextInt(4);
                Nivel.list_villano.get(id).setDireccion(D);
                Nivel.list_villano.get(id).setCamino(false);
            }
        }
    }
}

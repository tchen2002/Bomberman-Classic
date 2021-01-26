package com.company;

import java.util.ArrayList;
import java.util.Random;

public class InteligenciaArtificial {
    private final int TipoInteligencia;

    /* Función: InteligenciaArtificial
     Dominio: Recibe el tipo de inteligencia
     Codominio: Constructor que llama a las variables de la clase que se vaya a utilizar
    */
    public InteligenciaArtificial(int tipoInteligencia) {
        TipoInteligencia = tipoInteligencia;
    }

    /* Función: InteliArtificial
         Dominio: Recibe el id de enemigo
         Codominio: Se utiliza el id para ver cuál es el tipo de inteligencia que posee y llamar a la función correspondiente
    */
    public static void InteliArtificial(int id){

        if(Nivel.list_villano.get(id).getTipo()==0 || Nivel.list_villano.get(id).getTipo()==1 || Nivel.list_villano.get(id).getTipo()==2 ||
        Nivel.list_villano.get(id).getTipo()==3 || Nivel.list_villano.get(id).getTipo()==4 || Nivel.list_villano.get(id).getTipo()==5 || Nivel.list_villano.get(id).getTipo()==6){
            MoverRnd(id);
        }else if(Nivel.list_villano.get(id).getTipo()==7){
            LV1(id);
        }else if(Nivel.list_villano.get(id).getTipo()==8){
            LV2(id);
        }else if(Nivel.list_villano.get(id).getTipo()==11){
            LV3(id);
        }else if(Nivel.list_villano.get(id).getTipo()==14){
            LV4(id);
        }else{
            MoverRnd(id);
        }
    }

    /* Función: MoverRnd
     Dominio: Recibe el id de enemigo
     Codominio: Se utiliza el id para mover al enemigo de manera aleatoriamente
    */
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

    /* Función: LV1
     Dominio: Recibe el id de enemigo
     Codominio: Se utiliza el id para mover al enemigo, si el villano se encuentra en la misma fila o columna que se
     encuentra el héroe, persiguirá al héroe.
    */
    public static void LV1(int id){
        //héroe
        int x = Heroe.GetPosX();
        int y = Heroe.GetPosY();
        if(x==0 && y==0){
            Heroe.SetPosX(1);
            Heroe.SetPosY(1);
        }
        //villano
        int ex = Nivel.list_villano.get(id).getPosX();
        int ey =  Nivel.list_villano.get(id).getPosY();
        if(ex == x ){
            if(ey>y){
                if(Tablero.tipoCuadrado(ex,ey-1)){
                    Nivel.list_villano.get(id).setPosY(ey-1);
                }else{
                    MoverRnd(id);
                }
            }else{
                if(Tablero.tipoCuadrado(ex,ey+1)){
                    Nivel.list_villano.get(id).setPosY(ey+1);
                }else{
                    MoverRnd(id);
                }
            }
        }else if(ey == y){
            if(ex>x){
                if(Tablero.tipoCuadrado(ex-1,ey)){
                    Nivel.list_villano.get(id).setPosX(ex-1);
                }else{
                    MoverRnd(id);
                }
            }else{
                if(Tablero.tipoCuadrado(ex+1,ey)){
                    Nivel.list_villano.get(id).setPosX(ex+1);
                }else{
                    MoverRnd(id);
                }
            }
        }else{
            MoverRnd(id);
        }
    }

    /* Función: LV2
     Dominio: Recibe el id de enemigo
     Codominio: Se utiliza el id para mover al enemigo, si la probabilidad es menor que 75, se moverá random
     y en el caso contrario persigue al héroe.
    */
    public static void LV2(int id){
        Random ram = new Random();
        int x = ram.nextInt(100);
        if(x <75){
            MoverRnd(id);
        }else{
            LV4(id);
        }
    }

        /* Función: LV3
         Dominio: Recibe el id de enemigo
         Codominio: Se utiliza el id para mover al enemigo, persigue al héroe si está a menos de
         30 cuadrados del héroe.
    */
    public static void LV3(int id){
        int x = Heroe.GetPosX();
        int y = Heroe.GetPosY();
        if(x==0 && y==0){
            Heroe.SetPosX(1);
            Heroe.SetPosY(1);
        }
        //villano
        int ex = Nivel.list_villano.get(id).getPosX();
        int ey =  Nivel.list_villano.get(id).getPosY();

        ArrayList<Integer> caminito = new ArrayList<Integer>();

        if(caminito.size()<60){
            Nivel.list_villano.get(id).setPosX(caminito.get(2));
            Nivel.list_villano.get(id).setPosY(caminito.get(3));
        }else{
            MoverRnd(id);
        }

    }

    /* Función: LV4
     Dominio: Recibe el id de enemigo
     Codominio: Siempre persigue a nuestro héroe
    */
    public static void LV4(int id){
        int x = Heroe.GetPosX();
        int y = Heroe.GetPosY();
        if(x==0 && y==0){
            Heroe.SetPosX(1);
            Heroe.SetPosY(1);
        }
        //villano
        int ex = Nivel.list_villano.get(id).getPosX();
        int ey =  Nivel.list_villano.get(id).getPosY();

        int ini = ex*Juego.Ancho+ey;
        int fin = x*Juego.Ancho+y;

        ArrayList<Integer> caminito = new ArrayList<Integer>();

        caminito = Ruta.buscarRuta(ini,fin);

        if(Tablero.Mapa[caminito.get(2)][caminito.get(3)] != '-' || caminito.size()<6){
            MoverRnd(id);
        }else{
            Nivel.list_villano.get(id).setPosX(caminito.get(2));
            Nivel.list_villano.get(id).setPosY(caminito.get(3));
        }
    }
}

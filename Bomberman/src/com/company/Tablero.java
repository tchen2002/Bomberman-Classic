package com.company;

import java.util.*;

public class Tablero {
    public static char[][] Mapa;
    int Largo, Ancho,ProbaLadrillo;
    public static boolean estado_cupon=false;

    public Tablero(int Largo,int Ancho,int ProbaLadrillo){
        this.Largo = Largo;
        this.Ancho = Ancho;
        this.ProbaLadrillo = ProbaLadrillo;
        Mapa = new char[Largo][Ancho];
        //llenarMatriz();
    }

    public void llenarMatriz(){
        for (int i = 0; i < Largo; i++) {
            for (int j = 0; j < Ancho; j++) {
                if((i==1 && j==1) || (i==1 && j==2) || (i==2 && j==1)){
                    Mapa[i][j] = '-';
                }else if( (i==0) || (j==0) || (i== (Largo-1)) || (j==(Ancho-1)) ){
                    Mapa[i][j] = 'A';
                }else if( (i%2==0) && (j%2==0)){
                    Mapa[i][j] = 'A';
                }else{
                    if(generarRandom()){
                        Mapa[i][j]='L';
                    }else{
                        Mapa[i][j] = '-';
                    }
                }
            }
        }
    }

    public boolean generarRandom(){
        boolean res;
        double num;
        Random rand = new Random();
        num = rand.nextInt(100);
        res= !(num > ProbaLadrillo);
        return res;
    }

    public static boolean tipoCuadrado(int i, int j){
        if(Mapa[i][j] == 'A' || Mapa[i][j] == 'L'){
            return false;
        }
        if(!Juego.list_bomba.isEmpty()){
            for(int c=0;c<Juego.list_bomba.size();c++){
                if(Juego.list_bomba.get(c).getPosX() == i && Juego.list_bomba.get(c).getPosY() == j){
                    return false;
                }
            }
        }
        return true;
    }

    public static int colision(int i, int j,int dir){
        if (dir == 0) {  i -= 1;  }

        if (dir == 1) {  i += 1;  }

        if (dir == 2) {  j -= 1;  }

        if (dir == 3) {  j += 1;  }

        if(Mapa[i][j] == 'A' || Mapa[i][j] == 'L'){
            if((CuponDorado.AtraviesaMuro() && Mapa[i][j] == 'L')){
               return 0;
            }
            return 1;
        }

        if(!CuponDorado.AtraviesaBomba()){
            if(!Juego.list_bomba.isEmpty()){
                for(int c=0;c<Juego.list_bomba.size();c++){
                    if(Juego.list_bomba.get(c).getPosX() == i && Juego.list_bomba.get(c).getPosY() == j){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public static ArrayList<Bomba> explosion(int i, int j,int largo){
        ArrayList<Bomba> list_pos = new ArrayList<Bomba>();
        list_pos.add(new Bomba(i,j));
        for (int cont = 1;cont<=largo;cont++) {
            if (Mapa[i + cont][j] != 'A') {
                list_pos.add(new Bomba(i + cont,j));
                if(Mapa[i + cont][j] == 'L'){
                    Mapa[i + cont][j] = '-';
                    break;
                }
                Mapa[i + cont][j] = '-';
            }else{
                break;
            }
        }

        for (int cont = 1;cont<=largo;cont++) {
            if (Mapa[i-cont][j] != 'A'){
                list_pos.add(new Bomba(i - cont,j));
                if( Mapa[i-cont][j] == 'L'){
                    Mapa[i-cont][j] = '-';
                    break;
                }
                Mapa[i - cont][j] = '-';
            }else{
                break;
            }
        }

        for (int cont = 1;cont<=largo;cont++) {
            if (Mapa[i][j+ cont] != 'A'){
                list_pos.add(new Bomba(i,j+cont));
                if( Mapa[i][j+ cont] == 'L'){
                    Mapa[i][j+ cont] = '-';
                    break;
                }
                Mapa[i][j+ cont]= '-';
            }else{
                break;
            }
        }

        for (int cont = 1;cont<=largo;cont++) {
            if (Mapa[i][j-cont] != 'A') {
                list_pos.add(new Bomba(i,j-cont));
                if( Mapa[i][j-cont] == 'L'){
                    Mapa[i][j-cont] = '-';
                    break;
                }
                Mapa[i][j-cont] = '-';
            }else{
                break;
            }
        }

        for(int f=0;f<list_pos.size();f++){
            int x=Nivel.cupon.getPosX();
            int y=Nivel.cupon.getPosY();
            if(list_pos.get(f).getPosX() == x && list_pos.get(f).getPosY()==y && !Nivel.cupon.getEstado()){
                Timer time = new Timer();
                time.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        estado_cupon=true;
                    }
                } ,4000);
            }
        }
        return(list_pos);
    }

    public char[][] getMapa() {
        return Mapa;
    }

    public void setMapa(char[][] mapa) {
        Mapa = mapa;
    }



}



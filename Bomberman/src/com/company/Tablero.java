package com.company;

import java.util.*;

public class Tablero {
    public static char[][] Mapa;
    int Largo, Ancho,ProbaLadrillo;

    public Tablero(int Largo,int Ancho,int ProbaLadrillo){
        this.Largo = Largo;
        this.Ancho = Ancho;
        this.ProbaLadrillo = ProbaLadrillo;
        this.Mapa = new char[Largo][Ancho];
        llenarMatriz();
    }

    public void llenarMatriz(){
        for (int i = 0; i < Largo; i++) {
            for (int j = 0; j < Ancho; j++) {
                if((i==1 && j==1) || (i==1 && j==2) || (i==2 && j==1)){
                    this.Mapa[i][j] = '-';
                }else if( (i==0) || (j==0) || (i== (Largo-1)) || (j==(Ancho-1)) ){
                    this.Mapa[i][j] = 'A';
                }else if( (i%2==0) && (j%2==0)){
                    this.Mapa[i][j] = 'A';
                }else{
                    if(generarRandom()){
                        this.Mapa[i][j]='L';
                    }else{
                        this.Mapa[i][j] = '-';
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
        if(num>ProbaLadrillo)  res=false;
        else res=true;
        return res;
    }
    public static boolean tipoCuadrado(int i, int j){
        if(Mapa[i][j] == 'A' || Mapa[i][j] == 'L'){
            return false;
        }
        return true;
    }

    public static int colision(int i, int j,int dir){
        if (dir == 0) {  i -= 1;  }

        if (dir == 1) {  i += 1;  }

        if (dir == 2) {  j -= 1;  }

        if (dir == 3) {  j += 1;  }

        if(Mapa[i][j] == 'A' || Mapa[i][j] == 'L'){
            return 1;
        }

        return 0;

    }

    public static ArrayList<Bomba> explosion(int i, int j,int largo){
        ArrayList<Bomba> list_pos = new ArrayList<Bomba>();
        list_pos.add(new Bomba(i,j));
        System.out.println("HELLOPOSX"+list_pos.get(0).getPosX()+"POSY"+list_pos.get(0).getPosY());
        for (int cont = 1;cont<=largo;cont++) {
            if (Mapa[i + cont][j] != 'A') {
                Mapa[i + cont][j] = '-';
                list_pos.add(new Bomba(i + cont,j));
            }else{
                break;
            }
        }

        for (int cont = 1;cont<=largo;cont++) {
            if (Mapa[i-cont][j] != 'A'){
                Mapa[i-cont][j]= '-';
                list_pos.add(new Bomba(i - cont,j));
            }else{
                break;
            }
        }

        for (int cont = 1;cont<=largo;cont++) {
            if (Mapa[i][j+ cont] != 'A'){
                Mapa[i][j+ cont]= '-';
                list_pos.add(new Bomba(i,j+cont));
            }else{
                break;
            }
        }

        for (int cont = 1;cont<=largo;cont++) {
            if (Mapa[i][j-cont] != 'A') {
                Mapa[i][j-cont] = '-';
                list_pos.add(new Bomba(i,j-cont));
            }else{
                break;
            }
        }
        /*
        System.out.println("LARGO"+list_pos.size());
        for (int c=0;c<list_pos.size();c++) {
            System.out.println("POSX"+list_pos.get(c).getPosX()+"POSY"+list_pos.get(c).getPosY());
        }*/
        return(list_pos);
    }

    public char[][] getMapa() {
        return Mapa;
    }

    public void setMapa(char[][] mapa) {
        Mapa = mapa;
    }



}



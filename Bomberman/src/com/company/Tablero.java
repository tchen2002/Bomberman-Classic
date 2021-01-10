package com.company;
import java.util.*;

public class Tablero {
    static char[][] Mapa;
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
        for (int i = 0; i < Largo; i++) {
            for (int j = 0; j < Ancho; j++) {
                System.out.print(this.Mapa[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public boolean generarRandom(){
        boolean res;
        double num;
        Random rand = new Random();
        num = rand.nextInt(100);
        System.out.println(num);
        System.out.println(ProbaLadrillo);
        if(num>ProbaLadrillo)  res=false;
        else res=true;
        return res;
    }

    public char[][] getMapa() {
        return Mapa;
    }

    public void setMapa(char[][] mapa) {
        Mapa = mapa;
    }

    public static char TipoCuadrado(int x,int y){
        System.out.println(Mapa[0][0]);
        return Mapa[x][y];
    }
}



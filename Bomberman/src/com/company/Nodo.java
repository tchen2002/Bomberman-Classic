package com.company;

import java.util.ArrayList;

public class Nodo {

    private int Indice, NumNodo,PosX,PosY;
    private ArrayList<Integer> Arr_Pos = new ArrayList<Integer>();

    /* Función: Nodo
         Dominio: Recibe el índice, numNodo, posX, posY y arr_pos
         Codominio: Constructor que llama a las variables de la clase que se vaya a utilizar
    */
    public Nodo(int indice, int numNodo, int posX, int posY, ArrayList<Integer> arr_Pos) {
        Indice = indice;
        NumNodo = numNodo;
        PosX = posX;
        PosY = posY;
        Arr_Pos = arr_Pos;
    }

    /* --------------------------------------------------
                  Getters y Setters
   --------------------------------------------------
    */
    public int getIndice() {
        return Indice;
    }

    public void setIndice(int indice) {
        Indice = indice;
    }

    public int getNumNodo() {
        return NumNodo;
    }

    public void setNumNodo(int numNodo) {
        NumNodo = numNodo;
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

    public void setPosY(int posY) {
        PosY = posY;
    }

    public ArrayList<Integer> getArr_Pos() {
        return Arr_Pos;
    }

    public void setArr_Pos(ArrayList<Integer> arr_Pos) {
        Arr_Pos = arr_Pos;
    }
}

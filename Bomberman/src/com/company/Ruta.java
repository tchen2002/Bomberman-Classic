package com.company;

import java.util.*;

public class Ruta {

    private static ArrayList<Integer> cam = new ArrayList<Integer>();

    private static final int MAX = Tablero.CantEspacio();
    private static int INF = (int) 1e7;
    private static int [][]dist = new int[MAX][MAX];
    private static int [][]matriz_recorrido = new int[MAX][MAX];

    private static final char [][] matriz_copia = Tablero.Mapa;

    private static final int LargoT = Juego.Largo;
    private static final int AnchoT = Juego.Ancho;

    private static int Ini, Fin;

    private static ArrayList<Nodo> list_nodos = new ArrayList<Nodo>();

    //Constructor Ruta
    public Ruta() {
        llenarArreglo();
        inicializar_matriz_distancia();
        floydWarshall();
    }

    /*
    Función: buscarRuta
    Dominio: recibe dos parámetros ini y fin
    Codominio: En esta función, llamar a la función de encontrar la ruta más corta */
    public static ArrayList<Integer> buscarRuta(int Ini,int Fin){

        int ini_ = retornaPos(Ini);
        int fin_ = retornaPos(Fin);

        encontrar_ruta(ini_,fin_);

        ArrayList<Integer> caminito_final = new ArrayList<Integer>();

        for(int i=0;i<cam.size();i++){
            int x = list_nodos.get(cam.get(i)).getPosX();
            int y =  list_nodos.get(cam.get(i)).getPosY();
            caminito_final.add(x);
            caminito_final.add(y);
        }

        for(int i=0;i<caminito_final.size();i++){
            System.out.println("H"+caminito_final.get(i));
        }

        return caminito_final;
    }


    /*Función: llenarArreglo
        Dominio: No recibe ningún parámetro
        Codominio: En esta función se verifica cuáles son los nodos que están conectados con
        		   al menos un nodo, y se almacenará sus datos.  Por otro lado, se pone -1
     			    en la matriz de recorrido cuando no se sabe cuál es la distancia entre los nodos*/
    public static void llenarArreglo(){
        int nodo;
        int cont=0;
        for(int j=0;j < LargoT;j++){
            for(int k=0;k < AnchoT;k++){
                nodo= j*AnchoT + k;
                if(matriz_copia[j][k] == '-' || matriz_copia[j][k] == 'L' ){
                    ArrayList<Integer> arr_pos = new ArrayList<Integer>();
                    if(matriz_copia[j-1][k] == '-' || matriz_copia[j-1][k] == 'L'){
                        arr_pos.add(((j-1)*AnchoT)+k);
                    }
                    if(matriz_copia[j+1][k] == '-' || matriz_copia[j+1][k] == 'L'){
                        arr_pos.add(((j+1)*AnchoT)+k);
                    }
                    if(matriz_copia[j][k-1] == '-' || matriz_copia[j][k-1] == 'L'){
                        arr_pos.add((j*AnchoT)+(k-1));
                    }
                    if(matriz_copia[j][k+1] == '-'|| matriz_copia[j][k+1] == 'L'){
                        arr_pos.add((j*AnchoT)+(k+1));
                    }
                    list_nodos.add(new Nodo(cont,nodo,j,k,arr_pos));
                cont++;
                }
            }
        }
        for(int i = 0; i < MAX; i++){
            for(int j = 0; j < MAX; j++){

                if(dist[i][j]==INF){
                    matriz_recorrido[i][j]= -1;
                }else{
                    matriz_recorrido[i][j]=j;
                }
            }
        }

    }

    /*Función: retornarpos
      Dominio: Recibe un número entero, que es el índice del nodo
      Codominio: Retorna un número entero, que buscará cuál es el número que lo representa*/
    public static int retornaPos(int n){
        int i=0;
        while(i<list_nodos.size()){
            int p= list_nodos.get(i).getNumNodo();
            if(p==n){

                return i;
            }
            i++;
        }
        return 0;
    }

    /*
    Función: inicializar_matriz_distancia
    Dominio: No recibe ningún parámetro
    Codominio: En esta función, inicializa la matriz de adyacencia
           Se pone 0 cuando la distancia es del propio nodo
           En este caso, se pone 1 en aquellos nodos que están conectados
           Se pone inf, si no están relacionados*/
    public static void inicializar_matriz_distancia(){
        int p,pos;
        for(int i=0;i<MAX;i++){
            for(int j=0;j<MAX;j++){
                if ((i == j)) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for(int i=0;i<list_nodos.size();i++){
            for(int a=0;a<list_nodos.get(i).getArr_Pos().size();a++){
                pos=list_nodos.get(i).getArr_Pos().get(a);
                p = retornaPos(pos);
                int c= list_nodos.get(i).getIndice();
                dist[p][c]=1;
            }
        }
  }

    /*
    Función: encontrar_ruta
    Dominio: recibe dos parámetros ini y fin
    Codominio: En esta función, inicializa la matriz de adyacencia
           Se pone 0 cuando la distancia es del propio nodo
           En este caso, se pone 1 en aquellos nodos que están conectados
           Se pone inf, si no están relacionados */
    public static ArrayList<Integer> encontrar_ruta(int ini,int fin){

        if (matriz_recorrido[ini][fin] == -1)
            return null;

        ArrayList<Integer> camino = new ArrayList<Integer>();

        camino.add(ini);

        while (ini != fin){
            ini = matriz_recorrido[ini][fin];
            camino.add(ini);
        }

        cam = camino;
        return camino;
    }

    /*
    Función: floyd_warshall
    Dominio: No recibe ningún parámetro
    Codominio: En este caso el conjunto de vértices esta numerados de 0 a 239
               y existe dos opciones para recorrerlo, el primero sería solo utilizar los
               vértices del conjunto 1 a k, y luego se verifica desde i hasta k+1, y
               finalmente de j hasta k, y se verifica si la distancia actual dist[i][j]
               es mayor que la distancia dist[i][k] + dist[k][j], si es verdad se actualiza en  DIST[i][j] */
    public static void floydWarshall(){
        for(int k = 0; k < MAX; k++) {
            for(int i = 0; i < MAX; i++) {
                for(int j = 0; j < MAX; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF)
                        continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        matriz_recorrido[i][j] = matriz_recorrido[i][k];
                    }
                }
            }
        }
    }

}



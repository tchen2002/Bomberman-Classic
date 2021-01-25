package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Nivel {

    public static ArrayList<Villano> list_villano_inicial = new ArrayList<Villano>();
    public static ArrayList<CuponDorado> list_cupon = new ArrayList<CuponDorado>();
    private static final double euler = 2.71828;
    public static ArrayList<Villano> list_villano = new ArrayList<Villano>();
    public static ArrayList<Villano> list_villano_copia = new ArrayList<Villano>();
    public static int Nivel_, CantVillano;
    private Villano villano;
    public static Puerta puerta;
    public static CuponDorado cupon;

    public Nivel(int nivel,int cantVillano){
        Nivel_ = nivel;
        CantVillano=cantVillano;
        VillanoDefault();
        llenarArregloCupon();
    }

    public void iniciar(){
        list_villano.clear();
        list_villano_copia.clear();
        ENEMIGOS(getNivel());
        list_villano_copia = list_villano;
        generarPuerta();
        generarCupon(getNivel());
        System.out.println("NIVEL");
        System.out.println(getNivel());
        System.out.println("Puerta");
        System.out.println(puerta.getPosX());
        System.out.println(puerta.getPosY());
        System.out.println("CUPON");
        System.out.println(cupon.getPosX());
        System.out.println(cupon.getPosY());
    }

    public void ENEMIGOS(int n){
        int x,y;
        ArrayList<Double> rango = new ArrayList<Double>();
        rango =  CalcularRango(n);
        for(int i=0;i<getCantVillano();i++){
            int Dir=GenerarRandom(4);
            if(n==1){
                llenarArreglo(i,Dir,0);
            }else{
                int cont =0;
                for(int j=0;j<rango.size();j++){
                    double proba = GenerarRandomDouble();
                    if(rango.get(j)==1.00){
                        cont =6;
                        break;
                    }else if(proba<rango.get(j)){
                        break;
                    }else{
                        cont++;
                    }
                }
                llenarArreglo(i,Dir,cont);
            }
        }

    }

    public Double poisson(int lambda, int k){
        double res;
        res = (Math.pow(euler,-lambda) * Math.pow(lambda, k)) / factorial(k);
        String strDouble = String.format("%.5f", res);
        return Double.parseDouble(strDouble);
    }

    public ArrayList<Double> CalcularRango(int n){
        ArrayList<Double> rango = new ArrayList<Double>();
        int k=calNumVillanoPosible(n);
        for(int i=1;i<=k;i++){
            if(i>getCantVillano()){
                rango.add(1.00);
            }else{
                rango.add(poisson(n,i));
            }
        }
        return rango;
    }

    public int calNumVillanoPosible(int n){
        int k=0;
        for(int i=0;i<list_villano_inicial.size();i++){
            if(list_villano_inicial.get(i).getNiv_ini() <= n){
                k++;
            }else{
                break;
            }
        }
        return k-1;
    }

    public int factorial(int n){
        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
    }

    public void generarPuerta(){
        int x,y;
        x=GenerarRandom(Juego.Largo-1);
        y=GenerarRandom(Juego.Ancho-1);
        while(Tablero.Mapa[x][y] != 'L'){
            x=GenerarRandom(Juego.Largo-1);
            y=GenerarRandom(Juego.Ancho-1);
        }
        puerta = new Puerta(x,y,false);
    }

    public void generarCupon(int Nivel){
        int x,y;
        boolean flag;
        flag=false;
        x=GenerarRandom(Juego.Largo-1);
        y=GenerarRandom(Juego.Ancho-1);
        while(Tablero.Mapa[x][y] != 'L'){
            x=GenerarRandom(Juego.Largo-1);
            y=GenerarRandom(Juego.Ancho-1);
            if (x == puerta.getPosX() && y== puerta.getPosY()) {
                x=GenerarRandom(Juego.Largo-1);
                y=GenerarRandom(Juego.Ancho-1);
            }
        }
        for(int i=0;i<list_cupon.size();i++){
            if(Nivel == list_cupon.get(i).getNivel()){
                 cupon = new CuponDorado(list_cupon.get(i).getCupon(),false,x,y);
                 flag=true;
                 break;
            }
        }
        if(flag==false){
            int num_random=GenerarRandom(2);
            cupon = new CuponDorado(num_random-1,false,x,y);
        }
    }

    public int GenerarRandom(int rango){
        Random ram = new Random();
        return ram.nextInt(rango);
    }

    public double GenerarRandomDouble(){
        Random rand = new Random();
        double rand_dub1 = rand.nextDouble();
        String  strDouble = String.format("%.5f", rand_dub1);
        return Double.parseDouble(strDouble);
    }

    public void llenarArreglo(int cont, int dire, int tipo){
        int x,y;
        int i=cont;
        int Dir=dire;
        Boolean est = true;
        x=GenerarRandom(Juego.Largo-1);
        y=GenerarRandom(Juego.Ancho-1);
        while((Tablero.tipoCuadrado(x,y)!=true)){
            x=GenerarRandom(Juego.Largo-1);
            y=GenerarRandom(Juego.Ancho-1);
            if ((x == 1 && y==1) || (x == 1 && y==2) || (x == 2 && y==1)) {
                x=GenerarRandom(Juego.Largo-1);
                y=GenerarRandom(Juego.Ancho-1);
            }
        }
        list_villano.add(new Villano(i,x,y,Dir,tipo,est,true));
    }

    public void llenarArregloCupon(){
        CuponDorado cuponDorado0 = new CuponDorado(0,1,true,false);
        CuponDorado cuponDorado1 = new CuponDorado(1,2,true,false);
        CuponDorado cuponDorado2 = new CuponDorado(2,3,false,false);
        CuponDorado cuponDorado3 = new CuponDorado(3,4,true,false);
        CuponDorado cuponDorado4 = new CuponDorado(4,11,false,false);
        CuponDorado cuponDorado5 = new CuponDorado(5,14,false,false);
        CuponDorado cuponDorado6 = new CuponDorado(6,19,false,false);
        CuponDorado cuponDorado7 = new CuponDorado(7,23,false,false);
        list_cupon.add(cuponDorado0);
        list_cupon.add(cuponDorado1);
        list_cupon.add(cuponDorado2);
        list_cupon.add(cuponDorado3);
        list_cupon.add(cuponDorado4);
        list_cupon.add(cuponDorado5);
        list_cupon.add(cuponDorado6);
        list_cupon.add(cuponDorado7);
    }

    public void VillanoDefault(){
        list_villano_inicial.add(new Villano(0,"Globo",1,1.5,false,1,100));
        list_villano_inicial.add(new Villano(1,"Cel",2,1,false,1,200));
        list_villano_inicial.add(new Villano(2, "Haki",3, 4/3,false,1,400));
        list_villano_inicial.add(new Villano(3,"Espon",6,1/3,true,1,1000));
        list_villano_inicial.add(new Villano(4,"Fant",8,1,true,1,2000));
        list_villano_inicial.add(new Villano(5,"Mon",11,3/2,false,1,3000));
        list_villano_inicial.add(new Villano(6,"MonG",14,3/2,true,1,4000));
    }

    public static int getNivel() {
        return Nivel_;
    }

    public static void setNivel(int nivel) {
        Nivel_ = nivel;
    }

    public int getCantVillano() {
        return CantVillano;
    }

    public void setCantVillano(int cantVillano) {
        CantVillano = cantVillano;
    }
}

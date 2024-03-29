package com.company;

import java.awt.*;

public class Villano extends Personaje{

    private int id,PosX,PosY,Direccion,Tipo;
    private boolean Estado,Camino;

    private boolean Atm;
    private int Niv_ini,IA,Punt;
    private double Velocidad;
    private String Nombre;

    /* Función: Villano
      Dominio: Id,Posiciones x,y, direccion,tipo de tipo entero, el estado y el camino de tipo booleano
      Codominio: Constructor que llama a las variables de la clase que se vaya a utilizar
*/
    public Villano(int id,int PosX, int PosY, int Direccion, int tipo, boolean Estado,boolean Camino) {
        this.id=id;
        this.PosX=PosX;
        this.PosY=PosY;
        this.Direccion=Direccion;
        this.Tipo=tipo;
        this.Estado=Estado;
        this.Camino=Camino;

    }

    /* Función: Villano
       Dominio: Recibe un tipo,nivel de inicio,inteligencia artificial y un puntaje de tipo entero, tambien
              nombre de tipo string, velocidad de tipo flotante y atm de tipo booleano
       Codominio: Inicializa variables que se van a utilizar
    */
    public Villano(int tipo,String nombre,int niv_ini,double velocidad, boolean atm, int IA,  int punt) {
        Tipo = tipo;
        Nombre = nombre;
        Atm = atm;
        Niv_ini = niv_ini;
        this.IA = IA;
        Velocidad = velocidad;
        Punt = punt;
    }

    /*
    RND 0
    LV1 1
    LV2 2
    LV3 3
    LV4 4
     */

    @Override
    public void tick(int dir) {

    }

    @Override
    public void render(Graphics g) {
    }

    /* Función: COnvertir Monedas Giratorias
       Dominio: No recibe ningún parámetro
       Codominio: cambiar el tipo de villano al de moneda giratoria y agregar más villanos del tipo moneda giratoria
*/
    public static void ConvertirMonedasG(){
        int largo=Nivel.list_villano.size();
        int cont=0;
        for(int i=largo;i<largo+Juego.CantVillano;i++){
            Nivel.list_villano.add(new Villano(i,Nivel.list_villano.get(cont).getPosX(),
                    Nivel.list_villano.get(cont).getPosY(),Nivel.list_villano.get(cont).getDireccion(),
                    6,true,
                    Nivel.list_villano.get(cont).getCamino()));
            cont++;
        }
        for(int i=0;i<Nivel.list_villano.size();i++){
            if(Nivel.list_villano.get(i).getEstado()){
                Nivel.list_villano.get(i).setTipo(6);
                //g.drawImage(list_img_villanos[6],Nivel.list_villano.get(i).getPosY()*30,Nivel.list_villano.get(i).getPosX()*30,null);
            }

        }
    }

    /* Función: Dibujar Villano
       Dominio: Recibe la grafica que permite mostrar villano
       Codominio: Se realiza un ciclo de la cantidad de villanos en el arreglo y obtienen los datos relacionados a este
    */
    public static void DibujarVillanos(Graphics g){
        for(int i=0;i<Nivel.list_villano.size();i++){
            if(Nivel.list_villano.get(i).getEstado()){
                PersonajeElement.dibujar_enemigo(g,Nivel.list_villano.get(i).getId(),Nivel.list_villano.get(i).getTipo(),Nivel.list_villano.get(i).getPosY()*30 , Nivel.list_villano.get(i).getPosX()*30);
                InteligenciaArtificial.InteliArtificial(Nivel.list_villano.get(i).getId());
            }
        }
    }


     /* --------------------------------------------------
                      Getters y Setters
       --------------------------------------------------
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getVelocidad() {
        return Velocidad;
    }

    public void setVelocidad(double velocidad) {
        Velocidad = velocidad;
    }

    public int getDireccion() {
        return Direccion;
    }

    public void setDireccion(int direccion) {
        Direccion = direccion;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }

    public boolean getCamino() {
        return Camino;
    }

    public void setCamino(boolean camino) {
        Camino = camino;
    }

    public boolean getAtm() {
        return Atm;
    }

    public void setAtm(boolean atm) {
        Atm = atm;
    }

    public int getNiv_ini() {
        return Niv_ini;
    }

    public void setNiv_ini(int niv_ini) {
        Niv_ini = niv_ini;
    }

    public int getIA() {
        return IA;
    }

    public void setIA(int IA) {
        this.IA = IA;
    }

    public int getPunt() {
        return Punt;
    }

    public void setPunt(int punt) {
        Punt = punt;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}

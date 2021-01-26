package com.company;

public class Bomba{
    private int PosX,PosY,LargoBomba,CantBomba;
    private boolean Estado;

    /* Función: Bomba
   Dominio: Posicion X y Y de tipo entero, y un estado de tipo booleano
   Codominio: Constructor que permite utilizar los variables de la clase Bomba
    */
    public Bomba(int posX, int posY, boolean Estado) {
        this.PosX = posX;
        this.PosY = posY;
        this.Estado = Estado;
    }

    /* Función: Bomba
   Dominio: Posicion X y Y de tipo entero
   Codominio: Constructor donde solo utiliza estos dos parámetros para utilizar estas dos variables
    */
    public Bomba(int posX, int posY) {
        this.PosX = posX;
        this.PosY = posY;
    }

    /* Función: Render
   Dominio: No recibe ningún parámetro
   Codominio: Llama a la función en la clase PersonajeElement donde dibuja la explosión
*/
    public static void render(){
        PersonajeElement.dibujarExplosion();
        }

    /* --------------------------------------------------
                      Getters y Setters
       --------------------------------------------------
    */
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

    public int getLargoBomba() {
        return LargoBomba;
    }

    public void setLargoBomba(int largoBomba) {
        LargoBomba = largoBomba;
    }

    public int getCantBomba() {
        return CantBomba;
    }

    public void setCantBomba(int cantBomba) {
        CantBomba = cantBomba;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }
}

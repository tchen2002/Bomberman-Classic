package com.company;

public class Puerta {

    private int PosX,PosY;
    private boolean Estado;

    /* Función: Puerta
   Dominio: Posiciones x,y de tipo entero y el estado de tipo booleano
   Codominio: Constructor que llama a las variables de la clase que se vaya a utilizar
*/
    public Puerta(int posX, int posY, boolean Estado) {
        this.PosX = posX;
        this.PosY = posY;
        this.Estado = Estado;
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

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        Estado = estado;
    }
}

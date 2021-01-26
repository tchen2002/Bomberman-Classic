package com.company;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Observer {
    private static int Puntos_globales;
    private static int pts=0;

    /* Función: Observer
       Dominio: Recibe un entero con los puntos globales
       Codominio: Constructor que llama a las variables de la clase que se vaya a utilizar
*/
    public Observer(int puntos_globales) {
        Puntos_globales = puntos_globales;
    }

    /* Función: PasarSiguienteNivel
       Dominio: No recibe ningún parámetro
       Codominio: Retorna true si encuentra la puerta y todos los villanos han muerto
                  de lo contrario retorna false
*/
    public static boolean PasarSiguienteNivel(){
        SumarPuntajes();
        boolean res=false;
        for(int i=0;i<Nivel.list_villano.size();i++){
              if(Nivel.list_villano.get(i).getEstado()){
                  return false;
              }
        }
        if(Heroe.EncontrarPuerta()){
            res=true;
        }
        return res;
    }

    /* Función: SumarPuntajes
       Dominio: No recibe ningún parámetro
       Codominio: Si los puntos son mayores o iguales a 100000 se le da una vida más al heroe
*/
    public static void SumarPuntajes(){
        if(pts>=100000){
            Heroe.setVida(Heroe.getVida()+1);
            pts-=100000;
        }
    }

    /* Función: Verificar Colision
       Dominio: No recibe ningún parámetro
       Codominio: Verifica si un villano está en la misma posición que nuestro heroe
*/
    public static void VerificarColision(){
        for(int i=0;i<Nivel.list_villano.size();i++){
            if(Heroe.GetPosX() ==Nivel.list_villano.get(i).getPosX() &&  Heroe.GetPosY() ==Nivel.list_villano.get(i).getPosY() && Nivel.list_villano.get(i).getEstado() ){
                for(int c=0;c<Nivel.list_villano.size();c++){
                    Nivel.list_villano.get(c).setEstado(false);
                }
                Heroe.setVida(Heroe.getVida()-1);
                Juego.morir();
            }
        }
    }

    /* Función: Verificar Explosion
       Dominio: Recibe una lista de bombas de tipo arreglo
       Codominio: Verifica si un héroe se encuentra en las coordenas de explosión
*/
    public static void VerificarExplosion(ArrayList<Bomba> lista){
        VerificarExplosionVillano(lista);
        if(Nivel.getNivel()==19){
            if(!CuponDorado.flag_60s){
                VerificarExplosionHeroe(lista);
            }
        }else if(Nivel.getNivel()==23){
            if(!CuponDorado.HombreLlama()){
                VerificarExplosionHeroe(lista);
            }
        }else{
            VerificarExplosionHeroe(lista);
        }
    }


    /* Función: VerificarExplosionHeroe
       Dominio: Recibe una lista de bombas de tipo arreglo
       Codominio: Verifica que el héroe no este en el rango de la explosión
    */
    public static void VerificarExplosionHeroe(ArrayList<Bomba> lista){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getPosX() == Heroe.GetPosX() && lista.get(i).getPosY() == Heroe.GetPosY()){
                Heroe.setVida(Heroe.getVida()-1);
                 for(int c=0;c<Nivel.list_villano.size();c++){
                    Nivel.list_villano.get(c).setEstado(false);
                }
                Juego.morir();
            }
        }
    }

    /* Función: VerificarExplosionVillano
       Dominio: Recibe una lista de bombas de tipo arreglo
       Codominio: Verifica si el villano está dentro del rango de explosión de la bomba
*/
    public static void VerificarExplosionVillano(ArrayList<Bomba> lista){
        ArrayList<Integer> puntos = new ArrayList<Integer>();
        ArrayList<Integer> m_villanos = new ArrayList<Integer>();
        for(int i=0;i<lista.size();i++){
            for(int j=0; j<Nivel.list_villano.size();j++){
                if(lista.get(i).getPosX() == Nivel.list_villano.get(j).getPosX() && lista.get(i).getPosY() == Nivel.list_villano.get(j).getPosY()){
                    puntos.add(Nivel.list_villano_inicial.get(Nivel.list_villano.get(j).getTipo()).getPunt());
                    m_villanos.add(lista.get(i).getPosX());
                    m_villanos.add(lista.get(i).getPosY());
                    Nivel.list_villano.get(j).setEstado(false);
                    if(CantRestanteVillano()==0){
                        Timer time = new Timer();
                        time.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Sonido sonidoUltimoVillano = new Sonido();
                                sonidoUltimoVillano.play("Img/ultimo.mp3");
                            }
                        } ,2000);
                    }
                }
            }
        }
        int Puntos_villanos = SumarPuntosVillanos(puntos);
        setPuntos_globales(getPuntos_globales()+Puntos_villanos);
        pts+=Puntos_villanos;
        PersonajeElement.dibujar_villanos_muertos(m_villanos);
    }

    /* Función: VerificarExplosionPuerta
       Dominio:  Recibe una lista de bombas de tipo arreglo
       Codominio: Verifica si dentro del rango de explosión se encuentra la puerta
*/
    public static void VerificarExplosionPuerta(ArrayList<Bomba> lista){
        int x = Nivel.puerta.getPosX();
        int y = Nivel.puerta.getPosY();
        for(int i=0;i<lista.size();i++){
            int cont=0;
            if(x==lista.get(i).getPosX() && y==lista.get(i).getPosY() && Tablero.Mapa[x][y] == '-'){
                if(Nivel.puerta.getEstado()){
                    int largo = Nivel.list_villano.size();
                    for(int j=largo;j<largo+Juego.CantVillano;j++){
                        Nivel.list_villano.add(new Villano(j,Nivel.list_villano.get(cont).getPosX(),
                                Nivel.list_villano.get(cont).getPosY(),Nivel.list_villano.get(cont).getDireccion(),
                                Nivel.list_villano.get(cont).getTipo(),Nivel.list_villano.get(cont).getEstado(),
                                Nivel.list_villano.get(cont).getCamino()));
                        cont++;
                    }
                }
            }
        }
    }

    /* Función: VerificarExplosionCupon
       Dominio:  Recibe una lista de bombas de tipo arreglo
       Codominio: Verifica que ningún cupón esté en el rango de la bomba, si lo está pierde el cupón
*/
    public static void VerificarExplosionCupon(ArrayList<Bomba> lista){
        int x = Nivel.cupon.getPosX();
        int y = Nivel.cupon.getPosY();
        for(int i=0;i<lista.size();i++){
            if(x==lista.get(i).getPosX() && y==lista.get(i).getPosY() && Tablero.Mapa[x][y] == '-' && !Nivel.cupon.getEstado() && Tablero.estado_cupon){
                Nivel.cupon.setEstado(true);
            }
        }
    }

    /* Función: SumarPuntosVillanos
       Dominio: Recibe una lista de enteros
       Codominio: calcular puntos obtenidos después de una explosión
    */
    public static int SumarPuntosVillanos(ArrayList<Integer> lista){
        int puntos=0;
        for(int i=1;i<=lista.size();i++){
            puntos += (lista.get(i-1) * i);
        }
        return puntos;
    }

    /* Función:CantRestanteVillano
        Dominio: No recibe ningún parámetro
        Codominio: retorna la cantidad de villanos que queda en el juego
*/
    public static int CantRestanteVillano(){
        int cont=0;
        for(int i=0; i<Nivel.list_villano.size();i++){
            if(Nivel.list_villano.get(i).getEstado()){
                cont++;
            }
        }
        return cont;
    }

    /* Función: Gameover
       Dominio: No recibe ningún parámetro
       Codominio: Verifica que se haya perdido cuando tiene 0 vidas el heroe
*/
    public static boolean GameOver(){
        return Heroe.getVida() <= 0;
    }

    /* Función: Queda Villano
       Dominio: No recibe ningún parámetro
       Codominio: retorna un valor de tipo boolean, true si ya no queda ninguno, y false si aún hay enemigos en el juego
*/
    public static boolean QuedaVillano(){
        for(int j=0; j<Nivel.list_villano.size();j++){
            if(Nivel.list_villano.get(j).getEstado()){
                return false;
            }
        }
        return true;
    }

    public static int getPuntos_globales() {
        return Puntos_globales;
    }

    public static void setPuntos_globales(int puntos_globales) {
        Puntos_globales = puntos_globales;
    }
}

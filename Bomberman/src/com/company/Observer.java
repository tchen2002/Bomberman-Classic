package com.company;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Observer {

    public static void VerificarColision(){
        for(int i=0;i<Nivel.list_villano.size();i++){
            System.out.println("VIDA"+Heroe.getVida());
            if(Heroe.GetPosX() ==Nivel.list_villano.get(i).getPosX() &&  Heroe.GetPosY() ==Nivel.list_villano.get(i).getPosY()){
                Heroe.setVida(Heroe.getVida()-1);
            }
        }
    }

    public static void VerificarExplosion(ArrayList<Bomba> lista){
        VerificarExplosionVillano(lista);
        VerificarExplosionHeroe(lista);
    }

    public static void VerificarExplosionHeroe(ArrayList<Bomba> lista){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getPosX() == Heroe.GetPosX() && lista.get(i).getPosY() == Heroe.GetPosY()){
                Heroe.setVida(Heroe.getVida()-1);
            }
        }
    }

    public static void VerificarExplosionVillano(ArrayList<Bomba> lista){
        for(int i=0;i<lista.size();i++){
            for(int j=0; j<Nivel.list_villano.size();j++){
                if(lista.get(i).getPosX() == Nivel.list_villano.get(j).getPosX() && lista.get(i).getPosY() == Nivel.list_villano.get(j).getPosY()){
                    Nivel.list_villano.get(j).setEstado(false);
                }
            }
        }
    }


    public static void GameOver(){
        if(Heroe.getVida()<=0){
            System.out.println("GAMEOVER");
        }
    }

    public static boolean QuedaVillano(){
        for(int j=0; j<Nivel.list_villano.size();j++){
            if(Nivel.list_villano.get(j).getEstado()){
                return false;
            }
        }
        return true;
    }


}

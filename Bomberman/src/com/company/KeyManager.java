package com.company;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class KeyManager implements KeyListener, ActionListener {

    private final boolean[] keys;
    public boolean up, down, left, right;

    public KeyManager(){
        keys = new boolean[256];
    }

    /* Función: Tick
       Dominio: No recibe ningún parámetro
       Codominio: Reconoce cuál tecla se está presionando
*/
    public void tick(){
        up=keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN] ;
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT] ;
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT] ;
    }

    /* Función: KeyPressed
       Dominio: El evento de la tecla que se esta presionando
       Codominio: Según la tecla que se presione va a llamar a una función de otra clase
*/
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if(keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
            Heroe.mover(0);
        }
        if(keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]){
            Heroe.mover(1);
        }
        if(keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]){
            Heroe.mover(2);
        }

        if(keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]){
            Heroe.mover(3);
        }

        if(keys[KeyEvent.VK_X] || keys[KeyEvent.VK_SHIFT]){
            if(Heroe.getCantBomba()>0){
                Heroe.ColocarBomba();
                Heroe.setCantBomba(Heroe.getCantBomba()-1);
            }
        }

        if(keys[KeyEvent.VK_ENTER]){
            Juego.pausa= Juego.pausa != true;

        }

        if(keys[KeyEvent.VK_SPACE]){
            //Detona la bomba que lleva mas tiempo
            if(Nivel.list_cupon.get(2).getActivo()){
                if(!Juego.list_bomba.isEmpty()){
                    CuponDorado.Detonador();
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener, ActionListener {

    private boolean[] keys;
    public boolean up, down, left, right;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        up=keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN] ;
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT] ;
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT] ;
    }

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

        if(keys[KeyEvent.VK_X]){
            Heroe.ColocarBomba();
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
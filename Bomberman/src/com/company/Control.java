package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener {

    private boolean[] keys = new boolean[256];
    public boolean up, down, left, right;


    public void tick(){
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP] ;
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN] ;
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT] ;
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT] ;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        System.out.println("pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}

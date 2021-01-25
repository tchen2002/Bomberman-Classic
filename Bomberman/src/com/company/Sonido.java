package com.company;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sonido {
    private static Player player = null;
    private String Sonido;
    public Sonido(){
    }

    public static void play(String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File(s);
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream stream = new BufferedInputStream(fis);
                    player = new Player(stream);
                    player.play();
                } catch (Exception e) {

                    // TODO: handle exception
                }
            }
        }).start();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.close();
    }
}
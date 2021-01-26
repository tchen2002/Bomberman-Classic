package com.company;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/* Función principal Main llama a la clase jugar para iniciar el juego
 Se inicializa la musica que va a sonar durante el juego */
public class Main {
    static Player player = null;


    public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
        Juego juego = new Juego();
        juego.start();

        Sonido sonidoBG = new Sonido();
        sonidoBG.play("Img/bgmusic.mp3");
    }



}

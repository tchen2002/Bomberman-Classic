package com.company;

import sun.audio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Sonido{

    public static void ReproducirSonido(String nombreSonido) throws IOException, UnsupportedAudioFileException {

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
        AudioPlayer.player.start(audioInputStream);
        Timer time = new Timer();

        time.schedule(new TimerTask() {
            @Override
            public void run() {
                AudioPlayer.player.stop(audioInputStream);
            }
        } ,1300);
    }
}

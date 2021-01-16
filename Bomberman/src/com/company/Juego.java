package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Juego implements Runnable, ActionListener {


    static ArrayList<Bomba> list_bomba = new ArrayList<Bomba>();
    static ArrayList<Bomba> list_bomba_explosion = new ArrayList<Bomba>();
    private PersonajeElement personajeElement;
    private GamePanel gamePanel;
    private KeyManager keyManager;
    private Heroe heroe;

    private int CantVillano,Velocidad;
    public static int Largo,Ancho, ProbaLadrillo;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    public static Graphics g;

    public Juego(){
        leerDatos("Img/archiConf.txt");
        Tablero tablero = new Tablero(Largo,Ancho,ProbaLadrillo);
        Nivel nivel = new Nivel(1,CantVillano);
        keyManager = new KeyManager();

    }

    private void leerDatos(String path){
        String file = loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        Largo = parseInt(tokens[0]);
        Ancho = parseInt(tokens[1]);
        ProbaLadrillo = parseInt(tokens[2]);
        CantVillano = parseInt(tokens[3]);
        Velocidad = parseInt(tokens[4]);

    }

    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null)
                builder.append(line + "\n");

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }

    private void tick(){
        keyManager.tick();
    }

    private void render(){
        bs=gamePanel.getCanvas().getBufferStrategy();
        if(bs==null){
            gamePanel.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0,0,Ancho*30,Largo*30);

        gamePanel.dibujarMapa(g);
        heroe.render(g);
        Villano.DibujarVillanos(g);
        bs.show();
        g.dispose();
    }

    public void init(){
        gamePanel = new GamePanel(Ancho*30,Largo*30);
        //gamePanel.getFrame().addKeyListener(keyManager);
        gamePanel.getFrame().addKeyListener(new KeyManager());
        personajeElement = new PersonajeElement();
        heroe = new Heroe(0,30,30,30,30,true,3,0,0);


    }

    @Override
    public void run() {
        init();

        int fps = 3;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public synchronized void stop(){
        if(!running) return;
        running = false;
        try {
            thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

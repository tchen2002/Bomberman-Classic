package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Juego implements Runnable{
    private GamePanel gamePanel;
    private int CantVillano,Velocidad;
    public static int Largo,Ancho, ProbaLadrillo;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage PruebaImagen;

    public Juego(){
        leerDatos("Img/archiConf.txt");
        Tablero tablero = new Tablero(Largo,Ancho,ProbaLadrillo);

    }

    private void leerDatos(String path){
        String file = loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        Largo = parseInt(tokens[0]);
        Ancho = parseInt(tokens[1]);
        ProbaLadrillo = parseInt(tokens[2]);
        CantVillano = parseInt(tokens[3]);
        Velocidad = parseInt(tokens[4]);
        System.out.println("largo " + Largo );
        System.out.println("ancho " + Ancho );
        System.out.println("probaLadrillo " + ProbaLadrillo );
        System.out.println("CantVillano " + CantVillano );
        System.out.println("Velocidad " + Velocidad );

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

    private void render(){
        bs=gamePanel.getCanvas().getBufferStrategy();
        if(bs==null){
            gamePanel.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0,0,Ancho*30,Largo*30);
        gamePanel.dibujarMapa(g);

        //g.setColor(Color.green);
        //g.fillRect(0,0,Ancho*30,Largo*30);

        bs.show();
        g.dispose();
    }

    public void init(){
        gamePanel = new GamePanel(Ancho*30,Largo*30);
        //PruebaImagen = GamePanel.loadImage("Img/acero.jpg");
    }

    @Override
    public void run() {
        init();

        while(running){
            render();
        }
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

}

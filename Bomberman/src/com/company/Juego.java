package com.company;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Juego implements Runnable, ActionListener {

    private static Sonido sonidoBG;

    static ArrayList<Bomba> list_bomba = new ArrayList<Bomba>();
    private PersonajeElement personajeElement;
    private GamePanel gamePanel;
    private final KeyManager keyManager;
    public static Heroe heroe;
    private final Mouse mouse;

    public static Tablero tablero;
    public static Nivel nivel;
    public static Observer observer;

    public static int CantVillano;

    private int Velocidad;
    public static int Largo,Ancho, ProbaLadrillo;
    private static int reloj = 635;

    public static boolean running = false;
    public static boolean pausa = true;
    private Thread thread;

    private BufferStrategy bs;
    public static Graphics g;

    public Juego(){
        leerDatos("Img/archiConf.txt");
        tablero = new Tablero(Largo,Ancho,ProbaLadrillo);
        tablero.llenarMatriz();
        observer = new Observer(0);
        nivel = new Nivel(3,CantVillano);
        nivel.iniciar();
        keyManager = new KeyManager();
        mouse = new Mouse();
    }

    public static void reiniciar(){
        Timer time1 = new Timer();
        time1.schedule(new TimerTask() {
            @Override
            public void run() {
                tablero.llenarMatriz();
                nivel.iniciar();
                Heroe.SetPosX(1);
                Heroe.SetPosY(1);
                SetTiempo(635);

            }
        } ,2000);
    }

    public static void pasar(){
        tablero.llenarMatriz();
        nivel.iniciar();
        Heroe.SetPosX(1);
        Heroe.SetPosY(1);
        SetTiempo(635);
    }

    public static void morir(){
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {

                Sonido sonidoMuerte = new Sonido();
                sonidoMuerte.play("Img/muerte.mp3");
                for(int i=2;i<Nivel.list_cupon.size();i++){
                    if(!Nivel.list_cupon.get(i).getAfterLife()){
                        Nivel.list_cupon.get(i).setActivo(false);
                    }
                }
            }
        } ,2000);
        reiniciar();

    }

    private void addMouseListener(Mouse mouse) {
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
        Observer.VerificarColision();
        if(Observer.PasarSiguienteNivel()){
            Nivel.setNivel(Nivel.getNivel()+1);
            Heroe.setVida(Heroe.getVida()+1);
            pasar();
        }
        /*
        if(Observer.GameOver()){
            GamePanel.dibujarGameover(g);

            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit( 0 );
                }
            } ,2000);
        }*/

        if (getTiempo() == 0){
            Villano.ConvertirMonedasG();
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    reiniciar();
                }
            } ,5000);
        }

        bs.show();
        g.dispose();
    }

    public void init(){
        gamePanel = new GamePanel(Ancho*30,Largo*30);
        //gamePanel.getFrame().addKeyListener(keyManager);
        gamePanel.getFrame().addKeyListener(new KeyManager());
        gamePanel.getFrame().addMouseListener(mouse);
        gamePanel.getFrame().addMouseMotionListener(mouse);
        gamePanel.getCanvas().addMouseListener(mouse);
        gamePanel.getCanvas().addMouseMotionListener(mouse);
        personajeElement = new PersonajeElement();
        heroe = new Heroe(0,1,1,30,0,true,3,2,0);
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
            while(pausa){
                now = System.nanoTime();
                delta += (now - lastTime) / timePerTick;
                timer += now - lastTime;
                lastTime = now;

                if(delta >= 1){
                    setTiempo();
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
        }

        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public Mouse getMouseManager(){  return mouse; }

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
    public static int getTiempo(){
        return reloj;
    }

    public static int setTiempo(){
        return reloj--;
    }

    public static void SetTiempo(int t){
        reloj = t;
    }
}

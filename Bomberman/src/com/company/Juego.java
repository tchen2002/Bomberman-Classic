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

    /* Función: Juego
       Dominio: No recibe ningún parámetro
       Codominio: Lee los datos del archivo de configuración, inicializa lo
              necesario para comenzar el juego como los objetos de las clase: Tablero,
              Observe,el nivel,el teclado y mouse
*/
    public Juego(){
        leerDatos("Img/archiConf.txt");
        tablero = new Tablero(Largo,Ancho,ProbaLadrillo);
        tablero.llenarMatriz();
        observer = new Observer(0);
        nivel = new Nivel(3,CantVillano);
        nivel.iniciar();
        keyManager = new KeyManager();
        mouse = new Mouse();
        Ruta ruta_corta = new Ruta();
    }

    /* Función: Reiniciar
       Dominio: No recibe ningún parámetro
       Codominio: Restablece los valores de inicio del juego,cambiando la posición del Heroe a la inicial
                 Igual que el tiempo vuelve a comenzar a 200 segundos
*/
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

    /* Función: pasarNivel
       Dominio: No recibe ningún parámetro
       Codominio: el héroe pasa al siguiente nivel y reinicia el tablero
*/
    public static void pasarNivel(){
        tablero.llenarMatriz();
        nivel.iniciar();
        Heroe.SetPosX(1);
        Heroe.SetPosY(1);
        SetTiempo(635);
    }

    /* Función: Morir
       Dominio: No recibe ningún parámetro
       Codominio: cuando el héroe se muere, suena el sonido de que se murió, y desactivarán los poderes
       que no son de after life y reinciar el juego
*/
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

    /* Función: Leer Datos
       Dominio: Recibe un string
       Codominio: Almacena en las varibles correspondientes los datos del archivo
               de configuración inicial
*/
    private void leerDatos(String path){
        String file = loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        Largo = parseInt(tokens[0]);
        Ancho = parseInt(tokens[1]);
        ProbaLadrillo = parseInt(tokens[2]);
        CantVillano = parseInt(tokens[3]);
        Velocidad = parseInt(tokens[4]);
    }

    /* Función: Tick
       Dominio: No recibe ningún parámetro */
    private void tick(){
        keyManager.tick();
    }

        /*Función: Render
          Dominio: No recibe ningún parámetro
          Codominio: llaman las funciones principales del proyecto para iniciar el juego
        */
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
            pasarNivel();
        }

        if(Observer.GameOver()){
            GamePanel.dibujarGameover(g);

            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit( 0 );
                }
            } ,2000);
        }

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

    /* Función: Init
  Dominio: No recibe ningún parámetro
  Codominio: Inicializa lo relacionado con la interfaz como el panel, controles de teclado y mouse
             a los personajes como al heroe
*/
    public void init(){
        gamePanel = new GamePanel(Ancho*30,Largo*30);
        //gamePanel.getFrame().addKeyListener(keyManager);
        gamePanel.getFrame().addKeyListener(new KeyManager());
        gamePanel.getFrame().addMouseListener(mouse);
        gamePanel.getFrame().addMouseMotionListener(mouse);
        gamePanel.getCanvas().addMouseListener(mouse);
        gamePanel.getCanvas().addMouseMotionListener(mouse);
        personajeElement = new PersonajeElement();
        heroe = new Heroe(0,1,1,30,0,true,3,1,0);
    }

    /* Función: Run
      Dominio: No recibe ningún parámetro
      Codominio: Permite que el programa se mueva y realice todas las funciones
                 Va a correr en un tiempo establecido
                 Puede estar en pausa o corriendo mientras el tiempo se va decrementando
                 Llama a las funciones tick y render
   */
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

    /* Función: Start
   Dominio: No recibe ningún parámetro
   Codominio: crear un hilo para ejecutar el juego
 */
    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    /* Función: Stop
       Dominio: No recibe ningún parámetro
       Codominio: cambia running por false para indicar que debe deternese el juego
     */
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

    /* Función: Cargar Archivo como string
   Dominio: Un string con los datos del archivo
   Codominio: cargar el contenido del archivo como string

 */
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

    /* Función: parseInt
   Dominio: Un string con los datos del archivo
   Codominio: Cambia el formato de un string a formato int para poderlo usar
              en el programa

*/
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }

    /* ------------------------------------------
           Getters y Setters
   ------------------------------------------
 */
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

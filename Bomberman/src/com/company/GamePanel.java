package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Timer;

public class GamePanel {

    private BufferStrategy bs;
    private JFrame frame;
    private Canvas canvas;
    private JLabel timeLabel,scoreLabel,lifeLabel;
    public static BufferedImage acero,ladrillo,sacate,bomba,puertita,cc;
    final BufferedImage[]  list_imag_cupones = new BufferedImage[8];
    private int LargoTablero,AnchoTablero;

    public GamePanel(int AnchoTablero,int LargoTablero){
        this.AnchoTablero= AnchoTablero;
        this.LargoTablero= LargoTablero;
        createPanel();
        CargarImagenesGenerales();
        CargarImagenesCupones();
    }

    public void CargarImagenesCupones(){
        list_imag_cupones[0] = loadImage("Img/cupon0_sol.png");
        list_imag_cupones[1] = loadImage("Img/cupon1_bombaextra.png");
        list_imag_cupones[2] = loadImage("Img/cupon2_detonador.png");
        list_imag_cupones[3] = loadImage("Img/cupon3_patin.png");
        list_imag_cupones[4] = loadImage("Img/cupon4_bombarayada.png");
        list_imag_cupones[5] = loadImage("Img/cupon5_murorayado.png");
        list_imag_cupones[6] = loadImage("Img/cupon6_pregunta.png");
        list_imag_cupones[7] = loadImage("Img/cupon7_hombreenllamas.png");
    }

    public void CargarImagenesGenerales(){
        acero = loadImage("Img/acero.png");
        ladrillo = loadImage("Img/ladrillo.png");
        sacate = loadImage("Img/cesped.png");
        bomba = loadImage("Img/bomba.png");
        puertita = loadImage("Img/puerta.png");
    }

    private void createPanel(){
        frame = new JFrame("Bomberman");
        frame.setSize(AnchoTablero,LargoTablero);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(AnchoTablero, LargoTablero));
        canvas.setMaximumSize(new Dimension(AnchoTablero, LargoTablero));
        canvas.setMinimumSize(new Dimension(AnchoTablero, LargoTablero));
        canvas.setFocusable(false);

        timeLabel = new JLabel("Time: " + Juego.getTiempo());
        timeLabel.setBounds(0,0,100,20);
        timeLabel.setOpaque(true);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBackground(Color.BLACK);

        lifeLabel = new JLabel("Vidas: " + Heroe.getVida());
        lifeLabel.setBounds(100,0,80,20);
        lifeLabel.setOpaque(true);
        lifeLabel.setForeground(Color.WHITE);
        lifeLabel.setBackground(Color.BLACK);

        //Observer.getPuntos_globales()
        scoreLabel = new JLabel("Puntaje: " +  Heroe.getVida());
        scoreLabel.setBounds(180,0,200,20);
        scoreLabel.setOpaque(true);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.BLACK);

        frame.add(timeLabel);
        frame.add(lifeLabel);
        frame.add(scoreLabel);
        frame.add(canvas);
        frame.pack();

    }

    public static BufferedImage loadImage(String path){
        File file = new File(path);
        try {
            return ImageIO.read(file);
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public void dibujarMapa(Graphics g){
        timeLabel.setText("Tiempo: " + Juego.getTiempo());
        lifeLabel.setText("Vidas: " + Heroe.getVida());
        scoreLabel.setText("Puntajes: " + Observer.getPuntos_globales());

        for(int x = 0;x <(AnchoTablero/30);x++){
            for(int y = 0;y< (LargoTablero/30);y++){

                if(Tablero.Mapa[y][x] == 'A')
                    g.drawImage(acero,x*30,y*30,null);
                if(Tablero.Mapa[y][x] == 'L')
                    g.drawImage(ladrillo,x*30,y*30,null);
                if(Tablero.Mapa[y][x] == '-')
                    g.drawImage(sacate,x*30,y*30,null);
            }
        }

        if(dibujarBomba()) {
            for(int i=0;i<Juego.list_bomba.size();i++){
                g.drawImage(bomba,Juego.list_bomba.get(i).getPosY()*30,Juego.list_bomba.get(i).getPosX()*30,null);
            }
        }

        if(dibujarPuerta()){
            g.drawImage(puertita,Nivel.puerta.getPosY()*30,Nivel.puerta.getPosX()*30,null);

            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    Nivel.puerta.setEstado(true);
                }
            } ,5000);

        }

        if(dibujarCupon()) {
            g.drawImage(list_imag_cupones[Nivel.cupon.getCupon()],Nivel.cupon.getPosY()*30, Nivel.cupon.getPosX()*30, null);
        }

    }

    public static void dibujarGameover(Graphics g) {
        //Para poner fondo en negro,con un rectangulo
        g.setColor(Color.black);
        g.fillRect(0, 0, 1500, 1000);

        //Mostrar letras Game over en pantalla
        Font font = new Font("Arial", Font.PLAIN, 20 * 3);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Game Over!",400,300);
    }

    public static boolean dibujarPuerta(){
        int x = Nivel.puerta.getPosX();
        int y = Nivel.puerta.getPosY();
        if(Tablero.Mapa[x][y] == '-') return true;
        else return false;
    }

    public static boolean dibujarCupon(){
        Heroe.EncontrarCupon();
        int x = Nivel.cupon.getPosX();
        int y = Nivel.cupon.getPosY();
        if(Tablero.Mapa[x][y] == '-' && !Nivel.cupon.getEstado()) return true;
        else return false;
    }

    public static boolean dibujarBomba(){
        if(Juego.list_bomba.isEmpty()) return false;
        else return true;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame(){return frame;}

    public void addKeyListener(KeyManager keyManager) {
    }
}
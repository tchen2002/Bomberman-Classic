package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;


public class GamePanel {

    private BufferStrategy bs;
    private JFrame frame;
    private Canvas canvas;
    public static BufferedImage acero,ladrillo,sacate,bomba;

    private int LargoTablero,AnchoTablero;

    public GamePanel(int AnchoTablero,int LargoTablero){
        this.AnchoTablero= AnchoTablero;
        this.LargoTablero= LargoTablero;
        createPanel();
        acero = loadImage("Img/acero.jpg");
        ladrillo = loadImage("Img/ladrillo.jpeg");
        sacate = loadImage("Img/sacate.jpeg");
        bomba = loadImage("Img/bomba.png");
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
            g.drawImage(bomba,Juego.list_bomba.get(0).getPosY(),Juego.list_bomba.get(0).getPosX(),null);
        }
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

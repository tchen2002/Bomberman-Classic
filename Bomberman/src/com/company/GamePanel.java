package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

public class GamePanel {

    private BufferStrategy bs;
    private JFrame frame;
    private Canvas canvas;
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
        list_imag_cupones[6] = loadImage("Img/cupon6_hombreenllamas.png");
        list_imag_cupones[7] = loadImage("Img/cupon7_pregunta.png");
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
            for(int i=0;i<Juego.list_bomba.size();i++){
                g.drawImage(bomba,Juego.list_bomba.get(i).getPosY()*30,Juego.list_bomba.get(i).getPosX()*30,null);
            }
        }

        if(dibujarPuerta()){
            g.drawImage(puertita,Nivel.puerta.getPosY()*30,Nivel.puerta.getPosX()*30,null);
        }

        if(dibujarCupon()) {
            g.drawImage(list_imag_cupones[Nivel.cupon.getCupon()],Nivel.cupon.getPosY()*30, Nivel.cupon.getPosX()*30, null);
        }

    }

    public static boolean dibujarPuerta(){
        int x = Nivel.puerta.getPosX();
        int y = Nivel.puerta.getPosY();
        if(Tablero.Mapa[x][y] == '-') return true;
        else return false;
    }

    public static boolean dibujarCupon(){
        int x = Nivel.cupon.getPosX();
        int y = Nivel.cupon.getPosY();
        if(Tablero.Mapa[x][y] == '-') return true;
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

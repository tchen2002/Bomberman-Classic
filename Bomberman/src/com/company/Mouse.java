package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    public Mouse(){

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            if(Heroe.getCantBomba()>0){
                Heroe.ColocarBomba();
                Heroe.setCantBomba(Heroe.getCantBomba()-1);
            }
        }else if(e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("DERECHA");
            if(Nivel.list_cupon.get(2).getActivo()){
                if(!Juego.list_bomba.isEmpty()){
                    CuponDorado.Detonador();
                }
            }
            //Detona la bomba que lleva mas tiempo
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }


    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

}

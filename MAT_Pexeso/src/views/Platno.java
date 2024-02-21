package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import models.HerniPole;

public class Platno extends JPanel {
    HerniPole pole;
    public Platno() {
        pole = HerniPole.getInstance();
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int posx = ((int)e.getX()/76)*4;
                int posy = (int)e.getY()/76;
                pole.obratSe(posx+posy);
                pole.vyhodnotSe();
                repaint();
            }
        
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(163, 163, 163));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        pole.vykresliVse(g);
    }
    
    
}

package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import models.HerniLogika;

public class Platno extends JPanel {
    HerniLogika logika;
    public Platno() {
        logika = new HerniLogika();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getY());
                if(e.getX() > 220 && e.getX() < 320 && e.getY() > 425 & e.getY() < 450) {
                    logika.kliknuti();
                   repaint();
                }
            }            
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        logika.vykresliVse(g);
    }
}

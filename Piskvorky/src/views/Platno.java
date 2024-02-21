package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import models.ModelHry;
import models.Nastaveni;

public class Platno extends JPanel {
    ModelHry hra;
    public Platno() {
        this.setSize(Nastaveni.velikostPole*Nastaveni.pocetPolicek, Nastaveni.velikostPole*Nastaveni.pocetPolicek);
        hra = new ModelHry();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
              hra.polickoKliknuto(e.getX(), e.getY());
              hra.setIsHracJednaNaRade(!hra.isIsHracJednaNaRade());
              repaint();
            }
        });
    }
    public void initView(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (int i = 0; i < Nastaveni.pocetPolicek; i++) {
            for (int j = 0; j < Nastaveni.pocetPolicek; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(i*Nastaveni.velikostPole, j*Nastaveni.velikostPole, Nastaveni.velikostPole, Nastaveni.velikostPole);
            }
        }
    }
    public void paintVyhra(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.CYAN);
            g.setFont(new Font("Calibri", 0, 30));
        if(hra.isKoleckaVyhraly()) {
            g.drawString("Kolecka vyhraly", this.getHeight()/4, this.getHeight()/2);
        }else {
           g.drawString("Krizky vyhraly", this.getHeight()/4, this.getHeight()/2);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        initView(g);
        hra.vykresliSe(g);
        if(hra.isKoleckaVyhraly() || hra.isKrizkyVyhraly()) {
            paintVyhra(g);
        }
    }
    
}

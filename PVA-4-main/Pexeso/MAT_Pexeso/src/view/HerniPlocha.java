package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class HerniPlocha extends JPanel {
    public static final Dimension rozmer = new Dimension(2, 3);
    public ArrayList<Karta> pole;
    //Konstruktor
    public HerniPlocha() {
        this.setPreferredSize(new Dimension(
                rozmer.width * (Karta.velikost.width + 5) + 7,
                rozmer.height * (Karta.velikost.height + 5) + 5)
        );
        this.setLayout(null);
        pole = new ArrayList<>();
        NovaHra();
    }
    
    public void NovaHra() {
        pole.clear();
        for (int i = 0; i < rozmer.width; i++) {
            for (int j = 0; j < rozmer.height; j++) {
                Karta karta = new Karta(new Dimension(i, j), j);
                karta.setLocation(i * (karta.velikost.width + 5) + 5,
                        j * (karta.velikost.height + 5) + 5);
                pole.add(karta);
                this.add(karta);
            }
        }
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        
        for (Karta karta : pole) {
            karta.repaint();
        }
    }
    
}

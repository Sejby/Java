package komponenty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import nastaveni.Nastaveni;
import hra.Hra;
import java.awt.Font;
import java.awt.FontMetrics;

public class Platno extends JPanel {
    private Hra hra;
    public Platno() {
        hra = new Hra();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseClicked(e);
                int poziceX = e.getX() / Nastaveni.rozmerPolicka;
                int poziceY = e.getY() / Nastaveni.rozmerPolicka;
                if (poziceX >= Nastaveni.velikostPole || 
                        poziceY >= Nastaveni.velikostPole) return;
                hra.polickoKliknuto(poziceX, poziceY);
                repaint();
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        vykreslyPozadi(g);
        tiskPole(g);
    }
    
    
    //Metody
    public void NovaHra() {
        hra.NovaHra();
        this.repaint();
    }
    public Boolean GetTah() {
        return hra.GetTah();
    }
    
    private void vykreslyPozadi(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(
                0, 0,
                Nastaveni.rozmerPolicka * Nastaveni.velikostPole,
                Nastaveni.rozmerPolicka * Nastaveni.velikostPole
        );
    }
    
    private void tiskPole(Graphics g) {
        Boolean[][] pole = hra.getPole();
        if (pole == null) return;
        for (int i = 0; i < (Nastaveni.velikostPole); i++) {
            for (int j = 0; j < Nastaveni.velikostPole; j++) {
                g.setColor(Color.black);
                g.drawRect(
                        i * Nastaveni.rozmerPolicka,
                        j * Nastaveni.rozmerPolicka,
                        Nastaveni.rozmerPolicka, Nastaveni.rozmerPolicka
                );
                
                if (pole[i][j] == null) continue;
                g.setColor(pole[i][j] ? Nastaveni.barva1 : Nastaveni.barva2);
                g.fillRect(
                        i * Nastaveni.rozmerPolicka,
                        j * Nastaveni.rozmerPolicka, 
                        Nastaveni.rozmerPolicka, Nastaveni.rozmerPolicka
                );
                g.setColor(Color.WHITE);
                
                g.setFont(new Font("Times New Roman", 0, Nastaveni.rozmerPolicka));
                FontMetrics metrics = g.getFontMetrics();
                int vyska = metrics.getHeight();
                int sirka = g.getFontMetrics().charWidth(pole[i][j] ? Nastaveni.znak1 : Nastaveni.znak2);
                g.drawString(
                        String.valueOf(pole[i][j] ? Nastaveni.znak1 : Nastaveni.znak2),
                        i * Nastaveni.rozmerPolicka + (Nastaveni.rozmerPolicka - sirka) / 2, (j+1) * Nastaveni.rozmerPolicka - (vyska - Nastaveni.rozmerPolicka)
                );
            }
        }
    }
    
    private void tiskPole() {
        Boolean[][] pole = hra.getPole();
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[0].length; j++) {
                System.out.println(pole[i][j]);
            }
        }
    }
}

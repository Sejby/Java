package view;

import had.Had;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import jidlo.Jidlo;

public class Platno extends JPanel{
    //Vlastnosti
    public static final int velikost = 25;
    private Had had;
    private Had.Smer smer;
    private Had.Smer starySmer;
    private Jidlo jidlo;
    
    //Konstruktor
    public Platno() {
        jidlo = new Jidlo();
        had = new Had(jidlo);
        Timer timer = new Timer(300, (e) -> {
            repaint();
        });
        timer.start();
        
        smer = Had.Smer.Doleva;
        starySmer = Had.Smer.Doleva;
        this.registerKeyboardAction((ActionEvent e) -> {
            if (starySmer == Had.Smer.Dolu) return;
            smer = Had.Smer.Nahoru;
        }, KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), WHEN_IN_FOCUSED_WINDOW);
        
        this.registerKeyboardAction((ActionEvent e) -> {
            if (starySmer == Had.Smer.Doprava) return;
            smer = Had.Smer.Doleva;
        }, KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), WHEN_IN_FOCUSED_WINDOW);
        
        this.registerKeyboardAction((ActionEvent e) -> {
            if (starySmer == Had.Smer.Nahoru) return;
            smer = Had.Smer.Dolu;
        }, KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), WHEN_IN_FOCUSED_WINDOW);
        
        this.registerKeyboardAction((ActionEvent e) -> {
            if (starySmer == Had.Smer.Doleva) return;
            smer = Had.Smer.Doprava;
        }, KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), WHEN_IN_FOCUSED_WINDOW);
    }
    
    //Paint component
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!had.getProhral()) {
            had.pohyb(smer);
            starySmer = smer;
        }
        vykresliPozadi(g);
        had.vykresli(g, getWidth() / velikost);
        jidlo.vykresli(g, getWidth() / velikost);
    }
    
    private void vykresliPozadi(Graphics g) {
        int velikostPolicka = getWidth() / velikost;
        g.setColor(new Color(150, 150, 150));
        for (int i = 0; i < velikost; i++) {
            for (int j = 0; j < velikost; j++) {
                g.drawRect(velikostPolicka * i, velikostPolicka * j, 
                        velikostPolicka, velikostPolicka);
            }
        }
    }
}

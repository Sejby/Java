package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import mat_game_of_life.Model;

public class Platno extends JPanel {
    //Vlastnosti
    private ArrayList<Boolean> pole;
    private int velikost;
    private Model model;
    private Timer timer;
    //Konstruktor
    public Platno() {
        model = new Model();
        pole = new ArrayList<>();
        velikost = 500 / 20;
        naplnPole();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseClicked(e);
                zpracovatKliknuti(e.getX(), e.getY());
            }
            
        });
    }
    
    //Metody rozhraní
    public void naplnPole() {
        this.pole.clear();
        for (int i = 0; i < (20*20); i++) {
            this.pole.add(false);
        }
        this.repaint();
    }
    
    public void nahonePole() {
        this.pole.clear();
        Random rand = new Random();
        for (int i = 0; i < (20*20); i++) {
            this.pole.add(rand.nextBoolean());
        }
        this.repaint();
    }
    
    public ArrayList<Boolean> dejPole() {
        return this.pole;
    }
    
    public void startTimer() {
        timer = new Timer(50, (e) -> {
            model.staraGenerace(pole);
            pole = model.novaGenerace();
            this.repaint();
        });
        timer.start();
    }
    
    public void stopTimer() {
        if (timer != null) timer.stop();
    }
    
    //Paint component
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pole.isEmpty()) return;
        tiskPole(g);
    }
    
    //Metody
    private void tiskPole(Graphics g) {
        if (pole.isEmpty()) return;
        for (int i = 0; i < (20*20); i++) {
            g.setColor(pole.get(i) ? Color.BLACK : Color.WHITE);
            g.fillRect(i%20 * velikost, i/20 * velikost, velikost, velikost);
        }
    }
    
    private void zpracovatKliknuti(int x, int y) {
        int posX = x/velikost;
        int posY = y/velikost;
        int pos = posY * 20 + posX;
        pole.set(pos, !pole.get(pos));
        this.repaint();
    }
}

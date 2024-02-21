package mat_snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Platno extends JPanel implements ActionListener {

    static final int sirkaPole = 848;
    static final int vyskaPole = 480;
    static final int velikostPolicka = 25;
    static final int velikostPole = (sirkaPole * vyskaPole) / (velikostPolicka * velikostPolicka);
    static final int odezva = 150;
    final int x[] = new int[velikostPole];
    final int y[] = new int[velikostPole];
    int pocetClankuHada = 5;
    int zradloSezrano;
    int zradloX;
    int zradloY;
    char smer = 'R';
    boolean funguje = false;
    Timer timer;
    Random random;

    public Platno() {
        random = new Random();
        this.setPreferredSize(new Dimension(sirkaPole, vyskaPole));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MujKeyAdapter());
        zacitHru();
    }

    public void zacitHru() {
        dalsiZradlo();
        funguje = true;
        timer = new Timer(odezva, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        vykresli(g);
    }

    public void vykresli(Graphics g) {

        if (funguje) {
            g.setColor(Color.red);
            g.fillOval(zradloX, zradloY, velikostPolicka, velikostPolicka);

            for (int i = 0; i < pocetClankuHada; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], velikostPolicka, velikostPolicka);
                } else {
                    g.setColor(new Color(64, 173, 0));
                    g.fillRect(x[i], y[i], velikostPolicka, velikostPolicka);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Tahoma", Font.BOLD, 14));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Skóre: " + zradloSezrano, (sirkaPole - metrics.stringWidth("Skóre: " + zradloSezrano)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }

    }

    public void dalsiZradlo() {
        zradloX = random.nextInt((int) (sirkaPole / velikostPolicka)) * velikostPolicka;
        zradloY = random.nextInt((int) (vyskaPole / velikostPolicka)) * velikostPolicka;
    }

    public void pohyb() {
        for (int i = pocetClankuHada; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (smer) {
            case 'U':
                y[0] = y[0] - velikostPolicka;
                break;
            case 'D':
                y[0] = y[0] + velikostPolicka;
                break;
            case 'L':
                x[0] = x[0] - velikostPolicka;
                break;
            case 'R':
                x[0] = x[0] + velikostPolicka;
                break;
        }

    }

    public void kontrolaZradla() {
        if ((x[0] == zradloX) && (y[0] == zradloY)) {
            pocetClankuHada++;
            zradloSezrano++;
            dalsiZradlo();
        }
    }

    public void kontrolaKolize() {
        for (int i = pocetClankuHada; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                funguje = false;
            }
        }
        if (x[0] < 0) {
            funguje = false;
        }
        if (x[0] > sirkaPole) {
            funguje = false;
        }
        if (y[0] < 0) {
            funguje = false;
        }
        if (y[0] > vyskaPole) {
            funguje = false;
        }
        if (!funguje) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Tahoma", Font.BOLD, 14));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Skóre: " + zradloSezrano, (sirkaPole - metrics1.stringWidth("Skóre: " + zradloSezrano)) / 2, g.getFont().getSize());
        g.setColor(Color.red);
        g.setFont(new Font("Tahoma", Font.BOLD, 45));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Konec hry", (sirkaPole - metrics2.stringWidth("Konec hry")) / 2, vyskaPole / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (funguje) {
            pohyb();
            kontrolaZradla();
            kontrolaKolize();
        }
        repaint();
    }

    public class MujKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    if (smer != 'R') {
                        smer = 'L';
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if (smer != 'L') {
                        smer = 'R';
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (smer != 'D') {
                        smer = 'U';
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (smer != 'U') {
                        smer = 'D';
                    }
                }
            }
        }
    }
}

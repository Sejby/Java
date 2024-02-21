package jidlo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import view.Platno;

public class Jidlo {
    private int x;
    private int y;
    private final Random rand;
    public Jidlo() {
        rand = new Random();
        novaPozice();
    }
    
    public void novaPozice() {
        this.x = rand.nextInt(Platno.velikost);
        this.y = rand.nextInt(Platno.velikost);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void vykresli(Graphics g, int velikost) {
        g.setColor(Color.orange);
        g.fillRect(x * velikost, y * velikost, velikost, velikost);
    }
}

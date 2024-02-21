package had;

import java.awt.Color;
import java.awt.Graphics;

public class ClanekHada {
    //Vlastnosti
    private int x;
    private int y;
    private boolean jeHlava;
    
    //Konstruktor
    public ClanekHada(int x, int y, boolean jeHlava) {
        this.x = x;
        this.y = y;
        this.jeHlava = jeHlava;
    }
    
    //Metody rozhraní
    public void vykresliClanek(Graphics g, int velikost) {
        g.setColor(jeHlava ? Color.red : Color.green);
        g.fillRect(x * velikost, y * velikost, velikost, velikost);
    }
    
    //Getter Setter
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

    public boolean isJeHlava() {
        return jeHlava;
    }
    public void setJeHlava(boolean jeHlava) {
        this.jeHlava = jeHlava;
    }

    
    
}

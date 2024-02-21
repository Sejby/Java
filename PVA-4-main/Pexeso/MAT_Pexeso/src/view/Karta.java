package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Karta extends JPanel {
    public enum Stav {
        otocena, neotocena, uhodnuta
    }
    //Vlastnosti
    public Dimension pozice;
    public static final Dimension velikost = new Dimension(50, 50);
    public Stav stav;
    public int obrazek;
    //Konstruktor
    public Karta(Dimension pozice, int obrazek) {
        this.stav = Stav.neotocena;
        this.pozice = pozice;
        this.obrazek = obrazek;
        this.setSize(velikost);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println(obrazek);
                Otoc();
            }
            
        });
    }
    
    public void Otoc() {
        stav = Stav.otocena;
        this.repaint();
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        switch(stav) {
            case neotocena:
                g.setColor(Color.GRAY);
                g.fillRect(3, 3, this.velikost.width, this.velikost.height);
                g.setColor(Color.black);
                g.drawRect(3, 3, this.velikost.width-4, this.velikost.height-4);
                break;
            case otocena:
                g.setColor(Color.white);
                g.fillRect(3, 3, this.velikost.width, this.velikost.height);
                g.setColor(Color.black);
                g.drawRect(3, 3, this.velikost.width-4, this.velikost.height-4);
                g.setFont(new Font("Arial", 0, 20));
                g.drawString(this.obrazek + "",
                        this.getWidth()/2,
                        this.getHeight()/2);
                break;
            case uhodnuta:
                g.setColor(Color.white);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.setColor(Color.black);
                g.drawRect(3, 3, this.velikost.width-4, this.velikost.height-4);
                g.drawString("Uhodnuto", 0, this.getHeight()/2);
                break;
            default:
                break;
        }
    }
}

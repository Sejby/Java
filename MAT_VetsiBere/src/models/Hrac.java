package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import vetsibere.VetsiBere;

public class Hrac {
    private ArrayList<Karta> balicek;
    private String jmeno;
    public Hrac(String jmeno) {
        this.jmeno = jmeno;
        balicek = new ArrayList<>();   
    }

    public ArrayList<Karta> getBalicek() {
        return balicek;
    }
    public void setBalicek(ArrayList<Karta> balicek) {
        this.balicek = balicek;
    }
    public String getJmeno() {
        return jmeno;
    }
    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }
    public void pridejDoBalicku(Karta karta) {
        this.balicek.add(karta);
    }
    public void vykresliSvujBalicek(Graphics g,int x, int y) {
        int enumerator = 0;
        for (Karta karta : balicek) {
            BufferedImage img; 
            try {
                String neco =getClass().getClassLoader().getResource("resource/sprites.png").getFile();
                img = ImageIO.read(new File(neco.substring(1,neco.length())));
                img = img.getSubimage(0, 192, 32, 48);
                g.drawImage(img, x+enumerator, y, null);
                enumerator+= 5;
            } catch (IOException ex) {}
        }
        g.setColor(Color.BLACK);
        g.drawString(jmeno, x, y+80);
    }
    
    public Karta popBalicku() {
        Karta karta = balicek.get(balicek.size()-1);
        balicek.remove(balicek.size()-1);
        return karta;
    }
}

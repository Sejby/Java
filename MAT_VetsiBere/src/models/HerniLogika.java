package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;

public class HerniLogika {
    private ArrayList<Hrac> hraci;
    private ArrayList<Karta> karty;
    private ArrayList<Karta> kartyNaPranyri;
    
    public HerniLogika() {
        kartyNaPranyri = new ArrayList<>();
        hraci = new ArrayList<>();
        hraci.add(new Hrac("Pat"));
        hraci.add(new Hrac("Mat"));
        karty = new ArrayList<>();
        for (BarvaKarty barva : BarvaKarty.values()) {
            for (HodnotaKarty hodnota : HodnotaKarty.values()) {
                karty.add(new Karta(hodnota, barva));
            }
        }
        Collections.shuffle(karty);
        int puvodniVel = karty.size();
        for (int i = 0; i < hraci.size(); i++) {
            for(int x = 0; x < puvodniVel/hraci.size(); x++) {
                hraci.get(i).pridejDoBalicku(karty.get(karty.size()-1));
                karty.remove(karty.size()-1);
            }   
        }
    }
    public void vykresliVse(Graphics g) {
        if(hraci.size() == 1) {
            g.setColor(Color.BLACK);
            g.drawString(hraci.get(0).getJmeno()+" vyhrava!!", 150, 150);
            return;
        }
        for (int i = 0; i < hraci.size(); i++) {
            hraci.get(i).vykresliSvujBalicek(g, 50+i*400/hraci.size(), 300+i*5);
        }
        g.setColor(Color.BLACK);
        g.fillRect(220,430,100,25);
        g.setColor(Color.WHITE);
        g.drawString("Dalsi Tah", 240, 445);
        if (kartyNaPranyri.size() > 0) {
            Karta nejvyssiKarta = null;
            for (int x = 0; x < hraci.size(); x++) {
                if(nejvyssiKarta == null || kartyNaPranyri.get(x).hodnota.hodnota > nejvyssiKarta.hodnota.hodnota) {
                    nejvyssiKarta = kartyNaPranyri.get(x);
                }
                BufferedImage img;
                try {
                    String neco =getClass().getClassLoader().getResource("resource/sprites.png").getFile();
                    img = ImageIO.read(new File(neco.substring(1,neco.length())));
                    img = img.getSubimage(32*kartyNaPranyri.get(x).hodnota.hodnota, 48*kartyNaPranyri.get(x).barva.hodnota, 32, 48);
                    g.drawImage(img, 50+50*x, 150, null);
                }catch(IOException e) {
                    
                }
            }
            int vitez = kartyNaPranyri.indexOf(nejvyssiKarta) % hraci.size();
            for (Karta karta : kartyNaPranyri) {
                hraci.get(vitez).pridejDoBalicku(karta);
            }
            kartyNaPranyri = new ArrayList<>();
            for (Hrac hrac : hraci) {
                if(hrac.getBalicek().size() <= 0) {
                    hraci.remove(hrac);   
                }
            }
        }
    }
    public void kliknuti() {
        for (Hrac hrac : hraci) {
            kartyNaPranyri.add(0, hrac.popBalicku());
        }
    }
    
    
}

package hra;

import java.util.Objects;
import javax.swing.JOptionPane;
import nastaveni.Nastaveni;

public class Hra {
    private Boolean[][] pole;
    private Boolean hrac;
    private Boolean vyherce;
    
    public Hra() {
        NovaHra();
    }
    
    
    //Metody
    public void polickoKliknuto(int x, int y) {
        if (pole[x][y] != null || vyherce != null) return;
        pole[x][y] = hrac;
        if (Vyhra(x, y)) {
            vyherce = hrac;
            new Thread(() -> {
                JOptionPane.showMessageDialog(
                        null, "Vyhrál hráč " + (hrac ? Nastaveni.znak1 : Nastaveni.znak2)
                );
            }).start();
            return;
        }
        hrac = !hrac;
    }

    public Boolean[][] getPole() {
        return pole;
    }
    
    public void NovaHra() {
        this.hrac = true;
        this.pole = new Boolean[Nastaveni.velikostPole][Nastaveni.velikostPole];
        this.vyherce = null;
    }
    
    public Boolean GetTah() {
        return hrac;
    }
    
    private boolean Vyhra(int x, int y) {
        if (ZkontrolujSikmo(x, y)) return true;
        if (ZkontrolujVodorovne(x, y)) return true;
        if (ZkontrolujSvisle(x, y)) return true;
        
        return false;
    }
    
    private boolean ZkontrolujSikmo(int x, int y) {
        if (Porovnej(x, y, 1, 1) + Porovnej(x-1, y-1, -1, -1) 
                >= Nastaveni.PocetNaVyhru
        ) return true;
        
        if (Porovnej(x, y, -1, 1) + Porovnej(x+1, y-1, 1, -1) 
                >= Nastaveni.PocetNaVyhru
        ) return true;
        
        
        return false;
    }
    private boolean ZkontrolujVodorovne(int x, int y) {
        if (Porovnej(x, y, 0, 1) + Porovnej(x, y-1, 0, -1) 
                >= Nastaveni.PocetNaVyhru
        ) return true;
        
        return false;
    }
    private boolean ZkontrolujSvisle(int x, int y) {
        if (Porovnej(x, y, 1, 0) + Porovnej(x-1, y, -1, 0) 
                >= Nastaveni.PocetNaVyhru
        ) return true;
        
        return false;
    }
    
    private int Porovnej(int x, int y, int posunX, int posunY) {
        int pocet = 0;
        while (true) {
            if (x >= Nastaveni.velikostPole || 
                    y >= Nastaveni.velikostPole || 
                    x < 0 || y < 0 ||
                    !Objects.equals(pole[x][y], hrac)) break;
            pocet++;
            x+=posunX;
            y+=posunY;
        }
        return pocet;
    }
}

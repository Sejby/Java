package models;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class ModelHry {
    PoleEnum[][] herniPole;
    boolean isHracJednaNaRade;
    boolean krizkyVyhraly;
    boolean koleckaVyhraly;
    public ModelHry() {
        isHracJednaNaRade = true;
        krizkyVyhraly = false;
        koleckaVyhraly = false;
        herniPole = new PoleEnum[Nastaveni.pocetPolicek][Nastaveni.pocetPolicek];
        for (int i = 0; i < Nastaveni.pocetPolicek; i++) {
            for (int j = 0; j < Nastaveni.pocetPolicek; j++) {
                herniPole[i][j] = PoleEnum.prazdnePole;
            }
        }
    }
    public void polickoKliknuto(int x, int y) {
        int posx = x/Nastaveni.velikostPole;
        int posy = y/Nastaveni.velikostPole;
        if(posx >= Nastaveni.pocetPolicek || posy >= Nastaveni.pocetPolicek || posx < 0 || posy < 0) {
            return;
        }
        if(herniPole[posx][posy] != PoleEnum.prazdnePole) {
            return;
        }
        herniPole[posx][posy] = isHracJednaNaRade ? PoleEnum.hracJedna : PoleEnum.hracDva;
        if(zkontrolujVyhru(posx, posy)) {
            if(herniPole[posx][posy] == PoleEnum.hracJedna) {
                krizkyVyhraly = true;
            }else {
                koleckaVyhraly = true;
            }
        }
    }
    public void vykresliSe(Graphics g) {
        for (int i = 0; i < herniPole.length; i++) {
            for (int j = 0; j < herniPole[0].length; j++) {
               if(herniPole[i][j] == PoleEnum.hracJedna) {
                   g.setColor(Nastaveni.hracJednaBarva);
               }else {
                   g.setColor(Nastaveni.hracDvaBarva);
               }
                if(herniPole[i][j] != PoleEnum.prazdnePole) {
                    g.setFont(new Font("Calibri", 0, Nastaveni.velikostPole));
                    FontMetrics metrics = g.getFontMetrics();
                    int vyska = metrics.getHeight();
                    int sirka = g.getFontMetrics().charWidth(herniPole[i][j]==PoleEnum.hracJedna ? Nastaveni.hracJedna : Nastaveni.hracDva);
                    g.drawString(String.valueOf(herniPole[i][j]==PoleEnum.hracJedna ? Nastaveni.hracJedna : Nastaveni.hracDva),
                            i * Nastaveni.velikostPole + (Nastaveni.velikostPole - sirka) / 2, (j+1) * Nastaveni.velikostPole - (vyska - Nastaveni.velikostPole));
                }
            }
        }
    }
    public boolean zkontrolujVyhru(int x, int y) {
        PoleEnum barva = herniPole[x][y];
        int vyhraCounter = 0;
        while((x-1) >= 0 && herniPole[x-1][y] == barva) {
            x--;
        }
        while((x+1) < herniPole.length && herniPole[x+1][y] == barva) {
            vyhraCounter++;
            x++;
        }
        if(vyhraCounter == 4) return true;
        vyhraCounter = 0;
        while((y-1) >= 0 && herniPole[x][y-1] == barva) {
            y--;
        }
        while(y+1 < herniPole.length && herniPole[x][y+1] == barva) {
            vyhraCounter++;
            y++;
        }
        if(vyhraCounter == 4) return true;
        vyhraCounter = 0;
        while(x-1 >= 0 && y-1 >= 0 && herniPole[x-1][y-1] == barva ) {
            x--;
            y--;
        }
        while(x+1 < herniPole.length && y+1 < herniPole.length && herniPole[x+1][y+1] == barva) {
            x++;
            y++;
            vyhraCounter++;
        }
        if(vyhraCounter == 4) return true;
        vyhraCounter = 0;
        while(x-1 >= 0 && y+1 < herniPole.length && herniPole[x-1][y+1] == barva) {
            x--;
            y++;
        }
        while(x+1 < herniPole.length && y-1 >= 0 && herniPole[x+1][y-1] == barva) {
            x++;
            y--;
            vyhraCounter++;
        }
        if(vyhraCounter == 4) return true;
        return false;
    }
    public PoleEnum[][] getHerniPole() {
        return herniPole;
    }

    public boolean isIsHracJednaNaRade() {
        return isHracJednaNaRade;
    }

    public void setIsHracJednaNaRade(boolean isHracJednaNaRade) {
        this.isHracJednaNaRade = isHracJednaNaRade;
    }

    public boolean isKrizkyVyhraly() {
        return krizkyVyhraly;
    }

    public boolean isKoleckaVyhraly() {
        return koleckaVyhraly;
    }
    
    
    
}

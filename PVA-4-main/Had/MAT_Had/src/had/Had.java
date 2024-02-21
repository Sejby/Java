package had;

import java.awt.Graphics;
import java.util.ArrayList;
import view.Platno;
import jidlo.Jidlo;

public class Had {
    //Vlastnosti
    private ArrayList<ClanekHada> had;
    private boolean prohral;
    private boolean jedl;
    private Jidlo jidlo;
    public static enum Smer {
        Nahoru,
        Dolu,
        Doleva,
        Doprava
    }
    //Konstruktor
    public Had(Jidlo jidlo) {
        had = new ArrayList<>();
        this.jidlo = jidlo;
        had.add(new ClanekHada(Platno.velikost / 2, Platno.velikost / 2, true));
        had.add(new ClanekHada(Platno.velikost / 2 + 1, Platno.velikost / 2, false));
        prohral = false;
        jedl = false;
    }
    
    //Metody rozhraní
    public void vykresli(Graphics g, int velikost) {
        for (ClanekHada clanekHada : had) {
            clanekHada.vykresliClanek(g, velikost);
        }
    }
    
    public void pohyb(Smer smer) {
        ArrayList<ClanekHada> novePole = new ArrayList<>();
        
        ClanekHada hlava = had.get(0);
        ClanekHada posledni = had.get(had.size() - 1);
        
        if (jedl) {
            posledni = new ClanekHada(posledni.getX(), posledni.getY(), false);
            had.add(posledni);
            jedl = false;
        }
        
        novePole.add(posledni);
        novePole.add(hlava);
        novePole.addAll(had.subList(1, had.size()-1));
        
        switch(smer) {
            case Nahoru:
                if (posledni.getY() - 1 < 0) {prohra();return;}
                posledni.setX(hlava.getX());
                posledni.setY(hlava.getY() - 1);
                break;
            case Dolu:
                if (posledni.getY() + 1 > view.Platno.velikost) {prohra();return;}
                posledni.setX(hlava.getX());
                posledni.setY(hlava.getY() + 1);
                break;
            case Doleva:
                if (hlava.getX() - 1 < 0) {prohra();return;}
                posledni.setX(hlava.getX() - 1);
                posledni.setY(hlava.getY());
                break;
            case Doprava:
                if (hlava.getX() + 1 > view.Platno.velikost) {prohra();return;}
                posledni.setX(hlava.getX() + 1);
                posledni.setY(hlava.getY());
        }
        if (zkontrolujKolize(novePole)) {prohra();return;}
        hlava.setJeHlava(false);
        posledni.setJeHlava(true);
        
        had = novePole;
        zkontrolujJidlo();
    }
    
    private boolean zkontrolujKolize(ArrayList<ClanekHada> had) {
        for (int i = 0; i < had.size(); i++) 
            for (int j = i+1; j < had.size(); j++) 
                if (had.get(i).getX() == had.get(j).getX() && had.get(i).getY() == had.get(j).getY())
                    return true; 
        return false;
    }
    
    private void zkontrolujJidlo() {
        ClanekHada clanek = had.get(0);
        if (clanek.getX() == jidlo.getX() && clanek.getY() == jidlo.getY()) {
            jedl = true;
            jidlo.novaPozice();
        }
    }
    
    private void prohra() {
        prohral = true;
    }
    
    public boolean getProhral() {
        return prohral;
    }
}
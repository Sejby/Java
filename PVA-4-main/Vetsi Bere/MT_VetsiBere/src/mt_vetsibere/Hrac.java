package mt_vetsibere;

import java.util.ArrayList;

public class Hrac {
    public int score;
    public final ArrayList<Karta> karty;
    private Karta vylozenaKarta;
    
    public Hrac() {
        score = 0;
        karty = new ArrayList<>();
    }
    
    public void PridejKartu(Karta karta) {
        karty.add(karta);
    }
    
    public Karta OdeberKartu(int index) {
        return karty.remove(index);
    }
    
    public Karta getVylozenaKarta() {
        return vylozenaKarta;
    }
    
    public void setVylozenaKarta(Karta karta) {
        vylozenaKarta = karta;
    }
}

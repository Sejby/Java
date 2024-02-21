package logika;

import java.util.ArrayList;
import java.util.Random;

public class Logika {
    private final ArrayList<Karta> karty;
    private final Karta defaultKarta;
    public Logika() {
        karty = new ArrayList<>();
        naplnBalicek();
        defaultKarta = new Karta(Short.MAX_VALUE, Karta.Typ.PIKY);
    }
    
    public void naplnBalicek() {
        karty.clear();
        for (short i = 1; i <= 13; i++) {
            karty.add(new Karta(i, Karta.Typ.PIKY));
            karty.add(new Karta(i, Karta.Typ.KARY));
            karty.add(new Karta(i, Karta.Typ.KRIZE));
            karty.add(new Karta(i, Karta.Typ.SRDCE));
        }
    }
    public Karta lizniKartu() {
        return karty.remove(new Random().nextInt(karty.size()-1));
    }
    public Karta defaultKarta() {
        return defaultKarta;
    }
    
    
    
    private static Logika instance;
    public static Logika getInstance() {
        if (instance == null) instance = new Logika();
        return instance;
    }
}

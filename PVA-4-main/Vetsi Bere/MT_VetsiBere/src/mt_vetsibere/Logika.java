package mt_vetsibere;

import java.util.ArrayList;
import java.util.Random;

public class Logika {
    private static Logika instance;
    private final ArrayList<Karta> karty;
    private Karta[] vylozeneKarty;
    private Hrac[] hraci;
    
    private Logika() {
        karty = new ArrayList<>();
        ResetujHru(4);
        vylozKarty();
    }
    
    public static Logika getInstance() {
        if (instance == null) instance = new Logika();
        return instance;
    }
    
    
    public void ResetujHru(int count) {
        ResetujHrace(count);
        ResetujKartyNaStole(count);
        ResetujBalicek();
        RozdejKarty();
    }
    
    public Karta LizniKartu() {
        if (karty == null || karty.isEmpty()) return null;
        return karty.remove(new Random().nextInt(karty.size()));
    }
    
    public Karta[] getKartyNaStole() {
        return vylozeneKarty;
    }
    
    public void vylozKarty() {
        for (int i = 0; i < hraci.length; i++) {
            vylozeneKarty[i] = hraci[i].OdeberKartu(0);
            hraci[i].setVylozenaKarta(vylozeneKarty[i]);
        }
    }
    
    private void ResetujBalicek() {
        karty.clear();
        for (int i = 1; i <= 13; i++) {
            karty.add(new Karta(i, Karta.Typ.PIKY));
            karty.add(new Karta(i, Karta.Typ.KARY));
            karty.add(new Karta(i, Karta.Typ.KRIZE));
            karty.add(new Karta(i, Karta.Typ.SRDCE));
        }
    }
    
    private void ResetujHrace(int pocet) {
        hraci = new Hrac[pocet];
        for (int i = 0; i < pocet; i++) {
            hraci[i] = new Hrac();
        }
    }
    
    private void RozdejKarty() {
        while (!karty.isEmpty()) {
            for (Hrac hrac : hraci) {
                if (karty.isEmpty()) return;
                hrac.PridejKartu(LizniKartu());
            }
        }
    }
    
    private void ResetujKartyNaStole(int count) {
        vylozeneKarty = new Karta[count];
    }
}

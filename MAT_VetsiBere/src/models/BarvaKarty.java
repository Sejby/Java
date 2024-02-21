package models;

public enum BarvaKarty {
    SRDCE(0),
    PIKY(1),
    KRIZE(2),
    KARY(3);
    
    public int hodnota;
    BarvaKarty(int hodnota) {
        this.hodnota = hodnota;
    }    
}

package models;

public enum HodnotaKarty {
    DVOJKA(0),
    TROJKA(1),
    CTYRKA(2),
    PETKA(3),
    SESTKA(4),
    SEDMICKA(5),
    OSMICKA(6),
    DEVITKA(7),
    DESITKA(8),
    KLUK(9),
    KRALOVNA(10),
    KRAL(11),
    ESO(12);
    
    public int hodnota;
    HodnotaKarty(int hodnota) {
        this.hodnota = hodnota;
    }
    
}

package logika;

public class Karta {
    public short hodnota;
    public Typ typ;
    
    public static enum Typ {
        SRDCE("of_hearts"),
        PIKY("of_spades"),
        KRIZE("of_spades"),
        KARY("of_diamonds");
        
        public final String label;
        
        private Typ(String label) {
            this.label = label;
        }
                
        
    }
    public Karta(short hodnota, Typ typ) {
        this.hodnota = hodnota;
        this.typ = typ;
    }
}

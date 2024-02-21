package mt_vetsibere;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Karta {
    public static enum Typ {
        SRDCE("of_hearts"),
        PIKY("of_spades"),
        KRIZE("of_spades"),
        KARY("of_diamonds");
        
        public String label;
        private Typ(String typ) {
            this.label = typ;
        }
    }
    public Typ typ;
    public int hodnota;
    public BufferedImage obrazek;
    
    public Karta(int hodnota, Typ typ) {
        this.hodnota = hodnota;
        this.typ = typ;
        try {
            obrazek = ImageIO.read(obrazky.Obrazky.class.getResourceAsStream("" + hodnota + "_" + typ.label + ".png"));
        } catch (IOException|IllegalArgumentException ex) {
            System.out.println("Chyba načítání obrázku " + hodnota + "_" + typ.label + ".png");
        }
    }
}

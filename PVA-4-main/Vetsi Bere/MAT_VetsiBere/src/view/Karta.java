package view;

import Karty.ControlerKaret;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Karta extends JPanel {
    public logika.Karta karta;
    public Karta() {
        super();
        setSize(100, 144);
        karta = logika.Logika.getInstance().defaultKarta();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        BufferedImage img = karta.hodnota == Short.MAX_VALUE ? ControlerKaret.getInstance().getDefault() : ControlerKaret.getInstance().getCard(String.valueOf(karta.hodnota) + "_" + karta.typ.label);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}

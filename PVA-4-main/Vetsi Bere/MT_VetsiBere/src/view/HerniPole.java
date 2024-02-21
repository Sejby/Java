package view;

import java.awt.Graphics;
import javax.swing.JPanel;
import mt_vetsibere.Karta;
import mt_vetsibere.Logika;

public class HerniPole extends JPanel {
    private Logika logika;
    public HerniPole() {
        logika = Logika.getInstance();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (logika == null) return;
        Karta[] karty = logika.getKartyNaStole();
        if (karty == null || karty.length == 0) return;
        
        for (int i = 0; i < karty.length; i++) {
            if (karty[i] != null)
            g.drawImage(karty[i].obrazek, i * 110 + 10, 0, 100, 144, this);
        }
    }
}

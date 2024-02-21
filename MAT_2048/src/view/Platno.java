package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import model.GameModel;
import model.Smer;

public class Platno extends JPanel {
    
    GameModel hra;
    
    public Platno() {
      hra = new GameModel(300);
      this.setFocusable(true);
      this.addKeyListener(new KeyAdapter() {
          
          @Override
          public void keyReleased(KeyEvent e) {
              switch(e.getKeyCode()){
                  case 40 -> hra.provedKrok(Smer.DOLU);
                  case 39 -> hra.provedKrok(Smer.DOPRAVA);
                  case 38 -> hra.provedKrok(Smer.NAHORU);
                  case 37 -> hra.provedKrok(Smer.DOLEVA);
              }
              repaint();
          }
      });
    }

    public void initPole(Graphics g) {
        g.setColor(Color.decode("#CCC0B3"));
        g.fillRect(0, 0, 301, 301);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.setColor(Color.decode("#C0B0A0"));
                g.drawRect(i*75, j*75, 75, 75);
                g.setColor(Color.decode("#FAF8EF"));
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        initPole(g);
        hra.vykresliSe(g);
    }
}

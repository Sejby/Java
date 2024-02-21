package mat_miny;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Platno extends JPanel {

    HerniLogika log;

    public Platno() {
        log = HerniLogika.getInstance();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!log.vytvoreno) {
                    log.vytvorHru(new Point(e.getX() / 35, e.getY() / 35));
                }
                if (e.getButton() == 1 && !log.policka[e.getX() / 35][e.getY() / 35].oznaceno) {
                    log.odhal(new Point(e.getX() / 35, e.getY() / 35));
                    repaint();
                }
                if (e.getButton() == 3 && !log.policka[e.getX() / 35][e.getY() / 35].odhaleno) {
                    log.policka[e.getX() / 35][e.getY() / 35].oznaceno = !log.policka[e.getX() / 35][e.getY() / 35].oznaceno;
                    repaint();
                }
                repaint();
            }
        });
        this.setSize(log.velikostHernihoPole * 35 + 1, log.velikostHernihoPole * 35 + 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        vykresli(g);
    }

    public void vykresli(Graphics g) {
        g.setFont(new Font("Arial", 1, 25));
        for (int i = 0; i < log.velikostHernihoPole; i++) {
            for (int j = 0; j < log.velikostHernihoPole; j++) {
                if (log.policka[i][j].odhaleno) {
                    if (log.policka[i][j].mine) {
                        g.drawString("X", (log.policka[i][j].pozice.x * 35) + 10, ((log.policka[i][j].pozice.y + 1) * 35) - 8);
                    } else {
                        if (log.policka[i][j].hodnota == 0) {
                            g.setColor(Color.lightGray);
                            g.fillRect(log.policka[i][j].pozice.x * 35, log.policka[i][j].pozice.y * 35, 35, 35);
                        } else {
                            g.setColor(Color.lightGray);
                            g.fillRect(log.policka[i][j].pozice.x * 35, log.policka[i][j].pozice.y * 35, 35, 35);
                            g.setColor(Color.black);
                            g.drawString(String.valueOf(log.policka[i][j].hodnota), (log.policka[i][j].pozice.x * 35) + 11, ((log.policka[i][j].pozice.y + 1) * 35) - 8);
                        }

                    }
                }
                if (log.policka[i][j].oznaceno) {
                    g.fillOval((log.policka[i][j].pozice.x * 35) + 8, ((log.policka[i][j].pozice.y) * 35) + 8, 20, 20);
                }
                g.setColor(Color.black);
                g.drawRect(log.policka[i][j].pozice.x * 35, log.policka[i][j].pozice.y * 35, 35, 35);
            }
        }
    }
}

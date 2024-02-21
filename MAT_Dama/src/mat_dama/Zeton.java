package mat_dama;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;

public class Zeton extends JPanel {

    enum typZetonu {
        BILY,
        BDAMA,
        CERNY,
        CDAMA

    }
    //vlastnosti 
    public Point pozice;
    public typZetonu typ;

    public Zeton(int x, int y, typZetonu typ) {
        this.pozice = new Point(x, y);
        this.typ = typ;
        this.setSize(30, 30);
//        event
        Zeton t = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt){
                super.mouseReleased(evt);
                propertyChangeSupport.firePropertyChange("kliknuti", "", t);
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        vykresliZeton(g);
    }

    public void vykresliZeton(Graphics g) {
        vykresliCerny(g);
        switch (typ) {
            case BDAMA:
                vykresliBDama(g);
                break;
            case CDAMA:
                vykresliCDama(g);
                break;
            case BILY:
                vykresliBily(g);
                break;
            case CERNY:
                vykresliCerny(g);
                break;

        }

    }

    public void vykresliBily(Graphics g) {
        g.setColor(new Color(255, 255, 230));
        g.fillOval(0, 0, 30, 30);
        g.setColor(Color.black);
        g.drawOval(0, 0, 30, 30);

    }

    public void vykresliCerny(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(0, 0, 30, 30);
        g.setColor(Color.black);
        g.drawOval(0, 0, 30, 30);

    }

    public void vykresliBDama(Graphics g) {
        g.setColor(new Color(255, 255, 230));
        g.fillOval(0, 0, 30, 30);
        g.setColor(Color.black);
        g.drawOval(0, 0, 30, 30);
        g.fillOval(10, 10, 10, 10);
    }

    public void vykresliCDama(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(0, 0, 30, 30);
        g.setColor(Color.black);
        g.drawOval(0, 0, 30, 30);
        g.fillOval(10, 10, 10, 10);

    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }


}

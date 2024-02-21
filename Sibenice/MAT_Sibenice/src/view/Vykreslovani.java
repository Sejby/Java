package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Vykreslovani extends JPanel implements PropertyChangeListener {
    //Vlastnosti
    private ArrayList<BufferedImage> obrazky;
    private Integer stavajiciObrazek;
    
    //Konstruktor
    public Vykreslovani() {
        this.obrazky = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            try {
                this.obrazky.add(ImageIO.read(new File("src/obrazky/" + i + ".png")));
            } catch (IOException ex) {
                System.out.println("Chyba při načítání obrázku " + i + "!");
            }
        }
        this.novaHra();
    }
    //Metody rozhraní
    public void novaHra() {
        this.stavajiciObrazek = 0;
        this.repaint();
    }
    public boolean dalsiObrazek() {
        if (this.stavajiciObrazek >= obrazky.size() - 1) return true;
        this.stavajiciObrazek++;
        this.repaint();
        return this.stavajiciObrazek == obrazky.size() - 1;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.obrazky.isEmpty()) return;
        g.drawImage(this.obrazky.get(this.stavajiciObrazek),
                0, 0, 300, 300, this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("spatny_znak")) 
            if (dalsiObrazek()) propertyChangeSupport.firePropertyChange("prohra", "", "a");
    }

    //Property chaneg support
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}

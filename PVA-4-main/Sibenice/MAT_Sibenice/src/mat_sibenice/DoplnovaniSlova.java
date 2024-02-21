package mat_sibenice;

import java.awt.Font;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.Normalizer;
import javax.swing.JPanel;

public class DoplnovaniSlova extends JPanel implements PropertyChangeListener {
    //Vlastnosti
    private String slovo;
    private char[] pismenaSlova;
    private int delkaSlova;
    private SpravaSlov spravaSlov;
    //Konstruktor
    public DoplnovaniSlova() {
        delkaSlova = 0;
        slovo = "";
        pismenaSlova = new char[0];
        spravaSlov = new SpravaSlov("src/slova/slovo.txt");
    }
    
    //Metody rozhraní
    public void novaHra() {
        noveSlovo(spravaSlov.vyberNahodneSlovo());
    }
    public boolean zkusPismeno(char pismeno) {
        String str = Normalizer.normalize(this.slovo, Normalizer.Form.NFD);
        str = str.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        if (!str.contains(String.valueOf(pismeno))) return false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != pismeno) continue;
            this.pismenaSlova[i] = this.slovo.charAt(i);
        }
        this.repaint();
        return true;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", 42, 42));
        g.drawString(new String(this.pismenaSlova), 20, 40);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!"stisk_tlacitka".equals(evt.getPropertyName())) return;
        if (!zkusPismeno(evt.getNewValue().toString().charAt(0)))
            propertyChangeSupport.firePropertyChange("spatny_znak", "", "a");
        if (zkontrolujVyhru()) 
            propertyChangeSupport.firePropertyChange("vyhra", "", "a");
    }
    //Metody
    private void noveSlovo(String slovo) {
        slovo = slovo.toUpperCase();
        this.delkaSlova = slovo.length();
        this.slovo = slovo;
        this.pismenaSlova = slovo.replaceAll("[^.!?\\s]", "_").toCharArray();
        
        this.repaint();
    }
    
    private boolean zkontrolujVyhru() {
        return !(new String(this.pismenaSlova).contains("_"));
    }

    //Property change support
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}

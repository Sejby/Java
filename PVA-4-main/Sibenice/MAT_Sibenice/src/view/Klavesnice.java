package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Klavesnice extends JPanel implements PropertyChangeListener {
    //Vlastnosti
    private JButton[] tlacitka;
    //Konstruktor
    public Klavesnice() {
        this.pripravTlacitka();
    }
    
    //Metody
    private void pripravTlacitka() {
        tlacitka = new JButton[26];
        for (int i = 0; i < 26; i++) {
            JButton tlacitko = new JButton(String.valueOf((char) (i + 65)));
            tlacitko.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tlacitko.setEnabled(false);
                    propertyChangeSupport.firePropertyChange(
                            "stisk_tlacitka", "", tlacitko.getText());
                }
            });
            tlacitko.setSize(50, 25);
            tlacitko.setLocation((i % 5) * 55, (i / 5) * 30);
            this.add(tlacitko);
            tlacitka[i] = tlacitko;
        }
    }
    //Metody rezhraní
    public void novaHra() {
        for (JButton jButton : tlacitka) {
            jButton.setEnabled(true);
        }
    }
    
    //metody
    private void zasedniKlavesnici() {
        for (JButton jButton : tlacitka) {
            jButton.setEnabled(false);
        }
    }
    //Událost zmáčknutí tlačítka
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("prohra")) {
            zasedniKlavesnici();
            JOptionPane.showMessageDialog(this, "Prohrál jsi");
        }
        if (evt.getPropertyName().equals("vyhra")) {
            zasedniKlavesnici();
            JOptionPane.showMessageDialog(this, "Vyhral jsi");
        }
    }

}
